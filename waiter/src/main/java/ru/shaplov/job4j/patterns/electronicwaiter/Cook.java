package ru.shaplov.job4j.patterns.electronicwaiter;

import lombok.Getter;
import org.springframework.stereotype.Service;

/**
 * Повар.
 */
@Service
public class Cook implements OrderSubscriber {

    /**
     * Кухня, с которой повар забирает заказы и готовит их.
     */
    private final Kitchen kitchen;
    @Getter
    private Thread thread;

    public Cook(Kitchen kitchen) {
        this.kitchen = kitchen;
    }

    /**
     * Запуск работы повара.
     */
    public void startCooking() {
        thread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Order order = kitchen.takeOrder();
                    cooking(order);
                    kitchen.orderReady(order);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        });
        thread.start();
    }


    /**
     * Имитация готовки.
     * @param order заказ.
     */
    private void cooking(Order order) {
        try {
            System.out.println("Готовим заказ... Для: " + order.getUser().getId());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        order.setReady();
    }

    @Override
    public String toString() {
        return "im cook!";
    }

    @Override
    public void reactOnOrderIsReady(Order order) {
        if (thread != null) {
            thread.interrupt();
        }
    }
}
