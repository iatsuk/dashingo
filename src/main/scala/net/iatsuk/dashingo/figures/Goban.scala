package net.iatsuk.dashingo.figures

import org.scalajs.dom
import org.scalajs.dom.svg.SVG
import scalatags.JsDom.all._
import scalatags.JsDom.svgAttrs.stroke
import scalatags.JsDom.svgTags.{line, svg}

class Goban(size: Int) extends Figure {

  private[this] var length: Double = _
  private[this] var thickness: Double = _
  private[this] var cellSize: Double = _

  private val lines: Seq[dom.svg.Line] = for (_ <- 0 until size * 2) yield line(stroke := "black").render

  private val board: dom.svg.SVG = svg(lines, position := "absolute").render

  override def figure: SVG = board

  override def resize(length: Double): Unit = {
    this.length = length
    // set board length
    board.setAttribute("width", length.px)
    board.setAttribute("height", length.px)
    // set lines length and stroke
    thickness = length / 35.0 / size
    cellSize = (length - thickness) / (size - 1.0)
    val start = 0.5 * thickness
    val stop = length - 0.5 * thickness
    for (i <- 0 until size) {
      val dyn = 0.5 * thickness + i * cellSize
      updateLine(lines(i), (dyn.px, start.px), (dyn.px, stop.px), thickness.px)
      updateLine(lines(i + size), (start.px, dyn.px), (stop.px, dyn.px), thickness.px)
    }
  }

  def thickness(): Double = thickness
  def cellSize(): Double = cellSize

  private[this] def updateLine(line: dom.svg.Line, start: (String, String), stop: (String, String), thickness: String): Unit = {
    line.setAttribute("x1", start._1)
    line.setAttribute("y1", start._2)
    line.setAttribute("x2", stop._1)
    line.setAttribute("y2", stop._2)
    line.setAttribute("stroke-width", thickness)
  }

  override def locate(clientWidth: Int, clientHeight: Int): Unit = {
    board.style.left = ((clientWidth - length) / 2).px
    board.style.top = ((clientHeight - length) / 2).px
  }

}
