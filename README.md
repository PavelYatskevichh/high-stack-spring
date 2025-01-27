# high-stack-spring

## Quickstart

### Docker

* To run project in docker execute command from project root directory:
    ```shell
    docker compose -f docker/docker-compose.yaml up
    ```

### Kubernetes

* To run project in kubernetes execute command from project root directory:
    ```shell
    k8s/start.sh --cnpg --strimzi --ingress
    ```
  Available properties: \
  ```--cnpg``` to deploy Cloudnative-PG \
  ```--strimzi``` to deploy Strimzi \
  ```--ingress``` to enable Ingress addon
