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
package environment;

import capacity.MoveBody;
import com.google.common.base.Objects;
import fr.utbm.info.ia51.framework.environment.AbstractMobileObject;
import fr.utbm.info.ia51.framework.environment.Body;
import fr.utbm.info.ia51.framework.environment.DynamicType;
import fr.utbm.info.ia51.framework.environment.Frustum;
import fr.utbm.info.ia51.framework.environment.Influence;
import fr.utbm.info.ia51.framework.environment.MotionInfluence;
import fr.utbm.info.ia51.framework.environment.Percept;
import fr.utbm.info.ia51.framework.math.MathUtil;
import fr.utbm.info.ia51.framework.math.Shape2f;
import fr.utbm.info.ia51.framework.math.Vector2f;
import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * Object on the environment.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class AgentBody extends AbstractMobileObject implements Body, MoveBody {
  private final Frustum frustum;
  
  private Vector orientation;
  
  private transient MotionInfluence motionInfluence;
  
  private transient List<Influence> otherInfluences = new ArrayList<Influence>();
  
  private transient List<Percept> perceptions = new ArrayList<Percept>();
  
  /**
   * @param id
   * @param shape the shape of the body, considering that it is centered at the (0,0) position.
   * @param maxLinearSpeed is the maximal linear speed.
   * @param maxLinearAcceleration is the maximal linear acceleration.
   * @param maxAngularSpeed is the maximal angular speed.
   * @param maxAngularAcceleration is the maximal angular acceleration.
   * @param frustum the field-of-view associated to the body.
   */
  public AgentBody(final UUID id, final Shape2f<?> shape, final float maxLinearSpeed, final float maxLinearAcceleration, final float maxAngularSpeed, final float maxAngularAcceleration, final Frustum frustum) {
    super(id, shape, maxLinearSpeed, maxLinearAcceleration, maxAngularSpeed, maxAngularAcceleration);
    this.frustum = frustum;
    this.setType("BODY");
  }
  
  @Override
  @Pure
  public AgentBody clone() {
    AbstractMobileObject _clone = super.clone();
    AgentBody c = ((AgentBody) _clone);
    c.motionInfluence = null;
    ArrayList<Influence> _arrayList = new ArrayList<Influence>();
    c.otherInfluences = _arrayList;
    ArrayList<Percept> _arrayList_1 = new ArrayList<Percept>();
    c.perceptions = _arrayList_1;
    return c;
  }
  
  @Override
  @Pure
  public String toString() {
    String label = MessageFormat.format("Body of {0}", this.getID());
    String name = this.getName();
    boolean _isNullOrEmpty = StringExtensions.isNullOrEmpty(name);
    if (_isNullOrEmpty) {
      return (((name + "(") + label) + ")");
    }
    return label;
  }
  
  /**
   * Replies the frustum associated to this body.
   * 
   * @return the frustum.
   */
  @Pure
  public Frustum getFrustum() {
    return this.frustum;
  }
  
  /**
   * Invoked to send the given influence to the environment.
   * 
   * @param influence the influence.
   */
  public Boolean influence(final Influence influence) {
    boolean _xifexpression = false;
    if ((influence instanceof MotionInfluence)) {
      boolean _xifexpression_1 = false;
      if (((((MotionInfluence)influence).getInfluencedObject() == null) || Objects.equal(((MotionInfluence)influence).getInfluencedObject(), this.getID()))) {
        DynamicType _type = ((MotionInfluence)influence).getType();
        if (_type != null) {
          switch (_type) {
            case KINEMATIC:
              this.influenceKinematic(((MotionInfluence)influence).getLinearInfluence(), ((MotionInfluence)influence).getAngularInfluence());
              break;
            case STEERING:
              this.influenceSteering(((MotionInfluence)influence).getLinearInfluence(), ((MotionInfluence)influence).getAngularInfluence());
              break;
            default:
              break;
          }
        }
      } else {
        _xifexpression_1 = this.otherInfluences.add(((MotionInfluence)influence));
      }
      _xifexpression = _xifexpression_1;
    } else {
      boolean _xifexpression_2 = false;
      if ((influence != null)) {
        _xifexpression_2 = this.otherInfluences.add(influence);
      }
      _xifexpression = _xifexpression_2;
    }
    return Boolean.valueOf(_xifexpression);
  }
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @param linearInfluence is the linear influence to apply on the object.
   * @param angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueSource
  public void influenceKinematic(@DefaultValue("environment.AgentBody#INFLUENCEKINEMATIC_0") final Vector2f linearInfluence, @DefaultValue("environment.AgentBody#INFLUENCEKINEMATIC_1") final float angularInfluence) {
    Vector2f li = null;
    if ((linearInfluence != null)) {
      li = linearInfluence.clone();
      float nSpeed = li.length();
      float _maxLinearSpeed = this.getMaxLinearSpeed();
      boolean _greaterThan = (nSpeed > _maxLinearSpeed);
      if (_greaterThan) {
        li.setLength(this.getMaxLinearSpeed());
      }
    }
    float _maxAngularSpeed = this.getMaxAngularSpeed();
    float _minus = (-_maxAngularSpeed);
    float ai = MathUtil.clamp(angularInfluence, _minus, this.getMaxAngularSpeed());
    UUID _iD = this.getID();
    MotionInfluence _motionInfluence = new MotionInfluence(DynamicType.KINEMATIC, _iD, li, ai);
    this.motionInfluence = _motionInfluence;
  }
  
  /**
   * Default value for the parameter linearInfluence
   */
  @SyntheticMember
  @SarlSourceCode("null")
  private final static Vector2f $DEFAULT_VALUE$INFLUENCEKINEMATIC_0 = null;
  
  /**
   * Default value for the parameter angularInfluence
   */
  @SyntheticMember
  @SarlSourceCode("0")
  private final static float $DEFAULT_VALUE$INFLUENCEKINEMATIC_1 = 0;
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @param linearInfluence is the linear influence to apply on the object.
   * @param angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueSource
  public void influenceSteering(@DefaultValue("environment.AgentBody#INFLUENCESTEERING_0") final Vector2f linearInfluence, @DefaultValue("environment.AgentBody#INFLUENCESTEERING_1") final float angularInfluence) {
    Vector2f li = null;
    if ((linearInfluence != null)) {
      li = linearInfluence.clone();
      float nSpeed = li.length();
      float _maxLinearAcceleration = this.getMaxLinearAcceleration();
      boolean _greaterThan = (nSpeed > _maxLinearAcceleration);
      if (_greaterThan) {
        li.setLength(this.getMaxLinearAcceleration());
      }
    }
    float _maxAngularAcceleration = this.getMaxAngularAcceleration();
    float _minus = (-_maxAngularAcceleration);
    float ai = MathUtil.clamp(angularInfluence, _minus, this.getMaxAngularAcceleration());
    UUID _iD = this.getID();
    MotionInfluence _motionInfluence = new MotionInfluence(DynamicType.STEERING, _iD, li, ai);
    this.motionInfluence = _motionInfluence;
  }
  
  /**
   * Default value for the parameter linearInfluence
   */
  @SyntheticMember
  @SarlSourceCode("null")
  private final static Vector2f $DEFAULT_VALUE$INFLUENCESTEERING_0 = null;
  
  /**
   * Default value for the parameter angularInfluence
   */
  @SyntheticMember
  @SarlSourceCode("0")
  private final static float $DEFAULT_VALUE$INFLUENCESTEERING_1 = 0;
  
  /**
   * Replies all the perceived objects.
   * 
   * @return the perceived objects.
   */
  @Pure
  public List<Percept> getPerceivedObjects() {
    return this.perceptions;
  }
  
  /**
   * Replies the influence.
   * 
   * @return the influence.
   */
  List<Influence> consumeOtherInfluences() {
    List<Influence> _xblockexpression = null;
    {
      List<Influence> otherInfluences = this.otherInfluences;
      ArrayList<Influence> _arrayList = new ArrayList<Influence>();
      this.otherInfluences = _arrayList;
      for (final Influence i : otherInfluences) {
      }
      _xblockexpression = otherInfluences;
    }
    return _xblockexpression;
  }
  
  /**
   * Replies the influence.
   * 
   * @return the influence.
   */
  MotionInfluence consumeMotionInfluence() {
    MotionInfluence _xblockexpression = null;
    {
      MotionInfluence mi = this.motionInfluence;
      this.motionInfluence = null;
      _xblockexpression = mi;
    }
    return _xblockexpression;
  }
  
  /**
   * Set the perceptions.
   * 
   * @param perceptions
   */
  void setPerceptions(final List<Percept> perceptions) {
    this.perceptions = perceptions;
  }
  
  @Override
  public void move(final Vector2f linear, final float angular) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @optionalparam linearInfluence is the linear influence to apply on the object.
   * @optionalparam angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueUse("fr.utbm.info.ia51.framework.math.Vector2f,float")
  @SyntheticMember
  public final void influenceKinematic() {
    influenceKinematic($DEFAULT_VALUE$INFLUENCEKINEMATIC_0, $DEFAULT_VALUE$INFLUENCEKINEMATIC_1);
  }
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @optionalparam linearInfluence is the linear influence to apply on the object.
   * @param angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueUse("fr.utbm.info.ia51.framework.math.Vector2f,float")
  @SyntheticMember
  public final void influenceKinematic(final float angularInfluence) {
    influenceKinematic($DEFAULT_VALUE$INFLUENCEKINEMATIC_0, angularInfluence);
  }
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @param linearInfluence is the linear influence to apply on the object.
   * @optionalparam angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueUse("fr.utbm.info.ia51.framework.math.Vector2f,float")
  @SyntheticMember
  public final void influenceKinematic(final Vector2f linearInfluence) {
    influenceKinematic(linearInfluence, $DEFAULT_VALUE$INFLUENCEKINEMATIC_1);
  }
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @optionalparam linearInfluence is the linear influence to apply on the object.
   * @optionalparam angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueUse("fr.utbm.info.ia51.framework.math.Vector2f,float")
  @SyntheticMember
  public final void influenceSteering() {
    influenceSteering($DEFAULT_VALUE$INFLUENCESTEERING_0, $DEFAULT_VALUE$INFLUENCESTEERING_1);
  }
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @optionalparam linearInfluence is the linear influence to apply on the object.
   * @param angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueUse("fr.utbm.info.ia51.framework.math.Vector2f,float")
  @SyntheticMember
  public final void influenceSteering(final float angularInfluence) {
    influenceSteering($DEFAULT_VALUE$INFLUENCESTEERING_0, angularInfluence);
  }
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @param linearInfluence is the linear influence to apply on the object.
   * @optionalparam angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueUse("fr.utbm.info.ia51.framework.math.Vector2f,float")
  @SyntheticMember
  public final void influenceSteering(final Vector2f linearInfluence) {
    influenceSteering(linearInfluence, $DEFAULT_VALUE$INFLUENCESTEERING_1);
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
  
  @SyntheticMember
  private final static long serialVersionUID = -1463410517L;
}
