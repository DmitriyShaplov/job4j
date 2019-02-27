package ru.job4j.search;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Inserts task to desired position.
     * Position determinate by the priority field.
     * @param task task
     */
    public void put(Task task) {
        for (int index = 0; index <= tasks.size(); index++) {
            if (index == tasks.size()
                    || tasks.get(index).getPriority() > task.getPriority()) {
                tasks.add(index, task);
                break;
            }
        }
    }

    /**
     * Retrieves and removes the head of this list.
     * @return head task
     */
    public Task take() {
        return this.tasks.poll();
    }
}
