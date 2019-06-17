FROM openjdk:8
ENV APP_HOME=/home
WORKDIR $APP_HOME
COPY ./build/libs/ $APP_HOME
EXPOSE 8080
CMD sleep 180 && java -jar client-1.jar