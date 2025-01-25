#!/bin/bash

images=$(minikube image ls)

required_images=(
    "hs/moderation"
    "hs/distribution"
    "hs/content-creation"
    "hs/gateway"
)

all_images_found=true

for image in "${required_images[@]}"; do
    if ! grep -q "$image" <<< "$images"; then
        echo "Image '$image' is not found in minikube."
        all_images_found=false
        break
    fi
done

if ! $all_images_found; then
  ./gradlew clean
  ./gradlew build -x test
  docker compose -f docker/docker-compose.yaml build
  for image in "${required_images[@]}"; do
      minikube image load "$image"
  done
fi
