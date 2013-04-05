// Imports
var request = require('request'),
  	JSONStream = require('JSONStream'),
  	es = require('event-stream');
// EventStream is like functional programming meets IO

// Usamos un parser para cada uno de los rows del JSONStream
var parser = JSONStream.parse(['rows', true]),
  	req = request({url: 'http://isaacs.couchone.com/registry/_all_docs'}),
  	logger = es.mapSync(function (data) {
  		if( data.id.search('a') >= 0 ){
      		console.log(data.id)
  		}

      	return data
    });

req.pipe(parser).pipe(logger);



