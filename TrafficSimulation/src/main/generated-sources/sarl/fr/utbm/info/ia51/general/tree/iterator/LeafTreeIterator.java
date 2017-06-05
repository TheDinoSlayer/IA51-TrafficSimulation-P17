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
 * Iterator on tree leaves.
 * 
 * @param <N> - type of the root node.
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class LeafTreeIterator<N extends SpatialTreeNode<N, ?>> implements Iterator<N> {
  private final Stack<N> stack = new Stack<N>();
  
  private N next;
  
  /**
   * @param root - the root node.
   */
  public LeafTreeIterator(final N root) {
    if ((root != null)) {
      Iterables.<N>addAll(this.stack, root);
    }
    this.searchNext();
  }
  
  private void searchNext() {
    this.next = null;
    while (((this.next == null) && (!this.stack.isEmpty()))) {
      {
        N candidate = this.stack.pop();
        boolean _isLeaf = candidate.isLeaf();
        if (_isLeaf) {
          this.next = candidate;
        } else {
          List<N> _children = candidate.getChildren();
          for (final N child : _children) {
            Iterables.<N>addAll(this.stack, child);
          }
        }
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
    if ((this.next == null)) {
      throw new NoSuchElementException();
    }
    N n = this.next;
    this.searchNext();
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
