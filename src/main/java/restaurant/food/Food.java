package restaurant.food;

public abstract class Food {
    // название еды
    private String name;
    // базовая цена еды без скидки
    private int basicPrice;

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(int basicPrice) {
        this.basicPrice = basicPrice;
    }
}
