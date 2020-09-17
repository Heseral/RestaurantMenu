package restaurant;

import restaurant.food.dish.dishes.drinks.alcoholic.Beer;
import restaurant.food.dish.dishes.drinks.alcoholic.Champagne;
import restaurant.food.dish.dishes.drinks.alcoholic.Vodka;
import restaurant.food.dish.dishes.drinks.non_alcoholic.Milkshake;
import restaurant.food.dish.dishes.drinks.non_alcoholic.Soda;
import restaurant.food.dish.dishes.drinks.non_alcoholic.Water;
import restaurant.food.dish.dishes.snacks.Bread;
import restaurant.food.dish.dishes.snacks.Sandwich;
import restaurant.food.dish.dishes.soft_food.CutletsWithMashedPotato;
import restaurant.food.dish.dishes.soft_food.Spaghetti;
import restaurant.food.dish.dishes.soups.Borscht;
import restaurant.food.dish.dishes.soups.FishSoup;
import restaurant.food.dish.dishes.sweet_food.Cake;
import restaurant.food.dish.dishes.sweet_food.Pancakes;

import java.util.Arrays;

public class RestaurantService {
    public void fillDefaultRestaurantMenu(Restaurant restaurantToFill) {
        restaurantToFill.setMenu(Arrays.asList(
                // алкогольные напитки
                Beer.class,
                Champagne.class,
                Vodka.class,
                // неалкогольные напитки
                Milkshake.class,
                Soda.class,
                Water.class,
                // закуски
                Bread.class,
                Sandwich.class,
                // мягкая еда(второе)
                CutletsWithMashedPotato.class,
                Spaghetti.class,
                // супы(первое)
                Borscht.class,
                FishSoup.class,
                // десерты
                Cake.class,
                Pancakes.class
        ));
    }

    public void fillDefaultRestaurantCombinationsSales(Restaurant restaurantToFill) {
        addNewCombinationSaleToRestaurant(restaurantToFill, new CombinationSale(10, Cake.class, Soda.class));
        // суровый русский обед
        addNewCombinationSaleToRestaurant(restaurantToFill, new CombinationSale(25, Vodka.class, Borscht.class, CutletsWithMashedPotato.class));
        addNewCombinationSaleToRestaurant(restaurantToFill, new CombinationSale(10, Pancakes.class, Milkshake.class));
        addNewCombinationSaleToRestaurant(restaurantToFill, new CombinationSale(15, Beer.class, Sandwich.class));

    }

    public void addNewCombinationSaleToRestaurant(Restaurant restaurantToAdd, CombinationSale saleToAdd) {
        restaurantToAdd.getCombinationsSales().add(saleToAdd);
    }

    public void removeCombinationSaleFromRestaurant(Restaurant restaurantToRemove, CombinationSale saleToRemove) {
        int i = 0;
        for (CombinationSale possibleSaleToRemove : restaurantToRemove.getCombinationsSales()) {
            if (saleToRemove.equals(possibleSaleToRemove)) {
                restaurantToRemove.getCombinationsSales().remove(i);
            }
            i++;
        }
    }

    public void clearCombinationSaleListOfRestaurant(Restaurant restaurantToClear) {
        restaurantToClear.getCombinationsSales().clear();
    }
}
