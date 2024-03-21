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
	avdelingsid INT NOT NULL,
	PRIMARY KEY (id)
);

INSERT INTO ansatt (
	brukernavn,
	fornavn,
	etternavn,
	ansettelsesdato,
	stilling,
	maanedslonn,
	avdelingsid
) VALUES 
	('shs',	'Simen', 'Strømsnes', '2022-03-15', 'Kul fyr', 123456.50, 1),
	('lu', 'Lasse',	'Udjus', '2022-09-11', 'Sittende', 50, 3),
	('jh', 'Johan', 'Haavik', '2023-01-01', 'Deputy Junior Assistant Vice President', 98765432.10, 1),
	('bs', 'Bendik', 'Søgnesand', '2023-02-14', 'Øyvokter', 540923, 2),
	('abc', 'Atle', 'Creamsen', '2023-03-16', 'Heisåpner', 72348, 2),
	('bb', 'Bill', 'Bendiksen', '2023-03-17', 'Potetmoser', 738429, 3),
	('ky', 'Kalle', 'Ygg', '2023-03-17', 'Pølsevever', 8392, 4),
	('ok', 'Olve', 'Klam', '2023-03-18', 'Palmefukter', 984333.8, 3),
	('pp', 'Pelle', 'Parafin', '2023-03-19', 'Jingle-komponist',123456, 4),
	('lmno', 'Luggvald', 'Osnestangbuktvikfjord', '2023-03-19', 'Tønnesnekker',554433, 2);
	
CREATE TABLE avdeling (
	id SERIAL UNIQUE,
	navn VARCHAR(30),
	sjefsId INT NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (sjefsId) REFERENCES ansatt(id)
); 
 
INSERT INTO avdeling (
	 navn,
	 sjefsId 
) VALUES
	('Hovedkontoret', 1),
	('Osterøy', 4),
	('Arrakis', 2),
	('Haugesund', 7)
;

ALTER TABLE ansatt ADD CONSTRAINT avdFK
FOREIGN KEY (avdelingsid) REFERENCES avdeling(id);

SELECT * FROM ansatt;