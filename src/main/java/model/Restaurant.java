package model;

import java.util.*;

public class Restaurant {
    /*
    String name;
    String location;
    ...features...
     */
    // какие ингредиенты будет пытаться заказать ресторан для готовки блюд. Это модифицированный склад
    private Map<String, Integer> ingredients = new HashMap<>();
    // НЕ СКЛАД. Просто меню - доступные для покупки блюда(в виде классов)
    private List<String> menu = new ArrayList<>();
    // комбинации еды, с которыми будет скидка. Это список комбинаций, состоящий из пары "Набор, который нужен
    // для скидки" и сама скидка
    private List<CombinationSale> combinationsSales = new ArrayList<>();
    // жалкая попытка упростить алгоритм поиска доступной для клиента скидки. Если заказанное блюдо клиента относится
    // к классу-триггеру, то все таки проверяем собрана ли вся коллекция блюд для скидки
    private Set<String> combinationSaleTriggers = new HashSet<>();

    public List<String> getMenu() {
        return menu;
    }

    public void setMenu(List<String> menu) {
        this.menu = menu;
    }

    public List<CombinationSale> getCombinationsSales() {
        return combinationsSales;
    }

    public void setCombinationsSales(List<CombinationSale> combinationsSales) {
        this.combinationsSales = combinationsSales;
    }

    public Map<String, Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<String, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<String> getCombinationSaleTriggers() {
        return combinationSaleTriggers;
    }

    public void setCombinationSaleTriggers(Set<String> combinationSaleTriggers) {
        this.combinationSaleTriggers = combinationSaleTriggers;
    }
}
