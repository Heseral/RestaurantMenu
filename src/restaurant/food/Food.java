package restaurant.food;

import util.Pair;

import java.util.List;

public class Food {
    // название еды
    private String name;
    // комбинации еды, с которыми будет скидка. Это список комбинаций, состоящий из пары "Набор, который нужен
    // для скидки" и сама скидка
    private List<Pair<List<Food>, Integer>> combinationsSale;
    // базовая цена блюда без скидки
    private int basicPrice;
    // битово-флаговый тип еды: может быть острым и горячим, алкогольным и молочным и т. д.
    private int type;
    // категория еды: суп, закуска и т. д.
    private FoodCategory foodCategory;
    // время, необходимое для приготовления блюда
    private int timeToCook;

    // не передавайте нуллы никуда. Лучше передайте свежесозданный, но пустой объект
    public Food(
            String newName,
            List<Pair<List<Food>, Integer>> newCombinationsSale,
            int newPrice,
            int newType,
            FoodCategory newFoodCategory,
            int newTimeToCook
    ) {
        setName(newName);
        setCombinationsSale(newCombinationsSale);
        setBasicPrice(newPrice);
        setType(newType);
        setFoodCategory(newFoodCategory);
        setTimeToCook(newTimeToCook);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pair<List<Food>, Integer>> getCombinationsSale() {
        return combinationsSale;
    }

    public void setCombinationsSale(List<Pair<List<Food>, Integer>> combinationsSale) {
        this.combinationsSale = combinationsSale;
    }

    public int getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(int basicPrice) {
        this.basicPrice = basicPrice;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public FoodCategory getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(FoodCategory foodCategory) {
        this.foodCategory = foodCategory;
    }

    public int getTimeToCook() {
        return timeToCook;
    }

    public void setTimeToCook(int timeToCook) {
        this.timeToCook = timeToCook;
    }
}
