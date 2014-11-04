//--------------------------------------

function usuarios(){
    setTimeout(function(){
        console.log('listo aqui estan sus usuarios');
    }, 2000);
}

function articulos(){
    setTimeout(function(){
        console.log('listo aqui estan sus articulos');
    }, 5000);
}

usuarios()
articulos()
setTimeout(function(){
    usuarios()
}, 1500);

//--------------------------------------

function hola(){
    console.log('HOLA MUNDO');
}

window.hola() // navegador
global.hola() // node

var hola = function(){
    console.log('var HOLA MUNDO');
}

function sumaDosNumeros(numero_uno,numero_dos){
    console.log(numero_uno + numero_dos);
}
sumaDosNumeros(2,4);
6

var suma = sumaDosNumeros(2,4)
6
suma
undefined

function sumaDosNumeros(numero_uno,numero_dos){
    var suma = numero_uno + numero_dos;
    return suma;
}
var suma = sumaDosNumeros(2,4)
console.log(suma)
6

function sumaDosNumeros(numero_uno,numero_dos){
    var suma = numero_uno + numero_dos;
    setTimeout(function(){
        console.log(suma);
        return suma;
    }, 1000);
}
var suma = sumaDosNumeros(2,4)
// pasado un segundo ……….
suma
undefined

function sumaDosNumeros(numero_uno,numero_dos,callback){
    var suma = numero_uno + numero_dos;
    setTimeout(function(){
        console.log(suma);
        callback(suma);
    }, 1000);
}
var suma = 0;
sumaDosNumeros(2,4,function(callback){
    suma = callback;
    console.log('callback');
})

//--------------------------------------

function Ninja(name){
    this.name = name;
}

Ninja.prototype.swingSword = function(){
    return true;
};

var ninja1 = new Ninja('Diego');

var ninja2 = Ninja('Diego');

Ninja.prototype.myName = function(){
    return this.name;
}

ninja1.myName()

var ninja1 = new Ninja('Alonso');

ninja1.age = 27;
