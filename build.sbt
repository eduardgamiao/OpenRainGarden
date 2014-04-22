name := "OpenRainGarden"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "com.typesafe" %% "play-plugins-mailer" % "2.2.0",
  "org.mindrot" % "jbcrypt" % "0.3m",
  "mysql" % "mysql-connector-java" % "5.1.21"
)     

play.Project.playJavaSettings
