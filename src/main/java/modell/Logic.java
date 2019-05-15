package modell;

import controll.controllers.Window;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Logic {

    /**
     * Finds the food inside the food list by its name.
     * @param name the name of the food we are looking for
     * @return the food with its macros
     */
    public Food findFood(String name){
        return Food.basicFoodsArrayList.stream()
                                        .filter(e -> e.getName().equals(name))
                                        .findFirst().get();
    }

    /**
     * Converts a String value to a Double.
     * @param value String value we want to convert
     * @return the value converted to double
     */
    public Double converter(String value){

        if(value.length() == 0){
            Window window = new Window();
            window.popBox("Enter a number please!","No input");
        }

        try {
            Double target = Double.parseDouble(value);
            return target;
        }catch(Exception e){
            throw new AssertionError();
        }
    }

    /**
     * Summing the exercises done today.
     * @param dailyExercises the exercises of the day
     * @return a number,the sum of the reputations today
     */
    public int sumDailyReps(ArrayList<Exercise> dailyExercises){
        return dailyExercises.stream().map(Exercise::getReps)
                .collect(Collectors.summingInt(Integer::intValue));
    }

    /**
     * Calculates the eated macros from the food and the eaten amount.
     * @param choosenFood The food eaten by the user
     * @param multiple The amount of the eaten food(in gramm)
     * @return The chossenFood,but with the calculated macros.
     */
    public Food calculateFood(Food choosenFood,double multiple){

        Food calculatedFood = Food.builder()
                                  .name(choosenFood.getName())
                                  .eated(multiple)
                                  .calories(choosenFood.getCalories()*multiple)
                                  .carbo(choosenFood.getCarbo()*multiple)
                                  .fat(choosenFood.getFat()*multiple)
                                  .protein(choosenFood.getProtein()*multiple)
                                  .sugar(choosenFood.getSugar()*multiple)
                                  .build();

        return calculatedFood;
    }

    /**
     * Get's the unit the user used
     * @param selectedUnit unit selected by the user in string
     * @return the multiplier from the unit.
     */
    public int getUnit(String selectedUnit){

        if(selectedUnit.equals("g")){
            return 1;
        }else{
            return 10;
        }
    }

    /**
     * Calculate the macros eaten at the given day
     * @param dailyFoods the list of foods eaten at the selected date
     * @return food containing the sum of macros.
     */
    public Food evaulateDay(ArrayList<Food> dailyFoods){
        double calorieSum = dailyFoods.stream().map(Food::getCalories).collect(Collectors.summingDouble(Double::doubleValue));
        double carboSum = dailyFoods.stream().map(Food::getCarbo).collect(Collectors.summingDouble(Double::doubleValue));
        double sugarSum = dailyFoods.stream().map(Food::getSugar).collect(Collectors.summingDouble(Double::doubleValue));
        double fatSum = dailyFoods.stream().map(Food::getFat).collect(Collectors.summingDouble(Double::doubleValue));
        double proteinSum = dailyFoods.stream().map(Food::getProtein).collect(Collectors.summingDouble(Double::doubleValue));

        Food newFood = Food.builder().calories(calorieSum).carbo(carboSum).sugar(sugarSum).fat(fatSum).protein(proteinSum).build();

        return newFood;
    }
}
