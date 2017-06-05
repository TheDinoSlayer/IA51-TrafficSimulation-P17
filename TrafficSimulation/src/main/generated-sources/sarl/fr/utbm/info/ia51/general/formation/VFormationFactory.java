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

import fr.utbm.info.ia51.framework.math.Vector2f;
import fr.utbm.info.ia51.general.formation.FormationAnchor;
import fr.utbm.info.ia51.general.formation.FormationFactory;
import fr.utbm.info.ia51.general.formation.FormationPattern;
import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtext.xbase.lib.ExclusiveRange;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Factory of formation with a V pattern.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class VFormationFactory implements FormationFactory {
  /**
   * Space between anchor centers.
   */
  public final float distance;
  
  @DefaultValueSource
  public VFormationFactory(@DefaultValue("fr.utbm.info.ia51.general.formation.VFormationFactory#NEW_0") final float distanceBetweenAnchors, @DefaultValue("fr.utbm.info.ia51.general.formation.VFormationFactory#NEW_1") final float sizeOfAnchor) {
    float _max = Math.max(distanceBetweenAnchors, 0f);
    float _max_1 = Math.max(sizeOfAnchor, 0f);
    float _plus = (_max + _max_1);
    this.distance = _plus;
  }
  
  /**
   * Default value for the parameter distanceBetweenAnchors
   */
  @SyntheticMember
  @SarlSourceCode("10f")
  private final static float $DEFAULT_VALUE$NEW_0 = 10f;
  
  /**
   * Default value for the parameter sizeOfAnchor
   */
  @SyntheticMember
  @SarlSourceCode("50f")
  private final static float $DEFAULT_VALUE$NEW_1 = 50f;
  
  public FormationPattern build(final int nbAnchors) {
    FormationPattern pattern = new FormationPattern();
    FormationAnchor leader = new FormationAnchor(pattern);
    pattern.addAnchor(leader);
    final Vector2f topBranch = new Vector2f((-1), (-1));
    topBranch.setLength(this.distance);
    final Vector2f bottomBranch = new Vector2f((-1), 1);
    bottomBranch.setLength(this.distance);
    if ((nbAnchors > 1)) {
      Vector2f branch = null;
      boolean top = true;
      ExclusiveRange _doubleDotLessThan = new ExclusiveRange(1, nbAnchors, true);
      for (final Integer i : _doubleDotLessThan) {
        {
          if (top) {
            branch = topBranch;
          } else {
            branch = bottomBranch;
          }
          FormationAnchor _xifexpression = null;
          if (((i).intValue() > 1)) {
            _xifexpression = pattern.getAnchorAt(((i).intValue() - 2));
          } else {
            _xifexpression = leader;
          }
          FormationAnchor parent = _xifexpression;
          float _x = branch.getX();
          float _y = branch.getY();
          FormationAnchor anchor = new FormationAnchor(_x, _y, 0, parent, (i).intValue());
          pattern.addAnchor(anchor);
          top = (!top);
        }
      }
    }
    return pattern;
  }
  
  @DefaultValueUse("float,float")
  @SyntheticMember
  public VFormationFactory() {
    this($DEFAULT_VALUE$NEW_0, $DEFAULT_VALUE$NEW_1);
  }
  
  @DefaultValueUse("float,float")
  @SyntheticMember
  public VFormationFactory(final float distanceBetweenAnchors) {
    this(distanceBetweenAnchors, $DEFAULT_VALUE$NEW_1);
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
    VFormationFactory other = (VFormationFactory) obj;
    if (Float.floatToIntBits(other.distance) != Float.floatToIntBits(this.distance))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Float.floatToIntBits(this.distance);
    return result;
  }
}
