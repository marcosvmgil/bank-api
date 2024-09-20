
# Bank System

This project is a simple banking system implemented using Java Spring Boot with Maven, and PostgreSQL as the database. It supports basic banking operations like account creation, deposits, transfers, and balance extraction.

## Requirements

- Java 11+
- Maven 3+
- PostgreSQL 13+
- Spring Boot 2.5+

## Setup Instructions


### 1. Configure PostgreSQL Database

Create a PostgreSQL database and configure it in the `application.properties` or `application.yml` file:

#### application.properties:

```properties
spring.application.name=bank-api
spring.config.import=optional:file:.env[.properties]
spring.datasource.url=jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.show-sql=true
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.default_schema=bank
```
Create a `.env` file with the same parameters as the `.envModel`, and populate them with the respective variables from your database.

### 2. Database Schema

The following SQL commands create the necessary database tables.

#### Create Tables:

```sql
CREATE TABLE client (
    id UUID PRIMARY KEY,
    account_number BIGINT NOT NULL UNIQUE, 
    client_name VARCHAR(255) NOT NULL,
    document_number BIGINT,
    client_address VARCHAR(255)
);

CREATE TABLE account (
    id UUID NOT NULL,
    account_number BIGINT PRIMARY KEY,
    amount DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_client_account FOREIGN KEY (account_number) REFERENCES client (account_number) ON DELETE CASCADE
);

CREATE TABLE extract (
    id UUID PRIMARY KEY,
    account_number BIGINT NOT NULL,
    amount_total DECIMAL(10, 2) NOT NULL,
    amount_operation DECIMAL(10, 2) NOT NULL,
    date TIMESTAMP NOT NULL,
    operation VARCHAR(50) NOT NULL,
    CONSTRAINT fk_extract_account FOREIGN KEY (account_number) REFERENCES account (account_number) ON DELETE CASCADE
);

CREATE TABLE deposit (
    id UUID PRIMARY KEY,
    account_number BIGINT NOT NULL,
    amount_total DECIMAL(10, 2) NOT NULL,
    amount_operation DECIMAL(10, 2) NOT NULL,
    date TIMESTAMP NOT NULL,
    operation VARCHAR(50) NOT NULL,
    CONSTRAINT fk_deposit_account FOREIGN KEY (account_number) REFERENCES account (account_number) ON DELETE CASCADE
);

CREATE TABLE transfer (
    id UUID PRIMARY KEY,
    account_number BIGINT NOT NULL,
    amount_total DECIMAL(10, 2) NOT NULL,
    amount_operation DECIMAL(10, 2) NOT NULL,
    date TIMESTAMP NOT NULL,
    operation VARCHAR(50) NOT NULL,
    CONSTRAINT fk_transfer_account FOREIGN KEY (account_number) REFERENCES account (account_number) ON DELETE CASCADE
);
```

### 3. Insert Sample Data

The following `INSERT` statements populate the database with sample data.

#### Clients:

```sql
INSERT INTO client (id, account_number, client_name, document_number, client_address) VALUES
('aa18b279-6052-4b7d-ba03-916ae1afd7bf', 92838, 'João Lucas', 20329, 'joao.lucas@email.com'),
('cf612857-9d2c-4e14-8269-4b851bf3d3c9', 9283902, 'Antônio Gabriel', 98439048, 'antonio.gabriel@email.com'),
('d3b8e7b2-64f1-4b5f-a0a7-d3c11f8e1234', 192873, 'Maria Eduarda', 78472934, 'maria.eduarda@email.com');
```

#### Accounts:

```sql
INSERT INTO account (id, account_number, amount) VALUES
('6c2a88e2-4d5d-4dd0-ac6b-1bb3d69ab123', 92838, 5000.00),
('6c2a88e2-4d5d-4dd0-ac6b-1bb3d69ab124', 9283902, 1500.00),
('6c2a88e2-4d5d-4dd0-ac6b-1bb3d69ab125', 192873, 2500.00);
```

#### Deposits:

```sql
INSERT INTO deposit (id, account_number, amount_total, amount_operation, operation, date) VALUES
('b1b6ed77-1d86-4168-9626-6cc732ed7ca2', 92838, 5500.00, 500.00, 'Deposit', '2024-09-01 10:00:00'),
('c2c7fc94-5d6b-44d2-9c7b-3c2e8b6a6c9e', 9283902, 1800.00, 300.00, 'Deposit', '2024-09-02 11:15:00'),
('d3e5a5f8-92b3-4a7a-934c-e0c6e5a2b9f8', 192873, 2800.00, 300.00, 'Deposit', '2024-09-03 14:30:00');
```

#### Transfers:

```sql
INSERT INTO transfer (id, account_number, amount_total, amount_operation, operation, date) VALUES
('095481b0-8f61-40d2-adb3-053111e2ae98', 92838, 4500.00, 1000.00, 'Transfer', '2024-09-05 09:45:00'),
('095481b0-8f61-40d2-adb3-053111e2ae99', 9283902, 1200.00, 600.00, 'Transfer', '2024-09-06 12:00:00'),
('095481b0-8f61-40d2-adb3-053111e2ae9a', 192873, 2000.00, 800.00, 'Transfer', '2024-09-07 15:30:00');
```

#### Extracts:

```sql
INSERT INTO extract (id, account_number, amount_total, amount_operation, date, operation) VALUES
('69cf3ec1-a8f2-43e7-8a1e-07c4a7e0e31e', 92838, 4500.00, -500.00, '2024-09-08 17:13:00', 'Withdraw'),
('69cf3ec1-a8f2-43e7-8a1e-07c4a7e0e32f', 9283902, 1200.00, -300.00, '2024-09-09 18:20:00', 'Withdraw'),
('69cf3ec1-a8f2-43e7-8a1e-07c4a7e0e33c', 192873, 2000.00, -300.00, '2024-09-10 19:40:00', 'Withdraw');
```

## License

This project is licensed under the MIT License.
