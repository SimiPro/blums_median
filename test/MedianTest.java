import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MedianTest {

	@Test
	public void testContainer() throws Exception {
		OrderedContainer c = new OrderedContainer().add(1, 2, 3, 4, 5);
		Assert.assertEquals(3, c.getMedian());
	}

	@Test
	public void test1234() throws Exception {
		OrderedContainer c = new OrderedContainer().add(1, 2, 3, 4);
		Assert.assertEquals(2, c.getMedian());
	}

	@Test
	public void test11144() throws Exception {
		OrderedContainer c = new OrderedContainer().add(1, 1, 1, 4, 4);
		Assert.assertEquals(1, c.getMedian());
	}

	@Test
	public void test1224() throws Exception {
		OrderedContainer c = new OrderedContainer().add(1, 2, 2, 4);
		Assert.assertEquals(2, c.getMedian());
	}

	@Test
	public void test1124() throws Exception {
		OrderedContainer c = new OrderedContainer().add(1, 1, 2, 4);
		Assert.assertEquals(1, c.getMedian());
	}

	@Test
	public void test1244() throws Exception {
		OrderedContainer c = new OrderedContainer().add(1, 2, 4, 4);
		Assert.assertEquals(2, c.getMedian());
	}

	@Test
	public void test1144() throws Exception {
		OrderedContainer c = new OrderedContainer().add(1, 1, 4, 4);
		Assert.assertEquals(1, c.getMedian());
	}

	@Test
	public void test1111() throws Exception {
		OrderedContainer c = new OrderedContainer().add(1, 1, 1, 1);
		Assert.assertEquals(1, c.getMedian());
	}

	@Test
	public void test1121() throws Exception {
		OrderedContainer c = new OrderedContainer().add(1, 1, 2, 1);
		Assert.assertEquals(1, c.getMedian());
	}

	@Test
	public void test11() throws Exception {
		OrderedContainer c = new OrderedContainer().add(1, 1);
		Assert.assertEquals(1, c.getMedian());
	}

	@Test
	public void test12() throws Exception {
		OrderedContainer c = new OrderedContainer().add(1, 2);
		Assert.assertEquals(1, c.getMedian());
	}

	@Test
	public void test123() throws Exception {
		OrderedContainer c = new OrderedContainer().add(1, 2, 3);
		Assert.assertEquals(2, c.getMedian());
	}

	@Test
	public void test112() throws Exception {
		OrderedContainer c = new OrderedContainer().add(1, 1, 2);
		Assert.assertEquals(1, c.getMedian());
	}

	@Test
	public void test122() throws Exception {
		OrderedContainer c = new OrderedContainer().add(1, 2, 2);
		Assert.assertEquals(2, c.getMedian());
	}

	@Test
	public void test12345() throws Exception {
		OrderedContainer c = new OrderedContainer().add(1, 2, 3, 4, 5);
		Assert.assertEquals(3, c.getMedian());
	}

	@Test
	public void test12445() throws Exception {
		OrderedContainer c = new OrderedContainer().add(1, 2, 4, 4, 5);
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

	@Test
	public void testMedianOfMedianTest() {
		Random r = new Random(1);
		int n = 87;
		for (int trial = 0; trial < 1000; trial++) {
			ArrayList list = new ArrayList();
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				int v = r.nextInt(256);
				a[i] = v;
				list.add(v);
			}
			int m1 = (Integer) MedianOfMedians.getMedian(list);
			Arrays.sort(a);
			int m2 = a[n / 2];
			Assert.assertEquals(m1, m2);
		}

	}

	@Test
	public void testBlum() {
		for (int j = 0; j < 10; j++) {
			List<Integer> A = new ArrayList<>();
			for (int i = 0; i < 10; i++) {
				A.add(new Random().nextInt(100));
			}
			int k = A.size() % 2 == 0 ? A.size() / 2 - 1 : A.size() / 2;

			int blum = new Blum(A).blum(k);
			Integer median = A.get(blum);
			for (Integer integer : A) {
				System.out.print(integer + " ");
			}
			System.out.println("WITH median: " + median + " And key: " + blum);

			Collections.sort(A);
			for (Integer integer : A) {
				System.out.print(integer + " ");
			}
			System.out.println("WITH median: " + A.get(k) + " And key: " + k);
			Assert.assertEquals(A.get(k), median);
		}
	}

	@Test
	public void testBlum2() {
		List<Integer> A = getList();
		int k = A.size() % 2 == 0 ? A.size() / 2 - 1 : A.size() / 2;

		int blum = new Blum(A).blum(k);
		Integer median = A.get(blum);
		for (Integer integer : A) {
			System.out.print(integer + " ");
		}
		System.out.println("WITH median: " + median + " And key: " + blum);

		Collections.sort(A);
		for (Integer integer : A) {
			System.out.print(integer + " ");
		}
		System.out.println("WITH median: " + A.get(k) + " And key: " + k);
		Assert.assertEquals(A.get(k), median);
	}

	@Test
	public void testBlum3() {
		for (int j = 0; j < 10; j++) {
			int[] A = new int[1444];
			for (int i = 0; i < 44; i++) {
				A[i] = (new Random().nextInt(100));
			}
			int k = A.length % 2 == 0 ? A.length / 2 - 1 : A.length / 2;

			int median = Main.findMedian(A);
			for (int integer : A) {
				System.out.print(integer + " ");
			}
			System.out.println("WITH median: " + median + " And key: ");

			Arrays.sort(A);
			for (int integer : A) {
				System.out.print(integer + " ");
			}
			System.out.println("WITH median: " + A[k] + " And key: " + k);
			Assert.assertEquals(A[k], median);
		}
	}

	List<Integer> getList() {
		List<Integer> A = new ArrayList<>();
		A.add(5);
		A.add(2);
		A.add(17);
		A.add(13);
		A.add(14);
		A.add(17);
		A.add(19);
		A.add(14);
		A.add(18);
		A.add(2);
		A.add(4);
		A.add(8);
		A.add(2);
		A.add(5);
		A.add(0);
		A.add(6);
		A.add(13);
		A.add(4);
		A.add(12);
		A.add(18);
		A.add(10);
		return A;
	}
}
