package net.iatsuk.dashingo

import org.scalajs.dom
import scalatags.JsDom.all._
import scalatags.JsDom.svgAttrs.stroke
import scalatags.JsDom.svgTags.{circle, svg}

class Bowl(black: Boolean) {

  private[this] var length: Double = _

  private val round: dom.svg.Circle = circle(stroke := "black").render

  val bowl: dom.svg.SVG = svg(round).render

  def resize(length: Double): Unit = {
    this.length = length
    // set board length
    bowl.setAttribute("width", length.px)
    bowl.setAttribute("height", length.px)
    // set lines length and stroke
    round.setAttribute("r", ((length - 2) / 2).px)
    round.setAttribute("cx", (length / 2).px)
    round.setAttribute("cy", (length / 2).px)
  }

  def center(clientWidth: Int, clientHeight: Int): Unit = {
    bowl.style.removeProperty("left")
    bowl.style.removeProperty("top")
    bowl.style.removeProperty("right")
    bowl.style.removeProperty("bottom")
    if (black) {
      bowl.style.left = (-length / 4).px
      bowl.style.top = (-length / 4).px
    } else {
      bowl.style.right = (-length / 4).px
      bowl.style.bottom = (-length / 4).px
    }
    bowl.style.position = "absolute"
  }

}
