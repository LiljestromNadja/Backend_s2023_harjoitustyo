#updated 13-11-2023 harkkasyksydb manytomany memocategory


SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS memo_category;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS memo;
DROP TABLE IF EXISTS `comment`;
DROP TABLE IF EXISTS adtype; 
DROP TABLE IF EXISTS article; 
DROP TABLE IF EXISTS application_users;

SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE application_users
(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
firstname VARCHAR(100) NOT NULL,
lastname VARCHAR(100) NOT NULL,
username VARCHAR(250) NOT NULL,
password_hash VARCHAR(250) NOT NULL,
`role`  VARCHAR(100) NOT NULL
);

#tunnus/salasana, esim admin/admin, john/john, majlis/majlis
INSERT INTO application_users (firstname, lastname, username, password_hash, `role`) 
VALUES ("", "", "user","$2a$10$RIqlxElPXzQKJayHKJwSNOxDMnMh.j.OHwQvOoPj0gld.sbXsqqgK" ,"USER"),
("", "", "admin", "$2a$10$aGjp6jEUEspwUkQrCbGAWuKScc9DRHTQ6LXMRX2TAM5A6tzHdy8/6", "ADMIN"), 
("", "", "huolto", "$2a$10$lDtQP3VTBBHPocsCga.a6.iqXrQq2S3.nlaWJieniRwYlOUjpttUS", "ADMIN"),
("John", "Smith", "john", "$2a$10$Y8mUEwuYmdcPNhAyYR0rM.JEPW5aRgszj8h.Bk58IXapvMVnL.zLO", "USER"),
("Maj-Lis", "säkerhetsbyggare", "majlis", "$2a$10$am1hN1A77jRbknyCYztw2.Fx.nz2iYEscVFg5fcHXBaA/ewyVxjki", "USER"),
("Pentti", "Perushemmo", "pena", "$2a$10$7zJlh9owF6YNRMfMJWdqQerFmCe2iCvIUxn6q.Jl4v.ZN/B1WIYhW", "USER");



CREATE TABLE adtype(
adtypeid BIGINT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL,
PRIMARY KEY (adtypeid)
);

INSERT INTO adtype (`name`) 
VALUES ("Helsinki ostetaan"),
("Helsinki myydään"),
("Helsinki annetaan"),
("Espoo ostetaan"),
("Espoo myydään"),
("Espoo annetaan"),
("Vantaa ostetaan"),
("Vantaa myydään"),
("Vantaa annetaan"),
("Kaunianen ilmoitukset"),
("Joensuu ilmoitukset"), 
("Kempele ilmoitukset"), 
("Kotka ilmoitukset");


CREATE TABLE article(
id BIGINT NOT NULL AUTO_INCREMENT,
title VARCHAR(300) NOT NULL,
description VARCHAR(500) NOT NULL,
publisher VARCHAR(200),
dateadded VARCHAR(20),
price DECIMAL(10,2),
adtypeid BIGINT,
applicationuserid BIGINT,
PRIMARY KEY (id), 
FOREIGN KEY(adtypeid) REFERENCES adtype(adtypeid),
FOREIGN KEY (applicationuserid) REFERENCES application_users(id)
);



INSERT INTO article (title, description, publisher, dateadded, price, adtypeid, applicationuserid) 
VALUES ("3 hengen sohva", "Hyvässä kunnossa, ollut käytössä 3 vuotta", "Maija 050123456", "2023-10-11", 80, 1, 3),
("LP-levysoitin", "Vanha, hyvinpalvellut, toimiva", "Matti, 04412345678", "2023-10-11", 54.90, 1, 2),
("Iso kasa Aku Ankka-sarjiksia", "Ankkalinnaa vuosilta 1980-1986", "Pentti, 0441234567", "2023-10-11", 0, 3, 2),
("Piano", "Omat kantajat mukaan", "Liisa 09-123456", "2023-10-11", 19.90, 2, 5), 
("Iso kasa reseptikirjoja", "tiedustele viestitse", "0411234567","2023-10-11", 0.01, 4, 1),
("Lastenvaatteita", "Siistissä kunnossa, kokoa 140-158", "0411234567", "2023-10-11", 5, 3, 3),
("Ostetaan tietokoneita", "Käytettyjä kannettavia tietokoneita", "Pentin peeCee 0400123456", "2023-10-11", 0, 5, 6),
("Viherkasveja", "Tiedossa muutto, kasvikokoelma myyntiin, hinta neuvoteltavissa", "Heidi 05012345678", "2023-10-11", 0, 5,5);


CREATE TABLE `comment`(
commentid BIGINT NOT NULL AUTO_INCREMENT,
commentmessage VARCHAR(800) NOT NULL,
articleid BIGINT,
applicationuserid BIGINT,
commentcreated VARCHAR (20),
wheretocontact VARCHAR (200),
PRIMARY KEY (commentid),
FOREIGN KEY(articleid) REFERENCES article(id), 
FOREIGN KEY (applicationuserid) REFERENCES application_users(id)
);


INSERT INTO `comment` (commentmessage, articleid, applicationuserid, commentcreated)
VALUES ("Olisin kiinnostunut", 1,2,"2023-10-31 23:41:45"),
("Pystyykö hinnasta neuvottelemaan? Tiina 050-123456", 4,2,"2023-10-31 23:41:45"), 
("Tartteeko autoa noutoon", 4,2,"2023-10-31 23:41:45"), 
("Hyvä vuosikerta, millaista hintaa ajattelit? ", 3,2,"2023-10-31 23:41:45"),
("Täällä yksi Ankkalinnassa noina aikoina lorvinut mielellään verestäisi muistojaan. Missäs päin Vantaata sijaitsee?", 3,2,"2023-10-31 23:41:45"),
("Täällä voisi olla kiinnostusta. Onko miltä vuosilta? ", 5,1,"2023-10-31 23:41:45");



CREATE TABLE memo(
memoid BIGINT NOT NULL AUTO_INCREMENT,
memocontent VARCHAR(1000) NOT NULL,
memodate VARCHAR (20),
applicationuserid BIGINT,
PRIMARY KEY (memoid), 
FOREIGN KEY (applicationuserid) REFERENCES application_users(id)
);

INSERT INTO memo (memocontent, memodate, applicationuserid)
VALUES ("Käyttäjän user muistiinpano","2023-11-11", 1),
("Muistiiinpano, käyttäjä:admin", "2023-11-11", 2),
("Käyttäjän muistiinpano, huolto", "2023-11-11", 3 ),
("John has to remember...", "2023-11-11", 4 );


CREATE TABLE categories (
categoryid BIGINT NOT NULL AUTO_INCREMENT,
categoryname VARCHAR(200) NOT NULL,
PRIMARY KEY (categoryid)
);


INSERT INTO categories (categoryname)
VALUES ("Testi"), 
("Tärkeät"), 
("EOS"), 
("Mielenkiintoinen");



CREATE TABLE memo_category (
memocategoryid BIGINT NOT NULL AUTO_INCREMENT,
memoid BIGINT,
categoryid BIGINT,
PRIMARY KEY(memocategoryid),
FOREIGN KEY (memoid) REFERENCES memo(memoid),
FOREIGN KEY (categoryid) REFERENCES categories(categoryid)

);

INSERT INTO memo_category(memoid, categoryid)
VALUES (1,2), 
(2,3), 
(3,3);


SELECT * FROM memo;
SELECT * FROM `comment` WHERE articleid=3;


SELECT * FROM categories;
SELECT * FROM memo_category;






