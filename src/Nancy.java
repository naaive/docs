import java.util.Arrays;
import java.util.List;

public class Nancy {
    static {
        System.out.println("hello");

    }
    public static void main(String[] args) throws InterruptedException {
        int[] ints = {5, 3, 4, 33, 61, 123, 56, 123, 55, 231, 6656, 442, 7, 2};

        while (true) {
            System.out.println(123);
            Thread.sleep(2000);
        }
        //qsort(ints, 0, ints.length - 1);

        // msort(ints,from,to)=merge(msort(ints,from,mid), msort(ints,mid+1,to))
        //msort(ints, 0, ints.length - 1);
        //System.out.println(Arrays.toString(ints));

    }



    private static void msort(int[] ints, int from, int to) {
        if (from < to) {
            int mid = from + to >> 1;
            msort(ints, from, mid);
            msort(ints, mid + 1, to);
            merge(ints, from, to, mid);
        }
    }

    private static void merge(int[] ints, int from, int to, int mid) {
        int firstFrom = from;
        int firstTo = mid;
        int secondFrom = mid + 1;
        int secondTo = to;
        int[] tmpArr = new int[to - from + 1];
        while (true) {
            while (firstFrom <= firstTo) {
                if (ints[firstFrom] < ints[firstTo]) {

                }
            }
        }
    }

    private static void qsort(int[] ints, int from, int to) {
        if (from < to) {
            int pivot = partition(ints, from, to);
            qsort(ints, from, pivot - 1);
            qsort(ints, pivot + 1, to);
        }
    }

    private static int partition(int[] ints, int from, int to) {
        int pivotIndex = from;
        int pivotVal = ints[from];
        from++;
        while (true) {
            while (ints[from] < pivotVal && from < to) {
                from++;
            }
            while (pivotVal < ints[to] && from < to) {
                to--;
            }
            if (from < to) {
                swap(ints, from, to);
            }
            if (from == to) {
                if (pivotVal < ints[from]) {
                    swap(ints, pivotIndex, from - 1);
                    return from - 1;

                } else {
                    swap(ints, pivotIndex, from);
                    return from;
                }
            }

        }
    }

    private static void swap(int[] ints, int from, int to) {
        int tm = ints[from];
        ints[from] = ints[to];
        ints[to] = tm;
    }

}
