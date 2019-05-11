package modell;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Food {

    private String name;

    private double eated;

    private double calories;

    private double carbo;

    private double protein;

    private double fat;

    private double sugar;

    public static ArrayList<Food> basicFoodsArrayList
            = new ArrayList<>(Arrays.asList(
                    new Food("Fish",1,1,1,1,1,1),
                    new Food("Chicken",1,1,1,1,1,1)

    ));


}
