package me.txie;

/**
 * Counting inversions. An inversion in an array a[] is a pair of entries a[i] and a[j] such that i < j but a[i] > a[j].
 * Given an array, design a linearithmic algorithm to count the number of inversions.
 */
public class CountingInversions {
    private CountingInversions() {
    }

    // FIXME: 这种方法的时间复杂度是多少? 我算不出来.
    public static int inversions(Comparable[] a) {
        int N = a.length;
        int count = 0;

        for (int sz = 1; sz < N; sz = sz + sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                int mid = lo + sz - 1;
                int hi = Math.min(lo + sz + sz - 1, N - 1);
                for (int i = lo; i <= mid; i++) {
                    for (int j = mid + 1; j <= hi; j++) {
                        if (a[i].compareTo(a[j]) > 0) {
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Comparable[] a = {0};
        System.out.println(CountingInversions.inversions(a));

        Comparable[] b = {0, 0, 1};
        System.out.println(CountingInversions.inversions(b));

        Comparable[] c = {1, 0, 0};
        System.out.println(CountingInversions.inversions(c));

        Comparable[] d = {2, 0, -1};
        System.out.println(CountingInversions.inversions(d));

        Comparable[] e = {4, 3, 2, 1};
        System.out.println(CountingInversions.inversions(e));
    }
}
