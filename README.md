## Auction Management and Bidding

### Steps to Run the services

1. **Clone the application**

	```bash
	git clone https://github.com/debanshur/Auction-Service.git
	```

2. **Create MySQL database**

	```bash
	create database auction_db
	```

3. **Change MySQL username and password and Admin Secret Key**

	+ open `application.properties` file.

	+ change `spring.datasource.username` and `spring.datasource.password`

    + change `api.secret.key` for Admin Secret Key

4. **Copy properties file to Services**

	```bash
    cp application.properties UserManagement/src/main/resources/
    cp application.properties AuctionManagement/src/main/resources/
    ```

5. **Docker build User Management**
    ```bash
    cd UserManagement
    docker build --tag user-service:latest -f Dockerfile .
    ```

6. **Docker build Auction Management**
    ```bash
    cd AuctionManagement
    docker build --tag auction-service:latest -f Dockerfile .
     ```

7. **Docker Run**
    ```bash
    docker run -d --network host user-service:latest
    docker run -d --network host auction-service:latest
     ```

### API Specification

1. **Swagger UI**

    + User Management    : `http://localhost:9000/swagger-ui.html`

    + Auction Management : `http://localhost:9001/swagger-ui.html`


### Build and Run the Services Automatically

1. **Change MySQL username and password and Admin Sercet Key**

	+ open `application.properties` file.

	+ change `spring.datasource.username` and `spring.datasource.password`

    + change `api.secret.key` for Admin Secret Key

2. **Run the script**
    ```bash
    sh deploy.sh
    ```

