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

import fr.utbm.info.ia51.framework.environment.ShapedObject;
import fr.utbm.info.ia51.framework.math.Rectangle2f;
import fr.utbm.info.ia51.general.tree.SpatialTree;
import fr.utbm.info.ia51.general.tree.SpatialTreeNode;
import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.io.ObjectInputStream;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Abstract definition of a spatial tree node.
 * 
 * @param <N> - type of the root node.
 * @param <D> - type of the data inside the node.
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public abstract class AbstractSpatialTreeNode<N extends SpatialTreeNode<N, D>, D extends ShapedObject> implements SpatialTreeNode<N, D> {
  private Rectangle2f bounds;
  
  private Collection<D> data;
  
  private transient WeakReference<N> parentNode;
  
  private transient WeakReference<SpatialTree<N, D>> tree;
  
  /**
   * @param bounds
   */
  @DefaultValueSource
  public AbstractSpatialTreeNode(@DefaultValue("fr.utbm.info.ia51.general.tree.abstracts.AbstractSpatialTreeNode#NEW_0") final Rectangle2f bounds) {
    this.bounds = bounds;
  }
  
  /**
   * Default value for the parameter bounds
   */
  @SyntheticMember
  @SarlSourceCode("null")
  private final static Rectangle2f $DEFAULT_VALUE$NEW_0 = null;
  
  private void readObject(final ObjectInputStream stream) {
    try {
      stream.defaultReadObject();
      List<N> _children = this.getChildren();
      for (final N child : _children) {
        ((AbstractSpatialTreeNode<N, D>) child).setParent(((N) this));
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Replies the tree in which this node is located..
   * 
   * @return the tree.
   */
  @Pure
  protected SpatialTree<N, D> getTree() {
    WeakReference<SpatialTree<N, D>> _tree = this.tree;
    SpatialTree<N, D> _get = null;
    if (_tree!=null) {
      _get=_tree.get();
    }
    return _get;
  }
  
  /**
   * Create an instance of a tree node for the tree.
   * 
   * @param bounds - the bounds covered by the node.
   * @return the node.
   */
  protected N newNodeInstance(final Rectangle2f bounds) {
    return this.getTree().getNodeFactory().newInstance(bounds, ((N) this));
  }
  
  /**
   * Clear the list of data.
   */
  protected void clearData() {
    if ((this.data != null)) {
      this.data.clear();
    }
  }
  
  @Override
  @Pure
  public Collection<D> getData() {
    Collection<D> _xifexpression = null;
    if ((this.data == null)) {
      _xifexpression = CollectionLiterals.<D>emptyList();
    } else {
      _xifexpression = Collections.<D>unmodifiableCollection(this.data);
    }
    return _xifexpression;
  }
  
  @Override
  @Pure
  public int getDataCount() {
    Collection<D> _data = this.data;
    int _size = 0;
    if (_data!=null) {
      _size=_data.size();
    }
    return _size;
  }
  
  /**
   * Replies the internal data structure that is available for storing data.
   * 
   * @return the data structure
   */
  @Pure
  protected final Collection<D> getInternalDataStructure() {
    if ((this.data == null)) {
      TreeSet<D> _treeSet = new TreeSet<D>();
      this.data = _treeSet;
    }
    return this.data;
  }
  
  /**
   * Change the internal data structure that is available for storing data.
   * The data are moved from the previous data structure to the new data structure.
   * 
   * @param type the type of the internal data structure to use.
   */
  protected final Collection<D> setInternalDataStructure(final Class<? extends Collection<D>> type) {
    try {
      Collection<D> _xblockexpression = null;
      {
        Collection<D> newData = type.newInstance();
        if ((this.data != null)) {
          newData.addAll(this.data);
        }
        _xblockexpression = this.data = newData;
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Remove the data.
   * This function cannot be overridden.
   * 
   * @param data
   */
  protected final Collection<D> removeDataNotOverriddable(final D data) {
    Collection<D> _xifexpression = null;
    if ((this.data != null)) {
      Collection<D> _xblockexpression = null;
      {
        this.data.remove(data);
        Collection<D> _xifexpression_1 = null;
        boolean _isEmpty = this.data.isEmpty();
        if (_isEmpty) {
          _xifexpression_1 = this.data = null;
        }
        _xblockexpression = _xifexpression_1;
      }
      _xifexpression = _xblockexpression;
    }
    return _xifexpression;
  }
  
  @Override
  @Pure
  public N getParent() {
    WeakReference<N> _parentNode = this.parentNode;
    N _get = null;
    if (_parentNode!=null) {
      _get=_parentNode.get();
    }
    return _get;
  }
  
  /**
   * Change the parent node.
   * 
   * @param parent the new parent node.
   */
  protected WeakReference<SpatialTree<N, D>> setParent(final N parent) {
    WeakReference<SpatialTree<N, D>> _xblockexpression = null;
    {
      SpatialTree t = null;
      if ((parent == null)) {
        this.parentNode = null;
        t = null;
      } else {
        WeakReference<N> _weakReference = new WeakReference<N>(parent);
        this.parentNode = _weakReference;
        if ((parent instanceof AbstractSpatialTreeNode<?, ?>)) {
          t = ((AbstractSpatialTreeNode<?, ?>)parent).getTree();
        } else {
          t = null;
        }
      }
      _xblockexpression = this.setTree(t);
    }
    return _xblockexpression;
  }
  
  /**
   * Change the tree link.
   * 
   * @param tree the container.
   */
  protected WeakReference<SpatialTree<N, D>> setTree(final SpatialTree<N, D> tree) {
    WeakReference<SpatialTree<N, D>> _xifexpression = null;
    if ((tree == null)) {
      _xifexpression = this.tree = null;
    } else {
      WeakReference<SpatialTree<N, D>> _weakReference = new WeakReference<SpatialTree<N, D>>(tree);
      _xifexpression = this.tree = _weakReference;
    }
    return _xifexpression;
  }
  
  @Override
  @Pure
  public Rectangle2f getBounds() {
    return this.bounds;
  }
  
  @Override
  @Pure
  public boolean isRoot() {
    return (this.parentNode == null);
  }
  
  @Override
  @Pure
  public String toString() {
    return this.getInternalDataStructure().toString();
  }
  
  /**
   * @optionalparam bounds 
   */
  @DefaultValueUse("fr.utbm.info.ia51.framework.math.Rectangle2f")
  @SyntheticMember
  public AbstractSpatialTreeNode() {
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
  private final static long serialVersionUID = 5687063494L;
}
