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
import fr.utbm.info.ia51.framework.environment.SpatialDataStructure;
import fr.utbm.info.ia51.framework.math.Rectangle2f;
import fr.utbm.info.ia51.general.tree.SpatialTreeNode;
import fr.utbm.info.ia51.general.tree.SpatialTreeNodeFactory;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;

/**
 * Definition of a spatial tree.
 * 
 * @param <N> - type of the root node.
 * @param <D> - the type of the objects inside the tree.
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SarlElementType(9)
@SuppressWarnings("all")
public interface SpatialTree<N extends SpatialTreeNode<N, D>, D extends ShapedObject> extends SpatialDataStructure<D>, Iterable<N> {
  /**
   * Replies the bounds covered by the tree nodes.
   * 
   * @return the bounds covered by the tree.
   */
  public abstract Rectangle2f getBounds();
  
  /**
   * Returns the root node of the tree.
   * 
   * @return the root node.
   */
  public abstract N getRoot();
  
  /**
   * Set the root node of the tree.
   * 
   * @param root - the root node.
   */
  public abstract void setRoot(final N root);
  
  /**
   * Replies the node factory used by the tree.
   * 
   * @return the node factory.
   */
  public abstract SpatialTreeNodeFactory<N> getNodeFactory();
  
  /**
   * Change the node factory used by the tree.
   * 
   * @param factory the node factory.
   */
  public abstract void setNodeFactory(final SpatialTreeNodeFactory<N> factory);
}
