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
package fr.utbm.info.ia51.general.frustum;

import fr.utbm.info.ia51.framework.environment.AbstractFrustum;
import fr.utbm.info.ia51.framework.math.Circle2f;
import fr.utbm.info.ia51.framework.math.Point2f;
import fr.utbm.info.ia51.framework.math.Shape2f;
import fr.utbm.info.ia51.framework.math.Vector2f;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * A circular frustum.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class CircleFrustum extends AbstractFrustum {
  private final float radius;
  
  /**
   * @param owner the identifier of the owner of this frustum.
   * @param radius the radius of the field-of-view.
   */
  public CircleFrustum(final UUID owner, final float radius) {
    super(owner);
    this.radius = radius;
  }
  
  /**
   * Replies the radius of the frustum.
   * 
   * @return the radius.
   */
  @Pure
  public float getRadius() {
    return this.radius;
  }
  
  @Override
  @Pure
  public Shape2f<?> toShape(final Point2f position, final Vector2f orientation) {
    return new Circle2f(position, this.radius);
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
    CircleFrustum other = (CircleFrustum) obj;
    if (Float.floatToIntBits(other.radius) != Float.floatToIntBits(this.radius))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Float.floatToIntBits(this.radius);
    return result;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public CircleFrustum clone() {
    try {
      return (CircleFrustum) super.clone();
    } catch (Throwable exception) {
      throw new Error(exception);
    }
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 2737768602L;
}
