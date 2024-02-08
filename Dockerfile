
FROM openjdk:18 as build

LABEL maintainer="Daniil Dudak <daniildudakjava@gmail.com>"

WORKDIR application

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} application.jar

RUN java -Djarmode=layertools -jar application.jar extract


FROM openjdk:18

WORKDIR application

COPY --from=build application/dependencies/ ./
COPY --from=build application/spring-boot-loader/ ./
COPY --from=build application/snapshot-dependencies/ ./
COPY --from=build application/application/ ./

ENTRYPOINT ["java","org/springframework/boot/loader/launch/JarLauncher"]