# for apache/kafka:3.9.0 image
/opt/kafka/bin/kafka-topics.sh --create --topic hs-spring --bootstrap-server localhost:9092

# install cloudnative-pg:1.25 to kubernetes cluster
kubectl apply --server-side -f \
https://raw.githubusercontent.com/cloudnative-pg/cloudnative-pg/release-1.25/releases/cnpg-1.25.0.yaml
