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
		List<Integer> mediansOfGroups = new ArrayList<>();
		for (int j = start; j < end; j += 5) {
			if (j + 4 < end) {
				mediansOfGroups.add(getMedian(A, 2, j, j + 5));
			} else {
				int r = end % 5;
				switch (r) {
				case 4:
					mediansOfGroups.add(getMedian(A, 1, j, j + 3));
					break;
				case 3:
					mediansOfGroups.add(getMedian(A, 1, j, j + 2));
					break;
				case 2:
					mediansOfGroups.add(getMedian(A, 0, j, j + 1));
					break;
				case 1:
					mediansOfGroups.add(getMedian(A, 0, j, j + 0));
					break;
				}
			}
		}
		int roundUp = mediansOfGroups.size() % 5 == 0 ? mediansOfGroups.size() / 5 : mediansOfGroups.size() / 5 + 1;
		int median = blum(mediansOfGroups, roundUp / 2, 0, mediansOfGroups.size());
		int pivotIndex = -1;
		pivotIndex = getPivotIndex(A, median);
		pivotIndex = shuffle(A, pivotIndex, start, end); // now every element
															// left to the
		// pivot is
		// smaller and every element right is greater
		if (k == pivotIndex) {
			return pivotIndex;
		} else if (k < pivotIndex) {
			median = blum(A, k, start, pivotIndex);
			pivotIndex = getPivotIndex(A, median);
		} else {
			median = blum(A, k - pivotIndex, pivotIndex + 1, end - pivotIndex);
			pivotIndex = getPivotIndex(A, median);
		}

		return pivotIndex;
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
		return A.get(start + k);
	}
}
