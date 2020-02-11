package multiscripter.tmp.threadsafecollections;

import java.util.Comparator;
import java.util.TreeMap;
import net.jcip.annotations.ThreadSafe;

/**
 * Реализует сущность Потокобезопасное хранилище пользователей
 * на основе деревянной карты.
 */
@ThreadSafe
public class TSafeTreeMap<K, V> extends AbstractTSafeMap<K, V> {

  /**
   * Хранилище.
   */
  protected TreeMap<K, V> collection;

  /**
   * Конструктор по умолчанию.
   */
  public TSafeTreeMap(final Comparator<K> comparator) {
    super(new TreeMap<>(comparator));
    this.collection = (TreeMap<K, V>) this.getCollection();
  }
}
