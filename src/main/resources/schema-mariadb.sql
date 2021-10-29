DROP TABLE IF EXISTS contents CASCADE;

CREATE TABLE IF NOT EXISTS contents (
    id         INTEGER,
    title      VARCHAR(100),
    director   VARCHAR(100),
    year       VARCHAR(50),
    genre      VARCHAR(50),
    PRIMARY KEY (id)
);

ALTER TABLE contents MODIFY id INTEGER NOT NULL AUTO_INCREMENT;



DROP TABLE IF EXISTS user CASCADE;

CREATE TABLE IF NOT EXISTS user (
    username                VARCHAR(20) NOT NULL, 
    password                VARCHAR(500), 
    name                    VARCHAR(20), 
    account_non_expired     BOOLEAN, 
    account_non_locked      BOOLEAN, 
    credentials_non_expired BOOLEAN, 
    enabled                 BOOLEAN 
);

ALTER TABLE user ADD PRIMARY KEY (username);



DROP TABLE IF EXISTS authority CASCADE;

CREATE TABLE IF NOT EXISTS authority (
    username        VARCHAR(20) NOT NULL,
    authority_name  VARCHAR(20) NOT NULL
);

ALTER TABLE authority ADD PRIMARY KEY (username, authority_name);