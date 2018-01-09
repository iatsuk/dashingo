$(document).ready(function () {
    var blackBowl = new Bowl($('.bowl-black'), 'stone-black');
    blackBowl.draw();

    var whiteBowl = new Bowl($('.bowl-white'), 'stone-white');
    whiteBowl.draw();
});

function Bowl($bowl, stoneCls) {
    this.draw = function () {
        $bowl.droppable({
            accept: ".stone",
            greedy: true,
            drop: function (event, ui) {
                ui.draggable.detach();
            }
        });

        addFakeStone();
    };

    var addFakeStone = function () {
        var $fakeStone = $('<div class="bowl-stone"></div>');
        $fakeStone.draggable({
            zIndex: 100,
            start: function (event, ui) {
                var $this = $(this);
                $this.css('position', 'absolute')
                    .appendTo($('.container'))
                    .addClass("stone")
                    .addClass(stoneCls)
                    .removeClass("bowl-stone");
                addFakeStone();
            },
            cursorAt: {left: $fakeStone.width() / 2, top: $fakeStone.height() / 2}
        });
        $bowl.append($fakeStone);
    }
}