DROP SCHEMA IF EXISTS oblig3_min CASCADE;

CREATE SCHEMA oblig3_min;
SET search_path TO oblig3_min;

CREATE TABLE oblig3_min.ansatt (
    ansatt_id SERIAL PRIMARY KEY,
    brukernavn VARCHAR(6) UNIQUE NOT NULL,
    fornavn VARCHAR(50) NOT NULL, 
    etternavn VARCHAR(50) NOT NULL, 
    ansett_dato DATE,
    stilling VARCHAR(50),
    maanedslonn DECIMAL(10, 2),
    avdeling_id VARCHAR(50)
);

CREATE TABLE oblig3_min.avdeling (
    avdeling_id SERIAL PRIMARY KEY, 
    navn VARCHAR(50), 
    avdeling_sjef_id INTEGER,
    FOREIGN KEY (avdeling_sjef_id) REFERENCES ansatt(ansatt_id)
);

CREATE TABLE oblig3_min.prosjekt (
    prosjekt_id SERIAL PRIMARY KEY,
    navn VARCHAR(50),
    beskrivelse TEXT,
    ansatt_id INTEGER,
    rolle VARCHAR(50),
    arbeidstimer INTEGER,
    FOREIGN KEY (ansatt_id) REFERENCES ansatt(ansatt_id)
);


INSERT INTO oblig3_min.ansatt (brukernavn, fornavn, etternavn, ansett_dato, stilling, maanedslonn, avdeling_id)
VALUES 
    ('lph', 'Lars', 'Hansen', '2022-01-01', 'Senior Utvikler', 60000.00, 'IT'),
    ('annie', 'Anna', 'Berg', '2022-02-15', 'Prosjektleder', 70000.00, 'Prosjektledelse'),
    ('momo', 'Marius', 'Nilsen', '2022-03-10', 'Systemarkitekt', 75000.00, 'IT '),
    ('Syz', 'Susu', 'Ytre', '2022-04-20', 'Utvikler', 55000.00, 'IT fyr');