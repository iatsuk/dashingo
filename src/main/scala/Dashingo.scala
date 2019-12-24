import org.scalajs.dom.html
import org.scalajs.dom.svg.SVG
import scalatags.JsDom
import scalatags.JsDom.all._
import scalatags.JsDom.{svgAttrs, svgTags}

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("Dashingo")
object Dashingo {

  @JSExport
  def main(target: html.Body): Unit = {
    val gobanWidth = 500
    println(target.clientWidth - gobanWidth/2.0 + "px")
    target.appendChild(
      div(
        div(goban(9, gobanWidth), position:="absolute",
          left:=target.clientWidth - gobanWidth/2.0 + "px", top:=target.clientHeight - gobanWidth/2.0 + "px"
        ),
        for (x <- 0 until 9) yield p(s"This is a big $x"),
      ).render
    )
  }

  def goban(size: Int, width: Int): JsDom.TypedTag[SVG] = {
    val cellSize = width / (size - 1.0)
    val thickness = math.ceil(width / 50.0 / size)
    svgTags.svg(
      svgAttrs.width := width, svgAttrs.height := width,
      for (x <- 0 until size) yield svgTags.line(
        svgAttrs.x1 := x * cellSize, svgAttrs.y1 := 0,
        svgAttrs.x2 := x * cellSize, svgAttrs.y2 := width,
        svgAttrs.stroke := "black", svgAttrs.strokeWidth := thickness + "px"
      ),
      for (x <- 0 until size) yield svgTags.line(
        svgAttrs.x1 := 0, svgAttrs.y1 := x * cellSize,
        svgAttrs.x2 := width, svgAttrs.y2 := x * cellSize,
        svgAttrs.stroke := "black", svgAttrs.strokeWidth := thickness + "px"
      ))
  }
}
