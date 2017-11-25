package lists.coarse;

import lists.IIterator;
import lists.SortedLinkedList;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Sebi on 01-Nov-17.
 */
public class SortedLinkedListCoarse implements SortedLinkedList {
    Lock lock = new ReentrantLock();

    private Node start = null;

    @Override
    public void insert(double a) {
        lock.lock();
       // System.out.println("Lock acquired for inserting : " + a);

        try {

            Node current = new Node(a);

            if (start == null) {
                start = current;
                return;
            }

            if (a < start.getValue()) {
                current.setNext(start);
                start.setPrev(current);
                start = current;
                return;
            }

            Node it = start;
            while (it.getNext() != null && a > it.getNext().getValue()) {
                it = it.getNext();
            }

            if (it.getNext() != null) {
                current.setNext(it.getNext());
                it.getNext().setPrev(current);
                current.setPrev(it);
                it.setNext(current);
            } else {
                it.setNext(current);
                current.setPrev(it);
            }
        } catch (Exception ex){
            ex.printStackTrace();
         //   System.out.println("Error at inserting : " + a);
        }
        finally {
          //  System.out.println("Lock released for inserting : " + a);
            lock.unlock();
        }
    }

    @Override
    public void delete(double a) {
        lock.lock();
      //  System.out.println("Lock acquired for deleting : " + a);
        try {
            // empty list
            if (start == null) {
                return;
            }

            // first element
            if (start.getValue() == a) {
                if (start.getNext() == null) {
                    start = null;
                } else {
                    start.getNext().setPrev(null);
                    start = start.getNext();
                }
                return;
            }

            // rest of the list
            Node prev = null;
            Node it = start;
            while (it != null && it.getValue() != a) {
                prev = it;
                it = it.getNext();
            }

            // element not found
            if (it == null) {
                return;
            }

            // last element
            if (it.getNext() == null){
                prev.setNext(null);
                it.setPrev(null);
                return;
            }

            // somewhere in the list
            prev.setNext(it.getNext());
            it.getNext().setPrev(prev);
        } catch (Exception ex){
            ex.printStackTrace();
          //  System.out.println("Error at deleting : " + a);
        }
        finally {
          //  System.out.println("Lock released for deleting : " + a);
            lock.unlock();
        }
    }

    @Override
    public IIterator getIterator() {
        lock.lock();
        //System.out.println("Lock acquired for iterator");
        return new Iterator(start);
    }

    public class Iterator implements IIterator {
        private Node node;

        public Iterator(Node node){
            this.node = node;
        }

        @Override
        public void getNext() {
            node = node.getNext();
        }

        @Override
        public double getElement() {
            return node.getValue();
        }

        @Override
        public boolean isValid() {
            if (node == null){
                lock.unlock();
               // System.out.println("Lock released for iterator");
            }
            return node != null;
        }
    }

    public class Node{
        private double value;
        private Node next;
        private Node prev;

        public Node(double value, Node next, Node prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        public Node(double value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }
}
