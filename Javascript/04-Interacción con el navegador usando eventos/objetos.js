click
// cambiar color
$('*').click(function(event){ $(event.target).attr("style","background-color : #000000") });

hover
$('*').mouseleave(function(event){ $(event.target).remove() })


Mencionar diferencia mouseover  y mouseenter

submit
// prevent default de submit en jquery.com
$('.searchform').submit(function(event){
    event.preventDefault();
});

$('.searchform').submit(function(){
    return false;
});

mousemove
// Trackear consola
$(document).mousemove(function(event) {
  console.log(event.pageX,event.pageY);
});

Propiedades y metodos de un evento
event.preventDefault();
event.stopPropagation();
event.stopImmediatePropagation();
event.target;

on
//Caso sencillo
$('#menu-top a').on('click', function(event){
	event.preventDefault();
	console.log('click en el menu', event.target);
});

//Caso delegado
//Creamos un nuevo elemento
var newElement = $('#menu-top li:last').clone();
newElement.insertAfter('#menu-top li:last');
newElement.find('a').text('Awww').attr('href','http://i.imgur.com/gDNVSI5.jpg');

$('#menu-top').on('click', 'a',function(event){
	event.preventDefault();
	console.log('click en el menu', event.target);
});



