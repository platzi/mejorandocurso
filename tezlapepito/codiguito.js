$(document).on("ready", inicio);
function inicio () 
{
	//Aqui va todo el codigo relacionado con DOM
	$("#personalizar").on("click", movida);
}
function movida () 
{
	//JSON
	var cambiosCSS =
	{
		margin: 0,
		overflow: "hidden",
		padding: 0,
		width: 0
	};
	var cambiosPersonalizacion =
	{
		height: "auto",
		opacity: 1,
		width: "40%"
	};
	$("#historia").css(cambiosCSS);
	$("#personalizacion").css(cambiosPersonalizacion);
	$("#color div").on("click", cambiarColor);
}
function cambiarColor (datos) 
{
	var colorito = datos.currentTarget.id;
	var nuevoCoche = "c" + colorito + ".jpg";
	$("#cochecito img").attr("src", nuevoCoche);
}