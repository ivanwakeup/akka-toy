package com.example

import akka.actor.{ActorSystem, Props}

object Main extends App {
  val system = ActorSystem("StartStop")


  failureActors()

  def startStop(): Unit = {

    val actor1 = system.actorOf(Props[StartStopActor1], "actor1")

    actor1 ! "stop"

  }

  def failureActors(): Unit = {
    val actor1 = system.actorOf(Props[SupervisingActor])

    actor1 ! "failChild"
  }

}


