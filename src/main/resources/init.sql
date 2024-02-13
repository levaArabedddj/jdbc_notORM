CREATE TABLE customer(
    id  BIGSERIAL  PRIMARY KEY ,
    fio  TEXT NOT NULL ,
    phone TEXT ,
    adress TEXT,
    created timestamp DEFAULT now()
)