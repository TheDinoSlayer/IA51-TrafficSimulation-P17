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
package fr.utbm.info.ia51.general.formation;

import fr.utbm.info.ia51.general.formation.FormationAssignment;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Permits to agents to obtain a free anchor in a formation.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class FifoFormationAssignment implements FormationAssignment {
  private int nextFreeAnchor = 1;
  
  /**
   * Allocate the next free anchor.
   * 
   * @param isLeader indicates if the caller is the leader or a follower.
   * @return the index of the next free anchor.
   */
  public synchronized int allocate(final boolean isLeader) {
    if (isLeader) {
      return 0;
    }
    int n = this.nextFreeAnchor;
    this.nextFreeAnchor++;
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
    FifoFormationAssignment other = (FifoFormationAssignment) obj;
    if (other.nextFreeAnchor != this.nextFreeAnchor)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + this.nextFreeAnchor;
    return result;
  }
  
  @SyntheticMember
  public FifoFormationAssignment() {
    super();
  }
}
