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
package fr.utbm.info.ia51.general.influence;

import fr.utbm.info.ia51.framework.environment.Influence;
import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.io.Serializable;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Influence for changing the type of an object.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class TypeChangeInfluence extends Influence {
  private final Serializable type;
  
  /**
   * @param influencedObject the removed object.
   * @param newType the new type.
   */
  @DefaultValueSource
  public TypeChangeInfluence(@DefaultValue("fr.utbm.info.ia51.general.influence.TypeChangeInfluence#NEW_0") final UUID influencedObject, final Serializable newType) {
    super(influencedObject);
    this.type = newType;
  }
  
  /**
   * Default value for the parameter influencedObject
   */
  @SyntheticMember
  @SarlSourceCode("null")
  private final static UUID $DEFAULT_VALUE$NEW_0 = null;
  
  /**
   * Replies the new type.
   * 
   * @return the type.
   */
  @Pure
  public Serializable getType() {
    return this.type;
  }
  
  @Override
  @Pure
  public String toString() {
    return ("CHANGE_TYPE_TO: " + this.type);
  }
  
  /**
   * @optionalparam influencedObject the removed object.
   * @optionalparam newType the new type.
   */
  @DefaultValueUse("java.util.UUID,java.io.Serializable")
  @SyntheticMember
  public TypeChangeInfluence(final Serializable newType) {
    this($DEFAULT_VALUE$NEW_0, newType);
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
  private final static long serialVersionUID = 4583127163L;
}
