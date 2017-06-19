package fr.utbm.info.ia51.labworks.motionbeh.environment;

import fr.utbm.info.ia51.framework.environment.ShapedObject;
import fr.utbm.info.ia51.framework.math.Point2f;
import fr.utbm.info.ia51.framework.math.Rectangle2f;
import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class TreeNode<T extends ShapedObject> {
  private final WeakReference<TreeNode<T>> parent;
  
  private final Rectangle2f box;
  
  private LinkedList<T> objects = new LinkedList<T>();
  
  private List<TreeNode<T>> children;
  
  @DefaultValueSource
  public TreeNode(@DefaultValue("fr.utbm.info.ia51.labworks.motionbeh.environment.TreeNode#NEW_0") final TreeNode parent, final Rectangle2f box) {
    if ((parent != null)) {
      WeakReference<TreeNode<T>> _weakReference = new WeakReference<TreeNode<T>>(parent);
      this.parent = _weakReference;
    } else {
      this.parent = null;
    }
    this.box = box;
  }
  
  /**
   * Default value for the parameter parent
   */
  @SyntheticMember
  @SarlSourceCode("null")
  private final static TreeNode $DEFAULT_VALUE$NEW_0 = null;
  
  @Pure
  public TreeNode<T> getParent() {
    WeakReference<TreeNode<T>> _parent = this.parent;
    TreeNode<T> _get = null;
    if (_parent!=null) {
      _get=_parent.get();
    }
    return _get;
  }
  
  @Pure
  public Rectangle2f getBox() {
    return this.box;
  }
  
  @Pure
  public Iterable<T> getObjects() {
    return Collections.<T>unmodifiableList(this.objects);
  }
  
  @Pure
  public Iterable<TreeNode<T>> getChildren() {
    List<TreeNode<T>> _xifexpression = null;
    if ((this.children == null)) {
      _xifexpression = Collections.<TreeNode<T>>emptyList();
    } else {
      _xifexpression = Arrays.<TreeNode<T>>asList(((TreeNode<T>[])Conversions.unwrapArray(this.children, TreeNode.class)));
    }
    return _xifexpression;
  }
  
  public boolean add(final T o) {
    boolean _xifexpression = false;
    if ((this.children == null)) {
      boolean _xifexpression_1 = false;
      int _size = this.objects.size();
      int _plus = (_size + 1);
      boolean _greaterThan = (_plus > 10);
      if (_greaterThan) {
        boolean _xblockexpression = false;
        {
          LinkedList<T> list = this.objects;
          LinkedList<T> _linkedList = new LinkedList<T>();
          this.objects = _linkedList;
          this.createChildren();
          for (final T oo : list) {
            boolean _pushInChild = this.pushInChild(oo);
            boolean _not = (!_pushInChild);
            if (_not) {
              this.objects.add(oo);
            }
          }
          boolean _pushInChild_1 = this.pushInChild(o);
          boolean _not_1 = (!_pushInChild_1);
          if (_not_1) {
            this.objects.add(o);
          }
          _xblockexpression = this.collapseChildrenIfNecessary();
        }
        _xifexpression_1 = _xblockexpression;
      } else {
        _xifexpression_1 = this.objects.add(o);
      }
      _xifexpression = _xifexpression_1;
    } else {
      boolean _xifexpression_2 = false;
      boolean _pushInChild = this.pushInChild(o);
      boolean _not = (!_pushInChild);
      if (_not) {
        _xifexpression_2 = this.objects.add(o);
      }
      _xifexpression = _xifexpression_2;
    }
    return _xifexpression;
  }
  
  private void createChildren() {
    ArrayList<TreeNode<T>> _arrayList = new ArrayList<TreeNode<T>>(4);
    this.children = _arrayList;
    IntegerRange _upTo = new IntegerRange(0, 3);
    for (final Integer i : _upTo) {
      Rectangle2f _computeChildBox = this.computeChildBox((i).intValue());
      TreeNode<T> _treeNode = new TreeNode<T>(this, _computeChildBox);
      this.children.add(_treeNode);
    }
  }
  
  private boolean collapseChildrenIfNecessary() {
    IntegerRange _upTo = new IntegerRange(0, 3);
    for (final Integer i : _upTo) {
      {
        TreeNode<T> c = this.children.get((i).intValue());
        if (((!c.objects.isEmpty()) || (!c.children.isEmpty()))) {
          return false;
        }
      }
    }
    this.children = null;
    return true;
  }
  
  private Rectangle2f computeChildBox(final int i) {
    Point2f center = this.box.getCenter();
    switch (i) {
      case 0:
        Point2f _lower = this.box.getLower();
        return new Rectangle2f(_lower, center);
      case 1:
        float _x = center.getX();
        float _y = this.box.getLower().getY();
        float _x_1 = this.box.getUpper().getX();
        float _y_1 = center.getY();
        return new Rectangle2f(_x, _y, _x_1, _y_1);
      case 2:
        float _x_2 = this.box.getLower().getX();
        float _y_2 = center.getY();
        float _x_3 = center.getX();
        float _y_3 = this.box.getUpper().getY();
        return new Rectangle2f(_x_2, _y_2, _x_3, _y_3);
      case 3:
        Point2f _upper = this.box.getUpper();
        return new Rectangle2f(center, _upper);
    }
    return null;
  }
  
  private boolean pushInChild(final T o) {
    ArrayList<TreeNode<T>> candidates = new ArrayList<TreeNode<T>>(4);
    IntegerRange _upTo = new IntegerRange(0, 3);
    for (final Integer i : _upTo) {
      {
        TreeNode<T> node = this.children.get((i).intValue());
        boolean _intersects = node.box.intersects(o.getShape());
        if (_intersects) {
          candidates.add(node);
        }
      }
    }
    int _size = candidates.size();
    boolean _notEquals = (_size != 1);
    if (_notEquals) {
      return false;
    }
    candidates.get(0).add(o);
    return true;
  }
  
  @DefaultValueUse("fr.utbm.info.ia51.labworks.motionbeh.environment.TreeNode,fr.utbm.info.ia51.framework.math.Rectangle2f")
  @SyntheticMember
  public TreeNode(final Rectangle2f box) {
    this($DEFAULT_VALUE$NEW_0, box);
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
