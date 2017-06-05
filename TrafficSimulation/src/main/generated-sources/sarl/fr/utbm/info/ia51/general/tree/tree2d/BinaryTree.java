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
import fr.utbm.info.ia51.general.tree.SpatialTreeNodeFactory;
import fr.utbm.info.ia51.general.tree.abstracts.AbstractSpatialTree;
import fr.utbm.info.ia51.general.tree.tree2d.BinaryTreeNode;
import fr.utbm.info.ia51.general.tree.tree2d.SeparationLine;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Definition of a 2D-tree.
 * 
 * @param <D> - the type of data in the tree.
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class BinaryTree<D extends ShapedObject> extends AbstractSpatialTree<BinaryTreeNode<D>, D> {
  /**
   * @param <D> - the type of data in the tree.
   * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
   * @version $Name$ $Revision$ $Date$
   */
  @SarlSpecification("0.5")
  @SarlElementType(8)
  private static class BinaryTreeNodeFactory<D extends ShapedObject> implements SpatialTreeNodeFactory<BinaryTreeNode<D>> {
    @Override
    public BinaryTreeNode<D> newInstance(final Rectangle2f bounds, final BinaryTreeNode<D> parent) {
      BinaryTreeNode<D> _xblockexpression = null;
      {
        SeparationLine separationLine = null;
        if ((parent == null)) {
          separationLine = SeparationLine.VERTICAL;
        } else {
          separationLine = parent.getSeparationLine().turn();
        }
        _xblockexpression = new BinaryTreeNode<D>(bounds, separationLine);
      }
      return _xblockexpression;
    }
    
    @Override
    @Pure
    @Inline(value = "1", constantExpression = true)
    public int getMaxDataCountPerNode() {
      return 1;
    }
    
    @SyntheticMember
    public BinaryTreeNodeFactory() {
      super();
    }
    
    @SyntheticMember
    private final static long serialVersionUID = -3142945196L;
  }
  
  public BinaryTree() {
    super(new BinaryTree.BinaryTreeNodeFactory<D>());
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 2230L;
}
