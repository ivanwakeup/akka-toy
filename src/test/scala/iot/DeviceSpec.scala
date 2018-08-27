package iot

import akka.actor.{ActorSystem, PoisonPill}
import akka.testkit.{TestKit, TestProbe}
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FlatSpecLike, Matchers}

class DeviceSpec extends TestKit(ActorSystem()) with Matchers with FlatSpecLike with BeforeAndAfterEach with BeforeAndAfterAll {


  val probe = TestProbe()
  val deviceActor = system.actorOf(Device.props("group", "device"))

  override def beforeEach() = {
    deviceActor ! PoisonPill
  }

  override def afterAll() = {
    TestKit.shutdownActorSystem(system)
  }

  "A Device Actor" should "reply with empty reading if no temperature is known"  in {
    deviceActor.tell(Device.ReadTemperature(requestId = 42), probe.ref)
    val response = probe.expectMsgType[Device.RespondTemperature]
    response.requestId should ===(42)
    response.value should ===(None)
  }

  it should "reply with latest temperature reading" in {

  }

}
