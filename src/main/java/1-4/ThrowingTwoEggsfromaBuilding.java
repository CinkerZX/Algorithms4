import java.util.LinkedList;

/**
 * The {@CODE ThrowingTwoEggsfromaBuilding} class represents a set of increasing integers N (the floors of the building).
 * It supports determine the lowest floor F, from which throw an egg, the egg will break.
 * Only two eggs are available.
 *
 * Method finF_N detremine F by throwing at most √2*N times
 * Idea: suppose start throwing from x floor, if broken, then throw according to 0,1,2,3...,x-1   (x times in total)
 *                                            if unbroken, then throw from x + (x-1)
 *                                               if broken, then throw according to x+1, x+2, ..., 2*x-2  (x times in total)
 *                                               if unbroken, then throw from x + (x-1) + (x-2) ....
 *       x + (x-1) + (x-2) + ... + 1 = N
 *       x -> sqrt(2*N)
 *
 * Method findF_F determine F by throwing at most c*√F times
 * Idea: throw in the order 0, 1, 3, 6, 10, .... Oi = (1+i)*i/2
 *       Assume egg break when throwing from Oi => O_{i-1} < F < Oi   (O_{i-1} = i*(i-1)/2)   (already throw i times)
 *                                                 => i < (√(8*F) + 1)/2
 *       Between O_{i-1} ~ Oi, need to throw i-1 times, thus in total 2*i-1 times, < √(8*F)
 *
 */
public class ThrowingTwoEggsfromaBuilding {
    private int F; // F is the floor needs to be find, F < H
    private int H;

    public ThrowingTwoEggsfromaBuilding(int floor, int height){
        F = floor;
        H = height;
        if (F >= H){throw new IllegalArgumentException("F has to be less than N");}
    }

    public int findF_N(){
        //TODO: determine the optimized starting floor x
        int x = (int) Math.round(Math.sqrt(H));
        return findF_NHelper(0,x);
    }

    public int findF_NHelper(int l, int h){
//        if (l == h){return l;}
        if (h < F){ // unbroken
            return findF_NHelper(h, h + (h-1));
        }
        else{ // broken
            return traverse(l,h);
        }
    }

    public int traverse(int low, int high){
        //TODO: travel from low to high (included), return if equals to F
        for (int i = low; i < high+1; i++) {
            if (i == F){return i;}
        }
        return -1;
    }

    public int findF_F(){
        return findF_FHelper(0);
    }

    public int findF_FHelper(int trailTime){
        if(generateO(trailTime) < F){ // unbroken
            trailTime += 1;
            return findF_FHelper(trailTime);
        }
        // broken: traverse
        return traverse(generateO(trailTime-1), generateO(trailTime));
    }

    public static int generateO(int trailN){  // 0,1,2, ... N-1
        // TODO: generate the floor candidate
        return (1+trailN)*trailN/2;
    }

    public static void main(String[] args) {
        ThrowingTwoEggsfromaBuilding myThrow = new ThrowingTwoEggsfromaBuilding(7,10);
        System.out.println(myThrow.findF_N()); //7

        ThrowingTwoEggsfromaBuilding myThrow2 = new ThrowingTwoEggsfromaBuilding(1,10);
        System.out.println(myThrow2.findF_N()); //1

        ThrowingTwoEggsfromaBuilding myThrow3 = new ThrowingTwoEggsfromaBuilding(0,10);
        System.out.println(myThrow3.findF_N()); //0

        ThrowingTwoEggsfromaBuilding myThrow4 = new ThrowingTwoEggsfromaBuilding(9,10);
        System.out.println(myThrow4.findF_N()); //9
    }

}
