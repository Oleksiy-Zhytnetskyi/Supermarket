CREATE TABLE IF NOT EXISTS sale(
    upc          INTEGER NOT NULL,
    check_number INTEGER NOT NULL,
    product_number INTEGER NOT NULL,
    selling_price DECIMAL(13, 4) NOT NULL,
    PRIMARY KEY(upc, check_number),
    FOREIGN KEY(upc) REFERENCES store_product(upc)
        ON DELETE NO ACTION
        ON UPDATE CASCADE,
    FOREIGN KEY(check_number) REFERENCES receipt(check_number)
        ON DELETE CASCADE
        ON UPDATE CASCADE
)