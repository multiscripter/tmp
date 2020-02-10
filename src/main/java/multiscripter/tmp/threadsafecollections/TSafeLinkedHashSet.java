package multiscripter.tmp.threadsafecollections;

import java.util.LinkedHashSet;
import net.jcip.annotations.ThreadSafe;

/**
 * Реализует сущность Потокобезопасное хранилище пользователей
 * на основе связанного хэшированного множества.
 */
@ThreadSafe
public class TSafeLinkedHashSet<E> extends AbstractTSafeCollection<E> {

  /**
   * Конструктор по умолчанию.
   */
  public TSafeLinkedHashSet() {
    super(new LinkedHashSet<>());
  }
}
