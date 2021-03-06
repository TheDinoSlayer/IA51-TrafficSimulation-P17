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
package fr.utbm.info.ia51.general.tree.iterator;

import com.google.common.collect.Iterables;
import fr.utbm.info.ia51.general.tree.SpatialTreeNode;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Depth-first tree iterator.
 * 
 * @param <N> - type of the root node.
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class DepthFirstTreeIterator<N extends SpatialTreeNode<N, ?>> implements Iterator<N> {
  private final Stack<N> stack = new Stack<N>();
  
  /**
   * @param root - the root node.
   */
  public DepthFirstTreeIterator(final N root) {
    if ((root != null)) {
      Iterables.<N>addAll(this.stack, root);
    }
  }
  
  @Override
  @Pure
  public boolean hasNext() {
    boolean _isEmpty = this.stack.isEmpty();
    return (!_isEmpty);
  }
  
  @Override
  public N next() {
    boolean _isEmpty = this.stack.isEmpty();
    if (_isEmpty) {
      throw new NoSuchElementException();
    }
    N n = this.stack.pop();
    List<N> _children = n.getChildren();
    for (final N c : _children) {
      Iterables.<N>addAll(this.stack, c);
    }
    return n;
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
