package net.iatsuk.dashingo.figures

import org.scalajs.dom
import org.scalajs.dom.svg.SVG
import scalatags.JsDom.all._
import scalatags.JsDom.svgAttrs.{fill, stroke}
import scalatags.JsDom.svgTags.{circle, svg}

class Bowl(black: Boolean) extends Figure {

  private[this] var length: Double = _

  private val round: dom.svg.Circle = circle(stroke := black, fill := (if (black) "black" else "grey")).render

  private val bowl: dom.svg.SVG = svg(round).render

  override def figure: SVG = bowl

  override def resize(length: Double): Unit = {
    this.length = length
    // set board length
    bowl.setAttribute("width", length.px)
    bowl.setAttribute("height", length.px)
    // set lines length and stroke
    round.setAttribute("r", ((length - 2) / 2).px)
    round.setAttribute("cx", (length / 2).px)
    round.setAttribute("cy", (length / 2).px)
  }

  override def locate(clientWidth: Int, clientHeight: Int): Unit = {
    bowl.style.removeProperty("left")
    bowl.style.removeProperty("top")
    bowl.style.removeProperty("right")
    bowl.style.removeProperty("bottom")

    val vert = clientHeight >= clientWidth
    val (primary, secondary) = black match {
      case true if vert => ("top", "left")
      case true => ("bottom", "left")
      case false if vert => ("bottom", "right")
      case false => ("top", "right")
    }

    bowl.style.setProperty(primary, (-length / 4).px)
    bowl.style.setProperty(secondary, (-length / 4).px)
    bowl.style.position = "absolute"
  }

}
