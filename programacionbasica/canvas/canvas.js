var dibujo, lienzo, t, b;
function inicio()
{
	t = document.getElementById("usuario");
	b = document.getElementById("dibujalo");
	dibujo = document.getElementById("dibujito");
	lienzo = dibujo.getContext("2d");

	b.addEventListener("click", dibujarGrilla);

	lienzo.beginPath();
	lienzo.strokeStyle = "#00F";
	lienzo.arc(150,150,100, (Math.PI * 2), false);
	lienzo.closePath();
	lienzo.stroke();

}
function dibujarGrilla () 
{
	var pony = lienzo;
	var rayas = Number(t.value);
	var ancho = 300, alto = 300;
	var linea, punto;
	var anchoLinea = ancho / rayas;
	var limiteX = ancho / anchoLinea;
	var limiteY = alto / anchoLinea;

	pony.strokeStyle = "#AAA";

	for(linea = 0; linea <= limiteX; linea++)
	{
		punto = (linea * anchoLinea) - 0.5;
		pony.beginPath();
		pony.strokeStyle = "#AAA";
		pony.moveTo(punto, 0.5);
		pony.lineTo(punto, ancho-0.5);
		pony.stroke();
		pony.closePath();
	}

	for(linea = 0; linea <= limiteY; linea++)
	{
		punto = (linea * anchoLinea) - 0.5;
		pony.beginPath();
		pony.strokeStyle = "#AAA";
		pony.moveTo(0.5, punto);
		pony.lineTo(alto  - 0.5, punto);
		pony.stroke();
		pony.closePath();
	}

}




