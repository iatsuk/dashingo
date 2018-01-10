$(document).ready(function () {
    var blackBowl = new Bowl($('.bowl-black'), 'stone stone-black');
    blackBowl.addEvents();

    var whiteBowl = new Bowl($('.bowl-white'), 'stone stone-white');
    whiteBowl.addEvents();
});

function Bowl($bowl, stoneCls) {
    this.addEvents = function () {
        $bowl.droppable({
            accept: ".stone",
            greedy: true,
            drop: function (event, ui) {
                ui.draggable.detach();
            }
        });

        $bowl.on('mousedown', function (event) {
            var $stone = $('<div>', {class: stoneCls})
                .appendTo($(this).closest(".container"))
                .draggable({
                    start: function (event, ui) {
                        $(this).css('position', 'absolute').appendTo($('.container'));
                    }
                });
            $stone.css({
                left: event.pageX - ($stone.width() / 2),
                top: event.pageY - ($stone.height() / 2)
            });
            $stone.trigger(event);
        });
    };
}