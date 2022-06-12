CREATE TABLE manufacturer(
    id UUID DEFAULT gen_random_uuid(),
	name VARCHAR(1000) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE(name)
);

CREATE TABLE product(
    id UUID DEFAULT gen_random_uuid(),
	name VARCHAR(1000),
	price numeric,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users(
    id UUID DEFAULT gen_random_uuid(),
    first_name VARCHAR(1000),
    last_name VARCHAR(1000),
	email VARCHAR(1000),
	password VARCHAR(1000),
	user_role VARCHAR(1000),
	user_status VARCHAR(1000),
	PRIMARY KEY (id),
	UNIQUE(email)
);

ALTER TABLE product
	ADD manufacturer_id UUID,
	ADD foreign key (manufacturer_id) references manufacturer (id);

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO manufacturer (name)
VALUES ('Apple'), ('HP'), ('Dell');
