ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.3"

lazy val root = (project in file("."))
  .settings(
    name := "mp3Merger",

    resolvers ++= Opts.resolver.sonatypeOssSnapshots,

    scalacOptions ++= Seq("-unchecked", "-deprecation", "-Xcheckinit", "-encoding", "utf8"),

    libraryDependencies := Seq(
      "org.scalactic" %% "scalactic" % "3.2.19",
      "org.scalatest" %% "scalatest" % "3.2.19" % "test",
      "commons-io" % "commons-io" % "2.16.1",
      "org.apache.commons" % "commons-vfs2" % "2.9.0",
      "org.scalafx" %% "scalafx" % "22.0.0-R33"
    )
  )
