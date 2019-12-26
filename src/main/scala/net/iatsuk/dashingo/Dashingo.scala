package net.iatsuk.dashingo

import net.iatsuk.dashingo.figures.{Bowl, Goban, Stone}
import org.scalajs.dom
import org.scalajs.dom.html
import scalatags.JsDom.tags.div

import scala.collection.mutable
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("Dashingo")
object Dashingo {

  val goban = new Goban(11)
  val bowls = Map(true -> new Bowl(true), false -> new Bowl(false))
  val stonesContainer: html.Div = div().render
  val stones: mutable.MutableList[Stone] = mutable.MutableList()

  @JSExport
  def main(target: html.Body): Unit = {
    // set target style
    target.style.height = "100vh"
    target.style.width = "100%"
    target.style.margin = "0"
    // build scene
    bowls.values.map(_.figure).foreach(target.appendChild)
    target.appendChild(goban.figure)
    target.appendChild(stonesContainer)
    // set up interaction
    target.onresize = { _: dom.Event => onresize(target.clientWidth, target.clientHeight) }
    // update
    onresize(target.clientWidth, target.clientHeight)
    bowls.foreach(Function.tupled((color, bowl) => bowl.figure.onmousedown = { e: dom.MouseEvent =>
      val stone = new Stone(color, e.pageX, e.pageY, target.clientWidth, target.clientHeight)
      stone.resize(goban.cellSize())
      stones += stone
      stonesContainer.appendChild(stone.figure)
    }))
  }

  def onresize(clientWidth: Int, clientHeight: Int): Unit = {
    bowls.values.foreach(bowl => {
      bowl.resize(150)
      bowl.locate(clientWidth, clientHeight)
    })
    goban.resize(math.min(clientWidth, clientHeight) * 0.9)
    goban.locate(clientWidth, clientHeight)
    stones.foreach(stone => {
      stone.resize(goban.cellSize())
      stone.locate(clientWidth, clientHeight)
    })
  }

}
