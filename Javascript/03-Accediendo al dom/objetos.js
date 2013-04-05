// Seleccionamos un selector 
var home = $('#home-content');

// Buscamos un hijo dentro del selector
var sidebar = home.find("#sidebar");


// Que hijos tiene?
sidebar.childNodes

// Mas hijos
sidebar.children

// Cuantos elementos tiene
sidebar.childElementCount

// Seleccionar elemento
$(sidebar);

$(sidebar).before("<h2>Titulo Antes</h2>");
$(sidebar).after("<h2>Titulo Antes</h2>");

// Buscamos una clase
$(sidebar).find(".try-jquery");

$(sidebar).append('<li>Elemento</li>');
// Buscamos una clase
$(sidebar).find(".try-jquery").remove();

$(sidebar).parent()
$(sidebar).parents();
$(sidebar).parentsUntil("body");
//closets
$(sidebar).closest('div');
var home = $(sidebar).closest('div')[0];
$(home);
//attr
$(home).attr("style","background-color : #000000");

//Cambiar el contenido
$(home).html("<h2>HELLO WORLD</h2>");


//agregar y remover clases
$(home).addClass("nueva-clase");
$(home).attr("class");

//Sacar id
$(home).attr('id');
$(home).parentsUntil("body").remove();