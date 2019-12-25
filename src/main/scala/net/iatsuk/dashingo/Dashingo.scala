package net.iatsuk.dashingo

import org.scalajs.dom
import org.scalajs.dom.html
import scalatags.JsDom.all._
import scalatags.JsDom.svgAttrs.{stroke, strokeWidth, x1, x2, y1, y2}
import scalatags.JsDom.svgTags.{line, svg}

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("Dashingo")
object Dashingo {

  @JSExport
  def main(target: html.Body): Unit = {
    target.style.height = "100vh"
    target.style.width = "100%"
    val c = div().render
    c.textContent = s"${target.clientWidth} x ${target.clientHeight}"
    val l =  line(x1 := 0, y1 := 0, x2 := 100, y2 := 100, stroke := "black", strokeWidth := 1.px).render

    val gobanWidth = 500
    val goban = Goban.goban(11, gobanWidth)
    val gobanContainer = div(goban, position:="absolute",
      left:=(target.clientWidth-gobanWidth)/2+"px", top:=(target.clientHeight-gobanWidth)/2+"px"
    )
    target.appendChild(
      div(
        c, svg(l), gobanContainer,
        for (x <- 0 until 9) yield p(s"This is a big $x"),
      ).render
    )

    target.onresize = { _: dom.Event =>
      val s = new StringBuilder().append(target.clientWidth).append(" x ").append(target.clientHeight)
      c.textContent = s.toString()
      l.setAttribute("x2", target.clientWidth.px)
      l.setAttribute("y2", target.clientHeight.px)
    }
  }

}
