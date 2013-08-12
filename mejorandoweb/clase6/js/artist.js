
// Gobals

var LAST_FM_API_KEY = '42f75f939105d2110d6a0daf27db431c';
var LAST_FM_API_URL = 'http://ws.audioscrobbler.com/2.0/';

// AJAX functions

function getArtist(name, callback) {
  $.ajax({
    data: {
      artist: name,
      api_key: LAST_FM_API_KEY,
      format: 'json',
      method: 'artist.getinfo'
    },
    url: LAST_FM_API_URL
  })
  .done(callback);
}

function getArtistAlbums(name, callback) {
  $.ajax({
    data: {
      artist: name,
      api_key: LAST_FM_API_KEY,
      format: 'json',
      method: 'artist.gettopalbums'
    },
    url: LAST_FM_API_URL
  })
  .done(callback);
}

function getAlbumInfo(artist, album, callback) {
  $.ajax({
    data: {
      artist: artist,
      album: album,
      api_key: LAST_FM_API_KEY,
      format: 'json',
      method: 'album.getinfo'
    },
    url: LAST_FM_API_URL
  })
  .done(callback);
}

function getTopArtistsForCountry(country, callback) {
  $.ajax({
    data: {
      country: country,
      api_key: LAST_FM_API_KEY,
      format: 'json',
      method: 'geo.gettopartists'
    },
    url: LAST_FM_API_URL
  })
  .done(callback);
}

function getTopArtistsForMyCountry(callback) {
  navigator.geolocation.getCurrentPosition(function(position) {
    var latitude = position.coords.latitude;
    var longitude = position.coords.longitude;

    $.get('http://ws.geonames.org/countryCode', {
      type: "JSON",
      lat: latitude,
      lng: longitude
    }, function(data) {
      getTopArtistsForCountry(data.countryName, callback);
    });
  });
}

// Template functions

function artistTemplate(artist) {
  var html = '';
  html += '<h2>' + artist.name + '</h2>';
  html += '<figure><img src="' + artist.image[artist.image.length-1]['#text'] + '"></figure>';
  html += '<p class="bio">' + artist.bio.summary + '</p>';
  html += '<button class="btn btn-info get-albums">Albums</button>';
  return html;
}

function albumTemplate(album) {
  var html = '';
  html += '<div class="album album-onload" data-album="' + album.name + '" data-artist="' + album.artist.name + '">';
  html += '<figure>';
  html += '<h3>' + album.name + '</h3>';
  html += '<img src="' + album.image[album.image.length-1]['#text'] + '" class="img-rounded">';
  html += '</figure>';
  html += '</div>';
  return html;
}

function albumsListTemplate(albums) {
  var html = '';

  for (var i = 0; i < albums.topalbums.album.length; i++) {
    var album = albums.topalbums.album[i];
    html += albumTemplate(album);
  }

  return html;
}

function albumTrackTemplate(track) {
  var html = '';

  html += '<li>'; 
  html += '<a href="http://www.youtube.com/results?search_query=';
  html += (track.artist.name + ' ' + track.name).replace(new RegExp('\\s', 'g'), '+');
  html += '" target="_blank">' + track.name + '</a></li>';

  return html;
}

function albumDetailTemplate(album) {
  var html = '';
  html += '<div class="album-detail album-detail-onload">';
  html += '<figure>';
  html += '<h3>' + album.name + '</h3>';
  html += '<h4>' + album.artist + '</h4>';
  html += '<img src="' + album.image[album.image.length-1]['#text'] + '" class="img-rounded">';
  html += '</figure>';
  html += '<ol>';

  for (var i = 0; i < album.tracks.track.length; i++) {
    var track = album.tracks.track[i];
    html += albumTrackTemplate(track);
  }

  html += '</ol>';
  html += '</div>';
  return html;
}


var $artistInput = $('#artista');
var $button = $('#boton');
var $resultOut = $('#content');

$artistInput.on('keyup', onKeyUp);
$button.on('click', onSubmit);


function onKeyUp(evt) {
  if(evt.keyCode == 13) { // Enter
    onSubmit();
  }
}

function onSubmit() {
  getArtist($artistInput.val(), fillArtistInfo);
  $resultOut.html( '<p class="loading">cargando...</p>' );
}

function onError() {
  $resultOut.html( '<p class="error">Error... :(</p>' );
}

function fillArtistInfo(jsonData) {
  if (jsonData.error) {
    return onError();
  }

  var html = artistTemplate(jsonData.artist);
  $resultOut.html( html );

  $('.get-albums').on('click', function() {
    getArtistAlbums(jsonData.artist.name, fillAlbumsInfo);
  });
}

function fillAlbumsInfo(jsonData) { 
  if (jsonData.error) {
    return onError();
  }

  var html = albumsListTemplate(jsonData);
  $resultOut.html( html );
  $('#content .album').removeClass('album-onload');

  $('.album').on('click', function() {
    var album = $(this).data('album');
    var artist = $(this).data('artist');
    getAlbumInfo(artist, album, fillAlbumDetailInfo);
  });
}

function fillAlbumDetailInfo(jsonData) {
  if (jsonData.error) {
    return onError();
  }

  var html = albumDetailTemplate(jsonData.album);
  $resultOut.html( html );
}
