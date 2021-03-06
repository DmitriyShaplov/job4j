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
        var index = (int) this.tasks.stream()
                .filter(
                        t -> t.getPriority() < task.getPriority()
                ).count();
        this.tasks.add(index, task);
    }

    /**
     * Retrieves and removes the head of this list.
     * @return head task
     */
    public Task take() {
        return this.tasks.poll();
    }
}
