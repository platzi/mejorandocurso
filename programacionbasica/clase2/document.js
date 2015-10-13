function Pokemon(n,v,a)
{
	this.grito = "Pika!";
	this.nombre = n;
	this.vida = v;
	this.ataque = a;
	this.gritar = function ()
	{
		alert(this.grito);
	}
}

function inicio()
{
	var rattata = new Pokemon("Rattata", 40, 2);
	rattata.vida = rattata.vida - 13;
	nombrePokemon.textContent = rattata.nombre;
}


// Asignación por valor
// Asignación por referencia

//DOCUMENT OBJECT MODEL

// navigator, window, document

//Objeto
//	Variables
//	Funciones

// document.write("Hola mamá!");
// var pi = 3.141592654;
// pi = Math.floor(pi);
// document.write(pi);

// var maxima;
// maxima = Math.max( 5,23,4,5,12,23,23 );
// document.write(maxima);
