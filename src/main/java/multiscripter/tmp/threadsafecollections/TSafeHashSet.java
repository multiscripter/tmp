package multiscripter.tmp.threadsafecollections;

import java.util.HashSet;
import net.jcip.annotations.ThreadSafe;

/**
 * Реализует сущность Потокобезопасное хранилище пользователей
 * на основе хэшированного множества.
 */
@ThreadSafe
public class TSafeHashSet<E> extends AbstractTSafeCollection<E> {

  /**
   * Конструктор по умолчанию.
   */
  public TSafeHashSet() {
    super(new HashSet<>());
  }
}
