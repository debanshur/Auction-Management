### Auction Management and Bidding Service

## Steps to Setup the Auction Management app

1. **Clone the application**

	```bash
	git clone https://github.com/debanshur/Auction-Service.git
	cd AuctionManagement
	```

2. **Create MySQL database**

	```bash
	create database auction_db
	```

3. **Change MySQL username and password**

	+ open `src/main/resources/application.properties` file.

	+ change `spring.datasource.username` and `spring.datasource.password`

	+ change `server.port`

4. **Run the app**

	```bash
	mvn spring-boot:run
	```

## API Specification

    ```bash
    http://localhost:{{server.port}}/swagger-ui.html
    ```