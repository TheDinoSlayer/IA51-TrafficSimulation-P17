/**
 * $Id$
 * 
 * Copyright (c) 2011-15 Stephane GALLAND <stephane.galland@utbm.fr>.
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
import fr.utbm.info.ia51.framework.math.Shape2f;
import fr.utbm.info.ia51.general.frustum.FrustumCullerTreeIterator;
import fr.utbm.info.ia51.general.tree.SpatialTree;
import fr.utbm.info.ia51.general.tree.SpatialTreeNode;
import fr.utbm.info.ia51.general.tree.SpatialTreeNodeFactory;
import fr.utbm.info.ia51.general.tree.abstracts.AbstractSpatialTreeNode;
import fr.utbm.info.ia51.general.tree.iterator.BroadFirstTreeIterator;
import fr.utbm.info.ia51.general.tree.iterator.DepthFirstObjectTreeIterator;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.io.ObjectInputStream;
import java.util.Iterator;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Abstract definition of a spatial tree.
 * 
 * @param <N> - type of the root node.
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public abstract class AbstractSpatialTree<N extends SpatialTreeNode<N, D>, D extends ShapedObject> implements SpatialTree<N, D> {
  private SpatialTreeNodeFactory<N> factory;
  
  private N root;
  
  /**
   * @param factory the node factory to use.
   */
  public AbstractSpatialTree(final SpatialTreeNodeFactory<N> factory) {
    this.factory = factory;
  }
  
  private void readObject(final ObjectInputStream stream) {
    try {
      stream.defaultReadObject();
      for (final N node : this) {
        ((AbstractSpatialTreeNode<N, D>) node).setTree(((SpatialTree<N, D>) this));
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Override
  public boolean addData(final D data) {
    if ((data == null)) {
      return false;
    }
    N root = this.getRoot();
    if ((root != null)) {
      return root.addData(data);
    }
    return false;
  }
  
  @Override
  public boolean removeData(final D data) {
    if ((data == null)) {
      return false;
    }
    N root = this.getRoot();
    if ((root != null)) {
      return root.removeData(data);
    }
    return false;
  }
  
  @Override
  @Pure
  public Rectangle2f getBounds() {
    N root = this.getRoot();
    if ((root == null)) {
      return new Rectangle2f();
    }
    return root.getBounds();
  }
  
  @Override
  @Pure
  public N getRoot() {
    return this.root;
  }
  
  public void setRoot(final N root) {
    if ((this.root instanceof AbstractSpatialTreeNode<?, ?>)) {
      ((AbstractSpatialTreeNode<N, D>) this.root).setTree(null);
    }
    this.root = root;
    if ((this.root instanceof AbstractSpatialTreeNode<?, ?>)) {
      ((AbstractSpatialTreeNode<N, D>) this.root).setTree(((SpatialTree<N, D>) this));
    }
  }
  
  @Override
  @Pure
  public Iterator<N> iterator() {
    N _root = this.getRoot();
    return new BroadFirstTreeIterator<N>(_root);
  }
  
  @Override
  @Pure
  public SpatialTreeNodeFactory<N> getNodeFactory() {
    return this.factory;
  }
  
  @Override
  public void setNodeFactory(final SpatialTreeNodeFactory<N> factory) {
    this.factory = factory;
  }
  
  @Override
  public void initialize(final Rectangle2f worldSize) {
    this.root = this.getNodeFactory().newInstance(worldSize, null);
  }
  
  @Override
  public Iterator<D> dataIterator() {
    N _root = this.getRoot();
    return new DepthFirstObjectTreeIterator<N, D>(_root);
  }
  
  @Override
  public Iterator<D> dataIterator(final Shape2f<?> bounds) {
    N _root = this.getRoot();
    return new FrustumCullerTreeIterator<D>(_root, bounds);
  }
  
  @Override
  @Pure
  public Iterable<D> getData() {
    final Iterable<D> _function = () -> {
      return this.dataIterator();
    };
    return _function;
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
  private final static long serialVersionUID = 5233254749L;
}
