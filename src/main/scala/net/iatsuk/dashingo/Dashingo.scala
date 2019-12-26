package net.iatsuk.dashingo

import net.iatsuk.dashingo.figures.{Bowl, Goban, Stone}
import org.scalajs.dom
import org.scalajs.dom.html

import scala.collection.mutable
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("Dashingo")
object Dashingo {

  val goban = new Goban(11)
  val bowls = Map("black" -> new Bowl(true), "white" -> new Bowl(false))
  val stones: mutable.MutableList[Stone] = mutable.MutableList()

  @JSExport
  def main(target: html.Body): Unit = {
    // set target style
    target.style.height = "100vh"
    target.style.width = "100%"
    target.style.margin = "0"
    // build scene
    target.appendChild(bowls("black").figure)
    target.appendChild(bowls("white").figure)
    target.appendChild(goban.figure)
    // set up interaction
    target.onresize = { _: dom.Event => onresize(target.clientWidth, target.clientHeight) }
    // update
    onresize(target.clientWidth, target.clientHeight)
    bowls.foreach(Function.tupled((color, bowl) => bowl.figure.onmousedown = { e: dom.MouseEvent =>
      dom.window.alert(color + ": " + e.pageX + " x " + e.pageY)
    }))
  }

  def onresize(clientWidth: Int, clientHeight: Int): Unit = {
    bowls.values.foreach(bowl => {
      bowl.resize(150)
      bowl.locate(clientWidth, clientHeight)
    })
    goban.resize(math.min(clientWidth, clientHeight) * 0.9)
    goban.locate(clientWidth, clientHeight)
  }

}
