package model;

import model.food.dish.Dish;
import util.Pair;

import java.util.List;

public class CombinationSale extends Pair<List<Class<? extends Dish>>, Integer> {
    public CombinationSale(Integer saleInPercents, List<Class<? extends Dish>> dishesCombination) {
        super(dishesCombination, saleInPercents);
    }
}
