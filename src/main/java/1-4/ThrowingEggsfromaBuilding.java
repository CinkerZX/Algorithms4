import java.util.LinkedList;

/**
 * The {@CODE} ThrowingEggsfromaBuilding class represents a set of Integers from 0 to N-1
 * which represent the hight of the building.
 * If an egg thrown from the flour higher than F, then it will broken
 * The algorithm finding F wastes ~2lgN in the worst case
 *
 * Idea: throw the eggs from the lower floor to higher floor, according to the Fibonacci series
 */
public class ThrowingEggsfromaBuilding {
    private int F; // F is the floor needs to be find
    private int H;
    private LinkedList<Integer> waitingList; // The floors need to be test

    public ThrowingEggsfromaBuilding(int floor, int height){
        F = floor;
        H = height;
        BinarySearchwithAddSubtract myBS = new BinarySearchwithAddSubtract(generateIntArray(height));
        waitingList = myBS.getFibonacci();
    }

    public static int[] generateIntArray(int length){  // 0,1,2, ... N-1
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = i;
        }
        return result;
    }

    public int findF(){
        //TODO: determine the initial searching interval
        LinkedList<Integer> fiboCopy = (LinkedList<Integer>) waitingList.clone();
        int[] floors = generateIntArray(H);
        int i;
        int testF = fiboCopy.peekFirst(); // the last num in fibo series
        if (F < floors[testF]){ // if contain F, F must be in [0, testF]
            i = 0;
        }
        else{ // x must be in [set.length-F -1, set.length-testF + testF -1]
            i = floors.length-testF-1;
        }
        fiboCopy.removeFirst();
        return findFHelper(i, testF+i, fiboCopy);
    }

    public int findFHelper(int l, int r, LinkedList<Integer> fibo){
        int k1;
        int k2;
        int[] floors = generateIntArray(H);
        while(fibo.size()>=2){
            k1 = fibo.removeFirst(); // Fk-1
            k2 = fibo.removeFirst(); //Fk-2
            int s = floors[l+k2];
            if (s == F){return s;}
            if (s > F){ // if contains, must be in [l, l+k2]
                return findFHelper(l, l+k2, fibo);
            }
            return findFHelper(l+k1,l+k1+k2, fibo); // if contains, must be in [l+k2, r]
        }
        if (floors[l] == F){return l;}
        return -1;
    }

    public static void main(String[] args) {
        ThrowingEggsfromaBuilding myThrow = new ThrowingEggsfromaBuilding(7,10);
        System.out.println(myThrow.findF()); //7

        ThrowingEggsfromaBuilding myThrow2 = new ThrowingEggsfromaBuilding(1,10);
        System.out.println(myThrow2.findF()); //1

        ThrowingEggsfromaBuilding myThrow3 = new ThrowingEggsfromaBuilding(0,10);
        System.out.println(myThrow3.findF()); //0

        ThrowingEggsfromaBuilding myThrow4 = new ThrowingEggsfromaBuilding(9,10);
        System.out.println(myThrow4.findF()); //9

        ThrowingEggsfromaBuilding myThrow5 = new ThrowingEggsfromaBuilding(10,10); // only has 9 floors
        System.out.println(myThrow5.findF()); //-1
    }

}
