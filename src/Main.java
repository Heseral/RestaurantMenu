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
                GlobalVar.SECOND,
                restaurant.get(),
                restaurantService,
                visitorService
        ));

        GlobalVar.timer.scheduleAtFixedRate(timerTaskTime.get(), timerTaskTime.get().getLaunchTimeMillis(), timerTaskTime.get().getPeriodMillis());

        JFrame mainFrame = new JFrame();
        JPanel panel = new JPanel();
        Button serializeButton = new Button("Сохранить");
        serializeButton.addActionListener(actionEvent -> {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                FileWriter fileWriter = new FileWriter("json.json");
                timerTaskTime.get().setCurrentTimeMillis(currentTimeMillis);
                timerTaskTime.get().setLaunchTimeMillis(timerTaskTime.get().scheduledExecutionTime());
                GlobalVar.GSON.toJson(restaurant.get(), fileWriter);
                GlobalVar.GSON.toJson(GlobalVar.timeWrapper, fileWriter);
                GlobalVar.GSON.toJson(timerTaskTime.get(), fileWriter);

                for (TimerTaskCooking timerTaskCooking : GlobalVar.cookingList) {
                    timerTaskCooking.setCurrentTimeMillis(currentTimeMillis);
                    timerTaskCooking.setLaunchTimeMillis(timerTaskCooking.scheduledExecutionTime());
                }
                GlobalVar.GSON.toJson(GlobalVar.cookingList, fileWriter);
                for (TimerTaskWaiting timerTaskWaiting : GlobalVar.waitingList) {
                    timerTaskWaiting.setCurrentTimeMillis(currentTimeMillis);
                    timerTaskWaiting.setLaunchTimeMillis(timerTaskWaiting.scheduledExecutionTime());
                }
                GlobalVar.GSON.toJson(GlobalVar.waitingList, fileWriter);

                fileWriter.flush();

                System.out.println(">>>>>>>>>> Сохранено на момент времени: " + GlobalVar.timeWrapper.getTime());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        panel.add(serializeButton);
        Button deserializeButton = new Button("Загрузить");
        deserializeButton.addActionListener(actionEvent -> {
            try {
                GlobalVar.timer = new Timer();
                JsonReader fileReader = new JsonReader(new FileReader("json.json"));
                fileReader.setLenient(true);
                restaurant.set(GlobalVar.GSON.fromJson(fileReader, Restaurant.class));
                GlobalVar.timeWrapper = GlobalVar.GSON.fromJson(fileReader, TimeWrapper.class);

                TimerTaskTime modifiedTimerTask = GlobalVar.GSON.fromJson(fileReader, TimerTaskTime.class);
                timerTaskTime.get().cancel();
                timerTaskTime.set(modifiedTimerTask.clone());

                GlobalVar.timer = new Timer();
                GlobalVar.timer.scheduleAtFixedRate(
                        timerTaskTime.get(),
                        Math.abs(timerTaskTime.get().getCurrentTimeMillis() - timerTaskTime.get().getLaunchTimeMillis()),
                        timerTaskTime.get().getPeriodMillis()
                );

                ArrayList<TimerTaskCooking> timerTaskCookingList = GlobalVar.GSON.fromJson(fileReader,
                        new TypeToken<ArrayList<TimerTaskCooking>>() {
                        }.getType());
                ArrayList<TimerTaskWaiting> timerTaskWaitingList = GlobalVar.GSON.fromJson(fileReader,
                        new TypeToken<ArrayList<TimerTaskWaiting>>() {
                        }.getType());
                GlobalVar.cookingList.forEach(ModifiedTimerTask::cancel);
                GlobalVar.cookingList = new ArrayList<>();
                GlobalVar.waitingList.forEach(ModifiedTimerTask::cancel);
                GlobalVar.waitingList = new ArrayList<>();

                for (TimerTaskCooking timerTaskCooking : timerTaskCookingList) {
                    // великолепная конструкция. Избавляемся от поля state, которое не позволяет запустить таск снова!
                    timerTaskCooking = timerTaskCooking.clone();
                    GlobalVar.timer.schedule(
                            timerTaskCooking,
                            Math.abs(timerTaskCooking.getLaunchTimeMillis() - timerTaskCooking.getCurrentTimeMillis())
                    );
                }
                for (TimerTaskWaiting timerTaskWaiting : timerTaskWaitingList) {
                    // великолепная конструкция. Избавляемся от поля state, которое не позволяет запустить таск снова!
                    timerTaskWaiting = timerTaskWaiting.clone();
                    GlobalVar.timer.schedule(
                            timerTaskWaiting,
                            Math.abs(timerTaskWaiting.getLaunchTimeMillis() - timerTaskWaiting.getCurrentTimeMillis())
                    );
                }

                System.out.println(">>>>>>>>>> Загружено на момент времени: " + GlobalVar.timeWrapper.getTime());
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
