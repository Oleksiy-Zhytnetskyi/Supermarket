CREATE TABLE IF NOT EXISTS store_product(
    upc        INTEGER AUTO_INCREMENT PRIMARY KEY,
    upc_prom   INTEGER,
    id_product INTEGER NOT NULL,
    selling_price DECIMAL(13, 4) NOT NULL,
    products_number INTEGER NOT NULL,
    promotional_product BOOLEAN NOT NULL,
    FOREIGN KEY(upc_prom) REFERENCES store_product(upc)
        ON DELETE SET NULL
        ON UPDATE CASCADE,
    FOREIGN KEY(id_product) REFERENCES product(id_product)
        ON DELETE NO ACTION
        ON UPDATE CASCADE
)