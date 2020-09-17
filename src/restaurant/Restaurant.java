package restaurant;

import restaurant.food.dish.Dish;
import restaurant.food.ingredient.Ingredient;
import util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Restaurant {
    /*
    String name;
    String location;
    ...features...
     */

    // какие ингредиенты будет пытаться заказать ресторан для готовки блюд. НЕ СКЛАД, это список классов, как у меню
    private Map<Class<? extends Ingredient>, Integer> ingredients = new HashMap<>();
    // НЕ СКЛАД. Просто меню - доступные для покупки блюда(в виде классов)
    private List<Class<? extends Dish>> menu = new ArrayList<>();
    // комбинации еды, с которыми будет скидка. Это список комбинаций, состоящий из пары "Набор, который нужен
    // для скидки" и сама скидка
    private List<CombinationSale> combinationsSales = new ArrayList<>();

    public List<Class<? extends Dish>> getMenu() {
        return menu;
    }

    public void setMenu(List<Class<? extends Dish>> menu) {
        this.menu = menu;
    }

    public List<CombinationSale> getCombinationsSales() {
        return combinationsSales;
    }

    public void setCombinationsSales(List<CombinationSale> combinationsSales) {
        this.combinationsSales = combinationsSales;
    }

    public Map<Class<? extends Ingredient>, Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<Class<? extends Ingredient>, Integer> ingredients) {
        this.ingredients = ingredients;
    }
}
