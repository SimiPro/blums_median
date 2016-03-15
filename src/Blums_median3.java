import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Blums_median3 {

	public static void main(String[] args) {
		LinkedList<Container> A = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			A.add(new Container(i, new Random().nextInt(100)));
		}
		System.out.println(new Blums_median3().find_kth_largest_element(A, A.size() / 2, 0, A.size() - 1));
	}

	public Container median(List<Container> A, int b, int e) {
		List<OrderedContainer> groups = new ArrayList<>();
		// split in 5 groups and order them
		if (A.size() < 5) {
			switch (A.size()) {
			case 4:
			case 3:
				return A.get(1);
			case 2:
			case 1:
				return A.get(0);
			}
		} else {
			for (int j = b; j < e; j += 5) {
				OrderedContainer group = new OrderedContainer();
				for (int i = 0; i < 5; i++) {
					group.add(new Container(i * j, A.get(i + j).getValue()));
				}
				groups.add(group);
			}
		}

		// get medians of div groups
		List<Container> medians = new LinkedList<>();
		for (int i = 0; i < groups.size(); i++) {
			medians.add(groups.get(i).getMedian());
		}

		return find_kth_largest_element(medians, medians.size() / 2, 0, medians.size() - 1);
	}

	public Container find_kth_largest_element(List<Container> A, int k, int beg, int end) {
		Container median = median(A, beg, end);
		swapElements((LinkedList<Container>) A, median, beg, end);
		if (median.getIndex() == k) {
			return median;
		} else if (median.getIndex() < k) {
			return find_kth_largest_element(A, k, median.getIndex() + 1, end);
		} else {
			return find_kth_largest_element(A, k - (A.size() - median.getIndex()), 0, median.getIndex());
		}
	}

	private Container swapElements(LinkedList<Container> A, Container pivot, int left, int right) {
		swap(A, pivot.getIndex(), right);
		int lefti = left;
		for (int i = 0; i < right - 1; i++) { // stop before pivot
			if (A.get(i).getValue() < pivot.getValue()) {
				swap(A, lefti, i);
				lefti++;
			}
		}
		swap(A, right, lefti); // swap pivot to final position
		return A.get(lefti);
	}

	private void swap(LinkedList<Container> A, int storeIndex, int i) {
		Container z = A.get(storeIndex);
		Container y = A.get(i);
		A.set(storeIndex, y);
		A.set(i, z);
	}

	public class OrderedContainer {
		LinkedList<Container> A;

		OrderedContainer() {
			A = new LinkedList<>();
		}

		public boolean isEmpty() {
			return A.isEmpty();
		}

		public OrderedContainer add(Container... v) {
			for (int i = 0; i < v.length; i++) {
				add(v[i]);
			}
			return this;
		}

		public void add(Container v) {
			if (A.size() == 0) {
				A.add(v);
			} else if (A.get(0).getValue() > v.getValue()) {
				A.add(0, v);
			} else if (A.get(A.size() - 1).getValue() < v.getValue()) {
				A.add(v);
			} else {
				int j = 0;
				while (A.get(j).getValue() < v.getValue()) {
					j++;
				}
				A.add(j, v);
			}
		}

		public Container getMedian() {
			switch (A.size()) {
			case 5:
				return A.get(2);
			case 4:
				return A.get(1);
			case 3:
				return A.get(1);
			case 2:
				return A.get(0);
			case 1:
				return A.get(0);
			default:
				return A.get((A.size() - 1) / 2);
			}
		}
	}

	public static class Container {

		/**
		 * Index in the original unsorted list
		 */
		private int index;
		private int value;

		public Container(int index, int value) {
			setIndex(index);
			setValue(value);

		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}
}
