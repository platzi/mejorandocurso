(function(){
	var	X =  150,
		Y = X,
		R = 100,
		STROKE_STYLE = 'rgb(200,0,0)',
		FILL = 'red'

	function drawCircleInSVG(x, y, r, fill, strokeStyle){
		var paper = Raphael('svg-container')
		paper.circle(x, y, r)
			.attr({
				fill: fill,
				stroke: strokeStyle,
				'stroke-width': 4
			})
	}

	drawCircleInSVG( X, Y, R, FILL, STROKE_STYLE )

}())