package multiscripter.tmp.datastructs;

import java.util.Comparator;
import java.util.Iterator;

/**
 * АВЛ-дерево без дубликатов.
 * @param <E> параметризированный тип.
 */
public class AVLTree<E> {

  private Comparator<E> comparator;

  private Node<E> root;

  public AVLTree(Comparator<E> comparator) {
    this.comparator = comparator;
  }

  private int height(Node<E> node) {
    return node != null ? node.height : 0;
  }

  private int bFactor(Node<E> node) {
    return this.height(node.right) - this.height(node.left);
  }

  private void fixHeight(Node<E> node) {
    int hl = this.height(node.left);
    int hr = this.height(node.right);
    node.height = Math.max(hl, hr) + 1;
  }

  private void balance(Node<E> node) {
    this.fixHeight(node);
    // Нужен левый поворот.
    if (this.bFactor(node) == 2) {
      /*if (this.bFactor(node.right) < 0) {
        this.rotateRight(node.right);
      }*/
      this.rotateLeft(node);
//      if (this.bFactor(node.right) < 0) {
//        node.right = this.rotateRight(node.right);
//        this.rotateLeft(node);
//      }
    }
    // Нужен правый поворот.
    else if (this.bFactor(node) == -2) {
      /*if (this.bFactor(node.left) > 0) {
        this.rotateLeft(node.left);
      }*/
      this.rotateRight(node);
//      if (this.bFactor(node.left) > 0) {
//        node.left = this.rotateLeft(node.left);
//        this.rotateRight(node);
//      }
    }
  }

  /**
   * Левый поворот.
   * @param left корень поддерева.
   */
  private void rotateLeft(Node<E> left) {
    //System.err.println("left: " + left.val);
    Node<E> parent = left.parent;
    Node<E> core = left.right;
    Node<E> middle = core.left;
    // Большой поворот.
    if (middle != null) {
      middle.parent = parent;
      core.left = null;
      if (parent != null) {
        parent.right = middle;
      } else {
        this.root = middle;
      }
      middle.right = core;
      core.parent = middle;
      middle.left = left;
      left.parent = middle;
    }
    // Малый поворот.
    else {
      core.parent = parent;
      if (parent != null) {
        parent.right = core;
      } else {
        this.root = core;
      }
      core.left = left;
      left.parent = core;
    }
    left.right = null;
    this.fixHeight(left);
    this.fixHeight(core);
    if (middle != null) {
      this.fixHeight(middle);
    }
    if (parent != null) {
      this.fixHeight(parent);
    }
  }

  /**
   * Правый поворот.
   * @param right корень поддерева.
   */
  private void rotateRight(Node<E> right) {
    //System.err.println("right: " + right.val);
    Node<E> parent = right.parent;
    Node<E> core = right.left;
    Node<E> middle = core.right;
    // Большой поворот.
    if (middle != null) {
      middle.parent = parent;
      core.right = null;
      if (parent != null) {
        parent.left = middle;
      } else {
        this.root = middle;
      }
      middle.left = core;
      core.parent = middle;
      middle.right = right;
      right.parent = middle;
    }
    // Малый поворот.
    else {
      core.parent = parent;
      if (parent != null) {
        parent.left = core;
      } else {
        this.root = core;
      }
      core.right = right;
      right.parent = core;
    }
    right.left = null;
    this.fixHeight(right);
    this.fixHeight(core);
    if (middle != null) {
      this.fixHeight(middle);
    }
    if (parent != null) {
      this.fixHeight(parent);
    }
  }

  public Node<E> getRoot() {
    return this.root;
  }

  public boolean insert(E val) {
    Node<E> node = new Node<>(null, val);
    if (this.root == null) {
      this.root = node;
    } else {
      Node<E> parent = this.root;
      while (true) {
        //System.err.println(node.val);
        node.parent = parent;
        if (this.comparator.compare(node.val, parent.val) < 0) {
          if (parent.left != null) {
            parent = parent.left;
          } else {
            parent.left = node;
            break;
          }
        } else {
          if (parent.right != null) {
            parent = parent.right;
          } else  {
            parent.right = node;
            break;
          }
        }
      }
      do {
        //if (parent.parent != null)
          //System.err.println("parent before: " + parent.val + ", " + parent.parent.val);
        this.balance(parent);
        //if (parent.parent != null)
          //System.err.println("parent after: " + parent.val + ", " + parent.parent.val);
      } while ((parent = parent.parent) != null);
    }
    return true;
  }

  public Iterator<E> iterator() {
    return new SimpleIterator();
  }

  /**
   * Узел дерева.
   * @param <E> параметрический тип.
   */
  protected class Node<E> {
    private Node<E> parent;
    private E val;
    private int height;
    private Node<E> left;
    private Node<E> right;

    public Node(Node<E> parent, E val) {
      this.parent = parent;
      this.val = val;
      this.height = 1;
    }

    public void setParent(Node<E> parent) {
      this.parent = parent;
    }

    public int getHeight() {
      return this.height;
    }

    public Node<E> getLeft() {
      return this.left;
    }

    public Node<E> getRight() {
      return this.right;
    }

    public E getVal() {
      return this.val;
    }

    public void setLeft(Node<E> left) {
      this.left = left;
    }

    public void setRight(Node<E> right) {
      this.right = right;
    }
  }

  private class SimpleIterator implements Iterator<E> {

    private AVLTree<E>.Node<E> cur;

    private AVLTree<E>.Node<E> next;

    public SimpleIterator() {
      if (AVLTree.this.root != null) {
        AVLTree<E>.Node<E> tmp = AVLTree.this.root;
        // Поиск наименьшего элемента в дереве.
        while (tmp.left != null) {
          tmp = tmp.left;
        }
        this.next = tmp;
      }
    }

    /**
     * Итеративный обход в глубину.
     * Не использует стэк, флаг направления и пр.
     */
    private void getNext() {
      Node<E> tmp = this.cur;
      while (tmp != null) {
        if (tmp != this.cur
            && tmp.parent != null
            && (tmp.parent.left == tmp || tmp.parent.right == tmp)
            && tmp.left == null
            && AVLTree.this.comparator.compare(tmp.val, this.cur.val) > 0) {
          this.next = tmp;
          break;
        }
        if (tmp.left != null && AVLTree.this.comparator.compare(this.cur.val, tmp.left.val) < 0) {
          tmp = tmp.left;
        } else if (tmp.right != null && AVLTree.this.comparator.compare(this.cur.val, tmp.right.val) < 0) {
          tmp = tmp.right;
        } else {
          if (tmp != this.cur && tmp.left == null && tmp.right == null) {
            this.next = tmp;
            break;
          }
          tmp = tmp.parent;
          if (tmp != null && AVLTree.this.comparator.compare(tmp.val, this.cur.val) > 0) {
            this.next = tmp;
            break;
          }
        }
      }
    }

    @Override
    public boolean hasNext() {
      return this.next != null;
    }

    @Override
    public E next() {
      this.cur = this.next;
      this.getNext();
      return this.cur.val;
    }
  }
}
