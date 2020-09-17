import restaurant.Restaurant;
import restaurant.RestaurantService;
import restaurant.food.dish.dishes.drinks.alcoholic.Vodka;
import restaurant.food.dish.dishes.soups.FishSoup;
import restaurant.food.ingredient.*;

import java.lang.reflect.InvocationTargetException;

/*
    9. Меню ресторана, скидки. Дано меню ресторана, которое содержит набор
    различных блюд - напитки, салаты, закуски и т.д. Каждое блюдо имеет
    стоимость, время приготовления, категорию, ограничения. Для некоторых
    сочетаний блюд могут быть даны скидки. Посетители ресторана могут
    заказывать блюда исходя из своих пожеланий, наличия свободного времени
    и денежных средств.
 */
public class Main {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        RestaurantService restaurantService = new RestaurantService();

        restaurantService.fillDefaultRestaurantMenu(restaurant);
        restaurantService.fillDefaultRestaurantCombinationsSales(restaurant);
        restaurantService.fillDefaultRestaurantIngredients(restaurant);

        System.out.println(">>>>>>>>>> START <<<<<<<<<<");

        System.out.println(restaurant.getIngredients().get(Alcohol.class));
        System.out.println(restaurant.getIngredients().get(Egg.class));
        System.out.println(restaurant.getIngredients().get(Flour.class));
        System.out.println(restaurant.getIngredients().get(Fruits.class));
        System.out.println(restaurant.getIngredients().get(Meat.class));
        System.out.println(restaurant.getIngredients().get(Milk.class));
        System.out.println(restaurant.getIngredients().get(Sugar.class));
        System.out.println(restaurant.getIngredients().get(Vegetables.class));
        System.out.println(restaurant.getIngredients().get(Water.class));

        System.out.println("    =====> COOKING...");
        try {
            System.out.println("        COOKED: " + restaurantService.tryToCookDish(restaurant, FishSoup.class));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }

        System.out.println("            " + restaurant.getIngredients().get(Alcohol.class));
        System.out.println("            " + restaurant.getIngredients().get(Egg.class));
        System.out.println("            " + restaurant.getIngredients().get(Flour.class));
        System.out.println("            " + restaurant.getIngredients().get(Fruits.class));
        System.out.println("            " + restaurant.getIngredients().get(Meat.class));
        System.out.println("            " + restaurant.getIngredients().get(Milk.class));
        System.out.println("            " + restaurant.getIngredients().get(Sugar.class));
        System.out.println("            " + restaurant.getIngredients().get(Vegetables.class));
        System.out.println("            " + restaurant.getIngredients().get(Water.class));
        System.out.println("    <===== END COOKED...");

        System.out.println("    =====> COOKING...");
        try {
            System.out.println("        COOKED: " + restaurantService.tryToCookDish(restaurant, Vodka.class));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        System.out.println("            " + restaurant.getIngredients().get(Alcohol.class));
        System.out.println("            " + restaurant.getIngredients().get(Egg.class));
        System.out.println("            " + restaurant.getIngredients().get(Flour.class));
        System.out.println("            " + restaurant.getIngredients().get(Fruits.class));
        System.out.println("            " + restaurant.getIngredients().get(Meat.class));
        System.out.println("            " + restaurant.getIngredients().get(Milk.class));
        System.out.println("            " + restaurant.getIngredients().get(Sugar.class));
        System.out.println("            " + restaurant.getIngredients().get(Vegetables.class));
        System.out.println("            " + restaurant.getIngredients().get(Water.class));
        System.out.println("    <===== END COOKED...");

        System.out.println(">>>>>>>>>> END <<<<<<<<<<");
    }
}
