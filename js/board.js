$(document).ready(function () {

    const N = 19;
    const stars = [4, 10, 16];

    var grid = $('#grid');
    for (y = 1; y <= N; y++) {
        for (x = 1; x <= N; x++) {
            var cell = $("<div style='grid-row: " + y + "; grid-column: + " + x + ";'></div>")
                .addClass("unselectable grid-item");
            if (y === 1 && x === 1) {
                cell.addClass("grid-top-left");
            } else if (y === 1 && x === N) {
                cell.addClass("grid-top-right");
            } else if (y === N && x === 1) {
                cell.addClass("grid-bottom-left");
            } else if (y === N && x === N) {
                cell.addClass("grid-bottom-right");
            } else if (y === 1) {
                cell.addClass("grid-top");
            } else if (y === N) {
                cell.addClass("grid-bottom");
            } else if (x === 1) {
                cell.addClass("grid-left");
            } else if (x === N) {
                cell.addClass("grid-right");
            } else if (stars.includes(x) && stars.includes(y)) {
                cell.addClass("grid-cross-dot");
            } else {
                cell.addClass("grid-cross");
            }
            grid.append(cell);
        }
    }
});