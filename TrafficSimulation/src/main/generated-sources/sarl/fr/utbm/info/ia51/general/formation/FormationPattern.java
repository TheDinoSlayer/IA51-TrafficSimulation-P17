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
import fr.utbm.info.ia51.general.formation.FormationAnchor;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * A formation composed of connected anchors.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class FormationPattern {
  private FormationAnchor leaderAnchor;
  
  private final List<FormationAnchor> anchors;
  
  private final Point2f globalPosition = new Point2f();
  
  private final Vector2f globalOrientation = new Vector2f(1, 0);
  
  public FormationPattern() {
    this.leaderAnchor = null;
    ArrayList<FormationAnchor> _arrayList = new ArrayList<FormationAnchor>();
    this.anchors = _arrayList;
  }
  
  /**
   * Clear the formation body.
   */
  protected void clear() {
    this.clearBuffers();
    this.leaderAnchor = null;
    this.anchors.clear();
  }
  
  /**
   * Clear the buffers.
   */
  protected void clearBuffers() {
    for (final FormationAnchor anchor : this.anchors) {
      anchor.clear();
    }
  }
  
  FormationAnchor addAnchor(final FormationAnchor anchor) {
    FormationAnchor _xblockexpression = null;
    {
      this.anchors.add(anchor);
      anchor.clear();
      FormationAnchor _xifexpression = null;
      int _size = this.anchors.size();
      boolean _equals = (_size == 1);
      if (_equals) {
        _xifexpression = this.leaderAnchor = anchor;
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  /**
   * Replies the anchors in the formation body.
   * 
   * @return the anchors in the formation body.
   */
  @Pure
  public List<FormationAnchor> getAnchors() {
    return Collections.<FormationAnchor>unmodifiableList(this.anchors);
  }
  
  /**
   * Replies the anchor at the given index.
   * 
   * @param index
   * @return the anchor at the given index.
   */
  @Pure
  public FormationAnchor getAnchorAt(final int index) {
    return this.anchors.get(index);
  }
  
  /**
   * Change the global position and orientation of the formation.
   * 
   * @param position
   * @param direction
   */
  public void setFormationLocation(final Point2f position, final Vector2f direction) {
    if (((position != null) || (direction != null))) {
      if ((position != null)) {
        this.globalPosition.set(position);
      }
      if ((direction != null)) {
        this.globalOrientation.set(direction);
      }
      this.clearBuffers();
    }
  }
  
  /**
   * Replies the global position of the formation, ie of the leader.
   * 
   * @return the global position of the leader.
   */
  @Pure
  public Point2f getGlobalPosition() {
    return this.globalPosition.clone();
  }
  
  /**
   * Replies the global orientation of the formation, ie of the leader.
   * 
   * @return the global orientation of the leader.
   */
  @Pure
  public Vector2f getGlobalOrientation() {
    return this.globalOrientation.clone();
  }
  
  /**
   * Replies the global orientation of the formation, ie of the leader.
   * 
   * @return the global orientation of the leader.
   */
  @Pure
  public float getGlobalAngle() {
    return this.globalOrientation.getOrientationAngle();
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
