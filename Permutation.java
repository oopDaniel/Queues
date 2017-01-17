/*----------------------------------------------------------------
*  Author:        Daniel Chiang
*  Written:       1/17/2017
*  Last updated:  1/17/2017
*
*  Compilation:   javac -cp $CLASSPATH Permutation.java
*  Execution:     java Permutation <lines> <fileName>
*  
*
*  Read lines in file in order to test RandomizedQueue
*
*----------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        int max = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        while (!StdIn.isEmpty())
            rq.enqueue(StdIn.readString());

        Iterator<String> iterator = rq.iterator();
        for (int i = 0; i < max && iterator.hasNext(); i++) {
            System.out.println(iterator.next());
        }
    }
}