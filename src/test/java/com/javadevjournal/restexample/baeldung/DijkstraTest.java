package com.javadevjournal.restexample.baeldung;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

	@Test
	public void whenSPPSolved_thenCorrect() {

		Node nodeA = new Node("A");
		Node nodeB = new Node("B");
		Node nodeC = new Node("C");
		Node nodeD = new Node("D");
		Node nodeE = new Node("E");
		Node nodeF = new Node("F");

		nodeA.addDestination(nodeB, 10);
		nodeA.addDestination(nodeC, 15);

		nodeB.addDestination(nodeD, 12);
		nodeB.addDestination(nodeF, 15);

		nodeC.addDestination(nodeE, 10);

		nodeD.addDestination(nodeE, 2);
		nodeD.addDestination(nodeF, 1);

		nodeF.addDestination(nodeE, 5);

		Graph graph = new Graph();

		graph.addNode(nodeA);
		graph.addNode(nodeB);
		graph.addNode(nodeC);
		graph.addNode(nodeD);
		graph.addNode(nodeE);
		graph.addNode(nodeF);

		Dijkstra.calculateShortestPathFromSource(graph, nodeA);

		List<Node> shortestPathForNodeB = List.of(nodeA);
		List<Node> shortestPathForNodeC = List.of(nodeA);
		List<Node> shortestPathForNodeD = Arrays.asList(nodeA, nodeB);
		List<Node> shortestPathForNodeE = Arrays.asList(nodeA, nodeB, nodeD);
		List<Node> shortestPathForNodeF = Arrays.asList(nodeA, nodeB, nodeD);

		for (Node node : graph.getNodes()) {
			switch (node.getName()) {
				case "B" -> assertEquals(node
						.getShortestPath(), shortestPathForNodeB);
				case "C" -> assertEquals(node
						.getShortestPath(), shortestPathForNodeC);
				case "D" -> assertEquals(node
						.getShortestPath(), shortestPathForNodeD);
				case "E" -> assertEquals(node
						.getShortestPath(), shortestPathForNodeE);
				case "F" -> assertEquals(node
						.getShortestPath(), shortestPathForNodeF);
			}
		}
	}
	@Test
	public void whenSPPSolved2_thenCorrect() {

		Node node0 = new Node("0");
		Node node1 = new Node("1");
		Node node2 = new Node("2");
		Node node3 = new Node("3");
		Node node4 = new Node("4");
		Node node5 = new Node("5");
		Node node6 = new Node("6");
		Node node7 = new Node("7");

		node0.addDestination(node1,2);
		node0.addDestination(node2,2);
		node0.addDestination(node4,2);

		node1.addDestination(node0,2);
		node1.addDestination(node2,2);
		node1.addDestination(node3,2);
		node1.addDestination(node6,5);

		node2.addDestination(node0,2);
		node2.addDestination(node1,2);
		node2.addDestination(node5,8);

		node3.addDestination(node1,2);
		node3.addDestination(node5,5);
		node3.addDestination(node7,3);

		node4.addDestination(node0,2);
		node4.addDestination(node6,9);
		node4.addDestination(node7,3);

		node5.addDestination(node2,8);
		node5.addDestination(node3,5);

		node6.addDestination(node1,5);
		node6.addDestination(node4,9);

		node7.addDestination(node3,3);
		node7.addDestination(node4,3);

		Graph graph = new Graph();

		graph.addNode(node0);
		graph.addNode(node1);
		graph.addNode(node2);
		graph.addNode(node3);
		graph.addNode(node4);
		graph.addNode(node5);
		graph.addNode(node6);
		graph.addNode(node7);

		Dijkstra.calculateShortestPathFromSource(graph, node6);

		List<Node> shortestPathForNode0 = Arrays.asList(node6, node1);
		List<Node> shortestPathForNode1 = List.of(node6);
		List<Node> shortestPathForNode2 = Arrays.asList(node6, node1);
		List<Node> shortestPathForNode3 = Arrays.asList(node6, node1);
		List<Node> shortestPathForNode4 = List.of(node6);
		List<Node> shortestPathForNode5 = Arrays.asList(node6, node1, node3);
		List<Node> shortestPathForNode7 = Arrays.asList(node6, node1, node3);

		for (Node node : graph.getNodes()) {
			switch (node.getName()) {
				case "0" -> assertEquals(node
						.getShortestPath(), shortestPathForNode0);
				case "1" -> assertEquals(node
						.getShortestPath(), shortestPathForNode1);
				case "2" -> assertEquals(node
						.getShortestPath(), shortestPathForNode2);
				case "3" -> assertEquals(node
						.getShortestPath(), shortestPathForNode3);
				case "4" -> assertEquals(node
						.getShortestPath(), shortestPathForNode4);
				case "5" -> assertEquals(node
						.getShortestPath(), shortestPathForNode5);
				case "7" -> assertEquals(node
						.getShortestPath(), shortestPathForNode7);
			}
		}
	}

	@Test
	void one_node_graph() {
		Node oneNode = new Node("A");
		Graph graph = new Graph();
		graph.addNode(oneNode);
		Dijkstra.calculateShortestPathFromSource(graph, oneNode);
		assertEquals(oneNode.getDistance(), 0);
	}


	@Test
	void two_nodes() {  // Не связанные между собой
		Node nodeA = new Node("A");
		Node nodeB = new Node("B");

		Graph graph = new Graph();

		graph.addNode(nodeA);
		graph.addNode(nodeB);

		Dijkstra.calculateShortestPathFromSource(graph, nodeA);

		assertEquals(nodeB.getDistance(), Integer.MAX_VALUE);
		assertEquals(nodeB.getShortestPath(), List.of());

	}


	@Test
	void node_loop() {
		Node nodeA = new Node("A");
		nodeA.addDestination(nodeA, 1);

		Graph graph = new Graph();

		graph.addNode(nodeA);

		Dijkstra.calculateShortestPathFromSource(graph, nodeA);

		assertEquals(nodeA.getShortestPath(), List.of());
	}
}