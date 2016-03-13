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
	public void isEvenMedian1234() throws Exception {
		Set<Integer> group = getList(1, 2, 3, 4);
		Assert.assertFalse(new Main().isMedian(group, 1));
		Assert.assertTrue(new Main().isMedian(group, 2));
		Assert.assertFalse(new Main().isMedian(group, 3));
		Assert.assertFalse(new Main().isMedian(group, 4));
	}

	@Test
	public void isEvenMedian1224() throws Exception {
		Set<Integer> group = getList(1, 2, 2, 4);
		Assert.assertFalse(new Main().isMedian(group, 1));
		Assert.assertTrue(new Main().isMedian(group, 2));
		Assert.assertFalse(new Main().isMedian(group, 4));
	}

	@Test
	public void isEvenMedian1124() throws Exception {
		Set<Integer> group = getList(1, 1, 2, 4);
		Assert.assertFalse(new Main().isMedian(group, 1));
		Assert.assertTrue(new Main().isMedian(group, 2));
		Assert.assertFalse(new Main().isMedian(group, 4));
	}

	@Test
	public void isEvenMedian1244() throws Exception {
		Set<Integer> group = getList(1, 2, 4, 4);
		Assert.assertFalse(new Main().isMedian(group, 1));
		Assert.assertTrue(new Main().isMedian(group, 2));
		Assert.assertFalse(new Main().isMedian(group, 4));
	}

	@Test
	public void isEvenMedian1144() throws Exception {
		Set<Integer> group = getList(1, 1, 4, 4);
		Assert.assertTrue(new Main().isMedian(group, 1));
		Assert.assertFalse(new Main().isMedian(group, 4));
	}

	@Test
	public void isEvenMedian1111() throws Exception {
		Set<Integer> group = getList(1, 1, 1, 1);
		Assert.assertTrue(new Main().isMedian(group, 1));
	}

	@Test
	public void isEvenMedian1121() throws Exception {
		Set<Integer> group = getList(1, 1, 2, 1);
		Assert.assertTrue(new Main().isMedian(group, 1));
		Assert.assertFalse(new Main().isMedian(group, 2));
	}

	@Test
	public void isEvenMedian11() throws Exception {
		Set<Integer> group = getList(1, 1);
		Assert.assertTrue(new Main().isMedian(group, 1));
	}

	@Test
	public void isEvenMedian12() throws Exception {
		Set<Integer> group = getList(1, 2);
		Assert.assertTrue(new Main().isMedian(group, 1));
		Assert.assertFalse(new Main().isMedian(group, 2));
	}

	@Test
	public void isOddMedian123() throws Exception {
		Set<Integer> group = getList(1, 2, 3);
		Assert.assertFalse(new Main().isMedian(group, 1));
		Assert.assertTrue(new Main().isMedian(group, 2));
		Assert.assertFalse(new Main().isMedian(group, 3));
	}

	@Test
	public void isOddMedian112() throws Exception {
		Set<Integer> group = getList(1, 1, 2);
		Assert.assertTrue(new Main().isMedian(group, 1));
		Assert.assertFalse(new Main().isMedian(group, 2));
	}

	@Test
	public void isOddMedian122() throws Exception {
		Set<Integer> group = getList(1, 2, 2);
		Assert.assertTrue(new Main().isMedian(group, 1));
		Assert.assertFalse(new Main().isMedian(group, 2));
	}

	@Test
	public void isOddMedian12345() throws Exception {
		Set<Integer> group = getList(1, 2, 3, 4, 5);
		Assert.assertFalse(new Main().isMedian(group, 1));
		Assert.assertFalse(new Main().isMedian(group, 2));
		Assert.assertTrue(new Main().isMedian(group, 3));
		Assert.assertFalse(new Main().isMedian(group, 4));
		Assert.assertFalse(new Main().isMedian(group, 5));
	}

	@Test
	public void isOddMedian12445() throws Exception {
		Set<Integer> group = getList(1, 2, 4, 4, 5);
		Assert.assertFalse(new Main().isMedian(group, 1));
		Assert.assertTrue(new Main().isMedian(group, 2));
		Assert.assertFalse(new Main().isMedian(group, 4));
		Assert.assertFalse(new Main().isMedian(group, 5));
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
