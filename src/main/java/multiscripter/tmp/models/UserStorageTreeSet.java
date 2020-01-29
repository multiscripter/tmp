package multiscripter.tmp.models;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Реализует сущность Хранилище пользователей на основе Деревянного множества.
 */
@ThreadSafe
public class UserStorageTreeSet {

  /**
   * Монитор.
   */
  private final Object lock;

  /**
   * Хранилище.
   */
  private TreeSet<User> storage;

  /**
   * Конструктор.
   *
   * @param comparator сравниватель.
   */
  public UserStorageTreeSet(final Comparator<User> comparator) {
    this.lock = this;
    this.storage = new TreeSet<>(comparator);
  }

  @GuardedBy("lock")
  public boolean add(final User user) {
    synchronized (this.lock) {
      return this.storage.add(user);
    }
  }

  @GuardedBy("lock")
  public boolean contains(final User user) {
    synchronized (this.lock) {
      return this.storage.contains(user);
    }
  }

  public Iterator<User> iterator() {
    return this.storage.iterator();
  }
}
