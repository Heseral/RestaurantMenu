package util.timer_tasks;

import restaurant.RestaurantService;
import restaurant.food.dish.Dish;
import util.GlobalVar;
import visitor.Visitor;

public class TimerTaskCooking extends ModifiedTimerTask {
    private Dish dish;
    private Visitor visitor;
    private RestaurantService restaurantService;

    public TimerTaskCooking(long delayMillis, long periodMillis, Dish dish, Visitor visitor, RestaurantService restaurantService) {
        super(delayMillis, periodMillis);
        setDish(dish);
        setVisitor(visitor);
        setRestaurantService(restaurantService);
        GlobalVar.COOKING_LIST.add(this);
    }

    @Override
    public void run() {
        getRestaurantService().onDishCooked(getDish(), getVisitor());
        GlobalVar.COOKING_LIST.remove(this);
        cancel();
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
