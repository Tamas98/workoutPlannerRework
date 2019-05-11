package modell;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
/**
 * This class contains the data of the exercises. Getters,setters,constructors are created by
 * the lombok dependency
 */
public class Exercise {

    private String name;

    private int reps;

    public static String[] exerciseList = {"Push-ups","Supermans","Bent Knee Push-ups","Plank","Squat Jumps",
            "Forward Lunge","Glute Bridge","Hip-Rotations","Side Lunge","Side Plank","Single Leg Stand"};

}
