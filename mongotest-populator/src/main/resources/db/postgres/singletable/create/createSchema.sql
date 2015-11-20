--CREATE PRODUCTS TABLES

CREATE SEQUENCE PRODUCTS_SEQ;

CREATE TABLE PRODUCT(
  ID INTEGER NOT NULL DEFAULT NEXTVAL('PRODUCTS_SEQ'),
  CATEGORY VARCHAR(20) NOT NULL,
  PRICE NUMERIC(10, 4) NOT NULL,
  DESCRIPTION VARCHAR(100),
  UNITS INTEGER NOT NULL,

  COLORS VARCHAR(100),
  ENGINE_TYPE VARCHAR(35),
  SOUND_TYPE VARCHAR(35),

  FLAVORS VARCHAR(100),
  MADEIN VARCHAR(30),
  ALCOHOL_CONTENT NUMERIC(4, 2),

  BLOCK INTEGER NOT NULL,
  CONSTRAINT P_PK PRIMARY KEY(ID)
);

/*
  Insert Vehicle
INSERT INTO PRODUCT(CATEGORY, PRICE, DESCRIPTION, UNITS, COLORS, ENGINE_TYPE, SOUND_TYPE, FLAVORS, MADEIN, ALCOHOL_CONTENT, BLOCK)
VALUES ('Vehicle', 150, 'Vehicle1', 2, 'Blue, Red, Yellow', 'engine1', 'soundType1', null, null, null, 1);
*/

/*
  Insert Beer
INSERT INTO PRODUCT(CATEGORY, PRICE, DESCRIPTION, UNITS, COLORS, ENGINE_TYPE, SOUND_TYPE, FLAVORS, MADEIN, ALCOHOL_CONTENT, BLOCK)
VALUES ('Beer', 150, 'Beer1', 2, null, null, null, 'Tomato, Potato, Banana', 'BeerCountry1', 0.1, 1);
*/
