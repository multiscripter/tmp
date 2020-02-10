package multiscripter.tmp.threadsafecollections;

import java.util.HashMap;
import net.jcip.annotations.ThreadSafe;

/**
 * Реализует сущность Потокобезопасное хранилище пользователей
 * на основе хэшированной карты.
 */
@ThreadSafe
public class TSafeHashMap<K, V> extends AbstractTSafeMap<K, V> {

  protected HashMap<K, V> collection;

  /**
   * Конструктор по умолчанию.
   */
  public TSafeHashMap() {
    super(new HashMap<>());
    this.collection = (HashMap<K, V>) this.getCollection();
  }
}
