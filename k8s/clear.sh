#!/bin/bash

if pwd | grep 'k8s' > /dev/null; then
  echo "Execute script from project root directory \"k8s/clear.sh\""
  exit 1
fi

. k8s/variables.sh

helm uninstall "$RELEASE_NAME" --namespace "$NAMESPACE" --timeout "$TIMEOUT"
