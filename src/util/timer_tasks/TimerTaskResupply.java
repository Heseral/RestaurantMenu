package util.timer_tasks;

import restaurant.Restaurant;
import restaurant.RestaurantService;

public class TimerTaskResupply extends ModifiedTimerTask {
    private RestaurantService restaurantService;
    private Restaurant restaurant;

    public TimerTaskResupply(long delay, long period, RestaurantService restaurantService, Restaurant restaurant) {
        super(delay, period);
        setRestaurantService(restaurantService);
        setRestaurant(restaurant);
    }

    @Override
    public void run() {
        getRestaurantService().resupplyIngredientsRandomly(getRestaurant());
        System.out.println("      RESUPPLY: новые ингредиенты были поставлены в ресторан.");
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
