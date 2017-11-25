package tests;

import lists.IIterator;
import lists.fine.SortedLinkedListFine;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sebi on 02-Nov-17.
 */
public class SortedLinkedListFineTest {

    @Test
    public void basic1() throws Exception{
        SortedLinkedListFine list = new SortedLinkedListFine();
        list.insert(2);
        list.insert(3);
        list.insert(1);
        list.insert(10);
        list.insert(6);
        list.insert(20);

        System.out.println(list);
    }

    @Test
    public void threads() throws Exception {
        SortedLinkedListFine list = new SortedLinkedListFine();

        List<Thread> threads = new LinkedList<>();
        threads.add(new Thread(new TestAdder(list, "T1")));
        threads.add(new Thread(new TestRemover(list, "T2")));
        threads.add(new Thread(new TestAdder(list, "T3")));

        for (Thread t : threads){
            t.start();
        }

        for (Thread t : threads){
            t.join();
        }

        System.out.println(list);

        IIterator iterator = list.getIterator();

        int count = 0;

        while (iterator.isValid()){
            System.out.println(iterator.getElement());
            iterator.getNext();
            count++;
        }
        System.out.println(count);
    }
}
