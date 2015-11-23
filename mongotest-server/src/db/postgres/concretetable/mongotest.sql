--CREATE PRODUCTS TABLES

--DROP SEQUENCE PRODUCTS_SEQ;
--DROP TABLE PRODUCT_VEHICLE;
--DROP TABLE PRODUCT_BEER;

CREATE SEQUENCE PRODUCTS_SEQ;

CREATE TABLE PRODUCT_VEHICLE(
  ID INTEGER NOT NULL DEFAULT NEXTVAL('PRODUCTS_SEQ'),
  PRICE NUMERIC(30,15) NOT NULL,
  DESCRIPTION VARCHAR(100),
  UNITS INTEGER NOT NULL,
  COLORS VARCHAR(100) NOT NULL,
  ENGINE_TYPE VARCHAR(35) NOT NULL,
  SOUND_TYPE VARCHAR(35),
  BLOCK INTEGER NOT NULL,
  CONSTRAINT PV_PK PRIMARY KEY(ID)
);

/*INSERT INTO PRODUCT_VEHICLE(PRICE, DESCRIPTION, UNITS, COLORS, ENGINE_TYPE, SOUND_TYPE, BLOCK)
VALUES (150, 'Vehicle1', 2, 'Blue, Red, Yellow', 'engine1', 'soundType1', 1);*/


CREATE TABLE PRODUCT_BEER(
  ID INTEGER NOT NULL DEFAULT NEXTVAL('PRODUCTS_SEQ'),
  PRICE NUMERIC(30,15) NOT NULL,
  DESCRIPTION VARCHAR(100),
  UNITS INTEGER NOT NULL,
  FLAVORS VARCHAR(100) NOT NULL,
  MADEIN VARCHAR(30),
  ALCOHOL_CONTENT NUMERIC(30, 15),
  BLOCK INTEGER NOT NULL,
  CONSTRAINT PB_PK PRIMARY KEY(ID)
)