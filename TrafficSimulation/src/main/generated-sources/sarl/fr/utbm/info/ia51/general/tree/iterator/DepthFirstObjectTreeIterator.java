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
import fr.utbm.info.ia51.framework.environment.ShapedObject;
import fr.utbm.info.ia51.general.tree.SpatialTreeNode;
import fr.utbm.info.ia51.general.tree.iterator.DepthFirstTreeIterator;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Depth-first tree iterator.
 * 
 * @param <N> - type of the root node.
 * @param <D> - type of the data in the nodes.
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class DepthFirstObjectTreeIterator<N extends SpatialTreeNode<N, D>, D extends ShapedObject> implements Iterator<D> {
  private final DepthFirstTreeIterator<N> iterator;
  
  private final LinkedList<D> nexts = new LinkedList<D>();
  
  /**
   * @param root - the root node.
   */
  public DepthFirstObjectTreeIterator(final N root) {
    DepthFirstTreeIterator<N> _depthFirstTreeIterator = new DepthFirstTreeIterator<N>(root);
    this.iterator = _depthFirstTreeIterator;
    this.searchNext();
  }
  
  private void searchNext() {
    boolean _isEmpty = this.nexts.isEmpty();
    if (_isEmpty) {
      while ((this.iterator.hasNext() && this.nexts.isEmpty())) {
        {
          N n = this.iterator.next();
          Collection<D> _data = n.getData();
          Iterables.<D>addAll(this.nexts, _data);
        }
      }
    }
  }
  
  @Override
  @Pure
  public boolean hasNext() {
    boolean _isEmpty = this.nexts.isEmpty();
    return (!_isEmpty);
  }
  
  @Override
  public D next() {
    boolean _isEmpty = this.nexts.isEmpty();
    if (_isEmpty) {
      throw new NoSuchElementException();
    }
    D n = this.nexts.removeFirst();
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
