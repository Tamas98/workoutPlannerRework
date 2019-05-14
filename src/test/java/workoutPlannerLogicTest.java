import modell.Exercise;
import modell.Food;
import modell.Logic;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class workoutPlannerLogicTest {

    private Logic logic;

    @Before
    public void setupTest(){
        logic = new Logic();
    }

    @After
    public void tearDown(){
        logic = null;
    }

    @Test
    public void testConverter(){
        double test = logic.converter("2.5");
        Assert.assertEquals(2.5,test,0);
    }

    @Test
    public void testFindFood(){
        Food test = logic.findFood("Chicken");

        Assert.assertNotNull(test);
        Assert.assertEquals("Chicken",test.getName());
    }

    @Test
    public void testSumReps(){
        ArrayList<Exercise> testExercises = new ArrayList<>(Arrays.asList(
                new Exercise("test",20),
                new Exercise("test2",50),
                new Exercise("test3",30)
        ));

        int sum = logic.sumDailyReps(testExercises);

        Assert.assertEquals(100,sum);
    }

    @Test
    public void testMacroCalculator(){
        Food test = new Food("test",1,1,1,1,1,1);
        Food result = logic.calculateFood(test,10);

        Assert.assertEquals(10,result.getCalories(),0);
        Assert.assertEquals(10,result.getProtein(),0);
        Assert.assertEquals(10,result.getFat(),0);
        Assert.assertEquals(10,result.getCarbo(),0);
        Assert.assertEquals(10,result.getSugar(),0);

    }

    @Test
    public void testGetUNit(){
        int result = logic.getUnit("dkg");

        Assert.assertEquals(10,result);

        result = logic.getUnit("g");

        Assert.assertEquals(1,result);
    }

    @Test
    public void testEvaulate(){
        ArrayList<Food> testFoods = new ArrayList<>(Arrays.asList(
                new Food("test",1,1,1,1,1,1),
                new Food("test2",1,1,1,1,1,1)
        ));

        Food result = logic.evaulateDay(testFoods);

        Assert.assertEquals(2,result.getSugar(),0);
        Assert.assertEquals(2,result.getProtein(),0);
        Assert.assertEquals(2,result.getFat(),0);
        Assert.assertEquals(2,result.getCarbo(),0);
        Assert.assertEquals(2,result.getCalories(),0);
    }
}
