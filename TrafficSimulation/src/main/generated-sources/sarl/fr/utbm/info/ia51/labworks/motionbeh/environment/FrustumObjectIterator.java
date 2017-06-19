package fr.utbm.info.ia51.labworks.motionbeh.environment;

import fr.utbm.info.ia51.framework.environment.ShapedObject;
import fr.utbm.info.ia51.framework.math.Shape2f;
import fr.utbm.info.ia51.labworks.motionbeh.environment.TreeNode;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Iterator;
import java.util.LinkedList;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class FrustumObjectIterator<T extends ShapedObject> implements Iterator<T> {
  private Iterator<TreeNode<T>> iterator;
  
  private Shape2f<?> frustum;
  
  private LinkedList<T> buffer = new LinkedList<T>();
  
  public FrustumObjectIterator(final Iterator<TreeNode<T>> iterator, final Shape2f<?> f) {
    this.frustum = f;
    this.iterator = iterator;
    this.fillBuffer();
  }
  
  @Override
  @Pure
  public boolean hasNext() {
    boolean _isEmpty = this.buffer.isEmpty();
    return (!_isEmpty);
  }
  
  @Override
  public T next() {
    T o = this.buffer.removeFirst();
    this.fillBuffer();
    return o;
  }
  
  private void fillBuffer() {
    while ((this.buffer.isEmpty() && this.iterator.hasNext())) {
      {
        TreeNode<T> node = this.iterator.next();
        Iterable<T> _objects = node.getObjects();
        for (final T o : _objects) {
          boolean _intersects = this.frustum.intersects(o.getShape());
          if (_intersects) {
            this.buffer.add(o);
          }
        }
      }
    }
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    return result;
  }
}
