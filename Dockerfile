FROM openjdk:11
WORKDIR /app
COPY ./target/user-context-extractor-plugin-0.0.1-SNAPSHOT.jar  .
ENTRYPOINT ["java",  "-DAPISIX_LISTEN_ADDRESS=unix:/sockets/runner.sock", "-DAPISIX_CONF_EXPIRE_TIME=3600", "-Dsocket.file=unix:/sockets/runner.sock", "-jar", "./user-context-extractor-plugin-0.0.1-SNAPSHOT.jar"]