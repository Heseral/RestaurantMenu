package util.timer_tasks;

import restaurant.Restaurant;
import restaurant.RestaurantService;
import visitor.Visitor;
import visitor.VisitorService;

public class TimerTaskVisitor extends ModifiedTimerTask {
    private RestaurantService restaurantService;
    private VisitorService visitorService;
    private Restaurant restaurant;

    public TimerTaskVisitor(long period, RestaurantService restaurantService, VisitorService visitorService, Restaurant restaurant) {
        super(period);
        setRestaurantService(restaurantService);
        setVisitorService(visitorService);
        setRestaurant(restaurant);
    }

    @Override
    public void run() {
        Visitor visitor = new Visitor();
        getVisitorService().createPartiallyRandomOrder(visitor, visitor.getWishes(), getRestaurantService(), getRestaurant());
        System.out.println("      NEW ORDER: " + visitor + " сделал новый заказ.");
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
