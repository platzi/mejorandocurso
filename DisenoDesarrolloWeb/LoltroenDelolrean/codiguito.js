$(document).on("ready", inicio);
function inicio () 
{
	$("#personalizar").on("click", transicion);
}
function transicion ()
{
	//Objeto Javascript: JSON
	var cambiosHistoria = 
	{ 
		margin: 0,
		overflow: "hidden",
		padding: 0,
		width: 0
	};
	var cambiosPerson =
	{
		height: "auto",
		opacity: 1,
		width: "40%"
	};
	$("#historia").css(cambiosHistoria);
	$("#personalizacion").css(cambiosPerson);
	$("#color div").on("click", cambiarColor);
}
function cambiarColor (datos) 
{
	var col = datos.currentTarget.id;
	$("#cochecito img").attr("src", "c"+ col +".jpg")
}