CREATE TABLE prices (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    brand_id BIGINT NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    price_list_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    priority INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    currency VARCHAR(3) NOT NULL,
    last_update TIMESTAMP NOT NULL,
    last_update_by VARCHAR(25) NOT NULL
);