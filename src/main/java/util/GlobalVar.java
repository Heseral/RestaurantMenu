package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.food.dish.*;
import model.food.ingredient.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class GlobalVar {
    // примитивная защита от конфликтов потоков
    public static boolean isSafeToSave = true;
    // пока через глобальную переменную. Это сериализатор и десериализатор
    public final static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    // секунда в миллисекундах
    public final static int SECOND = 1000;

    // по крайней мере нужно время на то чтобы официанту донести заказ
    public final static int COOK_TIME_MINIMUM = 2;
    // откуда я взял эти цифры? Очевидно, среднепотолочные значения
    public final static int COOK_TIME_LOW = 4;
    public final static int COOK_TIME_DEFAULT = 6;
    public final static int COOK_TIME_LONG = 8;
    // не будем мучать клиентов ожиданиями выше этого значения. И так ждать абстрактный час, ужас!
    public final static int COOK_TIME_MAXIMUM = 10;

    public final static List<String> DISH_CATEGORIES = new ArrayList<>(Arrays.asList(
            Alcoholic.class.getName(),
            Drink.class.getName(),
            Snack.class.getName(),
            SoftFood.class.getName(),
            Soup.class.getName(),
            SweetFood.class.getName()
    ));
    public final static Map<String, List<String>> DISHES_BY_CATEGORY = Map.of(
            Alcoholic.class.getName(), new ArrayList<>(Arrays.asList(
                    Beer.class.getName(),
                    Champagne.class.getName(),
                    Vodka.class.getName()
            )),
            Drink.class.getName(), new ArrayList<>(Arrays.asList(
                    Milkshake.class.getName(),
                    Soda.class.getName(),
                    WaterBottle.class.getName()
            )),
            Snack.class.getName(), new ArrayList<>(Arrays.asList(
                    Bread.class.getName(),
                    Sandwich.class.getName()
            )),
            SoftFood.class.getName(), new ArrayList<>(Arrays.asList(
                    CutletsWithMashedPotato.class.getName(),
                    Spaghetti.class.getName()
            )),
            Soup.class.getName(), new ArrayList<>(Arrays.asList(
                    Borscht.class.getName(),
                    FishSoup.class.getName()
            )),
            SweetFood.class.getName(), new ArrayList<>(Arrays.asList(
                    Cake.class.getName(),
                    Pancakes.class.getName()
            ))

    );
    public final static List<String> INGREDIENTS = new ArrayList<>(Arrays.asList(
            Alcohol.class.getName(),
            Egg.class.getName(),
            Flour.class.getName(),
            Fruits.class.getName(),
            Meat.class.getName(),
            Milk.class.getName(),
            Sugar.class.getName(),
            Vegetables.class.getName(),
            model.food.ingredient.Water.class.getName()
    ));
    public final static List<String> ALL_AVAILABLE_DISHES = new ArrayList<>();

    static {
        for (String category : DISH_CATEGORIES) {
            ALL_AVAILABLE_DISHES.addAll(DISHES_BY_CATEGORY.get(category));
        }
    }
}
