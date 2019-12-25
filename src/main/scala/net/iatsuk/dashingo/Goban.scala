package net.iatsuk.dashingo

import org.scalajs.dom.svg.SVG
import scalatags.JsDom
import scalatags.JsDom.implicits._
import scalatags.JsDom.svgAttrs._
import scalatags.JsDom.svgTags._

object Goban {

  def goban(size: Int, length: Int): JsDom.TypedTag[SVG] = {
    val thickness = length / 35.0 / size
    val cellSize = (length - thickness) / (size - 1.0)
    val vert: scalatags.JsDom.svgTags.SeqFrag[_] = for (x <- 0 until size) yield line(
      x1 := 0.5 * thickness + x * cellSize, y1 := 0.5 * thickness,
      x2 := 0.5 * thickness + x * cellSize, y2 := length - 0.5 * thickness,
      stroke := "black", strokeWidth := thickness.px
    )
    val horz: scalatags.JsDom.svgTags.SeqFrag[_] = for (x <- 0 until size) yield line(
      x1 := 0.5 * thickness, y1 := 0.5 * thickness + x * cellSize,
      x2 := length - 0.5 * thickness, y2 := 0.5 * thickness + x * cellSize,
      stroke := "black", strokeWidth := thickness.px
    )
    svg(
      width := length, height := length,
      vert, horz
    )
  }
}
