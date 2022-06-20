import java.util.stream.IntStream;

/**
 * The {@Code HotOrCold} class realize the function of guessing a secret int X in [1,2,3, .., N]
 * X is of the index X-1
 * ---------------------------------------------------------------------------------
 * int guessX2Index         find X and return
 * Idea of: index = 0
 *          indexCandidate = n-1
 *          check if indexCandidate == index
 *          if yes, return
 *          else
 *              check(index), check(indexCandidate)
 *              if colder(get far) => X is closer to index
 *                  indexCandidate = (indexCandidate + index)/2
 *              if warmer => X is closer to indexCandidate
 *                  index = (index + indexCandidate)/2
 * Note: It takes ~2logN time in the worst case
 *
 * Idea2: l = 0, r = n-1, temp = (l+r)/2
 *        check if l == r
 *        if yes, return
 *        else
 *            check(index), check(temp)
 *            if warmer(get closer) and colder(get father) => (index, (index+temp)/2)
 *            if warmer(get closer) and warmer(get closer)
 *               check(candidate)
 *               if warmer(get closer) and warmer(get closer) and colder(get father) => ((index+temp)/2, (temp+candidate)/2)
 *               if warmer(get closer) and warmer(get closer) and warmer(get closer) => ((temp+candidate)/2, candidate)
 * Note: It takes ~logN time in the worst case
 *
 */
public class HotOrCold {
    private int[] N;
    private int X;
    private int distance; // the distance from the guessing number to X

    public HotOrCold(int x, int n){
        if (x>n || x<1){throw new IllegalArgumentException("The unknown X need to be within [1,N]");}
        N =  IntStream.range(1, n+1).toArray();;
        X = x;
        distance = n;
    }

    public int guessX2Index() {
        distance = N.length;
        int initialIndex = 0;
        int indexCandidate = N.length-1;
        if (initialIndex == indexCandidate){return N[indexCandidate];}
        int updateIndex;
        boolean checkInitialIndex = HotCold(initialIndex); // record the X-0
        boolean checkCandidateIndex = HotCold(indexCandidate); // record the N-X
        //TODO: if both true => dis(X,initialIndex) > dis(X,indexCandidate)
        if(checkInitialIndex && checkCandidateIndex){
            initialIndex = (initialIndex + indexCandidate) / 2;
            updateIndex = 1;
            return guessX2IndexHelper(initialIndex, indexCandidate, updateIndex);
        }
        //TODO: if both false => dis(X,initialIndex) <= dis(X,indexCandidate)
        if (!checkInitialIndex && !checkCandidateIndex){
            indexCandidate = (initialIndex + indexCandidate) / 2;
            updateIndex = 2;
            return guessX2IndexHelper(initialIndex,indexCandidate,updateIndex);
        }
        //TODO: if false true => dis(X,indexCandidate) == 0
        if (!checkInitialIndex){
            return N[indexCandidate];
        }
        //TODO: if true false => dis(X,initialIndex) < dis(X,indexCandidate)
        indexCandidate = (initialIndex + indexCandidate) / 2;
        updateIndex = 2;
        return guessX2IndexHelper(initialIndex,indexCandidate, updateIndex);
    }

    public int guessX2IndexHelper(Integer index, Integer candidate, Integer updated){
        //Special case: there is only one element in N
        if (index == candidate){return N[candidate];}
        distance = Math.abs(candidate-index)+1;

        boolean checkInitialIndex = HotCold(index); // record the X-0
        boolean checkCandidateIndex = HotCold(candidate); // record the N-X
        //TODO: if both true => dis(X,initialIndex) > dis(X,indexCandidate)
        if(checkInitialIndex && checkCandidateIndex){
            index = (index + candidate) / 2;
            updated = 1;
            if (candidate-index==1){return N[candidate];}
            return guessX2IndexHelper(index, candidate, updated);
        }

        if (!checkInitialIndex && !checkCandidateIndex){
            if (updated == 2){
                updated = 1; //TODO: if both false => dis(X,initialIndex) <= dis(X,indexCandidate)
                candidate = (index + candidate) / 2;
                return guessX2IndexHelper(candidate,index, updated);
            }
            //TODO: if both false => dis(X,initialIndex) >= dis(X,indexCandidate)
            index = (index + candidate) / 2;
            return guessX2IndexHelper(index,candidate, updated);
        }
        //TODO: if false true => dis(X,indexCandidate) == 0
        if (!checkInitialIndex){return N[candidate];
        }
        //TODO: if true false => dis(X,initialIndex) < dis(X,indexCandidate)
        candidate = (index + candidate) / 2;
        updated = 2;
        return guessX2IndexHelper(index,candidate, updated);
    }

