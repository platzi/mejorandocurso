$(function(){
	var geo = navigator.geolocation;
	var opciones = {}

	function geo_error() {
		console.log("HHhmm... this is akward... no puedo saber donde estas.");
	}

	function geo_exito(posicion) {
		var lat  = posicion.coords.latitude;
		var lon  = posicion.coords.longitude;
		var mapa = new Image();
		mapa.src = "http://maps.googleapis.com/maps/api/staticmap?maptype=hybrid&zoom=13&size=300x300&sensor=false&center="+lat+","+lon;
		$('#geo').append(mapa);

		obtenerGeoInformacion(lat, lon);
	}

	geo.getCurrentPosition(geo_exito, geo_error, opciones);
});