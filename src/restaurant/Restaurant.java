package restaurant;

import restaurant.food.Food;
import java.util.List;

public class Restaurant {
    /*
    String name;
    String location;
    ...features...
     */
    private List<Food> availableFood;

    public List<Food> getAvailableFood() {
        return availableFood;
    }

    public void setAvailableFood(List<Food> availableFood) {
        this.availableFood = availableFood;
    }
}
