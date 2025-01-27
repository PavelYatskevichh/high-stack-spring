#!/bin/bash

if pwd | grep 'k8s' > /dev/null; then
  echo "Execute script from project root directory \"k8s/start.sh\""
  exit 1
fi

. k8s/variables.sh

. k8s/check-flags.sh

. k8s/build-images.sh

echo "Checking for installed cloudnative-pg operator ..."
if [[ $cnpg_flag == true && -z $(kubectl get pod -l app.kubernetes.io/name=cloudnative-pg --all-namespaces) ]]; then
  echo "Installing cloudnative-pg operator ..."
  kubectl apply --server-side -f https://raw.githubusercontent.com/cloudnative-pg/cloudnative-pg/release-1.25/releases/cnpg-1.25.0.yaml
fi

echo "Checking for installed strimzi-cluster-operator ..."
if [[ $strimzi_flag == true && -z $(kubectl get pod -l name=strimzi-cluster-operator --all-namespaces) ]]; then
  echo "Creating namespace for strimzi-cluster-operator ..."
  kubectl create namespace "$STRIMZI_NAMESPACE"
  echo "Installing strimzi-cluster-operator ..."
  kubectl create -f "https://strimzi.io/install/latest?namespace=$STRIMZI_NAMESPACE" -n "$STRIMZI_NAMESPACE"
fi

echo "Checking for installed ingress-controller ..."
if [[ $ingress_flag == true && -z $(kubectl get pod -l app.kubernetes.io/name=ingress-nginx --all-namespaces) ]]; then
  echo "Installing ingress-controller ..."
  minikube addons enable ingress
fi

kubectl wait pod --all --for=condition=ready --timeout="$TIMEOUT" -l app.kubernetes.io/name=cloudnative-pg --all-namespaces \
&& echo "Cloudnative-pg operator is installed."
kubectl wait pod --all --for=condition=ready --timeout="$TIMEOUT" -l name=strimzi-cluster-operator --all-namespaces \
&& echo "Strimzi-cluster-operator is installed."

echo "Creating namespace for application ..."
kubectl create ns "$NAMESPACE"

helm upgrade --install "$RELEASE_NAME" "$CHART_LOCATION" \
    --devel \
    --dependency-update \
    --atomic \
    --namespace "$NAMESPACE" \
    --timeout "$TIMEOUT"
