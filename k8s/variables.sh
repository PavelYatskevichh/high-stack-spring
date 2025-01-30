#!/bin/bash
# shellcheck disable=SC2034

# Kubernetes cluster config address for kubectl
#KUBECONFIG=

# Namespace for application deployment
NAMESPACE=high-stack

# Helm chart location
CHART_LOCATION=helm/hs-facade

# Helm release name
RELEASE_NAME=hs-spring

# Namespace for Strimzi operator
STRIMZI_NAMESPACE=strimzi-system

# Timeout
TIMEOUT=20m
