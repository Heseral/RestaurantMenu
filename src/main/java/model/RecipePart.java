package model;

public class RecipePart {
    private String ingredient;
    private Integer amount;

    public RecipePart(String ingredient, Integer amount) {
        setIngredient(ingredient);
        setAmount(amount);
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
