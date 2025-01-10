#!/bin/bash

if pwd | grep 'k8s' > /dev/null; then
  echo "Execute script from project root directory \"k8s/start.sh\""
  exit 1
fi

. k8s/variables.sh

if ! kubectl get pod -l app.kubernetes.io/name=cloudnative-pg --all-namespaces > /dev/null; then
  kubectl apply --server-side -f https://raw.githubusercontent.com/cloudnative-pg/cloudnative-pg/release-1.25/releases/cnpg-1.25.0.yaml
  kubectl wait pod --all --for=condition=ready --timeout="$TIMEOUT" -l app.kubernetes.io/name=cloudnative-pg --all-namespaces
fi

kubectl create ns "$NAMESPACE"

helm upgrade --install "$RELEASE_NAME" "$CHART_LOCATION" \
    --devel \
    --dependency-update \
    --atomic \
    --namespace "$NAMESPACE" \
    --timeout "$TIMEOUT"
