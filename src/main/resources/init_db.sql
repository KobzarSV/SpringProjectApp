CREATE TABLE manufacturer(
    id UUID DEFAULT gen_random_uuid(),
	name VARCHAR(1000),
	PRIMARY KEY (id)
);

CREATE TABLE product(
    id UUID DEFAULT gen_random_uuid(),
	name VARCHAR(1000),
	price money,
	PRIMARY KEY (id)
);

CREATE TABLE role(
    id UUID DEFAULT gen_random_uuid(),
	name VARCHAR(1000),
	PRIMARY KEY (id)
);

CREATE TABLE users(
    id UUID DEFAULT gen_random_uuid(),
	email VARCHAR(1000),
	password VARCHAR(1000),
	first_name VARCHAR(1000),
	last_name VARCHAR(1000),
	PRIMARY KEY (id)
);

ALTER TABLE product
	ADD manufacturer_id UUID,
	ADD foreign key (manufacturer_id) references manufacturer (id);

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO manufacturer (name)
VALUES ('Apple'), ('HP'), ('Dell');


--INSERT INTO product (name, price, manufacturer_id)
--VALUES ('MacBook Pro M1', 2000, '1e33b6db-8c3a-48b5-b7ae-77a4a206956b'),
--	   ('MacBook Air M1', 1500, '1e33b6db-8c3a-48b5-b7ae-77a4a206956b'),
--	   ('MacBook Pro Touch', 3000, '1e33b6db-8c3a-48b5-b7ae-77a4a206956b'),
--       ('ProBook 470 G8', 1250, 'eb2567ad-5345-4633-9789-d53858ce68cd'),
--	   ('Pavilion 15', 900, 'eb2567ad-5345-4633-9789-d53858ce68cd'),
--	   ('Laptop 15', 800, 'eb2567ad-5345-4633-9789-d53858ce68cd'),
--	   ('XPS 17', 3500, '2257b86a-e71f-494d-ac6f-0532d3c4703c'),
--	   ('Latitude 5401', 1125, '2257b86a-e71f-494d-ac6f-0532d3c4703c'),
--	   ('Vostro 3500', 800, '2257b86a-e71f-494d-ac6f-0532d3c4703c');