package util.timer_tasks;

import restaurant.RestaurantService;
import restaurant.food.dish.Dish;
import util.GlobalVar;
import visitor.Visitor;

import java.util.Arrays;

public class TimerTaskCooking extends ModifiedTimerTask {
    private Dish dish;
    private Visitor visitor;
    private RestaurantService restaurantService;

    public TimerTaskCooking(Dish dish, Visitor visitor, RestaurantService restaurantService) {
        super(-1);
        setDish(dish);
        setVisitor(visitor);
        setRestaurantService(restaurantService);
        GlobalVar.cookingList.add(this);
        /*System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Добавлено " + dish);
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            System.out.println(stackTraceElement);
        }*/
    }

    @Override
    public void run() {
        GlobalVar.isSafeToSave = false;
        getRestaurantService().onDishCooked(getDish(), getVisitor());
        GlobalVar.cookingList.remove(this);
        cancel();
        GlobalVar.isSafeToSave = true;
    }

    @Override
    public TimerTaskCooking clone() {
        // да, я просто ссылочки устанавливаю. В рамках этого таска этого достаточно. Да, я знаю, что нужно клонировать
        // и то, что в скобочках!
        TimerTaskCooking result = new TimerTaskCooking(getDish(), getVisitor(), getRestaurantService());

        result.setCurrentTimeMillis(getCurrentTimeMillis());
        result.setLaunchTimeMillis(getLaunchTimeMillis());
        result.setPeriodMillis(getPeriodMillis());

        return result;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public RestaurantService getRestaurantService() {
        return restaurantService;
    }

    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }
}
