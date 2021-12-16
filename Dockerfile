FROM adoptopenjdk/openjdk15:jre-15.0.1_9
COPY ./build/libs/*.jar /Documents/mydocker/demo.jar
WORKDIR /Documents/mydocker
RUN sh -c 'touch demo.jar'
ENTRYPOINT ["java","-jar","demo.jar"]