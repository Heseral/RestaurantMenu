package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import restaurant.food.dish.Dish;
import restaurant.food.dish.dishes.drinks.Drink;
import restaurant.food.dish.dishes.drinks.alcoholic.Alcoholic;
import restaurant.food.dish.dishes.drinks.alcoholic.Beer;
import restaurant.food.dish.dishes.drinks.alcoholic.Champagne;
import restaurant.food.dish.dishes.drinks.alcoholic.Vodka;
import restaurant.food.dish.dishes.drinks.non_alcoholic.Milkshake;
import restaurant.food.dish.dishes.drinks.non_alcoholic.Soda;
import restaurant.food.dish.dishes.drinks.non_alcoholic.Water;
import restaurant.food.dish.dishes.snacks.Bread;
import restaurant.food.dish.dishes.snacks.Sandwich;
import restaurant.food.dish.dishes.snacks.Snack;
import restaurant.food.dish.dishes.soft_food.CutletsWithMashedPotato;
import restaurant.food.dish.dishes.soft_food.SoftFood;
import restaurant.food.dish.dishes.soft_food.Spaghetti;
import restaurant.food.dish.dishes.soups.Borscht;
import restaurant.food.dish.dishes.soups.FishSoup;
import restaurant.food.dish.dishes.soups.Soup;
import restaurant.food.dish.dishes.sweet_food.Cake;
import restaurant.food.dish.dishes.sweet_food.Pancakes;
import restaurant.food.dish.dishes.sweet_food.SweetFood;
import restaurant.food.ingredient.*;
import util.timer_tasks.TimerTaskCooking;
import util.timer_tasks.TimerTaskWaiting;

import java.util.*;

public abstract class GlobalVar {
    // обертка для времени. Имеет только дно поле - время в секундах.
    public static TimeWrapper timeWrapper = new TimeWrapper();
    // хранилище всех отсроченных, но еще, возможно, выполнимых заказов
    public static ArrayList<TimerTaskWaiting> waitingList = new ArrayList<>();
    // хранилище всех заказов, которые сейчас готовят
    public static ArrayList<TimerTaskCooking> cookingList = new ArrayList<>();
    // пока через глобальную переменную. Это сериализатор и десериализатор
    public final static Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Class.class, new ClassTypeAdapter())
            .setPrettyPrinting()
            .create();
    // используется для тестов и симуляции работы ресторана
    public static Timer timer = new Timer();
    // секунда в миллисекундах
    public final static int SECOND = 1000;

    // по крайней мере нужно время на то чтобы официанту донести заказ
    public final static int COOK_TIME_MINIMUM = 1;
    // откуда я взял эти цифры? Очевидно, среднепотолочные значения
    public final static int COOK_TIME_LOW = 4;
    public final static int COOK_TIME_DEFAULT = 16;
    public final static int COOK_TIME_LONG = 32;
    // не будем мучать клиентов ожиданиями выше этого значения. И так ждать абстрактный час, ужас!
    public final static int COOK_TIME_MAXIMUM = 64;

    public final static List<Class<? extends Dish>> DISH_CATEGORIES = new ArrayList<>(Arrays.asList(
            Alcoholic.class,
            Drink.class,
            Snack.class,
            SoftFood.class,
            Soup.class,
            SweetFood.class
    ));
    public final static Map<Class<? extends Dish>, List<Class<? extends Dish>>> DISHES_BY_CATEGORY = Map.of(
            Alcoholic.class, new ArrayList<>(Arrays.asList(
                    Beer.class,
                    Champagne.class,
                    Vodka.class
            )),
            Drink.class, new ArrayList<>(Arrays.asList(
                    Milkshake.class,
                    Soda.class,
                    Water.class
            )),
            Snack.class, new ArrayList<>(Arrays.asList(
                    Bread.class,
                    Sandwich.class
            )),
            SoftFood.class, new ArrayList<>(Arrays.asList(
                    CutletsWithMashedPotato.class,
                    Spaghetti.class
            )),
            Soup.class, new ArrayList<>(Arrays.asList(
                    Borscht.class,
                    FishSoup.class
            )),
            SweetFood.class, new ArrayList<>(Arrays.asList(
                    Cake.class,
                    Pancakes.class
            ))

    );
    public final static List<Class<? extends Ingredient>> INGREDIENTS = new ArrayList<>(Arrays.asList(
            Alcohol.class,
            Egg.class,
            Flour.class,
            Fruits.class,
            Meat.class,
            Milk.class,
            Sugar.class,
            Vegetables.class,
            restaurant.food.ingredient.Water.class
    ));
    public final static List<Class<? extends Dish>> ALL_AVAILABLE_DISHES = new ArrayList<>();

    static {
        for (Class<? extends Dish> category : DISH_CATEGORIES) {
            ALL_AVAILABLE_DISHES.addAll(DISHES_BY_CATEGORY.get(category));
        }
    }
}
