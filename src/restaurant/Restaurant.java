package restaurant;

import restaurant.food.dish.Dish;
import java.util.List;

public class Restaurant {
    /*
    String name;
    String location;
    ...features...
     */
    private List<Dish> availableDish;

    public List<Dish> getAvailableDish() {
        return availableDish;
    }

    public void setAvailableDish(List<Dish> availableDish) {
        this.availableDish = availableDish;
    }
}
