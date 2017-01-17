/*----------------------------------------------------------------
*  Author:        Daniel Chiang
*  Written:       1/17/2017
*  Last updated:  1/17/2017
*
*  Compilation:   javac -cp $CLASSPATH Deque.java
*  Execution:     java Deque
*  
*
*  Basic data structure implementation: Deque (Double-ended queue)
*
*----------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        int max = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        // int count = 0;
        while (!StdIn.isEmpty()) {
            // ++count;
            // String data = StdIn.readString();
            // if (count < max) rq.enqueue(data);
            rq.enqueue(StdIn.readString());
        }

        // int i = 0;
        // for (String s : rq) {
        //     System.out.println(s);
        //     ++i;
        //     if (i >= n) break;
        // }

        Iterator<String> iterator = rq.iterator();
        for (int i = 0; i < max && iterator.hasNext(); i++) {
            System.out.println(iterator.next());
        }

    }
}