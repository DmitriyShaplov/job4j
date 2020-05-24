package ru.shaplov.job4j.patterns.electronicwaiter;

import lombok.Setter;
import org.springframework.stereotype.Service;
import ru.shaplov.job4j.patterns.electronicwaiter.factory.MainTypeFoodFactory;
import ru.shaplov.job4j.patterns.electronicwaiter.factory.api.FoodTypeFactory;
import ru.shaplov.job4j.patterns.electronicwaiter.factory.api.OrderFactory;
import ru.shaplov.job4j.patterns.electronicwaiter.ui.RestaurantUI;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

@Service
public class Waiter {

    private static final String LN = System.lineSeparator();

    private final MainTypeFoodFactory foodFactory;
    private final Cook cook;
    private final Kitchen kitchen;
    private final RestaurantUI restaurantUI;

    @Setter
    private InputStream input = System.in;

    public Waiter(MainTypeFoodFactory foodFactory, Cook cook, Kitchen kitchen, RestaurantUI restaurantUI) {
        this.foodFactory = foodFactory;
        this.cook = cook;
        this.kitchen = kitchen;
        this.restaurantUI = restaurantUI;
    }

    public void start() {
        restaurantUI.drawMenu();
        cook.startCooking();
    }

    /**
     * Взаимодействие с пользователем.
     * @param user Пользователь, совершивший заказ.
     */
    public void startInteract(User user) {
        restaurantUI.printInitMessage(user);
        Scanner scanner = new Scanner(input);
        boolean correct = true;
        do {
            String userInput = scanner.nextLine();
            try {
                Order order = createOrderByString(userInput);
                user.setOrder(order);
                order.setUser(user);
                kitchen.subscribe(order, user);
                kitchen.subscribe(order, cook);
            } catch (Exception e) {
                System.out.println("Неверная строка заказа!");
                System.out.print("Введите заказ:");
                correct = false;
            }
        } while (!correct);
    }

    /**
     * Парсим строку и возвращаем заказ, созданный нашими фабриками.
     * @param userInput строка с токенами заказа, разделенная ":"
     * @return собранный заказ.
     */
    private Order createOrderByString(String userInput) {
        List<String> tokens = List.of(userInput.split(":"));
        if (tokens.size() < 3) {
            throw new IllegalArgumentException("Введите корректный заказ!");
        }
        FoodTypeFactory factoryByName = foodFactory.getFactoryByName(tokens.get(0));
        OrderFactory orderFactory = factoryByName.getOrderFactory(tokens.get(1));
        Order order = orderFactory.createOrder(tokens.get(2));
        for (int index = 3; index < tokens.size(); index++) {
            order = orderFactory.addAdditivity(order, tokens.get(index));
        }
        return order;
    }
}
