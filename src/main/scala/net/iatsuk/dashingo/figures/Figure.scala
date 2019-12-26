package net.iatsuk.dashingo.figures

import org.scalajs.dom.svg.SVG

trait Figure {

  def figure: SVG

  def resize(length: Double): Unit

  def locate(clientWidth: Int, clientHeight: Int): Unit

}
