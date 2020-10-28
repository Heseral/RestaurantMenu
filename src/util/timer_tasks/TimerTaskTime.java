package util.timer_tasks;

import restaurant.Restaurant;
import restaurant.RestaurantService;
import visitor.Visitor;
import visitor.VisitorService;

import static util.GlobalVar.timeWrapper;

public class TimerTaskTime extends ModifiedTimerTask {

    private Restaurant restaurant;
    private VisitorService visitorService;
    private RestaurantService restaurantService;

    public TimerTaskTime(long period, Restaurant restaurant, RestaurantService restaurantService, VisitorService visitorService) {
        super(period);
        setRestaurant(restaurant);
        setRestaurantService(restaurantService);
        setVisitorService(visitorService);
    }

    @Override
    public TimerTaskTime clone() {
        TimerTaskTime result = new TimerTaskTime(getPeriodMillis(), getRestaurant(), getRestaurantService(), getVisitorService());

        result.setCurrentTimeMillis(getCurrentTimeMillis());
        result.setLaunchTimeMillis(getLaunchTimeMillis());

        return result;
    }

    @Override
    public void run() {
        System.out.println(">>>>> " + (timeWrapper.getTime() + 1));

        if (timeWrapper.getTime() % 15 == 0) {
            Visitor visitor = new Visitor();
            getVisitorService().createPartiallyRandomOrder(visitor, visitor.getWishes(), getRestaurantService(), getRestaurant());
            System.out.println("      NEW ORDER: " + visitor + " сделал новый заказ.");
        }

        if (timeWrapper.getTime() % 60 == 0) {
            getRestaurantService().resupplyIngredientsRandomly(getRestaurant());
            System.out.println("      RESUPPLY: новые ингредиенты были поставлены в ресторан.");
        }
        timeWrapper.setTime(timeWrapper.getTime() + 1);
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
}
