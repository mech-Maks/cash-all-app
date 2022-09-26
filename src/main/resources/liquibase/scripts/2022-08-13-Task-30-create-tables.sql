CREATE TABLE IF NOT EXISTS users (
        id BIGSERIAL PRIMARY KEY,
        email VARCHAR(225) NOT NULL UNIQUE,
        full_balance BIGINT,
        full_income BIGINT,
        full_consump BIGINT,
        created_at DATE NOT NULL
);

CREATE TABLE if NOT EXISTS currency (
        id BIGSERIAL primary key,
        currency_name varchar(225),
        char_code varchar(50),
        unicode varchar(50)
);

CREATE TABLE if NOT EXISTS wallet (
        id BIGSERIAL PRIMARY KEY,
        user_id BIGINT NOT NULL,
        wallet_name VARCHAR(50),
        currency_id BIGINT NOT NULL,
        pay_limit BIGINT,
        balance BIGINT,
        full_income BIGINT,
        full_consump BIGINT,
        is_deleted BOOLEAN DEFAULT FALSE NOT NULL,
        is_exceeded BOOLEAN DEFAULT FALSE NOT NULL,
        is_hidden BOOLEAN DEFAULT FALSE NOT NULL,
        created_at DATE NOT NULL,
        FOREIGN KEY (user_id) REFERENCES users(id),
        FOREIGN KEY (currency_id) REFERENCES currency(id)
);

CREATE TABLE IF NOT EXISTS icon (
        id BIGSERIAL PRIMARY KEY,
        icon_name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS colour (
        id BIGSERIAL PRIMARY KEY,
        colour_name TEXT NOT NULL
);

CREATE TABLE if NOT EXISTS category (
        id BIGSERIAL PRIMARY KEY,
        user_id BIGINT NOT NULL,
        category_name VARCHAR(50),
        operation_type VARCHAR(50),
        icon_id BIGINT NOT NULL,
        colour_id BIGINT NOT NULL,
        is_default BOOLEAN DEFAULT FALSE NOT NULL,
        is_deleted BOOLEAN DEFAULT FALSE NOT NULL,
        created_at DATE NOT NULL,
        FOREIGN KEY (user_id) REFERENCES users(id),
        FOREIGN KEY (icon_id) REFERENCES icon(id),
        FOREIGN KEY (colour_id) REFERENCES colour(id)
);

CREATE TABLE IF NOT EXISTS operation (
        id BIGSERIAL PRIMARY KEY,
        operation_type VARCHAR(50) NOT NULL,
        operation_name VARCHAR(225) NOT NULL,
        wallet_id BIGINT NOT NULL,
        category_id BIGINT NOT NULL,
        currency_id BIGINT NOT NULL,
        amount BIGINT NOT NULL,
        created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
        is_deleted BOOLEAN DEFAULT FALSE NOT NULL,
        FOREIGN KEY (wallet_id) REFERENCES wallet(id),
        FOREIGN KEY (category_id) REFERENCES category(id),
        FOREIGN KEY (currency_id) REFERENCES currency(id)
);
