CREATE TABLE IF NOT EXISTS receipt(
    check_number INTEGER AUTO_INCREMENT PRIMARY KEY,
    id_employee  INTEGER NOT NULL,
    card_number  INTEGER,
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