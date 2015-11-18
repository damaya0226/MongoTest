/**
 * Created by ES-CO-DAMAYA on 11/17/2015.
 */
//Inserting

//Please make sure you are going to use the inventory database to insert in the collection
//use inventory;

var numberOfProductsPerCategory = 1000000;

function insertBeerDocuments(){
    var newDocument = {};
    var price = 1500;
    var units = 2;
    var alcoholContent = 0.1;
    var block = 101;
    for(var i = 1; i <= numberOfProductsPerCategory; i++){
        newDocument.category = "Beer";
        newDocument.price = price;
        newDocument.description = "Beer " + i;
        newDocument.units = units;
        newDocument.flavors = ['Tomato', 'Potato', 'Banana'];
        newDocument.madeIn = "BeerCountry" + i;
        newDocument.alcoholContent = alcoholContent;
        newDocument.block = block;
        db.products.insert(newDocument);
        price += 500;
        units += 2;
        alcoholContent += 0.01;
        block++;
    }
}

function insertVehicleDocuments(){
    var newDocument = {};
    var price = 1500;
    var units = 2;
    for(var i = 1; i <= numberOfProductsPerCategory; i++){
        newDocument.category = "Vehicle";
        newDocument.price = price;
        newDocument.description = "Vehicle " + i;
        newDocument.units = units;
        newDocument.colors = ["Blue", "Red", "Yellow"];
        newDocument.engineType = "engine" + i;
        newDocument.soundType = "soundType" + i;
        newDocument.block = i;
        db.products.insert(newDocument);
        price += 20;
        units += 1;
    }


}