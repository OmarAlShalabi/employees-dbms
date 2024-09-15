CREATE TABLE IF NOT EXISTS request_category (
    id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(100) NOT NULL UNIQUE,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS time_off_request (
    id INT GENERATED ALWAYS AS IDENTITY,
    request_category_id INT NOT NULL,
    employee_id uuid NOT NULL,
    start_date TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    end_date TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    CONSTRAINT fk_request_category_id
        FOREIGN KEY(request_category_id)
            REFERENCES request_category(id),
    CONSTRAINT fk_employee_id
        FOREIGN KEY(employee_id)
            REFERENCES employee(id)
);

INSERT INTO
    request_category (name)
VALUES
    ('SICK_LEAVE'),
    ('WORK_REMOTELY'),
    ('ANNUAL_LEAVE');
