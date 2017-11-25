package lists.fine;

import lists.IIterator;
import lists.SortedLinkedList;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Sebi on 02-Nov-17.
 */
public class SortedLinkedListFine implements SortedLinkedList {
    private Node dummyStart;
    private Node start;

    public SortedLinkedListFine(){
        dummyStart = new Node();
    }

    @Override
    public void insert(double a) {
        dummyStart.getLock().lock();
      //  System.out.println("Lock acquired for inserting " + a);

        // empty list
        if (start == null) {
            start = new Node(a);
            dummyStart.setNext(start);
         //   System.out.println("Lock released for inserting " + a);
            dummyStart.getLock().unlock();
            return;
        }

        // before first
        if (a <= start.getValue()) {
            Node element = new Node(a);
            dummyStart.setNext(element);
            element.setNext(start);
            start.setPrev(element);
            start = element;
          //  System.out.println("Lock released for inserting " + a);
            dummyStart.getLock().unlock();
            return;
        }

        Node prev = dummyStart;
        Node curr = start;

        curr.getLock().lock();
        while (curr != null && a > curr.getValue()) {
            prev.getLock().unlock();
            prev = curr;
            if (curr.getNext() != null){
                curr.getNext().getLock().lock();
            }
            curr = curr.getNext();
        }

        Node element = new Node(a);

        // last element
        if (curr == null) {
            prev.setNext(element);
            element.setPrev(prev);
         //   System.out.println("Lock released for inserting " + a);
            prev.getLock().unlock();
            return;
        }

        // intermediary
        prev.setNext(element);
        element.setPrev(prev);
        element.setNext(curr);
        curr.setPrev(element);

       // System.out.println("Lock released for inserting " + a);
        prev.getLock().unlock();
        curr.getLock().unlock();
    }

    @Override
    public void delete(double a) {
        dummyStart.getLock().lock();
      //  System.out.println("Lock acquired for deleting " + a);


        // empty list
        if (start == null) {
          //  System.out.println("Lock released for deleting " + a);
            dummyStart.getLock().unlock();
            return;
        }

        // first
        if (a == start.getValue()) {
            if (start.getNext() != null) {
                start.getNext().setPrev(null);
            }
            start = start.getNext();
         //   System.out.println("Lock released for deleting " + a);
            dummyStart.getLock().unlock();
            return;
        }

        Node prev = dummyStart;
        Node curr = start;

        curr.getLock().lock();
        while (curr != null && a != curr.getValue()) {
            prev.getLock().unlock();
            prev = curr;
            if (curr.getNext() != null){
                curr.getNext().getLock().lock();
            }
            curr = curr.getNext();
        }

        Node element = new Node(a);

        // not found
        if (curr == null) {
          //  System.out.println("Lock released for deleting " + a);
            prev.getLock().unlock();
            return;
        }

        // last element
        if (curr.getNext() == null){
            prev.setNext(null);
            curr.setPrev(null);

          //  System.out.println("Lock released for deleting " + a);
            prev.getLock().unlock();
            curr.getLock().unlock();
            return;
        }

        // intermediary
        prev.setNext(curr.getNext());
        curr.getNext().setPrev(prev);

      //  System.out.println("Lock released for deleting " + a);
        prev.getLock().unlock();
        curr.getLock().unlock();
    }

    @Override
    public IIterator getIterator() {
        return new Iterator();
    }

    public class Node {
        private double value;
        private Node next;
        private Node prev;
        private Lock lock;

        public Node(){
            lock = new ReentrantLock();
        }

        public Node(double value, Node next, Node prev, Lock lock) {
            this.value = value;
            this.next = next;
            this.prev = prev;
            this.lock = lock;
        }

        public Node(double value) {
            this.value = value;
            this.next = null;
            this.prev = null;
            this.lock = new ReentrantLock();
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

        public Lock getLock() {
            return lock;
        }

        public void setLock(Lock lock) {
            this.lock = lock;
        }
    }

    public class Iterator implements IIterator {
        private Node node;

        // lock all nodes
        Iterator(){
            dummyStart.getLock().lock();

            Node it = dummyStart.getNext();
            while (it != null){
                it.getLock().lock();
                it = it.getNext();
            }

            // after locks acquired
          //  System.out.println("Lock acquired for iterator");
            this.node = dummyStart.getNext(); // bypass dummy start
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
              //  System.out.println("Lock released for iterator");
                Node it = dummyStart;
                while (it != null){
                    it.getLock().unlock();
                    it = it.getNext();
                }
            }
            return node != null;
        }
    }
}
