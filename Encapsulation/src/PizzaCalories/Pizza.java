package PizzaCalories;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name;
    private Dough dough;
    private List<Topping> toppings;

    public Pizza(String name, int numberOfToppings) {
        this.setName(name);
        setToppings(numberOfToppings);


    }

    private void setName(String name) {
        if (name.trim().isEmpty() || name.trim().length() > 15) {
            throw new IllegalArgumentException("Pizza name should be between 1 and 15 symbols.");
        }
        this.name = name;
    }

    private void setToppings(int numberOfToppings) {
        if (numberOfToppings <= 0 || numberOfToppings > 10) {
            throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        }
        this.toppings = new ArrayList<>();

    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    public String getName() {
        return name;
    }

    public void addTopping(Topping topping) {

        toppings.add(topping);
    }

    public double getOverallCalories() {
        double doughCalories = dough.calculateCalories();

        double toppingCalories = toppings.stream()
                .map(Topping::calculateCalories)
                .mapToDouble(Double::doubleValue)
                .sum();

        return doughCalories+toppingCalories;
    }

    @Override
    public String toString() {
        return String.format("%s - %.2f%n",this.name,getOverallCalories());
    }
}
