package model;

import model.food.dish.Dish;

import java.util.ArrayList;
import java.util.List;

public class Order {
    // список успешно заказанных блюд
    private List<Dish> orderedDishes = new ArrayList<>();
    // список приготовленных блюд из списка заказанных блюд
    private List<Dish> servedDishes = new ArrayList<>();
    // во сколько обойдется заказ
    private int totalPrice = 0;
    // кто сделал заказ
    private transient Visitor orderedBy = null; // TODO: при десериализации засетать эту переменную используя виситора

    public Order(Visitor orderedBy) {
        setOrderedBy(orderedBy);
    }

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

    public Visitor getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(Visitor orderedBy) {
        this.orderedBy = orderedBy;
    }

    public List<Dish> getServedDishes() {
        return servedDishes;
    }

    public void setServedDishes(List<Dish> servedDishes) {
        this.servedDishes = servedDishes;
    }
}
