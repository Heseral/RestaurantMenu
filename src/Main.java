import restaurant.Restaurant;
import restaurant.RestaurantService;
import util.Random;
import visitor.Visitor;
import visitor.VisitorService;

import java.lang.reflect.InvocationTargetException;
import java.util.Timer;
import java.util.TimerTask;

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

        restaurantService.setDefaultRestaurantMenu(restaurant);
        restaurantService.setDefaultRestaurantCombinationsSales(restaurant);
        restaurantService.setDefaultRestaurantIngredientsRandomly(restaurant);

        VisitorService visitorService = new VisitorService();


        TimerTask newVisitor = new TimerTask() {
            @Override
            public void run() {
                Visitor visitor = new Visitor();
                try {
                    visitorService.createAbsolutelyRandomOrder(visitor, restaurantService , restaurant);
                } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        };
        TimerTask ingredientsResupply = new TimerTask() {
            @Override
            public void run() {
                restaurantService.resupplyIngredientsRandomly(restaurant);
            }
        };
        Timer visitorTimer = new Timer();
        visitorTimer.schedule(newVisitor, 0, 15 * 1000);

        Timer ingredientsResupplyTimer = new Timer();
        ingredientsResupplyTimer.schedule(ingredientsResupply, 0, 120 * 1000);
    }
}
