package util.timer_tasks;

import restaurant.Restaurant;
import restaurant.RestaurantService;
import restaurant.food.dish.Dish;
import util.GlobalVar;
import visitor.Visitor;
import visitor.VisitorService;

public class TimerTaskWaiting extends ModifiedTimerTask {
    private Restaurant restaurant;
    private Visitor visitor;
    private RestaurantService restaurantService;
    private VisitorService visitorService;
    private Dish dish;

    public TimerTaskWaiting(
            Restaurant restaurant,
            Visitor visitor,
            RestaurantService restaurantService,
            VisitorService visitorService,
            Dish dish
    ) {
        super(-1);
        setRestaurant(restaurant);
        setVisitorService(visitorService);
        setVisitor(visitor);
        setRestaurantService(restaurantService);
        setDish(dish);
        GlobalVar.waitingList.add(this);
    }

    @Override
    public void run() {
        getRestaurantService().handleOrderedDish(getRestaurant(), getDish(), getVisitor(), getVisitorService());
        GlobalVar.waitingList.remove(this);
        cancel();
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
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

    public VisitorService getVisitorService() {
        return visitorService;
    }

    public void setVisitorService(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }
}
