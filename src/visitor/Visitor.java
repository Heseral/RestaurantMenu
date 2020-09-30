package visitor;

import restaurant.food.dish.Dish;
import restaurant.food.ingredient.Ingredient;
import util.Random;

import java.util.ArrayList;
import java.util.List;

public class Visitor {
    // пожелания посетителя в виде списка классов-категорий блюд
    private List<Class<? extends Dish>> wishes;
    // ограничения в блюдах в виде классов ингредиентов блюд
    private List<Class<? extends Ingredient>> restrictions = new ArrayList<>();
    // свободное время, которое посетитель готов подождать до приготовления блюда
    private int freeTime;
    // деньги посетителя
    private int cash;
    // уже сделанный и ожидаемый заказ
    private Order order = null;
    // имя клиента
    private String name = "Нулл Андефайнед Нуллович";

    public Visitor(List<Class<? extends Ingredient>> restrictions, List<Class<? extends Dish>> wishes, int freeTime, int cash) {
        setRestrictions(restrictions);
        setWishes(wishes);
        setFreeTime(freeTime);
        setCash(cash);
        setOrder(new Order(this));
    }

    public Visitor() {
        setOrder(new Order(this));
        setCash(Random.random(300, 10000));
        setFreeTime(Random.random(5, 120));
    }

    public List<Class<? extends Dish>> getWishes() {
        return wishes;
    }

    public void setWishes(List<Class<? extends Dish>> wishes) {
        this.wishes = wishes;
    }

    public List<Class<? extends Ingredient>> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(List<Class<? extends Ingredient>> restrictions) {
        this.restrictions = restrictions;
    }

    public int getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(int freeTime) {
        this.freeTime = freeTime;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
