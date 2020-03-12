package multiscripter.tmp.datastructs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AVLTreeTest {

  Comparator<Integer> comparator = Integer::compare;

  @Test
  public void testInsert() {
    Random rand = new Random();
    int max = 1;
    int size = 100000;

    for (int a = 0; a < max; a++) {
      LinkedList<Integer> nums = new LinkedList<>();
      for (int c = 0; c < size; c++) {
        nums.add(c + 1);
      }
      LinkedList<Integer> list = new LinkedList<>();
      AVLTree<Integer> tree = new AVLTree<>(comparator);
      System.out.println("Filling list and tree started.");
      while (nums.size() > 0) {
        int index = rand.nextInt(nums.size());
        int num = nums.remove(index);
        list.add(num);
        tree.insert(num);
      }
      System.out.println("List and tree filled.");
      //System.out.println(list);
      list.sort(comparator);
      //System.out.println(list);

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
    Integer[] nums = new Integer[] {2, 3, 5, 4, 9, 6, 10, 1, 7, 8};
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
}
