import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MedianTest {


    @Test
    public void testContainer() throws Exception {
        OrderedContainer c = new OrderedContainer().add(1,2,3,4,5);
        Assert.assertEquals(3, c.getMedian());
    }

    @Test
    public void test1234() throws Exception {
        OrderedContainer c = new OrderedContainer().add(1,2,3,4);
        Assert.assertEquals(2, c.getMedian());
    }
    @Test
    public void test11144() throws Exception {
        OrderedContainer c = new OrderedContainer().add(1,1,1,4,4);
        Assert.assertEquals(1, c.getMedian());
    }


    @Test
    public void test1224() throws Exception {
        OrderedContainer c = new OrderedContainer().add(1,2,2,4);
        Assert.assertEquals(2, c.getMedian());
    }


    @Test
    public void test1124() throws Exception {
        OrderedContainer c = new OrderedContainer().add(1,1,2,4);
        Assert.assertEquals(1, c.getMedian());
    }


    @Test
    public void test1244() throws Exception {
        OrderedContainer c = new OrderedContainer().add(1,2,4,4);
        Assert.assertEquals(2, c.getMedian());
    }

    @Test
    public void test1144() throws Exception {
        OrderedContainer c = new OrderedContainer().add(1,1,4,4);
        Assert.assertEquals(1, c.getMedian());
    }

    @Test
    public void test1111() throws Exception {
        OrderedContainer c = new OrderedContainer().add(1,1,1,1);
        Assert.assertEquals(1, c.getMedian());
    }
    @Test
    public void test1121() throws Exception {
        OrderedContainer c = new OrderedContainer().add(1,1,2,1);
        Assert.assertEquals(1, c.getMedian());
    }

    @Test
    public void test11() throws Exception {
        OrderedContainer c = new OrderedContainer().add(1,1);
        Assert.assertEquals(1, c.getMedian());
    }

    @Test
    public void test12() throws Exception {
        OrderedContainer c = new OrderedContainer().add(1,2);
        Assert.assertEquals(1, c.getMedian());
    }
    @Test
    public void test123() throws Exception {
        OrderedContainer c = new OrderedContainer().add(1,2,3);
        Assert.assertEquals(2, c.getMedian());
    }
    @Test
    public void test112() throws Exception {
        OrderedContainer c = new OrderedContainer().add(1,1,2);
        Assert.assertEquals(1, c.getMedian());
    }
    @Test
    public void test122() throws Exception {
        OrderedContainer c = new OrderedContainer().add(1,2,2);
        Assert.assertEquals(2, c.getMedian());
    }


    @Test
    public void test12345() throws Exception {
        OrderedContainer c = new OrderedContainer().add(1,2,3,4,5);
        Assert.assertEquals(3, c.getMedian());
    }
    @Test
    public void test12445() throws Exception {
        OrderedContainer c = new OrderedContainer().add(1,2,4,4,5);
        Assert.assertEquals(4, c.getMedian());
    }

	private Set<Integer> getList(final int... i) {
		ArrayList<Integer> arrayList = new ArrayList<Integer>() {
			{
				for (int j = 0; j < i.length; j++) {
					add(Integer.valueOf(i[j]));
				}

			}
		};

		Set<Integer> set = new HashSet<>();
		set.addAll(arrayList);
		return set;
	}

}
