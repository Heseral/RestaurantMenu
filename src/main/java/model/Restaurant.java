package model;

import model.food.dish.Dish;
import model.food.ingredient.Ingredient;

import java.util.*;

public class Restaurant {
    /*
    String name;
    String location;
    ...features...
     */
    // какие ингредиенты будет пытаться заказать ресторан для готовки блюд. Это модифицированный склад
    private Map<Class<? extends Ingredient>, Integer> ingredients = new HashMap<>();
    // НЕ СКЛАД. Просто меню - доступные для покупки блюда(в виде классов)
    private List<Class<? extends Dish>> menu = new ArrayList<>();
    // комбинации еды, с которыми будет скидка. Это список комбинаций, состоящий из пары "Набор, который нужен
    // для скидки" и сама скидка
    private List<CombinationSale> combinationsSales = new ArrayList<>();
    // жалкая попытка упростить алгоритм поиска доступной для клиента скидки. Если заказанное блюдо клиента относится
    // к классу-триггеру, то все таки проверяем собрана ли вся коллекция блюд для скидки
    private Set<Class<? extends Dish>> combinationSaleTriggers = new HashSet<>();

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

    public Set<Class<? extends Dish>> getCombinationSaleTriggers() {
        return combinationSaleTriggers;
    }

    public void setCombinationSaleTriggers(Set<Class<? extends Dish>> combinationSaleTriggers) {
        this.combinationSaleTriggers = combinationSaleTriggers;
    }
}
