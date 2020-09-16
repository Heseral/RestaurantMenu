package restaurant.food.dish;

import restaurant.food.Food;
import restaurant.food.ingredient.Ingredient;
import util.GlobalVar;
import util.Pair;

import java.util.List;

public abstract class Dish extends Food {
    // комбинации еды, с которыми будет скидка. Это список комбинаций, состоящий из пары "Набор, который нужен
    // для скидки" и сама скидка
    private List<Pair<List<Dish>, Integer>> combinationsSale;
    // время, необходимое для приготовления блюда
    private int timeToCook;
    // ингредиенты, необходимые для приготовления блюда в виде: Список пар вида: Ингредиент-количество.
    // В первую очередь это создано для взаимодействиями с предпочтениями посетителей, а не для того, чтобы
    // готовить из этих ингредиентов. Не удивляйтесь, что, например, бутерброд создается из мяса, муки, яиц и воды.
    // Это лишь значит то, что он состоит из абстрактных и обобщенных ингредиентов, озвученных ранеее.
    // Напомню, что приготовление из ингредиентов не предусматривается ТЗ, сказано только "в ресторане есть блюда".
    // В теории может быть расширено и до таких целей, но тогда, думаю, лучше будет объединить классы ингредиентов и
    // еды, так как, например, немного глупо готовить хлеб с нуля из базовых ингредиентов для бутерброда.
    private List<Pair<Class<? extends Ingredient>, Integer>> necessaryIngredients;

    // не передавайте нуллы никуда. Лучше передайте свежесозданный, но пустой объект
    public Dish(
            String newName,
            List<Pair<List<Dish>, Integer>> newCombinationsSale,
            int newPrice,
            List<Pair<Class<? extends Ingredient>, Integer>> newIngredients,
            int newTimeToCook
    ) {
        setName(newName);
        setCombinationsSale(newCombinationsSale);
        setBasicPrice(newPrice);
        setNecessaryIngredients(newIngredients);
        setTimeToCook(newTimeToCook);
    }

    public List<Pair<List<Dish>, Integer>> getCombinationsSale() {
        return combinationsSale;
    }

    public void setCombinationsSale(List<Pair<List<Dish>, Integer>> combinationsSale) {
        this.combinationsSale = combinationsSale;
    }

    public List<Pair<Class<? extends Ingredient>, Integer>> getNecessaryIngredients() {
        return necessaryIngredients;
    }

    public void setNecessaryIngredients(List<Pair<Class<? extends Ingredient>, Integer>> necessaryIngredients) {
        this.necessaryIngredients = necessaryIngredients;
    }

    public int getTimeToCook() {
        return timeToCook;
    }

    public void setTimeToCook(int timeToCook) {
        this.timeToCook = util.Misc.clamp(timeToCook, GlobalVar.COOK_TIME_MINIMUM, GlobalVar.COOK_TIME_MAXIMUM);
    }
}

/* // шаблон для создания "компактных" конструкторов
    public (List<Pair<List<Dish>, Integer>> newCombinationsSale, int newPrice) {
        this(
                "",
                newCombinationsSale,
                newPrice,
                new ArrayList<>
                        (Arrays.asList(
                                new Pair<>(, ),
                                new Pair<>(, )
                        )),
                GlobalVar.COOK_TIME_DEFAULT
        );
    }
 */
