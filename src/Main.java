import restaurant.Restaurant;
import restaurant.RestaurantService;
import util.GlobalVar;
import util.timer_tasks.TimerTaskResupply;
import util.timer_tasks.TimerTaskTime;
import util.timer_tasks.TimerTaskVisitor;
import visitor.VisitorService;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

import static util.GlobalVar.timeWrapper;

/*
    9. Меню ресторана, скидки. Дано меню ресторана, которое содержит набор
    различных блюд - напитки, салаты, закуски и т.д. Каждое блюдо имеет
    стоимость, время приготовления, категорию, ограничения. Для некоторых
    сочетаний блюд могут быть даны скидки. Посетители ресторана могут
    заказывать блюда исходя из своих пожеланий, наличия свободного времени
    и денежных средств.
 */
public class Main {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        RestaurantService restaurantService = new RestaurantService();

        restaurantService.setDefaultRestaurantMenu(restaurant);
        restaurantService.setDefaultRestaurantCombinationsSales(restaurant);
        restaurantService.setDefaultRestaurantIngredientsRandomly(restaurant);

        VisitorService visitorService = new VisitorService();

        TimerTaskTime timerTaskTime = new TimerTaskTime(
                0,
                GlobalVar.SECOND
        );
        TimerTaskVisitor timerTaskVisitor = new TimerTaskVisitor(
                0,
                15 * GlobalVar.SECOND,
                restaurantService,
                visitorService,
                restaurant
        );
        TimerTaskResupply timerTaskResupply = new TimerTaskResupply(
                120 * GlobalVar.SECOND,
                120 * GlobalVar.SECOND,
                restaurantService,
                restaurant
        );


        GlobalVar.TIMER.schedule(timerTaskTime, timerTaskTime.getDelayMillis(), timerTaskTime.getPeriodMillis());
        GlobalVar.TIMER.schedule(timerTaskVisitor, timerTaskVisitor.getDelayMillis(), timerTaskVisitor.getPeriodMillis());
        GlobalVar.TIMER.schedule(timerTaskResupply, timerTaskResupply.getDelayMillis(), timerTaskResupply.getPeriodMillis());

        // TODO: десеарилацзия? Кажется уже можно

        JFrame mainFrame = new JFrame();
        JPanel panel = new JPanel();
        Button serializeButton = new Button("Сериализовать");
        serializeButton.addActionListener(actionEvent -> {
            try {
                FileWriter fileWriter = new FileWriter("json.json");
                GlobalVar.GSON.toJson(restaurant, fileWriter);
                GlobalVar.GSON.toJson(timeWrapper, fileWriter);
                GlobalVar.GSON.toJson(timerTaskTime, fileWriter);
                GlobalVar.GSON.toJson(timerTaskVisitor, fileWriter);
                GlobalVar.GSON.toJson(timerTaskResupply, fileWriter);
                GlobalVar.GSON.toJson(GlobalVar.COOKING_LIST, fileWriter);
                GlobalVar.GSON.toJson(GlobalVar.WAITING_LIST, fileWriter);
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        panel.add(serializeButton);
        Button deserializeButton = new Button("Десеарилозвать");
        deserializeButton.addActionListener(actionEvent -> {

        });
        panel.add(deserializeButton);

        mainFrame.add(panel);
        mainFrame.setSize(300, 100);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}
