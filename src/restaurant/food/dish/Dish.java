package restaurant.food.dish;

import restaurant.food.Food;
import restaurant.food.ingredient.Ingredient;
import util.GlobalVar;
import util.Pair;

import java.util.List;

public abstract class Dish extends Food{

    // время, необходимое для приготовления блюда
    private int timeToCook;
    // цена с учетом скидок
    private int currentPrice;
    // ингредиенты, необходимые для приготовления блюда в виде: Список пар вида: Ингредиент-количество.
    // В первую очередь это создано для взаимодействиями с предпочтениями посетителей, а не для того, чтобы
    // готовить из этих ингредиентов. Не удивляйтесь, что, например, бутерброд создается из мяса, муки, яиц и воды.
    // Это лишь значит то, что он состоит из абстрактных и обобщенных ингредиентов, озвученных ранеее.
    // Напомню, что приготовление из ингредиентов не предусматривается ТЗ, сказано только "в ресторане есть блюда".
    // В теории может быть расширено и до таких целей, но тогда, думаю, лучше будет объединить классы ингредиентов и
    // еды, так как, например, немного глупо готовить хлеб с нуля из базовых ингредиентов для бутерброда.
    private transient List<Pair<Class<? extends Ingredient>, Integer>> recipe;

    // не передавайте нуллы никуда. Лучше передайте свежесозданный, но пустой объект
    public Dish(
            String newName,
            int newPrice,
            List<Pair<Class<? extends Ingredient>, Integer>> newIngredients,
            int newTimeToCook
    ) {
        setName(newName);
        setBasicPrice(newPrice);
        setCurrentPrice(newPrice);
        setRecipe(newIngredients);
        setTimeToCook(newTimeToCook);
    }

    public List<Pair<Class<? extends Ingredient>, Integer>> getRecipe() {
        return recipe;
    }

    public void setRecipe(List<Pair<Class<? extends Ingredient>, Integer>> recipe) {
        this.recipe = recipe;
    }

    public int getTimeToCook() {
        return timeToCook;
    }

    public void setTimeToCook(int timeToCook) {
        this.timeToCook = util.Misc.clamp(timeToCook, GlobalVar.COOK_TIME_MINIMUM, GlobalVar.COOK_TIME_MAXIMUM);
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int affectedBySale) {
        this.currentPrice = affectedBySale;
    }
}

/* // шаблон для создания "компактных" конструкторов
    public () {
        this(
                "",
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
