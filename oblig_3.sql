DROP SCHEMA IF EXISTS oblig_3 CASCADE;

CREATE SCHEMA oblig_3;
SET search_path TO oblig_3;

CREATE TABLE ansatt (
	id SERIAL,
	brukernavn VARCHAR(4) UNIQUE NOT NULL,
	fornavn VARCHAR(20) NOT NULL,
	etternavn VARCHAR(30) NOT NULL,
	ansettelsesdato DATE,
	stilling VARCHAR(40),
	maanedslonn NUMERIC(12,2),
	PRIMARY KEY (id)
);

INSERT INTO ansatt (
	brukernavn,
	fornavn,
	etternavn,
	ansettelsesdato,
	stilling,
	maanedslonn
) VALUES 
	('shs',	'Simen', 'Strømsnes', '2022-03-15', 'Kul fyr', 123456.50),
	('lu', 'Lasse',	'Udjus', '2022-09-11', 'Sittende', 50),
	('jh', 'Johan', 'Haavik', '2023-01-01', 'Deputy Junior Assistant Vice President', 98765432.10),
	('bs', 'Bendik', 'Søgnesand', '2023-02-14', 'Øyvokter', 540923);

SELECT * FROM ansatt;