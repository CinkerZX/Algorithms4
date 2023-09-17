/**
 * Aim: merge two queues of sorted items
 * Input: two sorted queue
 * Output: one merged queue
 */
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

public class MergingSortedQueue {

    public static ArrayDeque<Comparable> mergeSortedQueue(ArrayDeque<Comparable> queueA, ArrayDeque<Comparable> queueB){
        // TODO: merge ordered queueA and ordered queueB into result (quequeA: 5, 3, 1, queueB: 6, 4, 2)
        int lenA = queueA.size();
        int lenB = queueB.size();
        int lenResult = lenA+lenB;
        ArrayDeque<Comparable> result = new ArrayDeque<>(lenResult);
        ArrayDeque<Comparable> queueACopy = queueA.clone();
        ArrayDeque<Comparable> queueBCopy = queueB.clone();

        // compare e1 and e2, save into result
        if (sort.less(queueACopy.getLast(), queueBCopy.getFirst())){
            // the largest of queueA is smaller than the smallest in queueB
            copyQueue(queueACopy, result);
            copyQueue(queueBCopy, result);
        }
        else if (sort.less(queueBCopy.getLast(), queueACopy.getFirst())){
            // the largest of queueB is smaller than the smallest in queueA
            copyQueue(queueBCopy, result);
            copyQueue(queueACopy, result);
        }
        else{ // need to compare
            while (!queueACopy.isEmpty() & !queueBCopy.isEmpty()){
                if (sort.less(queueACopy.getFirst(), queueBCopy.getFirst())){
                    result.add(queueACopy.pollFirst());
                }
                else{result.add(queueBCopy.pollFirst());}
            }
            if (result.size() != lenResult){ // either queueA has some elements left, or queueB has some left
                if (queueACopy.isEmpty()){
                    copyQueue(queueBCopy,result);
                }
                else{
                    copyQueue(queueACopy,result);
                }
            }
        }
        return result;
    }

    public static LinkedList<Comparable> mergeSortedQueue(LinkedList<Comparable> queueA, LinkedList<Comparable> queueB){
        // TODO: merge ordered queueA and ordered queueB into result (quequeA: 5, 3, 1, queueB: 6, 4, 2)
        int lenA = queueA.size();
        int lenB = queueB.size();
        int lenResult = lenA+lenB;
        LinkedList<Comparable> result = new LinkedList<Comparable>();
        LinkedList<Comparable> queueACopy = (LinkedList<Comparable>) queueA.clone();
        LinkedList<Comparable> queueBCopy = (LinkedList<Comparable>) queueB.clone();

        // compare e1 and e2, save into result
        if (sort.less(queueACopy.getLast(), queueBCopy.getFirst())){
            // the largest of queueA is smaller than the smallest in queueB
            copyQueue(queueACopy, result);
            copyQueue(queueBCopy, result);
        }
        else if (sort.less(queueBCopy.getLast(), queueACopy.getFirst())){
            // the largest of queueB is smaller than the smallest in queueA
            copyQueue(queueBCopy, result);
            copyQueue(queueACopy, result);
        }
        else{ // need to compare
            while (!queueACopy.isEmpty() & !queueBCopy.isEmpty()){
                if (sort.less(queueACopy.getFirst(), queueBCopy.getFirst())){
                    result.add(queueACopy.pollFirst());
                }
                else{result.add(queueBCopy.pollFirst());}
            }
            if (result.size() != lenResult){ // either queueA has some elements left, or queueB has some left
                if (queueACopy.isEmpty()){
                    copyQueue(queueBCopy,result);
                }
                else{
                    copyQueue(queueACopy,result);
                }
            }
        }
        return result;
    }

    public static void queueSort(ArrayDeque<Comparable> q){
        ArrayDeque<Comparable> aux = new ArrayDeque<>(q.size());
        Comparable e = q.pollFirst();
        aux.add(e);
        Comparable l;
        Comparable r;
        while(q.size() != 0){
            e = q.pollFirst();
            l = aux.getLast();
            r = aux.getFirst();
            if (sort.less(e, r) || e.equals(r)){aux.addFirst(e);}
            else if (sort.less(l, e) || e.equals(l)){aux.addLast(e);}
            else{
                ArrayDeque<Comparable> aux2 = new ArrayDeque<>(q.size());
                while(sort.less(r,e) & sort.less(e,l)){
                    aux2.add(aux.pollFirst());
                    r = aux.getFirst();
                }
                aux2.add(e);
                while(!aux.isEmpty()){aux2.add(aux.pollFirst());}
                aux = aux2.clone();
            }
        }
        copyQueue(aux,q);
    }

    //help function
    public static void copyQueue(ArrayDeque queueFrom, ArrayDeque queueTo){
        //TODO: copy queueFrom into queueTo
        ArrayDeque queueFromCopy = queueFrom.clone();
        try{
            while(!queueFromCopy.isEmpty()){
                queueTo.add(queueFromCopy.pollFirst());
            }
        }catch (Exception e){
            System.out.println("The size of destination queue is smaller than that of original queue");
        }
    }

    public static void copyQueue(LinkedList queueFrom, LinkedList queueTo){
        //TODO: copy queueFrom into queueTo
        LinkedList queueFromCopy = (LinkedList) queueFrom.clone();
        try{
            while(!queueFromCopy.isEmpty()){
                queueTo.add(queueFromCopy.pollFirst());
            }
        }catch (Exception e){
            System.out.println("The size of destination queue is smaller than that of original queue");
        }
    }

    public static void printQueue(ArrayDeque q){
        System.out.println("The elements in the array: ");
        ArrayDeque<Comparable> aux = q.clone();
        while(!aux.isEmpty()){
            System.out.print(aux.pollFirst()+"  ");
        }
        System.out.println("");
    }

    public static void printQueue(LinkedList<Comparable> q){
        System.out.println("The elements in the array: ");
        LinkedList<Comparable> aux = (LinkedList<Comparable>) q.clone();
        while(!aux.isEmpty()){
            System.out.print(aux.pollFirst()+"  ");
        }
        System.out.println("");
    }


    public static void main(String[] args) {
        // Test sort queue
//        ArrayDeque a = new ArrayDeque(4);
//        a.add("z");
//        a.add("b");
//        a.add("c");
//        a.add("z");
//        queueSort(a);
//        printQueue(a);

        // Test merge
        ArrayDeque b = sort.generateStingQueue(3);
        queueSort(b);
        printQueue(b);
        ArrayDeque c = sort.generateStingQueue(5);
        queueSort(c);
        printQueue(c);
        printQueue(mergeSortedQueue(b,c));
        printQueue(mergeSortedQueue(c,b));
    }
}
