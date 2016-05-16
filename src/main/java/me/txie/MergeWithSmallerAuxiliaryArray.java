package me.txie;

/**
 * Merging with smaller auxiliary array.
 * Suppose that the subarray a[0] to a[N-1] is sorted and the subarray a[N] to a[2*N-1] is sorted.
 * How can you merge the two subarrays so that a[0] to a[2*N-1] is sorted using an auxiliary array of size N (instead of 2N)?
 */
public class MergeWithSmallerAuxiliaryArray {
    private MergeWithSmallerAuxiliaryArray() {
    }

    private static void merge(Comparable[] a) {
        int N = a.length / 2;

        assert isSorted(a, 0, N - 1);
        assert isSorted(a, N, a.length - 1);

        Comparable[] aux = new Comparable[N];

        for (int k = 0; k < N; k++) {
            aux[k] = a[k];
        }

        int i = 0, j = N;
        for (int k = 0; k < a.length; k++) {
            if (i > N - 1) a[k] = a[j++];
            else if (j > a.length - 1) a[k] = aux[i++];
            else if (less(a[j], aux[i])) a[k] = a[j++];
            else a[k] = aux[i++];
        }
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (less(a[i], a[i - 1]))
                return false;
        }
        return true;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        Comparable[] a = {40, 61, 70, 71, 99, 20, 51, 55, 75, 100, 101};
        MergeWithSmallerAuxiliaryArray.merge(a);
        System.out.println("After merging: ");
        for (Comparable c : a) {
            System.out.print(c + ", ");
        }
    }
}
