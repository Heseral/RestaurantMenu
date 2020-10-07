import com.google.gson.Gson;
import restaurant.Restaurant;
import restaurant.RestaurantService;
import util.GlobalVar;
import visitor.Visitor;
import visitor.VisitorService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.TimerTask;

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

        GlobalVar.TIMER.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(">>>>> " + GlobalVar.time++);
            }
        }, 0, GlobalVar.SECOND);
        GlobalVar.TIMER.schedule(new TimerTask() {
            @Override
            public void run() {
                Visitor visitor = new Visitor();
                visitorService.createPartiallyRandomOrder(visitor, visitor.getWishes(), restaurantService, restaurant);
                System.out.println("      NEW ORDER: " + visitor + " сделал новый заказ.");
            }
        }, 0, 15 * GlobalVar.SECOND);
        GlobalVar.TIMER.schedule(new TimerTask() {
            @Override
            public void run() {
                restaurantService.resupplyIngredientsRandomly(restaurant);
                System.out.println("      RESUPPLY: новые ингредиенты были поставлены в ресторан.");
            }
        }, 120 * GlobalVar.SECOND, 120 * GlobalVar.SECOND);



        JFrame mainFrame = new JFrame();
        JPanel panel = new JPanel();
        Button serializeButton = new Button("Сериализовать");
        serializeButton.addActionListener(actionEvent -> {
            try {
                GlobalVar.GSON.toJson(restaurant, new FileWriter("json.json"));
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
