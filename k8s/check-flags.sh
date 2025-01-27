#!/bin/bash

cnpg_flag=false
strimzi_flag=false
ingress_flag=false

while [[ $# -gt 0 ]]; do
  case $1 in
    --cnpg)
      cnpg_flag=true
      shift
      ;;
    --strimzi)
      strimzi_flag=true
      shift
      ;;
    --ingress)
      ingress_flag=true
      shift
      ;;
    *)
      echo "Unknown flag: $1"
      exit 1
      ;;
  esac
done
