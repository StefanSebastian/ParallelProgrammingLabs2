package tests;

import lists.fine.SortedLinkedListFine;

import java.util.Random;

/**
 * Created by Sebi on 02-Nov-17.
 */
public class TestAdder implements Runnable {
    private SortedLinkedListFine list;
    private String name;

    public TestAdder(SortedLinkedListFine list, String name) {
        this.list = list;
        this.name = name;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < 6; i++){
            double e = random.nextInt() % 5;
            System.out.println(name + " inserting " + e);
            list.insert(e);
        }
    }
}
