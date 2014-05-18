package com.inplaytime.graph

import com.tinkerpop.blueprints.Edge
import com.tinkerpop.blueprints.Vertex
import com.tinkerpop.gremlin.scala._
import com.tinkerpop.blueprints.impls.neo4j2.Neo4j2Graph

trait BaseGraph {
  val path: String
  val graph: Neo4j2Graph
}

trait MemberWriter {
  self: BaseGraph =>

  def addMember(location: String) = {
    val vertex = graph.addV()
    vertex.setProperty("name", location)
    vertex
  }

  def isFanOf(member: Vertex, fan: Vertex) = {
    graph.addE(fan, member, "LIKES")
  }
}

class GraphWriter extends BaseGraph with MemberWriter {
  val path: String = "data/graph2"
  val graph: Neo4j2Graph = new Neo4j2Graph(path)

  def createFansOfMember(member: String, fans: List[String]): (Vertex, List[Vertex]) = {
    val memberElement = addMember(member)
    val fanElements = fans.map { fan =>
    	val fanElement = addMember(fan)
    	isFanOf(memberElement, fanElement)
    	fanElement
    }

    (memberElement, fanElements)
  }
}