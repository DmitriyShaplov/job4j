package ru.shaplov.job4j.patterns.electronicwaiter;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author shaplov
 * @since 22.02.2020
 */
@Service
public class Kitchen implements OrderPublisher {

    private BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
    private Map<Order, List<OrderSubscriber>> subscribers = new ConcurrentHashMap<>();

    /**
     * @param order заказ с описанием и пользователем.
     */
    private void putOrder(Order order) {
        orderQueue.offer(order);
    }

    /**
     * Забирает заказ на приготовление
     * @return Заказ для пригтовления.
     * @throws InterruptedException possible ex.
     */
    public Order takeOrder() throws InterruptedException {
        return orderQueue.take();
    }

    @Override
    public void subscribe(Order order, OrderSubscriber subscriber) {
        subscribers.putIfAbsent(order, new CopyOnWriteArrayList<>());
        subscribers.get(order).add(subscriber);
        putOrder(order);
        subscriber.reactOnOrderAccepted(order);
    }

    @Override
    public void unsubscribe(Order order, OrderSubscriber subscriber) {
        subscribers.get(order).remove(subscriber);
    }

    /**
     * Уведомляет клиента о готовности заказа.
     * Убираем его, он больше не нужен.
     * @param order заказ.
     */
    public void orderReady(Order order) {
        subscribers.get(order).forEach((v) -> v.reactOnOrderIsReady(order));
        subscribers.remove(order);
    }

    @Override
    public String toString() {
        return "Im kitchen!";
    }
}
