$(document).ready(function () {

    const N = 19;
    const stars = [4, 10, 16];

    var grid = $('#grid');
    for (y = 1; y <= N; y++) {
        for (x = 1; x <= N; x++) {
            if (y === 1 && x === 1) {
                grid.append("<div class='unselectable grid-item grid-top-left' style='grid-row: " + y + "; grid-column: + " + x + ";'></div>");
            } else if (y === 1 && x === N) {
                grid.append("<div class='unselectable grid-item grid-top-right' style='grid-row: " + y + "; grid-column: + " + x + ";'></div>");
            } else if (y === N && x === 1) {
                grid.append("<div class='unselectable grid-item grid-bottom-left' style='grid-row: " + y + "; grid-column: + " + x + ";'></div>");
            } else if (y === N && x === N) {
                grid.append("<div class='unselectable grid-item grid-bottom-right' style='grid-row: " + y + "; grid-column: + " + x + ";'></div>");
            } else if (y === 1) {
                grid.append("<div class='unselectable grid-item grid-top' style='grid-row: " + y + "; grid-column: + " + x + ";'></div>");
            } else if (y === N) {
                grid.append("<div class='unselectable grid-item grid-bottom' style='grid-row: " + y + "; grid-column: + " + x + ";'></div>");
            } else if (x === 1) {
                grid.append("<div class='unselectable grid-item grid-left' style='grid-row: " + y + "; grid-column: + " + x + ";'></div>");
            } else if (x === N) {
                grid.append("<div class='unselectable grid-item grid-right' style='grid-row: " + y + "; grid-column: + " + x + ";'></div>");
            } else if (stars.includes(x) && stars.includes(y)) {
                grid.append("<div class='unselectable grid-item grid-cross-dot' style='grid-row: " + y + "; grid-column: + " + x + ";'></div>");
            } else {
                grid.append("<div class='unselectable grid-item grid-cross' style='grid-row: " + y + "; grid-column: + " + x + ";'></div>");
            }
        }
    }
});