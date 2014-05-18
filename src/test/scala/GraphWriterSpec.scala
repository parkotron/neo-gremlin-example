package com.inplaytime.graph

import scala.collection.JavaConverters._

import com.tinkerpop.blueprints.Edge
import com.tinkerpop.blueprints.Vertex
import com.tinkerpop.gremlin.scala._

import org.scalatest.BeforeAndAfter
import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers

class GraphWriterBase extends FunSpec with ShouldMatchers with BeforeAndAfter {
  var writer: GraphWriter = _

  before {
    writer = new GraphWriter
  }

  after {
    writer.graph.getVertices.asScala.toList.map { v =>
      v.remove()
    }

    writer.graph.shutdown()
  }

  describe("A Graph Database") {
    it("should allow to store and retrieve data") {
      val (member:Vertex, fans) = writer.createFansOfMember("1111", "2222" :: "3333" :: "4444" :: Nil)
      writer.graph.graph.v(member.getId).inE("LIKES").outV.name.toSet should be (Set("2222", "3333", "4444"))
    }
  }
}
