package tests;

import lists.IIterator;
import lists.coarse.SortedLinkedListCoarse;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sebi on 01-Nov-17.
 */
public class SortedLinkedListCoarseTest {

    @Test
    public void basicInsert(){
        SortedLinkedListCoarse list = new SortedLinkedListCoarse();
        list.insert(2);
        list.insert(1);
        list.insert(4);
        list.insert(10);
        list.insert(23);
        list.insert(8);
        list.insert(16);

        System.out.println(list);
    }

    @Test
    public void deletes(){
        SortedLinkedListCoarse list = new SortedLinkedListCoarse();
        list.insert(2);
        list.insert(1);
        list.insert(4);
        list.insert(10);
        list.insert(23);
        list.insert(8);
        list.insert(16);

        list.delete(2);

        list.delete(10);


        SortedLinkedListCoarse list2 = new SortedLinkedListCoarse();
        list2.delete(4);
        list2.insert(3);
        list2.delete(3);
        System.out.println(list);
    }

    @Test
    public void iterator() throws Exception {
        SortedLinkedListCoarse list = new SortedLinkedListCoarse();
        list.insert(2);
        list.insert(1);
        list.insert(4);
        list.insert(10);
        list.insert(23);
        list.insert(8);
        list.insert(16);

        IIterator iterator = list.getIterator();
        while (iterator.isValid()){
            System.out.println(iterator.getElement());
            iterator.getNext();
        }
    }

    @Test
    public void nullPtr() throws Exception {
        SortedLinkedListCoarse list = new SortedLinkedListCoarse();

        list.insert(0);
        list.insert(2);
        list.delete(2);

    }

    @Test
    public void test2() throws Exception {
        SortedLinkedListCoarse list = new SortedLinkedListCoarse();

        list.insert(0);
        list.insert(1);
        list.delete(0);

    }

    @Test
    public void test3() throws Exception {
        SortedLinkedListCoarse list = new SortedLinkedListCoarse();

        list.insert(0);
        list.delete(0);


    }
}
