CREATE TABLE IF NOT EXISTS employee(
    id_employee     INTEGER AUTO_INCREMENT PRIMARY KEY,
    empl_surname    VARCHAR(50) NOT NULL,
    empl_name       VARCHAR(50) NOT NULL,
    empl_patronymic VARCHAR(50),
    empl_role       VARCHAR(10) NOT NULL,
    salary          DECIMAL(13, 4) NOT NULL,
    date_of_birth   DATE NOT NULL,
    date_of_start   DATE NOT NULL,
    phone_number    VARCHAR(13) NOT NULL,
    city            VARCHAR(50) NOT NULL,
    street          VARCHAR(50) NOT NULL,
    zip_code        VARCHAR(9) NOT NULL,
    email           VARCHAR(50) NOT NULL UNIQUE,
    password        VARCHAR(50) NOT NULL
)