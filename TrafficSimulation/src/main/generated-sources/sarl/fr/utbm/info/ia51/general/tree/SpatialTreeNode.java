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
package fr.utbm.info.ia51.general.tree;

import fr.utbm.info.ia51.framework.environment.ShapedObject;
import fr.utbm.info.ia51.framework.math.Rectangle2f;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Definition of a spatial tree node.
 * 
 * @param <N> - type of the root node.
 * @param <D> - type of the data inside the tree.
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SarlElementType(9)
@SuppressWarnings("all")
public interface SpatialTreeNode<N extends SpatialTreeNode<N, D>, D extends ShapedObject> extends Iterable<N>, Serializable {
  /**
   * Replies the data associated to the node.
   * 
   * @return the data.
   */
  public abstract Collection<D> getData();
  
  /**
   * Replies the number of data associated to the node.
   * 
   * @return the number of data.
   */
  public abstract int getDataCount();
  
  /**
   * Change the data associated to the node.
   * 
   * @param data - the data.
   * @return <code>true</code> if the data was added.
   */
  public abstract boolean addData(final D data);
  
  /**
   * Change the data associated to the node.
   * 
   * @param data - the data.
   * @return <code>true</code> if the data was removed.
   */
  public abstract boolean removeData(final D data);
  
  /**
   * Replies the bounds covered by the node.
   * 
   * @return the bounds of the area covered by the node.
   */
  public abstract Rectangle2f getBounds();
  
  /**
   * Return the parent node.
   * 
   * @return the parent node, or <code>null</code> if this node is the root.
   */
  public abstract N getParent();
  
  /**
   * Returns the children.
   * 
   * @return the children.
   */
  public abstract List<N> getChildren();
  
  /**
   * Replies if this node is a leaf in the tree.
   * 
   * @return <code>true</code> if this node has no child.
   */
  public abstract boolean isLeaf();
  
  /**
   * Replies if this node is the root in the tree.
   * 
   * @return <code>true</code> if this node has no parent.
   */
  public abstract boolean isRoot();
  
  /**
   * Create the child nodes if they are not existing.
   */
  public abstract void createChildren();
  
  /**
   * Remove the child nodes if they are existing.
   */
  public abstract void removeChildren();
  
  /**
   * Replies if the node has no data nor child.
   * 
   * @return <code>true</code> if there is no data nor child.
   */
  public abstract boolean isEmpty();
}
