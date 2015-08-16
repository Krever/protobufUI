package protobufui.service.source.loaders

import java.io.File

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActorRef, TestKit, TestProbe}
import org.scalatest.{Matchers, WordSpecLike, _}
import protobufui.service.source.ClassesLoader.Put
import protobufui.service.source.{ClassesContainer, Load, Loaded}

import scala.concurrent.duration.Duration

class ProtoLoaderSpec(_system: ActorSystem) extends TestKit(_system) with WordSpecLike with Matchers with ImplicitSender {

     def this() = this(ActorSystem("ProtoLoaderSpec"))

     def fixture = new {
       val parent = TestProbe()
       val props = Props(classOf[ProtoLoader])
       val protoFile = new File("src/test/resources/protobufui/service/source/proto/PersonTest.proto")
     }

  "ProtoLoaderSpec" should {
     "compile proto and send request to its parent" in {
       // arrange
       val f = fixture
       val protoLoader = TestActorRef(f.props, f.parent.ref, "ProtoLoaderSpec")
       // act
       protoLoader ! Load(f.protoFile)
       val receivedMsg: AnyRef = f.parent.receiveOne(Duration(2000, "millis"))
       val msg = receivedMsg.asInstanceOf[Put]
       val extractedFileName: String = msg.file.listFiles()
         .filter(
           file => file.getName.equals("test")
         )
         .head.listFiles()
         .head.getName
       // assert
       Assertions.assert("PersonTest.java".equals(extractedFileName))
     }
   }
}
