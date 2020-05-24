package ru.shaplov.job4j.patterns.electronicwaiter.ui;

import org.springframework.stereotype.Service;
import ru.shaplov.job4j.patterns.electronicwaiter.User;
import ru.shaplov.job4j.patterns.electronicwaiter.factory.MainTypeFoodFactory;

@Service
public class RestaurantUIImpl implements RestaurantUI {

    private final static String LN = System.lineSeparator();

    private final MainTypeFoodFactory mainFactory;

    public RestaurantUIImpl(MainTypeFoodFactory mainFactory) {
        this.mainFactory = mainFactory;
    }

    @Override
    public void drawMenu() {
        StringBuilder sb = new StringBuilder();
        sb
                .append("/************************************/").append(LN)
                .append("Welcome to our electronic restaurant!").append(LN)
                .append("/************************************/").append(LN)
                .append("Menu:").append(LN);
        mainFactory.getFactories().forEach((key, value) -> {
                    sb.append(key).append(":").append(LN);
                    value.getOrderFactories().forEach(
                            (key1, value1) -> {
                                sb.append("\t").append(key1).append(":").append(LN);
                                sb.append("\t\tБлюда:").append(LN);
                                value1.getItems().forEach((name, price) -> {
                                    sb.append("\t\t\t").append(name).append(" ").append(price).append(" руб.").append(LN);
                                });
                                sb.append("\t\tДобавки:").append(LN);
                                value1.getAdditives().forEach((name, price) -> {
                                    sb.append("\t\t\t").append(name).append(" ").append(price).append(" руб.").append(LN);
                                });
                            });
                }
        );
        System.out.println(sb.toString());
    }

    public void printInitMessage(User user) {
        StringBuilder sb = new StringBuilder();
        sb
                .append("Приветствуем, ").append(user.getId()).append("!").append(LN)
                .append("Для того, чтобы сделать заказ").append(LN)
                .append("выберите тип кухни, затем тип блюда, его название, и добавки, если нужно,").append(LN)
                .append("разделяя пункты запятыми. Пример ввода:").append(LN)
                .append("\"Итальянская:Десерт:Тирамису:Сироп:Топпинг\"").append(LN)
                .append("Введите заказ: ");
        System.out.print(sb.toString());
    }
}
