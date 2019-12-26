package net.iatsuk.dashingo.figures

import org.scalajs.dom
import org.scalajs.dom.svg.SVG
import scalatags.JsDom.all._
import scalatags.JsDom.svgAttrs.{fill, stroke}
import scalatags.JsDom.svgTags.{circle, svg}

class Stone(black: Boolean, x: Double, y: Double, clientWidth: Int, clientHeight: Int) extends Figure {

  private[this] var length: Double = _
  private[this] var oldClientWidth: Int = clientWidth
  private[this] var oldClientHeight: Int = clientHeight
  private[this] var captured: Boolean = true

  private val inner: dom.svg.Circle = circle(stroke := "white", fill := (if (black) "black" else "grey")).render

  private val stone: dom.svg.SVG = svg(inner, position := "absolute", left := x.px, top := y.px).render
  stone.onmousedown = { _: dom.MouseEvent =>
    val parent = stone.parentNode.asInstanceOf[dom.Element]
    parent.removeChild(stone)
    parent.appendChild(stone)
    captured = true
  }
  stone.onmousemove = { e: dom.MouseEvent =>
    if (captured) {
      stone.style.left = (e.pageX - length / 2).px
      stone.style.top = (e.pageY - length / 2).px
    }
  }
  stone.onmouseup = { _: dom.MouseEvent => captured = false }

  override def figure: SVG = stone

  override def resize(length: Double): Unit = {
    stone.style.left = (stone.style.left.dropRight(2).toDouble + this.length / 2).px
    stone.style.top = (stone.style.top.dropRight(2).toDouble + this.length / 2).px
    this.length = length * 0.9
    stone.style.left = (stone.style.left.dropRight(2).toDouble - this.length / 2).px
    stone.style.top = (stone.style.top.dropRight(2).toDouble - this.length / 2).px
    // set board length
    stone.setAttribute("width", length.px)
    stone.setAttribute("height", length.px)
    // set lines length and stroke
    inner.setAttribute("r", ((length - 2) / 2).px)
    inner.setAttribute("cx", (length / 2).px)
    inner.setAttribute("cy", (length / 2).px)
  }

  override def locate(clientWidth: Int, clientHeight: Int): Unit = {
    stone.style.left = (stone.style.left.dropRight(2).toDouble / oldClientWidth * clientWidth).px
    stone.style.top = (stone.style.top.dropRight(2).toDouble / oldClientHeight * clientHeight).px

    oldClientWidth = clientWidth
    oldClientHeight = clientHeight
  }

}
