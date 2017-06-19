package fr.utbm.info.ia51.labworks.motionbeh.environment;

import fr.utbm.info.ia51.framework.environment.ShapedObject;
import fr.utbm.info.ia51.framework.math.Shape2f;
import fr.utbm.info.ia51.labworks.motionbeh.environment.Tree;
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
public class FrustumNodeIterator<T extends ShapedObject> implements Iterator<TreeNode<T>> {
  private LinkedList<TreeNode<T>> stack = new LinkedList<TreeNode<T>>();
  
  private Shape2f<?> frustum;
  
  public FrustumNodeIterator(final Tree<T> tree, final Shape2f<?> f) {
    this.frustum = f;
    boolean _intersects = f.intersects(tree.getRoot().getBox());
    if (_intersects) {
      TreeNode<T> _root = tree.getRoot();
      this.stack.add(_root);
    }
  }
  
  @Override
  @Pure
  public boolean hasNext() {
    boolean _isEmpty = this.stack.isEmpty();
    return (!_isEmpty);
  }
  
  @Override
  public TreeNode<T> next() {
    TreeNode<T> n = this.stack.removeLast();
    Iterable<TreeNode<T>> _children = n.getChildren();
    for (final TreeNode<T> c : _children) {
      boolean _intersects = this.frustum.intersects(c.getBox());
      if (_intersects) {
        this.stack.add(c);
      }
    }
    return n;
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
