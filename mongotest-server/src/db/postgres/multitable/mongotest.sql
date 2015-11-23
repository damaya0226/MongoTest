--CREATE PRODUCTS TABLES

--DROP TABLE PRODUCT_VEHICLE;
--DROP TABLE PRODUCT_BEER;
--DROP TABLE PRODUCT;

CREATE TABLE PRODUCT(
  ID INTEGER NOT NULL,
  PRICE NUMERIC(30,15) NOT NULL,
  DESCRIPTION VARCHAR(100),
  UNITS INTEGER NOT NULL,
  CATEGORY VARCHAR(20) NOT NULL,
  BLOCK INTEGER NOT NULL,
  CONSTRAINT P_PK PRIMARY KEY(ID)
);

CREATE TABLE PRODUCT_VEHICLE(
  ID INTEGER NOT NULL REFERENCES PRODUCT (ID),
  COLORS VARCHAR(100) NOT NULL,
  ENGINE_TYPE VARCHAR(35) NOT NULL,
  SOUND_TYPE VARCHAR(35),
  CONSTRAINT PV_PK PRIMARY KEY(ID)
);


/*
INSERT INTO PRODUCT(ID, PRICE, DESCRIPTION, UNITS, CATEGORY, BLOCK)
VALUES(1, 150, 'Vehicle1', 2, 'Vehicle', 1);

INSERT INTO PRODUCT_VEHICLE(ID, COLORS, ENGINE_TYPE, SOUND_TYPE)
VALUES (1, 'Blue, Red, Yellow', 'engine1', 'soundType1');

*/

CREATE TABLE PRODUCT_BEER(
  ID INTEGER NOT NULL REFERENCES PRODUCT (ID),
  FLAVORS VARCHAR(100) NOT NULL,
  MADEIN VARCHAR(30),
  ALCOHOL_CONTENT NUMERIC(30,15),
  CONSTRAINT PB_PK PRIMARY KEY(ID)
)

/*
INSERT INTO PRODUCT(ID, PRICE, DESCRIPTION, UNITS, CATEGORY, BLOCK)
VALUES(2, 150, 'Beer1', 2, 'Beer', 1);

INSERT INTO PRODUCT_BEER(ID, FLAVORS, MADEIN, ALCOHOL_CONTENT)
VALUES (2, 'Tomato, Potato, Banana', 'BeerCountry1', 0.1);*/
