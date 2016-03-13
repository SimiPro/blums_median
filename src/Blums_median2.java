import java.util.LinkedList;

/**
 * Created by simipro on 13.03.16.
 */
public class Blums_median2 {


    public void pivot(LinkedList<Integer> A, int left, int right) {
        if (left - right < 5) {

        }

    }

    public int select(LinkedList<Integer> A, int left, int right, int n) {
        if (left == right) {
            return left;
        }


        return left;
    }

    // this routine divides a certain list into 2 groups
    // them greater then 1 element and them smaller
    public int partition(LinkedList<Integer> A, int left, int right, int pivotIndex) {
        int pivotValue = A.get(pivotIndex);
        swap(A, pivotIndex, right);// move pivot to right
        int storeIndex = left;
        for (int i = 0; i < right -1; i++) {
            if (A.get(i) < pivotValue) {
                swap(A, storeIndex, i);
                storeIndex++;
            }
        }
        swap(A, right, storeIndex); // move pivot to final place
        return storeIndex;
    }

    private void swap(LinkedList<Integer> A, int storeIndex, int i) {
        int z = A.get(storeIndex);
        int y = A.get(i);
        A.set(storeIndex, y);
        A.set(i, z);
    }


}
