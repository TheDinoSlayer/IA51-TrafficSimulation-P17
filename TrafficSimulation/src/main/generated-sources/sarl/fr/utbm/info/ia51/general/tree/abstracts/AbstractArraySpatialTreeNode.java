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
package fr.utbm.info.ia51.general.tree.abstracts;

import com.google.common.collect.Iterables;
import fr.utbm.info.ia51.framework.environment.ShapedObject;
import fr.utbm.info.ia51.framework.math.Rectangle2f;
import fr.utbm.info.ia51.framework.math.Shape2f;
import fr.utbm.info.ia51.general.tree.abstracts.AbstractSpatialTreeNode;
import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.eclipse.xtext.xbase.lib.ExclusiveRange;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Definition of a tree node that uses an array for storing the children.
 * 
 * @param <D> - type of the data in the node.
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public abstract class AbstractArraySpatialTreeNode<N extends AbstractArraySpatialTreeNode<N, D>, D extends ShapedObject> extends AbstractSpatialTreeNode<N, D> {
  /**
   * @param <D> - the type of the objects in the node.
   * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
   * @version $Name$ $Revision$ $Date$
   */
  @SarlSpecification("0.5")
  @SarlElementType(8)
  private static class ChildIterator<N extends AbstractArraySpatialTreeNode<N, D>, D extends ShapedObject> implements Iterator<N> {
    private final AbstractArraySpatialTreeNode<N, D> parent;
    
    private int n = 0;
    
    private N next;
    
    public ChildIterator(final AbstractArraySpatialTreeNode<N, D> parent) {
      this.parent = parent;
      this.searchNext();
    }
    
    private void searchNext() {
      this.next = null;
      while (((this.n < this.parent.children.length) && (this.next == null))) {
        {
          N c = this.parent.children[this.n];
          if ((c != null)) {
            this.next = c;
          }
          this.n++;
        }
      }
    }
    
    @Override
    @Pure
    public boolean hasNext() {
      return (this.next != null);
    }
    
    @Override
    public N next() {
      N n = this.next;
      this.searchNext();
      return n;
    }
    
    @Override
    @Pure
    @SyntheticMember
    public boolean equals(final Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      ChildIterator<N, D> other = (ChildIterator<N, D>) obj;
      if (other.n != this.n)
        return false;
      return super.equals(obj);
    }
    
    @Override
    @Pure
    @SyntheticMember
    public int hashCode() {
      int result = super.hashCode();
      final int prime = 31;
      result = prime * result + this.n;
      return result;
    }
  }
  
  /**
   * Array of the child nodes.
   */
  protected N[] children;
  
  /**
   * @param bounds the bounds covered by the node.
   */
  @DefaultValueSource
  public AbstractArraySpatialTreeNode(@DefaultValue("fr.utbm.info.ia51.general.tree.abstracts.AbstractArraySpatialTreeNode#NEW_0") final Rectangle2f bounds) {
    super(bounds);
  }
  
  /**
   * Default value for the parameter bounds
   */
  @SyntheticMember
  @SarlSourceCode("null")
  private final static Rectangle2f $DEFAULT_VALUE$NEW_0 = null;
  
  @Override
  public boolean addData(final D data) {
    boolean _isLeaf = this.isLeaf();
    if (_isLeaf) {
      int maxObjectsPerNode = this.getTree().getNodeFactory().getMaxDataCountPerNode();
      int _dataCount = this.getDataCount();
      boolean _greaterEqualsThan = (_dataCount >= maxObjectsPerNode);
      if (_greaterEqualsThan) {
        this.createChildren();
        Iterator<D> iterator = this.getInternalDataStructure().iterator();
        while (iterator.hasNext()) {
          {
            D existingData = iterator.next();
            boolean _addInChildWhenPossible = this.addInChildWhenPossible(existingData);
            if (_addInChildWhenPossible) {
              iterator.remove();
            }
          }
        }
        boolean _addInChildWhenPossible = this.addInChildWhenPossible(data);
        boolean _not = (!_addInChildWhenPossible);
        if (_not) {
          Collection<D> _internalDataStructure = this.getInternalDataStructure();
          return _internalDataStructure.add(data);
        }
      } else {
        Collection<D> _internalDataStructure_1 = this.getInternalDataStructure();
        return _internalDataStructure_1.add(data);
      }
    } else {
      boolean _addInChildWhenPossible_1 = this.addInChildWhenPossible(data);
      boolean _not_1 = (!_addInChildWhenPossible_1);
      if (_not_1) {
        Collection<D> _internalDataStructure_2 = this.getInternalDataStructure();
        return _internalDataStructure_2.add(data);
      }
    }
    return true;
  }
  
  private boolean addInChildWhenPossible(final D data) {
    Shape2f<?> dataShape = data.getShape();
    N selectedChild = null;
    for (final N child : this.children) {
      boolean _intersects = child.getBounds().intersects(dataShape);
      if (_intersects) {
        if ((selectedChild != null)) {
          return false;
        }
        selectedChild = child;
      }
    }
    if ((selectedChild != null)) {
      return selectedChild.addData(data);
    }
    return false;
  }
  
  @Override
  public boolean removeData(final D data) {
    boolean removed = true;
    boolean _isLeaf = this.isLeaf();
    if (_isLeaf) {
      Collection<D> _internalDataStructure = this.getInternalDataStructure();
      boolean _remove = _internalDataStructure.remove(data);
      removed = _remove;
    } else {
      boolean _removeFromChildWhenPossible = this.removeFromChildWhenPossible(data);
      boolean _not = (!_removeFromChildWhenPossible);
      if (_not) {
        Collection<D> _internalDataStructure_1 = this.getInternalDataStructure();
        boolean _remove_1 = _internalDataStructure_1.remove(data);
        removed = _remove_1;
      }
    }
    if (removed) {
      this.removeChildrenIfEmpty();
    }
    return removed;
  }
  
  private boolean removeChildrenIfEmpty() {
    if ((this.children != null)) {
      int _length = this.children.length;
      ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _length, true);
      for (final Integer i : _doubleDotLessThan) {
        if (((this.children[(i).intValue()] != null) && (!this.children[(i).intValue()].isEmpty()))) {
          return false;
        }
      }
      int _length_1 = this.children.length;
      ExclusiveRange _doubleDotLessThan_1 = new ExclusiveRange(0, _length_1, true);
      for (final Integer i_1 : _doubleDotLessThan_1) {
        N _get = this.children[(i_1).intValue()];
        boolean _tripleNotEquals = (_get != null);
        if (_tripleNotEquals) {
          N _get_1 = this.children[(i_1).intValue()];
          _get_1.setParent(null);
          this.children[(i_1).intValue()] = null;
        }
      }
      this.children = null;
      return true;
    }
    return false;
  }
  
  private boolean removeFromChildWhenPossible(final D data) {
    Shape2f<?> dataShape = data.getShape();
    N selectedChild = null;
    for (final N child : this.children) {
      boolean _intersects = child.getBounds().intersects(dataShape);
      if (_intersects) {
        if ((selectedChild != null)) {
          return false;
        }
        selectedChild = child;
      }
    }
    if ((selectedChild != null)) {
      return selectedChild.removeData(data);
    }
    return false;
  }
  
  @Override
  @Pure
  public boolean isLeaf() {
    return (this.children == null);
  }
  
  @Override
  @Pure
  public boolean isEmpty() {
    return (this.isLeaf() && (this.getDataCount() == 0));
  }
  
  @Override
  public void removeChildren() {
    if ((this.children != null)) {
      int _length = this.children.length;
      ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _length, true);
      for (final Integer i : _doubleDotLessThan) {
        N _get = this.children[(i).intValue()];
        boolean _tripleNotEquals = (_get != null);
        if (_tripleNotEquals) {
          N _get_1 = this.children[(i).intValue()];
          _get_1.setParent(null);
          this.children[(i).intValue()] = null;
        }
      }
      this.children = null;
    }
  }
  
  @Override
  @Pure
  public List<N> getChildren() {
    ArrayList<N> nodes = new ArrayList<N>(4);
    if ((this.children != null)) {
      int _length = this.children.length;
      ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _length, true);
      for (final Integer i : _doubleDotLessThan) {
        N _get = this.children[(i).intValue()];
        boolean _tripleNotEquals = (_get != null);
        if (_tripleNotEquals) {
          N _get_1 = this.children[(i).intValue()];
          Iterables.<N>addAll(nodes, _get_1);
        }
      }
    }
    return Collections.<N>unmodifiableList(nodes);
  }
  
  @Override
  @Pure
  public String toString() {
    String _string = this.getBounds().toString();
    String _plus = (_string + "=>");
    String _string_1 = super.toString();
    return (_plus + _string_1);
  }
  
  @Override
  @Pure
  public Iterator<N> iterator() {
    return new AbstractArraySpatialTreeNode.ChildIterator<N, D>(this);
  }
  
  /**
   * @optionalparam bounds the bounds covered by the node.
   */
  @DefaultValueUse("fr.utbm.info.ia51.framework.math.Rectangle2f")
  @SyntheticMember
  public AbstractArraySpatialTreeNode() {
    this($DEFAULT_VALUE$NEW_0);
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
  private final static long serialVersionUID = 4536465771L;
}
