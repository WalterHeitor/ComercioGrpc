#FROM openjdk:11-jdk-slim
#ARG JAR_FILE=.build/libs/*-all.jar
##ARG ${JAR_FILE} build/libs
##ADD ${JAR_FILE} app.jar
##COPY ${JAR_FILE} vendas-0.1-runner.jar
##COPY build/libs/*all.jar vendas-0.1-runner.jar
#ENV APP_NAME vendas
##ENV HTTP_PORT 8080
##CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "/vendas-0.1-runner.jar"]
#ENTRYPOINT ["java", "-jar", "/vendas-0.1.jar"]
#EXPOSE 50051
##EXPOSE 8080
FROM openjdk:11
ARG JAR_FILE=build/libs/*all.jar
ARG ADDCITIONAL_OPTS
COPY build/libs/*all.jar comercio-0.1-runner.jar
ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}
#ENTRYPOINT ["java","-jar","/comercio-0.1-runner.jar"]
EXPOSE 50051
EXPOSE 8080
EXPOSE 5005
#CMD java ${ADDCITIONAL_OPTS} -jar /comercio-0.1-runner.jar
ENTRYPOINT java ${ADDCITIONAL_OPTS} -jar /comercio-0.1-runner.jar