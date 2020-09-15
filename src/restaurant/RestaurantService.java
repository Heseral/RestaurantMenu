package restaurant;

import restaurant.food.Food;
import restaurant.food.FoodCategory;
import util.GlobalVar;

import java.util.ArrayList;
import java.util.List;

import static restaurant.food.FoodCategory.*;
import static restaurant.food.FoodType.*;

public class RestaurantService {
    public void fillRestaurantWithRandomFood(Restaurant restaurantToFill) {
        restaurantToFill.setAvailableFood(generateBasicFood());
    }
    public List<Food> generateBasicFood() {
        // todo
        GlobalVar.basicFood.add(new Food("Борщ", null, generateRandomPrice(), LIQUID | HOT, Soup, generateRandomTimeToCook()));
        /*
        GlobalVar.basicFood.add(new Food(, , generateRandomPrice(), , , generateRandomTimeToCook()));
        GlobalVar.basicFood.add(new Food(, , generateRandomPrice(), , , generateRandomTimeToCook()));
        GlobalVar.basicFood.add(new Food(, , generateRandomPrice(), , , generateRandomTimeToCook()));
        GlobalVar.basicFood.add(new Food(, , generateRandomPrice(), , , generateRandomTimeToCook()));
        GlobalVar.basicFood.add(new Food(, , generateRandomPrice(), , , generateRandomTimeToCook()));
        GlobalVar.basicFood.add(new Food(, , generateRandomPrice(), , , generateRandomTimeToCook()));
        GlobalVar.basicFood.add(new Food(, , generateRandomPrice(), , , generateRandomTimeToCook()));
        GlobalVar.basicFood.add(new Food(, , generateRandomPrice(), , , generateRandomTimeToCook()));
        GlobalVar.basicFood.add(new Food(, , generateRandomPrice(), , , generateRandomTimeToCook()));
        GlobalVar.basicFood.add(new Food(, , generateRandomPrice(), , , generateRandomTimeToCook()));
        GlobalVar.basicFood.add(new Food(, , generateRandomPrice(), , , generateRandomTimeToCook()));
        GlobalVar.basicFood.add(new Food(, , generateRandomPrice(), , , generateRandomTimeToCook()));
        GlobalVar.basicFood.add(new Food(, , generateRandomPrice(), , , generateRandomTimeToCook()));
        GlobalVar.basicFood.add(new Food(, , generateRandomPrice(), , , generateRandomTimeToCook()));
        GlobalVar.basicFood.add(new Food(, , generateRandomPrice(), , , generateRandomTimeToCook()));
        GlobalVar.basicFood.add(new Food(, , generateRandomPrice(), , , generateRandomTimeToCook()));
        GlobalVar.basicFood.add(new Food(, , generateRandomPrice(), , , generateRandomTimeToCook()));
        */
        return null;
    }

    public int generateRandomPrice() {
        return util.Random.pick(49, 99, 149, 199, 249, 299, 399, 499, 999);
    }

    public int generateRandomTimeToCook() {
        return util.Misc.round(util.Random.random(5, 120), 5);
    }

}
