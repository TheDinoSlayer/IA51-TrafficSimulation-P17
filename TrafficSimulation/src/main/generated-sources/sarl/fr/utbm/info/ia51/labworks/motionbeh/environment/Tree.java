package fr.utbm.info.ia51.labworks.motionbeh.environment;

import fr.utbm.info.ia51.framework.environment.Frustum;
import fr.utbm.info.ia51.framework.environment.ShapedObject;
import fr.utbm.info.ia51.framework.math.Point2f;
import fr.utbm.info.ia51.framework.math.Rectangle2f;
import fr.utbm.info.ia51.framework.math.Shape2f;
import fr.utbm.info.ia51.framework.math.Vector2f;
import fr.utbm.info.ia51.labworks.motionbeh.environment.FrustumNodeIterator;
import fr.utbm.info.ia51.labworks.motionbeh.environment.FrustumObjectIterator;
import fr.utbm.info.ia51.labworks.motionbeh.environment.TreeNode;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Iterator;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class Tree<T extends ShapedObject> {
  private final TreeNode<T> root;
  
  public Tree(final Rectangle2f box) {
    TreeNode<T> _treeNode = new TreeNode<T>(box);
    this.root = _treeNode;
  }
  
  @Pure
  public TreeNode<T> getRoot() {
    return this.root;
  }
  
  @Pure
  public Iterator<T> iterator(final Frustum frustum, final Point2f position, final Vector2f orientation) {
    Shape2f<?> geometricFrustum = frustum.toShape(position, orientation);
    FrustumNodeIterator<T> nodeIterator = new FrustumNodeIterator<T>(this, geometricFrustum);
    return new FrustumObjectIterator<T>(nodeIterator, geometricFrustum);
  }
  
  public boolean add(final T o) {
    return this.root.add(o);
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
