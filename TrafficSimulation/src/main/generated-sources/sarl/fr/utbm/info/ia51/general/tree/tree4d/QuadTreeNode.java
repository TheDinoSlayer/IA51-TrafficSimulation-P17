/**
 * $Id$
 * 
 * Copyright (c) 2011-17 Stephane GALLAND <stephane.galland@utbm.fr>.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 * This program is free software; you can redistribute it and/or modify
 */
package fr.utbm.info.ia51.general.tree.tree4d;

import fr.utbm.info.ia51.framework.environment.ShapedObject;
import fr.utbm.info.ia51.framework.math.Point2f;
import fr.utbm.info.ia51.framework.math.Rectangle2f;
import fr.utbm.info.ia51.general.tree.abstracts.AbstractArraySpatialTreeNode;
import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.lang.reflect.Array;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Definition of a quadtree node.
 * 
 * @param <D> - type of the data in the node.
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class QuadTreeNode<D extends ShapedObject> extends AbstractArraySpatialTreeNode<QuadTreeNode<D>, D> {
  /**
   * Replies the lower-left child node.
   * 
   * @return the node.
   */
  @Pure
  public QuadTreeNode<D> getLowerLeftChild() {
    QuadTreeNode<D>[] _children = this.children;
    QuadTreeNode<D> _get = null;
    if (_children!=null) {
      _get=_children[0];
    }
    return _get;
  }
  
  /**
   * Replies the upper-left child node.
   * 
   * @return the node.
   */
  @Pure
  public QuadTreeNode<D> getUpperLeftChild() {
    QuadTreeNode<D>[] _children = this.children;
    QuadTreeNode<D> _get = null;
    if (_children!=null) {
      _get=_children[1];
    }
    return _get;
  }
  
  /**
   * Replies the lower-right child node.
   * 
   * @return the node.
   */
  @Pure
  public QuadTreeNode<D> getLowerRightChild() {
    QuadTreeNode<D>[] _children = this.children;
    QuadTreeNode<D> _get = null;
    if (_children!=null) {
      _get=_children[2];
    }
    return _get;
  }
  
  /**
   * Replies the upper-right child node.
   * 
   * @return the node.
   */
  @Pure
  public QuadTreeNode<D> getUpperRightChild() {
    QuadTreeNode<D>[] _children = this.children;
    QuadTreeNode<D> _get = null;
    if (_children!=null) {
      _get=_children[3];
    }
    return _get;
  }
  
  @Override
  public void createChildren() {
    if ((this.children == null)) {
      Object _newInstance = Array.newInstance(QuadTreeNode.class, 4);
      this.children = ((QuadTreeNode<D>[]) _newInstance);
    }
    QuadTreeNode<D> _get = this.children[0];
    boolean _tripleEquals = (_get == null);
    if (_tripleEquals) {
      Rectangle2f bounds = this.getBounds();
      Point2f lower = bounds.getLower();
      Point2f center = bounds.getCenter();
      Rectangle2f _rectangle2f = new Rectangle2f(lower, center);
      this.children[0] = this.newNodeInstance(_rectangle2f);
      QuadTreeNode<D> _get_1 = this.children[0];
      _get_1.setParent(this);
    }
    QuadTreeNode<D> _get_2 = this.children[1];
    boolean _tripleEquals_1 = (_get_2 == null);
    if (_tripleEquals_1) {
      Rectangle2f bounds_1 = this.getBounds();
      Point2f lower_1 = bounds_1.getLower();
      Point2f upper = bounds_1.getUpper();
      Point2f center_1 = bounds_1.getCenter();
      float _x = lower_1.getX();
      float _y = center_1.getY();
      float _x_1 = center_1.getX();
      float _y_1 = upper.getY();
      Rectangle2f _rectangle2f_1 = new Rectangle2f(_x, _y, _x_1, _y_1);
      this.children[1] = this.newNodeInstance(_rectangle2f_1);
      QuadTreeNode<D> _get_3 = this.children[1];
      _get_3.setParent(this);
    }
    QuadTreeNode<D> _get_4 = this.children[2];
    boolean _tripleEquals_2 = (_get_4 == null);
    if (_tripleEquals_2) {
      Rectangle2f bounds_2 = this.getBounds();
      Point2f lower_2 = bounds_2.getLower();
      Point2f upper_1 = bounds_2.getUpper();
      Point2f center_2 = bounds_2.getCenter();
      float _x_2 = center_2.getX();
      float _y_2 = lower_2.getY();
      float _x_3 = upper_1.getX();
      float _y_3 = center_2.getY();
      Rectangle2f _rectangle2f_2 = new Rectangle2f(_x_2, _y_2, _x_3, _y_3);
      this.children[2] = this.newNodeInstance(_rectangle2f_2);
      QuadTreeNode<D> _get_5 = this.children[2];
      _get_5.setParent(this);
    }
    QuadTreeNode<D> _get_6 = this.children[3];
    boolean _tripleEquals_3 = (_get_6 == null);
    if (_tripleEquals_3) {
      Rectangle2f bounds_3 = this.getBounds();
      Point2f upper_2 = bounds_3.getUpper();
      Point2f center_3 = bounds_3.getCenter();
      Rectangle2f _rectangle2f_3 = new Rectangle2f(center_3, upper_2);
      this.children[3] = this.newNodeInstance(_rectangle2f_3);
      QuadTreeNode<D> _get_7 = this.children[3];
      _get_7.setParent(this);
    }
  }
  
  /**
   * @optionalparam bounds the bounds covered by the node.
   */
  @SyntheticMember
  @DefaultValueUse("fr.utbm.info.ia51.framework.math.Rectangle2f")
  public QuadTreeNode() {
    super();
  }
  
  /**
   * @param bounds the bounds covered by the node.
   */
  @SyntheticMember
  @DefaultValueSource
  public QuadTreeNode(@DefaultValue("fr.utbm.info.ia51.general.tree.abstracts.AbstractArraySpatialTreeNode#NEW_0") final Rectangle2f bounds) {
    super(bounds);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = -808463542L;
}
