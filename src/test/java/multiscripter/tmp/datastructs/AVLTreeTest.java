package multiscripter.tmp.datastructs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AVLTreeTest {

  private Comparator<Integer> comparator = Integer::compare;

  @Test
  public void testInsert() {
    Random rand = new Random();
    int max = 1;
    int size = 100000;

    for (int a = 0; a < max; a++) {
      LinkedList<Integer> nums = new LinkedList<>();
      for (int c = 0; c < size;) {
        nums.add(++c);
      }
      LinkedList<Integer> list = new LinkedList<>();
      AVLTree<Integer> tree = new AVLTree<>(comparator);
      while (nums.size() > 0) {
        int index = rand.nextInt(nums.size());
        int num = nums.remove(index);
        list.add(num);
        tree.insert(num);
      }
      //System.out.println(list);
      list.sort(comparator);

      Iterator<Integer> iterExpected = list.iterator();
      Iterator<Integer> iterActual = tree.iterator();
      while (iterExpected.hasNext()) {
        int expected = iterExpected.next();
        int actual = iterActual.next();
        assertEquals(expected, actual);
      }
    }
  }

  @Test
  public void testDFS() {
    //Integer[] nums = new Integer[] {2, 3, 5, 4, 9, 6, 10, 1, 7, 8};
    //Integer[] nums = new Integer[] {4, 8, 10, 7, 5, 2, 3, 1, 9, 6};
    Integer[] nums = new Integer[] {7, 1, 10, 2, 5, 9, 3, 4, 8, 6};
    LinkedList<Integer> list = new LinkedList<>(Arrays.asList(nums));
    System.out.println(list);
    list.sort(comparator);
    System.out.println(list);
    AVLTree<Integer> tree = new AVLTree<>(comparator);
    for (Integer a : nums) {
      tree.insert(a);
    }
    Iterator<Integer> iterExpected = list.iterator();
    Iterator<Integer> iterActual = tree.iterator();
    while (iterExpected.hasNext()) {
      int expected = iterExpected.next();
      int actual = iterActual.next();
      assertEquals(expected, actual);
    }
  }

  @Test
  public void testRotateLeft() {
    int[] nums = new int[] {2, 1, 3, 4, 5};
    AVLTree<Integer> tree = new AVLTree<>(comparator);
    for (Integer a : nums) {
      tree.insert(a);
    }
    Integer[] expected = new Integer[] {2, 4, 5};
    AVLTree<Integer>.Node<Integer> node = tree.getRoot();
    int index = 0;
    do {
      assertEquals(expected[index++], node.getVal());
    } while ((node = node.getRight()) != null);
  }

  @Test
  public void testRotateLeft2() {
    Integer[] nums = new Integer[] {2, 1, 3, 5, 4, 6};
    LinkedList<Integer> list = new LinkedList<>(Arrays.asList(nums));
    list.sort(comparator);
    AVLTree<Integer> tree = new AVLTree<>(comparator);
    for (Integer a : nums) {
      tree.insert(a);
    }
    Iterator<Integer> iterExpected = list.iterator();
    Iterator<Integer> iterActual = tree.iterator();
    while (iterExpected.hasNext()) {
      int expected = iterExpected.next();
      int actual = iterActual.next();
      assertEquals(expected, actual);
    }
  }

  @Test
  public void testRotateRight() {
    int[] nums = new int[] {4, 5, 3, 2, 1};
    AVLTree<Integer> tree = new AVLTree<>(comparator);
    for (Integer a : nums) {
      tree.insert(a);
    }
    Integer[] expected = new Integer[] {4, 2, 1};
    AVLTree<Integer>.Node<Integer> node = tree.getRoot();
    int index = 0;
    do {
      assertEquals(expected[index++], node.getVal());
    } while ((node = node.getLeft()) != null);
  }
}
