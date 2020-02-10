package multiscripter.tmp.threadsafecollections;

import java.util.Vector;

public class StorageVector<E> extends AbstractStorage<E> {

  protected Vector<E> storage;

  public StorageVector() {
    super(new Vector<>());
    this.storage = (Vector<E>) this.getStorage();
  }
}
