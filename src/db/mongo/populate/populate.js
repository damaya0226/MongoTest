/**
 * Created by ES-CO-DAMAYA on 11/17/2015.
 */
//Inserting

var numberOfProductsPerCategory = 1000000;

function insertBeerDocuments(){
    var newDocument = {};
    var price = 1500;
    var units = 2;
    var alcoholContent = 0.1;
    for(var i = 1; i <= numberOfProductsPerCategory; i++){
        newDocument.category = "Beer";
        newDocument.price = price;
        newDocument.description = "Beer " + i;
        newDocument.units = units;
        newDocument.flavors = [];
        newDocument.madeIn = "BeerCountry1";
        newDocument.alcoholContent = alcoholContent;
        price += 500;
        units += 2;
        alcoholContent += 0.01;
    }
}