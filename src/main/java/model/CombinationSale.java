package model;

import model.food.dish.Dish;
import util.Pair;

import java.util.List;

public class CombinationSale extends Pair<List<String>, Integer> {
    public CombinationSale(Integer saleInPercents, List<String> dishesCombination) {
        super(dishesCombination, saleInPercents);
    }
}
