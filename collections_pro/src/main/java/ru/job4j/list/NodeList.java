package ru.job4j.list;

/**
 * @author shaplov
 * @since 09.03.2019
 */
public class NodeList {

    public static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    public boolean hasCycle(Node first) {
        boolean result = false;
        Node curNode = first;
        Node faster = first;
        while (faster != null && faster.next != null) {
            curNode = curNode.next;
            faster = faster.next.next;
            if (faster == curNode) {
                result = true;
                break;
            }
        }
        return result;
    }
}
