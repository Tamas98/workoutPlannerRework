package modell;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;

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

    public static ArrayList<Food> basicFoodsArrayList;
}
