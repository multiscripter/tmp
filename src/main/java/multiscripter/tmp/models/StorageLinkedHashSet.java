package multiscripter.tmp.models;

import net.jcip.annotations.ThreadSafe;

import java.util.LinkedHashSet;

/**
 * Реализует сущность Хранилище пользователей на основе
 * связанного хэшированного множества.
 */
@ThreadSafe
public class StorageLinkedHashSet<E> extends AbstractStorage<E> {

  public StorageLinkedHashSet() {
    super(new LinkedHashSet<>());
  }
}
