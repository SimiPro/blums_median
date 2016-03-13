import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Main {

	StreamTokenizer in;
	PrintWriter out;
	Reader reader;
	boolean oj = System.getProperty("LOCALE") != null;
	List<List<Set<Integer>>> z;

	public static void main(String[] args) throws IOException {
		new Main().run();
	}

	int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	void run() throws IOException {
		reader = oj ? new FileReader("public.in") : new InputStreamReader(System.in);
		in = new StreamTokenizer(new BufferedReader(reader));
		in.eolIsSignificant(true);
		out = new PrintWriter(new OutputStreamWriter(System.out));

		solve();
		out.flush();
	}

	int nextToken() throws IOException {
		return in.nextToken();
	}

	void solve() throws IOException {
		nextToken();
		int t = (int) in.nval;
		int i = 0;
		int j = 0;
		Set<Integer> x = new HashSet<>(5);
		List<Set<Integer>> y = new ArrayList<>();
		z = new ArrayList<>(t);

		while (i < t) {
			int nextToken = nextToken();
			switch (nextToken) {
			case StreamTokenizer.TT_NUMBER:
				if (j == 5) {
					j = 0;
					y.add(x);
					x = new HashSet<>(5);
				}
				x.add(Integer.valueOf((int) in.nval));
				j++;
				break;
			case StreamTokenizer.TT_EOL:
				if (!x.isEmpty()) {
					y.add(x);
					x = new HashSet<>(5);
					j = 0;
				}
				z.add(y);
				y = new ArrayList<>();
				i++;
				break;
			}
		}
		solvez();
	}

	boolean isEvenMedian(Set<Integer> group, Integer currElement) {
		int greater = 0;
		int smaller = 0;

		for (Integer c : group) {
			if (c != currElement) {
				if (currElement > c) {
					smaller++;
				} else if (currElement < c) {
					greater++;
				}
			}
		}

		if (smaller + 1 == greater) {
			return true;
		}
		return false;

	}

	void solvez() {
		for (List<Set<Integer>> row : z) {
			// foreach group we get the median
			// if 1 element the smallest one
			// if 2 elements the smaller one
			// if 3 elements the middle one is the median
			// if 4 elements the second smallest element is the median
			// if 5 elements the middle one is the median
			Set<Integer> medians = new HashSet<>();
			for (Set<Integer> group : row) {
				for (Integer integer : group) {
					if (isMedian(group, integer)) {
						out.print(integer);
						medians.add(integer);
						break;
					}
				}
			}

		}
	}

	// TODO: Maybe check this so that we have here no for loop. should be easy
	// this method works for every odd sized group
	boolean isOddMedian(Set<Integer> group, Integer currElement) {
		int greater = 0;
		int smaller = 0;

		for (Integer c : group) {
			if (c != currElement) {
				;
				if (currElement > c) {
					smaller++;
				} else if (currElement < c) {
					greater++;
				}
			}
		}

		if (greater == smaller) {
			return true;
		} else {
			return false;
		}
	}

	double readNumber() throws IOException {
		if (in.ttype == StreamTokenizer.TT_NUMBER) {
			return in.nval;
		}
		throw new IllegalStateException("Number expected. Found: " + in.ttype);
	}

	String readWord() throws IOException {
		if (in.ttype == StreamTokenizer.TT_WORD) {
			return in.sval;
		}
		throw new IllegalStateException("Word expected. Found: " + in.ttype);
	}

	public boolean isMedian(Set<Integer> group, int i) {
		if (group.size() % 2 == 0) {
			return isEvenMedian(group, i);
		}
		return isOddMedian(group, i);
	}
}
