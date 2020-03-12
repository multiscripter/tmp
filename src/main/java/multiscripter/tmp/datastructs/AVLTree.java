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

  private Node<E> balance(Node<E> p) {
    this.fixHeight(p);
    if (this.bFactor(p) == 2) {
      if (this.bFactor(p.right) < 0) {
        p.right = this.rotateRight(p.right);
        return this.rotateLeft(p);
      }
    } else if (this.bFactor(p) == -2) {
      if (this.bFactor(p.left) > 0) {
        p.left = this.rotateLeft(p.left);
        return this.rotateRight(p);
      }
    }
    return p;
  }

  public boolean insert(E val) {
    Node<E> node = new Node<>(null, val);
    if (this.root == null) {
      this.root = node;
    } else {
      Node<E> parent = this.root;
      while (true) {
        node.setParent(parent);
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
    }
    //this.balance(node);
    return true;
  }

  public Iterator<E> iterator() {
    return new SimpleIterator();
  }

  /**
   * Малый правый поворот.
   * @param p корень поддерева.
   * @return новый корень поддерева.
   */
  private Node<E> rotateRight(Node<E> p) {
    Node<E> q = p.left;
    p.left = q.right;
    q.right = p;
    this.fixHeight(p);
    this.fixHeight(q);
    return q;
  }

  private Node<E> rotateLeft(Node<E> q) {
    Node<E> p = q.right;
    q.right = p.left;
    p.left = q;
    this.fixHeight(q);
    this.fixHeight(p);
    return p;
  }

  /**
   * Узел дерева.
   * @param <E> параметрический тип.
   */
  private class Node<E> {
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
        /*
        while (true) {
          // (спуск налево).
          if (tmp.left != null) {
            tmp = tmp.left;
          }
          // Обработка дубликатов (спуск направо).
          else if (tmp.right != null && AVLTree.this.comparator.compare(tmp.val, tmp.right.val) == 0) {
            tmp = tmp.right;
          } else {
            break;
          }
        }*/
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
          /* Обработка дубликатов (подъём).
          if (tmp.parent != null && AVLTree.this.comparator.compare(tmp.parent.val, tmp.val) == 0) {
            this.next = tmp.parent;
            break;
          }*/
          tmp = tmp.parent;
          if (tmp != null && AVLTree.this.comparator.compare(tmp.val, this.cur.val) > 0) {
            this.next = tmp;
            break;
          }
          /*if (tmp == null || tmp.left == this.cur || (tmp.parent == null && AVLTree.this.comparator.compare(this.cur.val, tmp.val) < 0)) {
            // Обработка дубликатов (спуск направо).
            //          while (tmp != null && tmp.right != null && AVLTree.this.comparator.compare(tmp.val, tmp.right.val) == 0) {
            //            tmp = tmp.right;
            //          }
            this.next = tmp;
            break;
          }*/
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
