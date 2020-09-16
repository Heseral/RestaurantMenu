package restaurant;

import restaurant.food.dish.Dish;
import util.Pair;

public class CombinationSale extends Pair<Class<? extends Dish>[], Integer> {
    @SafeVarargs
    public CombinationSale(Integer saleInPercents, Class<? extends Dish>... dishesCombination) {
        super(dishesCombination, saleInPercents);
    }
}
