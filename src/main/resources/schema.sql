DROP TABLE IF EXISTS bookings;
DROP TABLE IF EXISTS customers;

CREATE TABLE IF NOT EXISTS users (
    user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(68) NOT NULL,
    enabled TINYINT NOT NULL
);

DROP TABLE IF EXISTS authorities;

CREATE TABLE authorities (
    authority_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    authority VARCHAR(45) NOT NULL,
    CONSTRAINT authorities_fk_1 FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE customers(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    address_id INT NULL,
    user_id INT NULL,
    email VARCHAR(45) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    date_of_birth DATE NOT NULL
);

DROP TABLE IF EXISTS address;

CREATE TABLE address(
    address_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    street VARCHAR(45) NOT NULL,
    postal_code INT NOT NULL,
    city VARCHAR(45) NOT NULL
);

DROP TABLE IF EXISTS destinations;

CREATE TABLE destinations(
    dest_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    country VARCHAR(45) NOT NULL,
    city VARCHAR(45) NOT NULL,
    hotel_name VARCHAR(55) NOT NULL,
    price_per_week_sek DOUBLE NOT NULL,
    price_per_week_pln DOUBLE NOT NULL
);

CREATE TABLE bookings(
    booking_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    departure_date DATE NOT NULL,
    id INT NOT NULL,
    dest_id INT NOT NULL
);








