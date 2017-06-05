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

import fr.utbm.info.ia51.framework.math.Point2f;
import fr.utbm.info.ia51.framework.math.Vector2f;
import fr.utbm.info.ia51.general.formation.FormationPattern;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.lang.ref.WeakReference;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * An anchor in a formation body.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class FormationAnchor {
  private final int spotIndex;
  
  private final Vector2f relativePosition;
  
  private final float relativeOrientation;
  
  private final FormationAnchor parent;
  
  private final WeakReference<FormationPattern> formationParent;
  
  private Point2f bufferGlobalPosition;
  
  private Vector2f bufferGlobalOrientation;
  
  /**
   * @param dx is the relative vector from the parent anchor to this anchor.
   * @param dy is the relative vector from the parent anchor to this anchor.
   * @param rotation is the relative rotation from the parent anchor.
   * @param parent is the parent anchor.
   * @param spotIndex is the index of the anchor in the formation body.
   */
  public FormationAnchor(final float dx, final float dy, final float rotation, final FormationAnchor parent, final int spotIndex) {
    this.spotIndex = spotIndex;
    Vector2f _vector2f = new Vector2f(dx, dy);
    this.relativePosition = _vector2f;
    this.relativeOrientation = rotation;
    this.parent = parent;
    FormationPattern _pattern = parent.getPattern();
    WeakReference<FormationPattern> _weakReference = new WeakReference<FormationPattern>(_pattern);
    this.formationParent = _weakReference;
  }
  
  /**
   * @param dx is the relative vector from the parent anchor to this anchor.
   * @param dy is the relative vector from the parent anchor to this anchor.
   * @param rotation is the relative rotation from the parent anchor.
   * @param parent the formation pattern
   */
  public FormationAnchor(final float dx, final float dy, final float rotation, final FormationPattern parent) {
    this.spotIndex = 0;
    Vector2f _vector2f = new Vector2f(dx, dy);
    this.relativePosition = _vector2f;
    this.relativeOrientation = rotation;
    this.parent = null;
    WeakReference<FormationPattern> _weakReference = new WeakReference<FormationPattern>(parent);
    this.formationParent = _weakReference;
  }
  
  /**
   * @param parent the formation pattern
   */
  public FormationAnchor(final FormationPattern parent) {
    this.spotIndex = 0;
    Vector2f _vector2f = new Vector2f();
    this.relativePosition = _vector2f;
    this.relativeOrientation = 0;
    this.parent = null;
    WeakReference<FormationPattern> _weakReference = new WeakReference<FormationPattern>(parent);
    this.formationParent = _weakReference;
  }
  
  @Override
  @Pure
  public String toString() {
    return ("Anchor " + Integer.valueOf(this.spotIndex));
  }
  
  /**
   * Clear the buffers
   */
  Vector2f clear() {
    Vector2f _xblockexpression = null;
    {
      this.bufferGlobalPosition = null;
      _xblockexpression = this.bufferGlobalOrientation = null;
    }
    return _xblockexpression;
  }
  
  /**
   * Replies the global position of this anchor.
   * 
   * @return the global position of this anchor.
   */
  @Pure
  public Point2f getGlobalPosition() {
    if ((this.bufferGlobalPosition == null)) {
      FormationAnchor current = this;
      Vector2f toRoot = new Vector2f();
      while ((current != null)) {
        {
          toRoot.operator_add(current.relativePosition);
          current = current.parent;
        }
      }
      FormationPattern pattern = this.getPattern();
      toRoot.turn(pattern.getGlobalAngle());
      Point2f _globalPosition = pattern.getGlobalPosition();
      Point2f _plus = toRoot.operator_plus(_globalPosition);
      this.bufferGlobalPosition = _plus;
    }
    return this.bufferGlobalPosition;
  }
  
  /**
   * Replies the global orientation which may be targeted by an entity
   * on this anchor.
   * 
   * @return the global orientation targeted by the entity on the anchor.
   */
  @Pure
  public Vector2f getGlobalOrientation() {
    if ((this.bufferGlobalOrientation == null)) {
      FormationAnchor current = this;
      float orientation = 0f;
      while ((current != null)) {
        {
          float _orientation = orientation;
          orientation = (_orientation + current.relativeOrientation);
          current = current.parent;
        }
      }
      Vector2f v = this.getPattern().getGlobalOrientation();
      v.turn(orientation);
      this.bufferGlobalOrientation = v;
    }
    return this.bufferGlobalOrientation;
  }
  
  /**
   * Replies the relative position from the parent anchor to this anchor.
   * 
   * @return the relative position.
   */
  @Pure
  public Vector2f getRelativePosition() {
    return this.relativePosition.clone();
  }
  
  /**
   * Replies the relative orientation from the parent anchor to this anchor.
   * 
   * @return the relative orientation
   */
  @Pure
  public float getRelativeOrientation() {
    return this.relativeOrientation;
  }
  
  /**
   * Replies the parent anchor.
   * 
   * @return the parent anchor, or <code>null</code> if none.
   */
  @Pure
  public FormationAnchor getParent() {
    return this.parent;
  }
  
  /**
   * Replies the formation pattern.
   * 
   * @return the pattern, never <code>null</code>.
   */
  @Pure
  public FormationPattern getPattern() {
    return this.formationParent.get();
  }
  
  /**
   * Replies the index of this anchor in the formation body.
   * 
   * @return the index of this anchor in the formation body.
   */
  @Pure
  public int getSpotIndex() {
    return this.spotIndex;
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
    FormationAnchor other = (FormationAnchor) obj;
    if (other.spotIndex != this.spotIndex)
      return false;
    if (Float.floatToIntBits(other.relativeOrientation) != Float.floatToIntBits(this.relativeOrientation))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + this.spotIndex;
    result = prime * result + Float.floatToIntBits(this.relativeOrientation);
    return result;
  }
}
