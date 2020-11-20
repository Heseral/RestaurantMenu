package util.timer_tasks;

import model.Restaurant;
import model.Visitor;
import model.food.dish.Dish;
import service.RestaurantService;

public class TaskCooking extends Task {
    private Dish dish;
    private Visitor visitor;
    private RestaurantService restaurantService;
    private transient Restaurant restaurant;

    public TaskCooking(
            Dish dish,
            Visitor visitor,
            RestaurantService restaurantService,
            Restaurant restaurant,
            int launchTime,
            TaskController controlledBy
    ) {
        super(launchTime, controlledBy);
        setDish(dish);
        setVisitor(visitor);
        setRestaurantService(restaurantService);
        setRestaurant(restaurant);
        getControlledBy().getTasksCooking().add(this);
    }

    @Override
    public void run() {
        getRestaurantService().onDishCooked(getDish(), getVisitor());
        getControlledBy().getTasksCooking().remove(this);
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
