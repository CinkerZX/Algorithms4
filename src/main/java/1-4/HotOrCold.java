import java.util.stream.IntStream;

/**
 * The {@Code HotOrCold} class realize the function of guessing a secret int X in [1,N]
 *
 * ---------------------------------------------------------------------------------
 * int guessX2Index         find X and return
 * Idea of : index = 0
 *       check if index == X
 *       if yes, return index
 *       else
 *          indexCandidate = n-1
 *          check if indexCandidate == X
 *          if yes, return
 *          else
 *              if colder(get far) => X is closer to index
 *              indexCandidate = (indexCandidate + index)/2
 *              if hoter => X is closer to indexCandidate
 *              index = (index + indexCandidate)/2
 * Note: It takes ~2logN time in the worst case
 *
 * Idea2: l = 0, r = n-1, temp = (l+r)/2
 *        check if l == X
 *        if yes, return
 *        else
 *            check if temp == X
 *            if yes, return
 *            if colder(get far) => X is in [l,temp], and is closer to l
 *                  r = temp;
 *                  temp = (l+r)/2
 *            irritate
 * Note: It takes ~logN time in the worst case
 *
 */
public class HotOrCold {
    private int[] N;
    private int X;

    public HotOrCold(int n, int x){
        N =  IntStream.range(1, n).toArray();;
        X = x;
    }

//    public static int guessX2Index(){
//
//    }
}










