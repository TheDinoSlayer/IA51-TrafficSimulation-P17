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
package fr.utbm.info.ia51.general.tree.tree2d;

import fr.utbm.info.ia51.framework.environment.ShapedObject;
import fr.utbm.info.ia51.framework.math.Rectangle2f;
import fr.utbm.info.ia51.general.tree.abstracts.AbstractArraySpatialTreeNode;
import fr.utbm.info.ia51.general.tree.tree2d.SeparationLine;
import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.lang.reflect.Array;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Definition of a 2D-tree node.
 * 
 * @param <D> - type of the data in the node.
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class BinaryTreeNode<D extends ShapedObject> extends AbstractArraySpatialTreeNode<BinaryTreeNode<D>, D> {
  private final SeparationLine separationLine;
  
  /**
   * @param bounds the bounds covered by the node.
   * @param separationLine defines the separation line for creating the children.
   */
  @DefaultValueSource
  public BinaryTreeNode(@DefaultValue("fr.utbm.info.ia51.general.tree.tree2d.BinaryTreeNode#NEW_0") final Rectangle2f bounds, final SeparationLine separationLine) {
    super(bounds);
    this.separationLine = separationLine;
  }
  
  /**
   * Default value for the parameter bounds
   */
  @SyntheticMember
  @SarlSourceCode("null")
  private final static Rectangle2f $DEFAULT_VALUE$NEW_0 = null;
  
  /**
   * Replies the separation line to use for creating the children.
   * 
   * @return the separation line.
   */
  @Pure
  public SeparationLine getSeparationLine() {
    return this.separationLine;
  }
  
  /**
   * Replies the lower or left child node.
   * 
   * @return the node.
   */
  @Pure
  public BinaryTreeNode<D> getLowerOrLeftChild() {
    BinaryTreeNode<D>[] _children = this.children;
    BinaryTreeNode<D> _get = null;
    if (_children!=null) {
      _get=_children[0];
    }
    return _get;
  }
  
  /**
   * Replies the upper or right child node.
   * 
   * @return the node.
   */
  @Pure
  public BinaryTreeNode<D> getUpperOrRightChild() {
    BinaryTreeNode<D>[] _children = this.children;
    BinaryTreeNode<D> _get = null;
    if (_children!=null) {
      _get=_children[1];
    }
    return _get;
  }
  
  @Override
  public void createChildren() {
    if ((this.children == null)) {
      Object _newInstance = Array.newInstance(BinaryTreeNode.class, 2);
      this.children = ((BinaryTreeNode<D>[]) _newInstance);
    }
    BinaryTreeNode<D> _get = this.children[0];
    boolean _tripleEquals = (_get == null);
    if (_tripleEquals) {
      Rectangle2f bounds = this.getBounds();
      Rectangle2f childBounds = this.separationLine.getLowerOrLeftBounds(bounds);
      this.children[0] = this.newNodeInstance(childBounds);
      BinaryTreeNode<D> _get_1 = this.children[0];
      _get_1.setParent(this);
    }
    BinaryTreeNode<D> _get_2 = this.children[1];
    boolean _tripleEquals_1 = (_get_2 == null);
    if (_tripleEquals_1) {
      Rectangle2f bounds_1 = this.getBounds();
      Rectangle2f childBounds_1 = this.separationLine.getUpperOrRightBounds(bounds_1);
      this.children[1] = this.newNodeInstance(childBounds_1);
      BinaryTreeNode<D> _get_3 = this.children[1];
      _get_3.setParent(this);
    }
  }
  
  /**
   * @optionalparam bounds the bounds covered by the node.
   * @optionalparam separationLine defines the separation line for creating the children.
   */
  @DefaultValueUse("fr.utbm.info.ia51.framework.math.Rectangle2f,fr.utbm.info.ia51.general.tree.tree2d.SeparationLine")
  @SyntheticMember
  public BinaryTreeNode(final SeparationLine separationLine) {
    this($DEFAULT_VALUE$NEW_0, separationLine);
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
  
  @SyntheticMember
  private final static long serialVersionUID = -183694672L;
}
