package util.timer_tasks;

import model.Restaurant;
import model.Visitor;
import service.RestaurantService;
import service.VisitorService;
import util.GlobalVar;

import java.util.ArrayList;

public class TaskController implements Runnable {
    private int currentTime = 0;
    private ArrayList<TaskCooking> tasksCooking = new ArrayList<>();
    private ArrayList<TaskWaiting> tasksWaiting = new ArrayList<>();
    private transient Restaurant restaurant;
    private VisitorService visitorService;
    private RestaurantService restaurantService;
    private transient boolean processing = true;

    public TaskController(Restaurant restaurant, VisitorService visitorService, RestaurantService restaurantService) {
        setRestaurant(restaurant);
        setRestaurantService(restaurantService);
        setVisitorService(visitorService);
    }

    public TaskController() {}

    public int getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public void run() {
        while (processing) {
            GlobalVar.isSafeToSave = false;
            System.out.println(">>>>> " + (getCurrentTime() + 1));

            if (getCurrentTime() % 15 == 0) {
                Visitor visitor = new Visitor();
                getVisitorService().createPartiallyRandomOrder(
                        visitor,
                        visitor.getWishes(),
                        getRestaurantService(),
                        getRestaurant(),
                        this
                );
                System.out.println("      NEW ORDER: " + visitor + " сделал новый заказ.");
            }

            if (getCurrentTime() % 60 == 0) {
                getRestaurantService().resupplyIngredientsRandomly(getRestaurant());
                System.out.println("      RESUPPLY: новые ингредиенты были поставлены в ресторан.");
            }

            setCurrentTime(getCurrentTime() + 1);

            for (int i = getTasksCooking().size() - 1; i >= 0; i--) {
                if (getTasksCooking().get(i).getLaunchTime() == getCurrentTime()) {
                    getTasksCooking().get(i).run();
                }
            }
            for (int i = getTasksWaiting().size() - 1; i >= 0; i--) {
                if (getTasksWaiting().get(i).getLaunchTime() == getCurrentTime()) {
                    getTasksWaiting().get(i).run();
                }
            }
            GlobalVar.isSafeToSave = true;
            try {
                Thread.sleep(GlobalVar.SECOND);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public VisitorService getVisitorService() {
        return visitorService;
    }

    public void setVisitorService(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    public RestaurantService getRestaurantService() {
        return restaurantService;
    }

    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    public boolean isProcessing() {
        return processing;
    }

    public void setProcessing(boolean processing) {
        this.processing = processing;
    }

    public ArrayList<TaskCooking> getTasksCooking() {
        return tasksCooking;
    }

    public void setTasksCooking(ArrayList<TaskCooking> tasksCooking) {
        this.tasksCooking = tasksCooking;
    }

    public ArrayList<TaskWaiting> getTasksWaiting() {
        return tasksWaiting;
    }

    public void setTasksWaiting(ArrayList<TaskWaiting> tasksWaiting) {
        this.tasksWaiting = tasksWaiting;
    }
}
