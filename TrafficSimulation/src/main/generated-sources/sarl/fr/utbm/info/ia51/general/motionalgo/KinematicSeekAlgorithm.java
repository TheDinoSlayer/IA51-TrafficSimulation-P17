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

import fr.utbm.info.ia51.framework.environment.DynamicType;
import fr.utbm.info.ia51.general.motionalgo.AbstractSeekAlgorithm;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Kinematic Seek Behaviour.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class KinematicSeekAlgorithm extends AbstractSeekAlgorithm {
  @Override
  @Pure
  @Inline(value = "JvmEnumerationLiteral: fr.utbm.info.ia51.framework.environment.DynamicType.KINEMATIC (visibility: PUBLIC, simpleName: KINEMATIC, identifier: fr.utbm.info.ia51.framework.environment.DynamicType.KINEMATIC, deprecated: <unset>) (static: true, final: true, volatile: false, transient: false, constant: <unset>, constantValue: null)", constantExpression = true)
  public DynamicType getDynamicType() {
    return DynamicType.KINEMATIC;
  }
  
  @SyntheticMember
  public KinematicSeekAlgorithm() {
    super();
  }
}
