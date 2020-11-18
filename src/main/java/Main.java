import com.google.gson.stream.JsonReader;
import model.Restaurant;
import service.RestaurantService;
import util.GlobalVar;
import util.timer_tasks.*;
import service.VisitorService;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;

/*
    9. Меню ресторана, скидки. Дано меню ресторана, которое содержит набор
    различных блюд - напитки, салаты, закуски и т.д. Каждое блюдо имеет
    стоимость, время приготовления, категорию, ограничения. Для некоторых
    сочетаний блюд могут быть даны скидки. Посетители ресторана могут
    заказывать блюда исходя из своих пожеланий, наличия свободного времени
    и денежных средств.
 */
// todo переделать склад ингредиентов ресторана как хешсет енамов
public class Main {
    public static void main(String[] args) {
        AtomicReference<Restaurant> restaurant = new AtomicReference<>(new Restaurant());
        RestaurantService restaurantService = new RestaurantService();

        restaurantService.setDefaultRestaurantMenu(restaurant.get());
        restaurantService.setDefaultRestaurantCombinationsSales(restaurant.get());
        restaurantService.setDefaultRestaurantIngredientsRandomly(restaurant.get());

        VisitorService visitorService = new VisitorService();

        AtomicReference<TaskController> taskController = new AtomicReference<>(
                new TaskController(restaurant.get(), visitorService, restaurantService)
        );
        new java.util.Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                JFrame mainFrame = new JFrame();
                JPanel panel = new JPanel();
                Button serializeButton = new Button("Сохранить");
                serializeButton.addActionListener(actionEvent -> {
                    if (!GlobalVar.isSafeToSave) {
                        System.out.println("$$$$$ Что-то пошло не так. Попобуйте еще раз.");
                        return;
                    }
                    try {
                        FileWriter fileWriter = new FileWriter("json.json");
                        GlobalVar.GSON.toJson(taskController.get(), fileWriter);
                        GlobalVar.GSON.toJson(restaurant.get(), fileWriter);

                        fileWriter.flush();

                        System.out.println(">>>>>>>>>> Сохранено на момент времени: " + taskController.get().getCurrentTime());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                panel.add(serializeButton);
                Button deserializeButton = new Button("Загрузить");
                deserializeButton.addActionListener(actionEvent -> {
                    if (!GlobalVar.isSafeToSave) {
                        System.out.println("$$$$$ Что-то пошло не так. Попобуйте еще раз.");
                        return;
                    }
                    try {
                        JsonReader fileReader = new JsonReader(new FileReader("json.json"));
                        fileReader.setLenient(true);

                        taskController.get().setProcessing(false);
                        taskController.set(GlobalVar.GSON.fromJson(fileReader, TaskController.class));
                        System.out.println(">>>>>>>>>> Загружено на момент времени: " + taskController.get().getCurrentTime());
                        restaurant.set(GlobalVar.GSON.fromJson(fileReader, Restaurant.class));
                        taskController.get().setRestaurant(restaurant.get());
                        for (Task task : taskController.get().getTasksCooking()) {
                            task.setControlledBy(taskController.get());
                        }
                        for (Task task : taskController.get().getTasksWaiting()) {
                            task.setControlledBy(taskController.get());
                        }
                        taskController.get().setProcessing(true);
                        new Thread(taskController.get()).start();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                });
                panel.add(deserializeButton);

                mainFrame.add(panel);
                mainFrame.setSize(300, 100);
                mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                mainFrame.setVisible(true);
            }}, 0);

        taskController.get().

            run();
        }
    }
