import java.io.*;
import java.util.*;

/**
 * Created by simipro on 13.03.16.
 */
class Main {

    public static void main(String[] args) throws Exception {
        new Main().run();
    }


    StreamTokenizer in;
    PrintWriter out;
    Reader reader;
    boolean oj = System.getProperty("LOCALE") != null;
    List<List<OrderedContainer>> z;
    List<OrderedContainer> fullLine;

    void run() throws Exception {
        reader = oj ? new FileReader("public.in") : new InputStreamReader(System.in);
        in = new StreamTokenizer(new BufferedReader(reader));
        in.eolIsSignificant(true);
        out = new PrintWriter(new OutputStreamWriter(System.out));
        fullLine = new ArrayList<>();

        solve();
        out.flush();
    }

    private void solve() throws Exception {
        nextToken();
        int t = (int) in.nval;
        nextToken(); // ignore end of line
        int i = 0;
        int j = 0;
        boolean ignoreFirst = true;
        String llll = "";
        OrderedContainer x = new OrderedContainer();
        List<OrderedContainer> y = new ArrayList<>();
        z = new ArrayList<>(t);

        OrderedContainer cont = new OrderedContainer();
        while (i < t) {

            int nextToken = nextToken();
            if (!ignoreFirst) {
                switch (nextToken) {
                    case StreamTokenizer.TT_NUMBER:
                        if (j == 5) {
                            j = 0;
                            y.add(x);
                            x = new OrderedContainer();
                        }
                        x.add((int) in.nval);
                        j++;
                        cont.add((int)in.nval);
                        break;
                    case StreamTokenizer.TT_EOL:
                        if (!x.isEmpty()) {
                            y.add(x);
                            x = new OrderedContainer();
                            j = 0;
                        }
                        z.add(y);
                        fullLine.add(cont);
                        cont = new OrderedContainer();
                        y = new ArrayList<>();
                        i++;
                        ignoreFirst = true;
                        break;
                }
            } else {
                ignoreFirst = false;
            }
        }
        solvez();
    }

    private void solvez() throws Exception {
        int i = 0;
        for (List<OrderedContainer> line:   z ) {
            OrderedContainer medians = new OrderedContainer();
            for(OrderedContainer o: line) {
                int median = o.getMedian();
                out.print(median + " ");
                medians.add(median);
            }
            out.print(medians.getMedian() + " ");
            out.print(fullLine.get(i).getMedian());
            out.println();
            i++;
        }
    }

    private int nextToken() throws IOException {
        return in.nextToken();
    }

}
