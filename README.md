# PricesApp
Prices App is a RESTful API Endpoint that allows users to retrieve prices for a specific product from different brands. If the date of the brand that the client is introducing it´s within the range of prices that are stored in the database, the API returns the price wich is stored in the DB and wich has the highest priority.

## Developed by

<img src="https://github.com/LordStabbo.png" alt="LordStabbo (Carlos Tebar)" width="100" style="border-radius: 50%">
    -LordStabbo (Carlos Tébar)

### IMPLEMENTED TECHNOLOGIES
- Java 11
- Spring Boot 2.5.0
- Maven
- PostgreSQL

### PREREQUISITES
- Docker: https://docs.docker.com/get-docker/
- Docker Compose: https://docs.docker.com/compose/install/


### INSTALLATION
1. Clone repository
  git clone https://gitlab.com/carloscinicshady/pricesappv2.git

2. Open Rancher/Docker-compose to launch the docker-compose.yml with:
  cd pricesappv2 || docker-compose up -d

3. Run the Maven install command with the pom.xml in orther to install the app
  mvn clean install

### HOW TO USE
1. Run the application using:

    java -jar target/hexagonal-architecture-1.0.0-SNAPSHOT.jar

2. Making inqueries to the Endpoint API

    Once the app is running it is possible to make inqueries using third-party services as Postman or Thunder Client. In order to do this we have to keep in mind the Date format, wich is "yyyy-MM-dd HH.mm.ss"
    So the resulting format for a valid inquery should be:
    http://localhost:9394/price/check?date=yyyy-MM-dd HH.mm.ss&productId=(productId)&brandId=(brandId)

3. Stopping the application:

    Identify the process ID (PID) of the running application using tools like ps or tasklist, and then use the appropriate command to kill the process. For example, on Unix-based systems, you can use the kill command followed by the PID. On Windows, you can use the taskkill command followed by the PID.

