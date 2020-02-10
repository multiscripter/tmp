package multiscripter.tmp.threadsafecollections;

import java.util.LinkedList;
import net.jcip.annotations.ThreadSafe;

/**
 * Реализует сущность Потокобезопасное хранилище пользователей
 * на основе связного списка.
 */
@ThreadSafe
public class TSafeLinkedList<E> extends AbstractTSafeCollection<E> {

  protected LinkedList<E> storage;

  /**
   * Конструктор по умолчанию.
   */
  public TSafeLinkedList() {
    super(new LinkedList<>());
    this.storage = (LinkedList<E>) this.getCollection();
  }
}
