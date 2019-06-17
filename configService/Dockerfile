FROM openjdk:8
ENV APP_HOME=/home
WORKDIR $APP_HOME
COPY ./build/libs/ $APP_HOME
EXPOSE 8888
CMD ["java", "-jar", "configService-1.jar"]