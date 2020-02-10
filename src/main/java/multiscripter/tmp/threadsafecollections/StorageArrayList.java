package multiscripter.tmp.threadsafecollections;

import java.util.ArrayList;
import net.jcip.annotations.ThreadSafe;

/**
 * Реализует сущность Потокобезопасное хранилище пользователей
 * на основе списочного массива.
 */
@ThreadSafe
public class StorageArrayList<E> extends AbstractStorage<E> {

  protected ArrayList<E> storage;

  /**
   * Конструктор по умолчанию.
   */
  public StorageArrayList() {
    super(new ArrayList<>());
    this.storage = (ArrayList<E>) this.getStorage();
  }
}
