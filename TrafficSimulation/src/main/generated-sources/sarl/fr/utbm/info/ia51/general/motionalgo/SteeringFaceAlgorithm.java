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
package fr.utbm.info.ia51.general.motionalgo;

import fr.utbm.info.ia51.framework.agent.MotionAlgorithmOutput;
import fr.utbm.info.ia51.framework.math.Point2f;
import fr.utbm.info.ia51.framework.math.Vector2f;
import fr.utbm.info.ia51.general.motionalgo.FaceAlgorithm;
import fr.utbm.info.ia51.general.motionalgo.SteeringAlignAlgorithm;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class SteeringFaceAlgorithm implements FaceAlgorithm {
  private final SteeringAlignAlgorithm align;
  
  public SteeringFaceAlgorithm(final float stopRadius, final float decelerateRadius) {
    SteeringAlignAlgorithm _steeringAlignAlgorithm = new SteeringAlignAlgorithm(stopRadius, decelerateRadius);
    this.align = _steeringAlignAlgorithm;
  }
  
  @Override
  public MotionAlgorithmOutput run(final Point2f position, final Vector2f orientation, final float angularSpeed, final float maxAngular, final Point2f target) {
    Vector2f _minus = target.operator_minus(position);
    return this.align.run(orientation, angularSpeed, maxAngular, _minus);
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
