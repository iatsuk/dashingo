$(document).ready(function () {
    var blackBowl = new Bowl('.bowl-black', '<div class="stone stone-black"></div>');
    blackBowl.draw();

    var whiteBowl = new Bowl('.bowl-white', '<div class="stone stone-white"></div>');
    whiteBowl.draw();
});

function Bowl(bowl_class, stone_html) {
    var $bowl = $(bowl_class);
    var stone = stone_html;

    this.draw = function () {
        $bowl.droppable({
            accept: ".stone",
            greedy: true,
            drop: function (event, ui) {
                ui.draggable.detach();
            }
        });

        $bowl.on('click', function () {
            var $this = $(this);
            var $stone = $(stone).css({
                left: $this.offset().left + $this.width() / 2 + "px",
                top: $this.offset().top + $this.height() / 2 + "px",
                position: "absolute"
            });
            $stone.draggable({
                zIndex: 100,
                start: function (event, ui) {
                    var $this = $(this);
                    $this.css('position', 'absolute');
                    $this.appendTo($('.container'));
                }
            });
            $this.parent().parent().append($stone);
        })
    }
}