    public int guessX3Index(){
        distance = N.length;
        int initialIndex = 0;
        int indexCandidate = N.length-1;
        int temp = (initialIndex + indexCandidate)/2;
        //Special case: there is only one element in N
        if (initialIndex == indexCandidate){return N[indexCandidate];}

        boolean checkInitialIndex = HotCold(initialIndex); // record the X-0
        //TODO: false(InitialIndex) => X == N[indexCandidate], return directly
        if (!checkInitialIndex){
            return N[indexCandidate];
        }

        boolean checkTemp = HotCold(temp); // record the X-temp
        //TODO: true(InitialIndex), false(temp) => X in [InitialIndex, (InitialIndex+temp)/2]
        if (!checkTemp){
            return guessX3IndexHelper(initialIndex,(initialIndex+temp)/2);
        }

        boolean checkCandidateIndex = HotCold(indexCandidate); // record the N-X
        //TODO: true(InitialIndex), true(temp), false(Candidate) => X in [(InitialIndex+temp)/2, (temp+Candidate)/2]
        if (!checkCandidateIndex){
            return guessX3IndexHelper((initialIndex+temp)/2, (temp+indexCandidate)/2);
        }

        //TODO: true(InitialIndex), true(temp), true(Candidate) => X in [(temp+Candidate)/2, Candidate]
        return guessX3IndexHelper((temp+indexCandidate)/2,indexCandidate);
    }

    public int guessX3IndexHelper(int initial, int candidate){
        distance = Math.abs(candidate-initial)+1;
        if (initial == candidate){return N[initial];}
        boolean checkInitialIndex = HotCold(initial); // record the X-0
        //TODO: false(InitialIndex) => X == N[indexCandidate], return directly
        if (!checkInitialIndex){
            return N[candidate];
        }

        int temp = (initial+candidate)/2;
        if (temp == initial){return N[candidate];}
        boolean checkTemp = HotCold(temp); // record the X-temp
        //TODO: true(InitialIndex), false(temp) => X in [InitialIndex, (InitialIndex+temp)/2]
        if (!checkTemp){
            return guessX3IndexHelper(initial,(initial+temp)/2);
        }

        boolean checkCandidateIndex = HotCold(candidate); // record the N-X
        //TODO: true(InitialIndex), true(temp), false(Candidate) => X in [(InitialIndex+temp)/2, (temp+Candidate)/2]
        if (!checkCandidateIndex){
            return guessX3IndexHelper((initial+temp)/2, (temp+candidate)/2);
        }

        //TODO: true(InitialIndex), true(temp), true(Candidate) => X in [(temp+Candidate)/2, Candidate]
        return guessX3IndexHelper((temp+candidate)/2,candidate);
    }

    public boolean HotCold(int pointer){
        //TODO: return if pointer is getting closer (return true) or further (return false)
        int newDistance = Math.abs(X-(pointer+1));
        if (newDistance < distance){
            distance = newDistance;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        HotOrCold guessGame = new HotOrCold(5,10);
        System.out.println(guessGame.guessX2Index());
        System.out.println(guessGame.guessX3Index());

        HotOrCold guessGame1 = new HotOrCold(1,10);
        System.out.println(guessGame1.guessX2Index());
        System.out.println(guessGame1.guessX3Index());

        HotOrCold guessGame2 = new HotOrCold(10,10);
        System.out.println(guessGame2.guessX2Index());
        System.out.println(guessGame2.guessX3Index());

        HotOrCold guessGame3 = new HotOrCold(3,10);
        System.out.println(guessGame3.guessX2Index());
        System.out.println(guessGame3.guessX3Index());

        HotOrCold guessGame4 = new HotOrCold(9,10);
        System.out.println(guessGame4.guessX2Index());
        System.out.println(guessGame4.guessX3Index());
    }
}











