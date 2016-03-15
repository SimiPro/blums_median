import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Main {

	StreamTokenizer in;
	static PrintWriter out;
	Reader reader;
	boolean oj = System.getProperty("LOCALE") != null;
	List<List<Set<Integer>>> z;

	int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	public static void main(String[] args) throws IOException {
		new Main().run();
	}

	void run() throws IOException {
		reader = oj ? new FileReader("public.in") : new InputStreamReader(System.in);
		in = new StreamTokenizer(new BufferedReader(reader));
		out = new PrintWriter(new OutputStreamWriter(System.out));

		read();
		out.flush();
	}

	int nextToken() throws IOException {
		return in.nextToken();
	}

	void read() throws IOException {
		nextToken();
		int t = (int) in.nval;

		for (int j = 0; j < t; j++) {
			nextToken();
			int lineCount = (int) in.nval;
			int[] A = new int[lineCount];
			for (int i = 0; i < lineCount; i++) {
				nextToken();
				A[i] = (int) in.nval;
			}
			out.print(findMedian(A));
			out.println();
			out.flush();
		}
	}

	public static int findMedian(int A[]) {
		int k = A.length % 2 == 0 ? A.length / 2 : A.length / 2 + 1;
		return findMedian(A, k, 0, A.length - 1);
	}

	private static int findMedian(int[] A, int k, int start, int end) {
		if (start == end) {
			return A[start];
		}
		int median = shuffle(A, start, end);
		int lenght = median - start + 1;
		if (lenght == k) {
			return A[median];
		}
		if (lenght > k) {
			return findMedian(A, k, start, median - 1);
		} else {
			return findMedian(A, k - lenght, median + 1, end);
		}
	}

	static void swap(int[] A, int a, int b) {
		int temp = A[a];
		A[a] = A[b];
		A[b] = temp;
	}

	private static int shuffle(int[] A, int start, int end) {
		int pivot = getPivot(true, A, start, end);
		int pivotIndex = 0;
		int lefti = start;
		for (int i = start; i <= end; i++) {
			if (A[i] == pivot) {
				pivotIndex = i;
			}
		}
		swap(A, pivotIndex, end);
		for (int i = start; i < end; i++) {
			if (A[i] <= pivot) {
				swap(A, i, lefti);
				lefti++;
			}
		}
		swap(A, lefti, end);
		return lefti;
	}

	private static int getPivot(boolean first, int[] A, int start, int end) {
		if (end - start + 1 < 5) {
			Arrays.sort(A);
			int mediansOfMedians = -0;
			switch (end - start + 1) {
			case 5:
				mediansOfMedians = A[start + 2];
				break;
			case 4:
			case 3:
				mediansOfMedians = A[start + 1];
				break;
			case 2:
			case 1:
				mediansOfMedians = A[start + 0];
			}
			out.print(mediansOfMedians + " ");
			if (first) {
				out.print(mediansOfMedians + " ");
			}
			return mediansOfMedians;
		}
		int temp[] = null;
		int mediansSize = (int) Math.ceil((double) (end - start + 1) / 5); // A.length
																			// %
																			// 5
																			// ==
																			// 0
																			// ?
		// A.length / 5 :
		// A.length / 5 + 1;
		int medians[] = new int[mediansSize];
		int mIndex = 0;

		while (start <= end) {
			temp = new int[Math.min(5, end - start + 1)]; // end - start + 1 > 5
															// ? 5 : end - start
															// + 1
			for (int i = 0; i < temp.length && start <= end; i++) {
				temp[i] = A[start];
				start++;
			}
			Arrays.sort(temp);
			medians[mIndex] = temp[temp.length / 2];
			out.print(medians[mIndex] + " ");
			mIndex++;
		}
		return getPivot(false, medians, 0, medians.length - 1); // medians of
																// median
	}

}
