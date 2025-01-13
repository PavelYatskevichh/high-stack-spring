#!/bin/bash

images=$(minikube image ls)

required_images=(
    "hs/moderation"
    "hs/distribution"
    "hs/content-creation"
)

all_images_found=true

for image in "${required_images[@]}"; do
    if ! grep -q "$image" <<< "$images"; then
        echo "Image '$image' is not found in minikube."
        all_images_found=false
        return
    fi
done

if ! $all_images_found; then
  ./gradlew build
  docker compose -f docker/docker-compose.yaml build
  for image in "${required_images[@]}"; do
      minikube image load "$image"
  done
fi
