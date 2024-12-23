FROM maven:3.9.9-amazoncorretto-17-alpine
COPY . /bet
WORKDIR /bet
RUN mvn package -DskipTests
ENTRYPOINT ["java","-jar","/bet/target/betting-app.jar"]