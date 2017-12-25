$(document).ready(function () {

    const N = 19;
    const stars = [4, 10, 16];

    var grid = $('#grid');
    for (y = 1; y <= N; y++) {
        for (x = 1; x <= N; x++) {
            if (y === 1 && x === 1) {
                grid.append("<div class='unselectable grid-top-left'></div>>");
            } else if (y === 1 && x === N) {
                grid.append("<div class='unselectable grid-top-right'></div>>");
            } else if (y === N && x === 1) {
                grid.append("<div class='unselectable grid-bottom-left'></div>>");
            } else if (y === N && x === N) {
                grid.append("<div class='unselectable grid-bottom-right'></div>>");
            } else if (y === 1) {
                grid.append("<div class='unselectable grid-top'></div>>");
            } else if (y === N) {
                grid.append("<div class='unselectable grid-bottom'></div>>");
            } else if (x === 1) {
                grid.append("<div class='unselectable grid-left'></div>>");
            } else if (x === N) {
                grid.append("<div class='unselectable grid-right'></div>>");
            } else if (stars.includes(x) && stars.includes(y)) {
                grid.append("<div class='unselectable grid-cross-dot'></div>>");
            } else {
                grid.append("<div class='unselectable grid-cross'></div>>");
            }
        }
        grid.append('<br>');
    }
});