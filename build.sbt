name := "3252"
version := "1.0"
scalaVersion := "2.12.18"

libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "5.1.0"
libraryDependencies += "org.slf4j" % "slf4j-api" % "2.0.13"
libraryDependencies += "org.slf4j" % "slf4j-simple" % "2.0.13" % Test
libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
