DROP SCHEMA IF EXISTS oblig3_min CASCADE;

CREATE SCHEMA oblig3_min;
SET search_path TO oblig3_min;

CREATE TABLE oblig3_min.ansatt (
    ansatt_id SERIAL PRIMARY KEY,
    brukernavn CHAR(4) UNIQUE NOT NULL,
    fornavn VARCHAR(50) NOT NULL, 
    etternavn VARCHAR(50) NOT NULL, 
    ansettelsesdato DATE,
    stilling VARCHAR(50),
    maanedslonn DECIMAL(10, 2),
    avdeling_id INTEGER
);

CREATE TABLE oblig3_min.avdeling (
    avdeling_id SERIAL PRIMARY KEY, 
    navn VARCHAR(50), 
    avdeling_sjef_id INTEGER,
    FOREIGN KEY (avdeling_sjef_id) REFERENCES ansatt(ansatt_id)
);

INSERT INTO oblig3_min.ansatt (brukernavn, fornavn, etternavn, ansettelsesdato, stilling, maanedslonn, avdeling_id)
VALUES 
    ('lph', 'Lars', 'Hansen', '2022-01-01', 'Senior Utvikler', 60000.00, '1'),
    ('anne', 'Anna', 'Berg', '2022-02-15', 'Prosjektleder', 70000.00, '1'),
    ('momo', 'Marius', 'Nilsen', '2022-03-10', 'Systemarkitekt', 735000.00, '2'),
    ('Syz', 'Susu', 'Ytre', '2022-04-20', 'Utvikler', 55000.00, '4'),
    ('boso', 'Bore', 'Setre', '2024-01-01', 'Senior Utvikler', 20000.00, '2'),
    ('joh', 'Johan', 'Olsen', '2019-10-15', 'Prosjektleder', 100000.00, '4'),
    ('lass', 'Lasse', 'Banden', '2023-06-10', 'Systemarkitekt', 43000.00, '3 '),
    ('sims', 'Simen', 'Geir', '2020-04-20', 'Utvikler', 51000.00, '1'),
    ('ben', 'Bendik', 'Petter', '2024-06-01', 'Senior Utvikler', 110000.00, '3');

INSERT INTO oblig3_min.avdeling(navn) 
VALUES
    ('IT avdeling'),
    ('Prosjektledelse'),
    ('Moro avdeling');


UPDATE oblig3_min.avdeling
SET avdeling_sjef_id =(
    SELECT ansatt_id
    FROM oblig3_min.ansatt
    WHERE brukernavn = 'simsin'
)

WHERE navn = 'IT avdeling';

UPDATE oblig3_min.avdeling
SET avdeling_sjef_id =(
    SELECT ansatt_id
    FROM oblig3_min.ansatt
    WHERE brukernavn = 'annie'
)   
WHERE navn = 'Prosjektledelse';

UPDATE oblig3_min.avdeling
SET avdeling_sjef_id =(
    SELECT ansatt_id
    FROM oblig3_min.ansatt
    WHERE brukernavn = 'ben'
)   
WHERE navn = 'Moro avdeling';