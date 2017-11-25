package tests;

import lists.fine.SortedLinkedListFine;

import java.util.Random;

/**
 * Created by Sebi on 02-Nov-17.
 */
public class TestRemover implements Runnable {
    private SortedLinkedListFine list;
    private String name;

    public TestRemover(SortedLinkedListFine list, String name) {
        this.list = list;
        this.name = name;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < 10; i++){
            double e = random.nextInt() % 5;
            System.out.println(name + " removing " + e);
            list.delete(e);
        }
    }
}
