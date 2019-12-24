scalaVersion := "2.12.8"

name := "dashingo"
organization := "net.iatsuk.dashingo"
version := "1.0"

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.8",
  "com.lihaoyi" %%% "scalatags" % "0.8.2",
)

enablePlugins(ScalaJSPlugin)