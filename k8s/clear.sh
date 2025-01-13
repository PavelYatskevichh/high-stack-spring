#!/bin/bash

if pwd | grep 'k8s' > /dev/null; then
  echo "Execute script from project root directory \"k8s/clear.sh\""
  exit 1
fi

. k8s/variables.sh

. k8s/check-flags.sh

helm uninstall "$RELEASE_NAME" --namespace "$NAMESPACE" --wait --timeout "$TIMEOUT"

if $strimzi_flag; then
  kubectl delete $(kubectl get strimzi -o name -n "$STRIMZI_NAMESPACE") -n "$STRIMZI_NAMESPACE"
  kubectl delete pvc -l "strimzi.io/name=$NAMESPACE" -n "$STRIMZI_NAMESPACE"
  kubectl delete -f "https://strimzi.io/install/latest?namespace=$STRIMZI_NAMESPACE" -n "$STRIMZI_NAMESPACE"
  kubectl delete namespace "$STRIMZI_NAMESPACE"
fi

if $cnpg_flag; then
  kubectl delete -f https://raw.githubusercontent.com/cloudnative-pg/cloudnative-pg/release-1.25/releases/cnpg-1.25.0.yaml
fi

kubectl delete namespace "$NAMESPACE"
