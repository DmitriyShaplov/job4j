package ru.job4j.list;

/**
 * @author shaplov
 * @since 09.03.2019
 */
public class NodeList {

    public static class Node<T> {
        T value;
        Node<T> next;
    }

    public boolean hasCycle(Node first) {
        boolean result = false;
        Node curNode = first;
        while(curNode.next != null) {
            curNode.value = true;
            curNode = curNode.next;
            if (curNode.value != null && curNode.value.equals(true)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
