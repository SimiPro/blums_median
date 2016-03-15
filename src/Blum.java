import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Blum {

	private List<Integer> R;

	public Blum(List<Integer> R) {
		this.R = R;
	}

	public int blum(int k) {
		return blum(R, k, 0, R.size());
	}

	public int blum(List<Integer> A, int k, int start, int end) {
		int n = end - start;
		if (n <= 5) {
			switch (n) {
			case 5:
				return getMedian(A, 2, start, end);
			case 4:
			case 3:
				return getMedian(A, 1, start, end);
			case 2:
			case 1:
				return getMedian(A, 0, start, end);
			}
		}
		// build group of 5
		// A[0..4], A[4...9]...
		// after that all of our medians are at the front
		List<Integer> mediansOfGroups = new ArrayList<>();
		int storeIndex = 0;
		for (int j = start; j < end; j += 5) {
			if (j + 4 < end) {
				int median = getMedian(A, 2, j, j + 5);
				Collections.swap(A, median, storeIndex);
				mediansOfGroups.add(median);
				storeIndex++;
			} else {
				int r = end % 5;
				switch (r) {
				case 4:
					int median = getMedian(A, 1, j, j + 3);
					mediansOfGroups.add(median);
					Collections.swap(A, median, storeIndex);
					storeIndex++;
					break;
				case 3:
					int median2 = getMedian(A, 1, j, j + 2);
					mediansOfGroups.add(median2);
					Collections.swap(A, median2, storeIndex);
					storeIndex++;
					break;
				case 2:
					int median3 = getMedian(A, 0, j, j + 1);
					mediansOfGroups.add(median3);
					Collections.swap(A, median3, storeIndex);
					storeIndex++;
					break;
				case 1:
					int median4 = getMedian(A, 0, j, j + 0);
					mediansOfGroups.add(median4);
					Collections.swap(A, median4, storeIndex);
					storeIndex++;
					break;
				}
			}
		}
		n = end - start;
		int roundUp = n % 5 == 0 ? n / 5 : n / 5 + 1;
		int median = blum(A, roundUp / 2, 0, storeIndex);
		// pivotIndex = getPivotIndex(A, median);
		// what we get back here is the index of our median of
		median = shuffle(A, median, start, end); // now every
													// element
													// left to the
		// pivot is
		// smaller and every element right is greater
		int kl = start + k - end;
		if (median > k) {
			return blum(A, k, start, median);
			// pivotIndex = getPivotIndex(A, median);
		} else if (k > median) {
			return blum(A, k - (median - start), median + 1, end);
			// pivotIndex = getPivotIndex(A, median);
		}
		return start + k;
	}

	private int getPivotIndex(List<Integer> A, int median) {
		for (int i = 0; i < A.size(); i++) {
			if (A.get(i) == median) {
				return i;
			}
		}
		return -1;
	}

	private int shuffle(List<Integer> A, int pivotIndex, int left, int end) {
		int pivotValue = A.get(pivotIndex);
		Collections.swap(A, pivotIndex, end - 1); // pivot to end
		int storeIndex = left;
		for (int i = left; i < end - 1; i++) {
			if (A.get(i) < pivotValue) {
				Collections.swap(A, storeIndex, i);
				storeIndex++;
			}
		}
		Collections.swap(A, storeIndex, end - 1); // set pivot back
		return storeIndex;
	}

	/**
	 * 
	 * @param has
	 *            to have <= 5 elements
	 * @return median
	 */
	private int getMedian(List<Integer> A, int k, int start, int end) {
		boolean swapped;
		do {
			swapped = false;
			for (int i = start; i < end - 1; i++) {
				if (A.get(i) > A.get(i + 1)) {
					swapped = true;
					Collections.swap(A, i, i + 1);
				}
			}
		} while (swapped);
		return start + k;
	}
}
