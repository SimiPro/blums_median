import java.util.LinkedList;

/**
 * Created by simipro on 13.03.16.
 */
public class OrderedContainer {
    LinkedList<Integer> A;

    OrderedContainer() {
        A = new LinkedList<>();
    }

    public boolean isEmpty() {
        return A.isEmpty();
    }


    public OrderedContainer add(int... v) {
        for (int i = 0; i< v.length; i++) {
            add(v[i]);
        }
        return this;
    }

    public void add(int v) {
        if (A.size() == 0) {
            A.add(v);
        } else if (A.get(0) > v) {
            A.add(0, v);
        } else if (A.get(A.size() - 1) < v) {
            A.add(v);
        } else {
            int j = 0;
            while (A.get(j) < v) {
                j++;
            }
            A.add(j, v);
        }
    }

    public int getMedian() throws Exception {
        switch(A.size()) {
            case 5: return A.get(2);
            case 4: return A.get(1);
            case 3: return A.get(1);
            case 2: return A.get(0);
            case 1: return A.get(0);
            default: return A.get((A.size()-1) /2 );
        }

    }
}
