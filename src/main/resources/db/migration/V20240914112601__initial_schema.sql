CREATE TABLE IF NOT EXISTS currency (
    code VARCHAR(5) NOT NULL,
    name VARCHAR(50) NOT NULL UNIQUE,
    decimals INT NOT NULL,
    PRIMARY KEY(code)
);

CREATE TABLE IF NOT EXISTS employee (
    id uuid,
    fullName VARCHAR(200) NOT NULL,
    email VARCHAR(256) NOT NULL UNIQUE,
    position VARCHAR(200) NOT NULL,
    salary DECIMAL NOT NULL,
    currency_code VARCHAR(5) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    CONSTRAINT fk_currency_code
        FOREIGN KEY(currency_code)
            REFERENCES currency(code)
);

INSERT INTO
    currency (code, name, decimals)
VALUES
    ('USD', 'US Dollar', 4);
