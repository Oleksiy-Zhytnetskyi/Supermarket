CREATE TABLE IF NOT EXISTS receipt(
    check_number VARCHAR(10) PRIMARY KEY,
    id_employee  VARCHAR(10) NOT NULL,
    card_number  VARCHAR(10),
    print_date   DATETIME NOT NULL,
    sum_total    DECIMAL(13, 4) NOT NULL,
    vat          DECIMAL(13, 4) NOT NULL,
    FOREIGN KEY(id_employee) REFERENCES employee(id_employee)
        ON DELETE NO ACTION
        ON UPDATE CASCADE,
    FOREIGN KEY(card_number) REFERENCES customer_card(card_number)
        ON DELETE NO ACTION
        ON UPDATE CASCADE
)