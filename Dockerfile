FROM maven:3.9.6-amazoncorretto-21

WORKDIR /opt

COPY . .

RUN mvn clean package -DskipTests -q

ENTRYPOINT ["java", "-jar","target/app.jar"]

EXPOSE 8081

























#FROM maven:3.9.6-amazoncorretto-21 as build
#
#ARG DEPLOY_MODULE=events-api
#
#USER root
#
#COPY . /opt
#
#WORKDIR /opt
#RUN mvn clean install -DskipTests -q
#
## Remove the problematic cp command from the build stage
#
#FROM maven:3.9.6-amazoncorretto-21 as production
#
#RUN echo "Zone America/Brasilia -3:00 - GMT-3" > /tmp/Brasilia.zic && \
#    zic /tmp/Brasilia.zic && \
#    ln -sf /usr/share/zoneinfo/America/Brasilia /etc/localtime
#
## Copy the app.jar built in the previous stage
#RUN ls
#
#COPY --from=build /opt/app.jar /app.jar
#
#RUN chmod 777 -Rf /app.jar
#
#
#CMD java -jar /app.jar
