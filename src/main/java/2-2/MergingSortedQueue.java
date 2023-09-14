/**
 * Aim: merge two queues of sorted items
 * Input: two sorted queue
 * Output: one merged queue
 */
import java.util.ArrayDeque;
import java.util.Deque;

public class MergingSortedQueue {

    public static ArrayDeque<Comparable> MergingSortedQueue(ArrayDeque<Comparable> queueA, ArrayDeque<Comparable> queueB){
        // TODO: merge ordered queueA and ordered queueB into result (quequeA: 5, 3, 1, queueB: 6, 4, 2)
        int lenA = queueA.size();
        int lenB = queueB.size();
        int lenResult = lenA+lenB;
        ArrayDeque<Comparable> result = new ArrayDeque<>(lenResult);

        // compare e1 and e2, save into result
        if (sort.less(queueA.getLast(), queueB.getFirst())){
            // the largest of queueA is smaller than the smallest in queueB
            copyQueue(queueA, result);
            copyQueue(queueB, result);
        }
        else if (sort.less(queueB.getLast(), queueA.getFirst())){
            // the largest of queueB is smaller than the smallest in queueA
            copyQueue(queueB, result);
            copyQueue(queueA, result);
        }
        else{ // need to compare
            Comparable a;
            Comparable b;
            while (!queueA.isEmpty() & !queueB.isEmpty()){
                a = queueA.pollFirst();
                b = queueB.pollFirst();
                if (sort.less(a, b)){
                    result.add(a);
                    queueB.addFirst(b);
                }
            }
            if (result.size() != lenResult){ // either queueA has some elements left, or queueB has some left
                if (queueA.isEmpty()){
                    copyQueue(queueA,result);
                }
                else{
                    copyQueue(queueB,result);
                }
            }
        }
        return result;
    }

    public static void queueSort(ArrayDeque<Comparable> q){
        ArrayDeque<Comparable> aux = new ArrayDeque<>(q.size());
        ArrayDeque<Comparable> aux2 = new ArrayDeque<>(q.size());
        Comparable e = q.pollFirst();
        aux.add(e);
        Comparable l;
        Comparable r;
        while(q.size() != 0){
            e = q.pollFirst();
            l = aux.getLast();
            r = aux.getFirst();
            if (sort.less(e, r)){aux.addFirst(e);}
            else if (sort.less(l, e)){aux.addLast(e);}
            else{
                while(sort.less(r,e) & sort.less(e,l)){
                    aux2.add(aux.pollFirst());
                    r = aux.getFirst();
                }
                aux2.add(e);
                while(!aux.isEmpty()){aux2.add(aux.pollFirst());}
                aux = aux2.clone();
            }
        }
        q = aux.clone();
    }

    //help function
    public static void copyQueue(ArrayDeque queueFrom, ArrayDeque queueTo){
        //TODO: copy queueFrom into queueTo
        try{
            while(!queueFrom.isEmpty()){
                queueTo.add(queueFrom.pollFirst());
            }
        }catch (Exception e){
            System.out.println("The size of destination queue is smaller than that of original queue");
        }
    }

    public static void printQueue(ArrayDeque q){
        System.out.println("The elements in the array: ");
        ArrayDeque<Comparable> aux = q.clone();
        while(aux.size()>0){
            System.out.print(aux.pollFirst()+"  ");
        }
        System.out.println("");
    }


    public static void main(String[] args) {
        ArrayDeque a = sort.generateStingQueue(5);
        printQueue(a);
        queueSort(a);
        printQueue(a);
    }
}
