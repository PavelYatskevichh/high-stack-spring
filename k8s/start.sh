#!/bin/bash

if pwd | grep 'k8s' > /dev/null; then
  echo "Execute script from project root directory \"k8s/start.sh\""
  exit 1
fi

. k8s/variables.sh

#./gradlew build
#
#images=$(minikube image ls)
#
#required_images=(
#    "hs/moderation"
#    "hs/distribution"
#    "hs/content-creation"
#)
#
#all_images_found=true
#
#for image in "${required_images[@]}"; do
#    if ! grep -q "$image" <<< "$images"; then
#        echo "Image '$image' is not found in minikube."
#        all_images_found=false
#        return
#    fi
#done
#
#if all_images_found; then


#echo "Checking for installed cloudnative-pg operator ..."
#if ! kubectl get pod -l app.kubernetes.io/name=cloudnative-pg --all-namespaces 2> /dev/null; then
  echo "Installing cloudnative-pg operator ..."
  kubectl apply --server-side -f https://raw.githubusercontent.com/cloudnative-pg/cloudnative-pg/release-1.25/releases/cnpg-1.25.0.yaml
#fi

#echo "Checking for installed strimzi-cluster-operator ..."
#if ! kubectl get pod -l name=strimzi-cluster-operator --all-namespaces 2> /dev/null; then
  kubectl create namespace "$STRIMZI_NAMESPACE"
  echo "Installing strimzi-cluster-operator ..."
  kubectl create -f "https://strimzi.io/install/latest?namespace=$STRIMZI_NAMESPACE" -n "$STRIMZI_NAMESPACE"
#fi

kubectl wait pod --all --for=condition=ready --timeout="$TIMEOUT" -l app.kubernetes.io/name=cloudnative-pg --all-namespaces
echo "Cloudnative-pg operator is installed."
kubectl wait pod --all --for=condition=ready --timeout="$TIMEOUT" -l name=strimzi-cluster-operator --all-namespaces
echo "Strimzi-cluster-operator is installed."

kubectl create ns "$NAMESPACE"

helm upgrade --install "$RELEASE_NAME" "$CHART_LOCATION" \
    --devel \
    --dependency-update \
    --atomic \
    --namespace "$NAMESPACE" \
    --timeout "$TIMEOUT"
