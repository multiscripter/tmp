package multiscripter.tmp.threadsafecollections;

import java.util.ArrayList;
import net.jcip.annotations.ThreadSafe;

/**
 * Реализует сущность Потокобезопасное хранилище пользователей
 * на основе списочного массива.
 */
@ThreadSafe
public class TSafeArrayList<E> extends AbstractTSafeCollection<E> {

  protected ArrayList<E> collection;

  /**
   * Конструктор по умолчанию.
   */
  public TSafeArrayList() {
    super(new ArrayList<>());
    this.collection = (ArrayList<E>) this.getCollection();
  }
}
