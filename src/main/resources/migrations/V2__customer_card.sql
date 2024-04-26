CREATE TABLE IF NOT EXISTS customer_card(
    card_number     INTEGER AUTO_INCREMENT PRIMARY KEY,
    cust_surname    VARCHAR(50) NOT NULL,
    cust_name       VARCHAR(50) NOT NULL,
    cust_patronymic VARCHAR(50),
    phone_number    VARCHAR(13) NOT NULL UNIQUE,
    city            VARCHAR(50),
    street          VARCHAR(50),
    zip_code        VARCHAR(9),
    percent         INTEGER NOT NULL
)