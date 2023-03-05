public class sort {
    // no constructor

    public static void merge(Comparable[] a, int lo, int mid, int hi){
        //Todo: merge array a[lo, mid] and a[mid+1, hi]
        Comparable[] aux = new Comparable[hi-lo]; //copy a[lo, hi] to aux[]
        for (int k = lo; k <hi ; k++) {
            aux[k] = a[k];
        }

        // merge back to a[lo, hi]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi ; k++) {
            if (i>mid) // a[0, 1] is sorted, and a[0] < a[1]
                // i = k = lo = 1, aux[a[1], a[0], a[2], a[3]]
                // a[0] = aux[1]
                a[k] = aux[j++];
            else if (j>hi) // a[2,3] is sorted, and a[2] < a[3]
                // j = 3, hi = 2, k = 2, i = 2, aux[a[0], a[1], a[3], a[2]];
                // a[2] = aux[3]
                a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++]; // if the smallest on the right size is smaller
            else
                a[k] = aux[i++]; // the smallest on the left is smaller
        }
    }

    //Help fun
    private static boolean less(Comparable a, Comparable b) {
        if (a.compareTo(b)<0)
            return true;
        else
            return false;
    }

}
