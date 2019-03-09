package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NodeListTest {

    @Test
    public void whenAddNodesWithCycleThenTrue() {
        NodeList.Node first = new NodeList.Node<>(1);
        NodeList.Node two = new NodeList.Node<>(2);
        NodeList.Node third = new NodeList.Node<>(3);
        NodeList.Node four = new NodeList.Node<>(4);
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;
        NodeList nodeList = new NodeList();
        assertThat(nodeList.hasCycle(first), is(true));
    }

    @Test
    public void whenAddNodesWithCycleInMiddleThenTrue() {
        NodeList.Node first = new NodeList.Node<>(1);
        NodeList.Node two = new NodeList.Node<>(2);
        NodeList.Node third = new NodeList.Node<>(3);
        NodeList.Node four = new NodeList.Node<>(4);
        first.next = two;
        two.next = third;
        third.next = two;
        four.next = first;
        NodeList nodeList = new NodeList();
        assertThat(nodeList.hasCycle(first), is(true));
    }

    @Test
    public void whenAddNodesNoCycleThenFalse() {
        NodeList.Node first = new NodeList.Node<>(1);
        NodeList.Node two = new NodeList.Node<>(2);
        NodeList.Node third = new NodeList.Node<>(3);
        NodeList.Node four = new NodeList.Node<>(4);
        first.next = two;
        two.next = third;
        third.next = two;
        four.next = first;
        NodeList nodeList = new NodeList();
        assertThat(nodeList.hasCycle(first), is(true));
    }
}