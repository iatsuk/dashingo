package net.iatsuk.dashingo.figures

import org.scalajs.dom
import org.scalajs.dom.svg.SVG
import scalatags.JsDom.all._
import scalatags.JsDom.svgAttrs.{fill, stroke}
import scalatags.JsDom.svgTags.{circle, svg}

class Stone(black: Boolean, x: Double, y: Double) extends Figure {

  private[this] var length: Double = _
  private[this] var oldClientWidth: Int = _
  private[this] var oldClientHeight: Int = _

  private val inner: dom.svg.Circle = circle(stroke := "white", fill := (if (black) "black" else "grey")).render

  private val stone: dom.svg.SVG = svg(inner, position := "absolute", left := x.px, top := y.px).render

  override def figure: SVG = stone

  override def resize(length: Double): Unit = {
    this.length = length
    // set board length
    stone.setAttribute("width", length.px)
    stone.setAttribute("height", length.px)
    // set lines length and stroke
    inner.setAttribute("r", ((length - 2) / 2).px)
    inner.setAttribute("cx", (length / 2).px)
    inner.setAttribute("cy", (length / 2).px)
  }

  override def locate(clientWidth: Int, clientHeight: Int): Unit = {
    val widthDiff = clientWidth - oldClientWidth
    val heightDiff = clientHeight - oldClientHeight

    inner.style.left = (inner.style.left.toInt + widthDiff).px
    inner.style.top = (inner.style.top.toInt + heightDiff).px

    oldClientWidth = clientWidth
    oldClientHeight = clientHeight
  }

}
