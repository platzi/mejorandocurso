var Grid = function(columns, rows) {
    columns = columns || 20;
    rows    = rows || 20;
    var self = {};

    self.element = $('<table class="grid"></table>');

    for(var r = 0; r < rows; r++) {
        var row = $('<tr></tr>');
        for(var c = 0; c < columns; c++) {
            var cell = $('<td></td>');

            cell.attr('x', c);
            cell.attr('y', r);

            cell.click(function (e) {
                //Click handler
                window.client.emit('pintar',{
                    x : $(this).attr('x'),
                    y : $(this).attr('y'),
                    color : $('input').val()
                });

                $(this).css('background-color', $('input').val() );
            });

            row.append(cell);
        }
        self.element.append(row);
    }

    self.render = function(where) {
        where.append(self.element);
    };

    self.pintar = function(x,y,color) {
        self.element.find('tr:eq('+ y +') td:eq('+ x +')').css('background-color', color);
    }

    return self;
};