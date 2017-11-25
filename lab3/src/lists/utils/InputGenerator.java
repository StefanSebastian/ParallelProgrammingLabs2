package lists.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Sebi on 02-Nov-17.
 */
public class InputGenerator {
    /*
    Generates a random array
     */
    public static List<Double> getValuesInRange(int nr, double min, double max){
        List<Double> res = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < nr; i++){
            res.add((double)Math.round(min + (max - min) * random.nextDouble()));
        }

        return res;
    }

    /*
    Generates an array of random numbers from the given arrays
     */
    public static List<Double> getValuesFromLists(int nr, List<Double> list1, List<Double> list2){
        List<Double> res = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < nr; i++){
            if (random.nextBoolean()){
                res.add(list1.get(i % list1.size()));
            } else {
                res.add(list2.get(i % list2.size()));
            }
        }

        return res;
    }
}
