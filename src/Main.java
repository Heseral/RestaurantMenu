import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import restaurant.Restaurant;
import restaurant.RestaurantService;
import util.GlobalVar;
import util.TimeWrapper;
import util.timer_tasks.*;
import visitor.VisitorService;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicReference;

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
        AtomicReference<Restaurant> restaurant = new AtomicReference<>(new Restaurant());
        RestaurantService restaurantService = new RestaurantService();

        restaurantService.setDefaultRestaurantMenu(restaurant.get());
        restaurantService.setDefaultRestaurantCombinationsSales(restaurant.get());
        restaurantService.setDefaultRestaurantIngredientsRandomly(restaurant.get());

        VisitorService visitorService = new VisitorService();

        AtomicReference<TimerTaskTime> timerTaskTime = new AtomicReference<>(new TimerTaskTime(
                GlobalVar.SECOND
        ));
        AtomicReference<TimerTaskVisitor> timerTaskVisitor = new AtomicReference<>(new TimerTaskVisitor(
                15 * GlobalVar.SECOND,
                restaurantService,
                visitorService,
                restaurant.get()
        ));
        AtomicReference<TimerTaskResupply> timerTaskResupply = new AtomicReference<>(new TimerTaskResupply(
                120 * GlobalVar.SECOND,
                restaurantService,
                restaurant.get()
        ));


        GlobalVar.timer.schedule(timerTaskTime.get(), timerTaskTime.get().getLaunchTimeMillis(), timerTaskTime.get().getPeriodMillis());
        GlobalVar.timer.schedule(timerTaskVisitor.get(), timerTaskVisitor.get().getLaunchTimeMillis(), timerTaskVisitor.get().getPeriodMillis());
        GlobalVar.timer.schedule(timerTaskResupply.get(), timerTaskResupply.get().getLaunchTimeMillis(), timerTaskResupply.get().getPeriodMillis());

        JFrame mainFrame = new JFrame();
        JPanel panel = new JPanel();
        Button serializeButton = new Button("Сериализовать");
        serializeButton.addActionListener(actionEvent -> {
            try {
                FileWriter fileWriter = new FileWriter("json.json");
                timerTaskTime.get().setCurrentTimeMillis(System.currentTimeMillis());
                timerTaskVisitor.get().setCurrentTimeMillis(System.currentTimeMillis());
                timerTaskResupply.get().setCurrentTimeMillis(System.currentTimeMillis());
                timerTaskTime.get().setLaunchTimeMillis(timerTaskTime.get().scheduledExecutionTime());
                timerTaskVisitor.get().setLaunchTimeMillis(timerTaskVisitor.get().scheduledExecutionTime());
                timerTaskResupply.get().setLaunchTimeMillis(timerTaskResupply.get().scheduledExecutionTime());
                for (TimerTaskWaiting timerTaskWaiting : GlobalVar.waitingList) {
                    timerTaskWaiting.setLaunchTimeMillis(timerTaskWaiting.scheduledExecutionTime());
                }
                for (TimerTaskCooking timerTaskCooking : GlobalVar.cookingList) {
                    timerTaskCooking.setLaunchTimeMillis(timerTaskCooking.scheduledExecutionTime());
                }
                GlobalVar.GSON.toJson(restaurant, fileWriter);
                GlobalVar.GSON.toJson(timeWrapper, fileWriter);
                GlobalVar.GSON.toJson(timerTaskTime, fileWriter);
                GlobalVar.GSON.toJson(timerTaskVisitor, fileWriter);
                GlobalVar.GSON.toJson(timerTaskResupply, fileWriter);
                GlobalVar.GSON.toJson(GlobalVar.cookingList, fileWriter);
                GlobalVar.GSON.toJson(GlobalVar.waitingList, fileWriter);
                fileWriter.flush();

                System.out.println(">>>>>>>>>> Сохранено на момент времени: " + timeWrapper.getTime());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        panel.add(serializeButton);
        Button deserializeButton = new Button("Десеарилозвать");
        deserializeButton.addActionListener(actionEvent -> {
            try {
                JsonReader fileReader = new JsonReader(new FileReader("json.json"));
                fileReader.setLenient(true);
                restaurant.set(GlobalVar.GSON.fromJson(fileReader, Restaurant.class));
                timeWrapper = GlobalVar.GSON.fromJson(fileReader, TimeWrapper.class);
                timerTaskTime.set(GlobalVar.GSON.fromJson(fileReader, TimerTaskTime.class));
                timerTaskVisitor.set(GlobalVar.GSON.fromJson(fileReader, TimerTaskVisitor.class));
                timerTaskResupply.set(GlobalVar.GSON.fromJson(fileReader, TimerTaskResupply.class));
                // todo НОРМАЛЬНО десеарилзовать списки, пушто вылетает рантайм экспешн
                GlobalVar.cookingList = GlobalVar.GSON.fromJson(fileReader,
                        new TypeToken<ArrayList<TimerTaskCooking>>() {
                        }.getType());
                GlobalVar.waitingList = GlobalVar.GSON.fromJson(fileReader,
                        new TypeToken<ArrayList<TimerTaskWaiting>>() {
                        }.getType());

                GlobalVar.timer = new Timer();
                // todo и тут что то не так. Какого черта тут период отрицательный, что не так?
                GlobalVar.timer.schedule(
                        timerTaskTime.get(),
                        timerTaskTime.get().getLaunchTimeMillis() - timerTaskTime.get().getCurrentTimeMillis(),
                        timerTaskTime.get().getPeriodMillis()
                );
                GlobalVar.timer.schedule(
                        timerTaskVisitor.get(),
                        timerTaskVisitor.get().getLaunchTimeMillis() - timerTaskVisitor.get().getCurrentTimeMillis(),
                        timerTaskVisitor.get().getPeriodMillis()
                );
                GlobalVar.timer.schedule(
                        timerTaskResupply.get(),
                        timerTaskResupply.get().getLaunchTimeMillis() - timerTaskResupply.get().getCurrentTimeMillis(),
                        timerTaskResupply.get().getPeriodMillis()
                );
                for (TimerTaskCooking timerTaskCooking : GlobalVar.cookingList) {
                    GlobalVar.timer.schedule(
                            timerTaskCooking,
                            timerTaskCooking.getLaunchTimeMillis() - timerTaskCooking.getCurrentTimeMillis()
                    );
                }
                for (TimerTaskWaiting timerTaskWaiting : GlobalVar.waitingList) {
                    GlobalVar.timer.schedule(
                            timerTaskWaiting,
                            timerTaskWaiting.getLaunchTimeMillis() - timerTaskWaiting.getCurrentTimeMillis()
                    );
                }

                System.out.println(">>>>>>>>>> Загружено на момент времени: " + timeWrapper.getTime());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        panel.add(deserializeButton);

        mainFrame.add(panel);
        mainFrame.setSize(300, 100);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}
