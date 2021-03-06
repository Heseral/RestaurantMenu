package model;

import util.GlobalVar;
import util.Random;

import java.util.ArrayList;
import java.util.List;

public class Visitor {
    // пожелания посетителя в виде списка классов-категорий блюд
    private transient List<String> wishes;
    // ограничения в блюдах в виде классов ингредиентов блюд
    private transient List<String> restrictions = new ArrayList<>();
    // свободное время, которое посетитель готов подождать до приготовления блюда
    private int freeTime;
    // деньги посетителя
    private int cash;
    // уже сделанный и ожидаемый заказ
    private Order order = null;
    // имя клиента
    private String name;

    public Visitor(List<String> restrictions, List<String> wishes, int freeTime, int cash) {
        setRestrictions(restrictions);
        setWishes(wishes);
        setFreeTime(freeTime);
        setCash(cash);
        setOrder(new Order(this));
    }

    public Visitor() {
        setName("Visitor #" + hashCode());
        setOrder(new Order(this));
        setCash(Random.random(300, 10000));
        setFreeTime(Random.random(5, 120));
        List<String> desirableCategories = new ArrayList<>();
        for (String category : GlobalVar.DISH_CATEGORIES) {
            // вероятность заказа этой категории 50%
            if (Random.prob(50)) {
                int amount = Random.random(1, 3);
                for (int i = 0; i < amount; i++) {
                    desirableCategories.add(category);
                }
            }
        }
        setWishes(desirableCategories);
    }

    @Override
    public String toString() {
        return name;
    }

    public List<String> getWishes() {
        return wishes;
    }

    public void setWishes(List<String> wishes) {
        this.wishes = wishes;
    }

    public List<String> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(List<String> restrictions) {
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
