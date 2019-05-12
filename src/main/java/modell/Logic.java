package modell;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Logic {

    public Food findFood(String name){
        return Food.basicFoodsArrayList.stream()
                                        .filter(e -> e.getName().equals(name))
                                        .findFirst().get();
    }

    public Double converter(String value){
        try {
            Double target = Double.parseDouble(value);
            return target;
        }catch(Exception e){
            throw new AssertionError();
        }
    }

    public Double sumDailyMacro(ArrayList<Food> todayFoods){
        return null;
    }

    public int sumDailyReps(ArrayList<Exercise> dailyExercises){
        return dailyExercises.stream().map(Exercise::getReps)
                .collect(Collectors.summingInt(Integer::intValue));
    }

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

    public int getUnit(String selectedUnit){

        if(selectedUnit.equals("g")){
            return 1;
        }else{
            return 10;
        }
    }

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
