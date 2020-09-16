package restaurant;

import restaurant.food.dish.Dish;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    /*
    String name;
    String location;
    ...features...
     */
    private List<Class<? extends Dish>> menu = new ArrayList<>();
    // комбинации еды, с которыми будет скидка. Это список комбинаций, состоящий из пары "Набор, который нужен
    // для скидки" и сама скидка
    private List<CombinationSale> combinationsSales = new ArrayList<>();

    public List<Class<? extends Dish>> getMenu()
    {
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
}
