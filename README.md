# customer-complaints

Create Spring boot Application in:

    https://start.spring.io/

## Necessary dependencies
lombok dependency:

    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

joda dependency:

    <dependency>
        <groupId>com.fasterxml.jackson.datatype</groupId>
        <artifactId>jackson-datatype-joda</artifactId>
        <version>2.12.3</version>
    </dependency>

integration dependency:

    <dependency>
        <groupId>org.apache.httpcomponents</groupId>
        <artifactId>httpcore</artifactId>
        <version>4.4.15</version>
    </dependency>

    <dependency>
        <groupId>org.apache.httpcomponents</groupId>
        <artifactId>httpclient</artifactId>
        <version>4.5.13</version>
    </dependency>

    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.9.0</version>
    </dependency>


## Swagger
Dependencies for Swagger:

    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger-ui</artifactId>
        <version>2.6.1</version>
    </dependency>

    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger2</artifactId>
        <version>2.6.1</version>
    </dependency>

Link to local swagger:

    http://localhost:8080/swagger-ui.html#

## Docker
Run docker-compose:

    docker-compose up -d

## DB
Dependencies for DB:

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>

		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-validator</artifactId>
			<version>6.1.5.Final</version>
		</dependency>

		<dependency>
			<groupId>joda-time</groupId>
			<artifactId>joda-time</artifactId>
			<version>2.10.13</version>
		</dependency>

Settings for application.properties:

    spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
    spring.datasource.username=postgres
    spring.datasource.password=postgres
    spring.datasource.driver-class-name=org.postgresql.Driver
    
    spring.jpa.show-sql = true
    spring.jpa.hibernate.ddl-auto = update
    spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

## Scheduler
Settings for application.properties:

    fixedRate.in.milliseconds=300000
    spring.task.scheduling.pool.size=2

## Test
Dependencies for Junit:

    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
        <version>3.4</version>
    </dependency>

    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <version>1.4.200</version>
        <scope>test</scope>
    </dependency>
