# stage 1
FROM eclipse-temurin:21-jdk-alpine AS build

ARG JAR_FILE
ARG MODULE_NAME

COPY $JAR_FILE /usr/$MODULE_NAME/app.jar
RUN mkdir -p /usr/$MODULE_NAME/artifacts && (cd /usr/$MODULE_NAME/artifacts; jar -xf ../app.jar)

# stage 2
FROM eclipse-temurin:21-jre-alpine

ARG MODULE_NAME

ENV MODULE_NAME=$MODULE_NAME
ENV APPLICATION_CLASS ""
ENV JAVA_OPTS ""

COPY --from=build /usr/$MODULE_NAME/artifacts/BOOT-INF/lib /usr/$MODULE_NAME/lib
COPY --from=build /usr/$MODULE_NAME/artifacts/META-INF /usr/$MODULE_NAME/META-INF
COPY --from=build /usr/$MODULE_NAME/artifacts/BOOT-INF/classes /usr/$MODULE_NAME

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Dfile.encoding=UTF-8 -cp /usr/$MODULE_NAME:/usr/$MODULE_NAME/lib/* $APPLICATION_CLASS" ]
EXPOSE 8080
