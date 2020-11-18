package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.food.dish.Dish;
import model.food.dish.Drink;
import model.food.dish.Alcoholic;
import model.food.dish.Beer;
import model.food.dish.Champagne;
import model.food.dish.Vodka;
import model.food.dish.Milkshake;
import model.food.dish.Soda;
import model.food.dish.Water;
import model.food.dish.Bread;
import model.food.dish.Sandwich;
import model.food.dish.Snack;
import model.food.dish.CutletsWithMashedPotato;
import model.food.dish.SoftFood;
import model.food.dish.Spaghetti;
import model.food.dish.Borscht;
import model.food.dish.FishSoup;
import model.food.dish.Soup;
import model.food.dish.Cake;
import model.food.dish.Pancakes;
import model.food.dish.SweetFood;
import model.food.ingredient.*;

import java.util.*;

public abstract class GlobalVar {
    // примитивная защита от конфликтов потоков
    public static boolean isSafeToSave = true;
    // пока через глобальную переменную. Это сериализатор и десериализатор
    public final static Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Class.class, new ClassTypeAdapter())
            .setPrettyPrinting()
            .create();
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
            model.food.ingredient.Water.class
    ));
    public final static List<Class<? extends Dish>> ALL_AVAILABLE_DISHES = new ArrayList<>();

    static {
        for (Class<? extends Dish> category : DISH_CATEGORIES) {
            ALL_AVAILABLE_DISHES.addAll(DISHES_BY_CATEGORY.get(category));
        }
    }
}
