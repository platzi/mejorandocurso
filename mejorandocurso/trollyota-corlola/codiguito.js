$(document).on("ready", inicio);
function inicio () 
{
	//AQUI VA EL CODIGO PARA EJECUTAR MI APP
	$("#personalizar").on("click", transicion);
}
function transicion()
{
	//Objecto JS/JSON
	var cambiosCSS = 
	{ 
		margin: 0,
		overflow: "hidden",
		padding: 0,
		width: 0,
		display: "none"
	};
	var cambiosPerson =
	{
		height: "auto",
		opacity: 1,
		width: "40%"
	};
	$("#historia").css(cambiosCSS);
	$("#personalizacion").css(cambiosPerson);
	$("#color div").on("click", cambiarColor);
}
function cambiarColor(datos)
{
	var col = datos.currentTarget.id;
	$("#cochecito img").attr("src", "c" + col + ".jpg");
}