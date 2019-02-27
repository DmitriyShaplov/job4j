package ru.job4j.search;

import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Inserts task to desired position.
     * Position determinate by the priority field.
     * @param task task
     */
    public void put(Task task) {
        this.tasks = Stream.concat(Stream.concat(tasks.stream().filter(
                t -> t.getPriority() < task.getPriority()
        ), Stream.of(task)),
                tasks.stream().filter(
                t -> t.getPriority() >= task.getPriority()
        )).collect(Collectors.toCollection(LinkedList::new));
//        for (int index = 0; index <= tasks.size(); index++) {
//            if (index == tasks.size()
//                    || tasks.get(index).getPriority() > task.getPriority()) {
//                tasks.add(index, task);
//                break;
//            }
//        }
    }

    /**
     * Retrieves and removes the head of this list.
     * @return head task
     */
    public Task take() {
        return this.tasks.poll();
    }
}
