package multiscripter.tmp.threadsafecollections;

import java.util.Vector;

public class TSafeVector<E> extends AbstractTSafeCollection<E> {

  protected Vector<E> collection;

  public TSafeVector() {
    super(new Vector<>());
    this.collection = (Vector<E>) this.getCollection();
  }

  public boolean add(final E item) {
    return this.collection.add(item);
  }

  public int size() {
    return this.collection.size();
  }
}
