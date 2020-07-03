import Dependencies._

ThisBuild / organization := "com.myorganization"
ThisBuild / scalaVersion := "2.13.3"
ThisBuild / version := "0.0.1-SNAPSHOT"

ThisBuild / scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-language:_",
  "-unchecked",
  "-Wunused:_",
  "-Xfatal-warnings",
  "-Ymacro-annotations"
)

val zioVersion = "1.0.0-RC21-2" //1.0.0-RC20

lazy val `my-project` =
  project
    .in(file("."))
    .settings(name := "My Project")
    .settings(commonSettings: _*)
    .settings(dependencies: _*)

lazy val commonSettings = Seq(
  addCompilerPlugin(org.augustjune.`context-applied`),
  addCompilerPlugin(org.typelevel.`kind-projector`),
  Compile / console / scalacOptions --= Seq(
    "-Wunused:_",
    "-Xfatal-warnings"
  ),
  Test / console / scalacOptions :=
    (Compile / console / scalacOptions).value
)

lazy val dependencies = Seq(
  libraryDependencies ++= Seq(
    "dev.zio" %% "zio" % zioVersion,
    "dev.zio" %% "zio-test" % zioVersion % "test",
    "dev.zio" %% "zio-test-sbt" % zioVersion % "test",
    "dev.zio" %% "zio-test-magnolia" % zioVersion % "test",
    ("dev.zio" %% "zio-interop-cats" % "2.1.3.0-RC16")
      .excludeAll(ExclusionRule("dev.zio")),
    "com.softwaremill.sttp.client" %% "async-http-client-backend-zio" % "2.2.1", //2.1.4
    "com.softwaremill.sttp.client" %% "circe" % "2.2.1", //2.1.4
    "io.circe" %% "circe-generic" % "0.14.0-M1", // 0.12.3
    "com.github.mlangc" %% "slf4zio" % "1.0.0-RC21"
  ),
  libraryDependencies ++= Seq(
    com.github.alexarchambault.`scalacheck-shapeless_1.14`,
    org.scalacheck.scalacheck,
    org.scalatest.scalatest,
    org.scalatestplus.`scalacheck-1-14`,
    org.typelevel.`discipline-scalatest`
  ).map(_ % Test)
)
