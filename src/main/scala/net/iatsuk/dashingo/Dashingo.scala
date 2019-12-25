package net.iatsuk.dashingo

import org.scalajs.dom
import org.scalajs.dom.html

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("Dashingo")
object Dashingo {

  val goban = new Goban(11)
  val bowl = new Bowl(true)

  @JSExport
  def main(target: html.Body): Unit = {
    // set target style
    target.style.height = "100vh"
    target.style.width = "100%"
    target.style.margin = "0"
    // build scene
    target.appendChild(bowl.bowl)
    target.appendChild(goban.board)
    // set up interaction
    target.onresize = { _: dom.Event => onresize(target.clientWidth, target.clientHeight) }
    // update
    onresize(target.clientWidth, target.clientHeight)
  }

  def onresize(clientWidth: Int, clientHeight: Int): Unit = {
    bowl.resize(150)
    bowl.center(clientWidth, clientHeight)
    goban.resize(math.min(clientWidth, clientHeight) * 0.9)
    goban.center(clientWidth, clientHeight)
  }

}
