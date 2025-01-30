#!/bin/bash

if pwd | grep 'k8s' > /dev/null; then
  echo "Execute script from project root directory \"k8s/clear.sh\""
  exit 1
fi

. k8s/variables.sh

. k8s/check-flags.sh

helm uninstall "$RELEASE_NAME" --namespace "$NAMESPACE" --wait --timeout "$TIMEOUT"

if $strimzi_flag; then
  helm uninstall strimzi-cluster-operator
fi

if $cnpg_flag; then
  kubectl delete -f https://raw.githubusercontent.com/cloudnative-pg/cloudnative-pg/release-1.25/releases/cnpg-1.25.0.yaml
fi

if $ingress_flag; then
  minikube addons disable ingress
fi

kubectl delete namespace "$NAMESPACE"
