package visitor;

import restaurant.food.dish.Dish;

import java.util.ArrayList;
import java.util.List;

public class Order {
    // список успешно заказанных блюд
    private List<Dish> orderedDishes = new ArrayList<>();
    // во сколько обойдется заказ
    private int totalPrice = 0;

    public List<Dish> getOrderedDishes() {
        return orderedDishes;
    }

    public void setOrderedDishes(List<Dish> orderedDishes) {
        this.orderedDishes = orderedDishes;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
