DROP SCHEMA IF EXISTS oblig_3 CASCADE;

CREATE SCHEMA oblig_3;
SET search_path TO oblig_3;

CREATE TABLE ansatt (
	id SERIAL,
	brukernavn VARCHAR(4) UNIQUE NOT NULL,
	fornavn VARCHAR(10) NOT NULL,
	etternavn VARCHAR(10) NOT NULL,
	annsettelsesdato DATE,
	stilling VARCHAR(10),
	maanedslonn NUMERIC(12,2),
	PRIMARY KEY (id)
);

INSERT INTO ansatt (
	brukernavn,
	fornavn,
	etternavn,
	annsettelsesdato,
	stilling,
	maanedslonn
) VALUES (
	'shs',
	'Simen',
	'Strømsnes',
	'2023-03-15',
	'Kul fyr',
	123456.50
);

SELECT * FROM ansatt;