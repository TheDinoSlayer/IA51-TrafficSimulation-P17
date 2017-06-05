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
package fr.utbm.info.ia51.general.qlearning;

import fr.utbm.info.ia51.general.qlearning.QComparable;
import fr.utbm.info.ia51.general.qlearning.QComparator;
import fr.utbm.info.ia51.general.qlearning.QState;
import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Objects;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * Default implementation of a QState.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class DefaultQState implements QState {
  private final int number;
  
  private String description;
  
  /**
   * @param number is the number associated to this state.
   * @param description describes the state.
   */
  @DefaultValueSource
  public DefaultQState(final int number, @DefaultValue("fr.utbm.info.ia51.general.qlearning.DefaultQState#NEW_0") final String description) {
    this.number = number;
    boolean _isNullOrEmpty = StringExtensions.isNullOrEmpty(description);
    if (_isNullOrEmpty) {
      this.description = ("STATE_" + Integer.valueOf(this.number));
    } else {
      this.description = description;
    }
  }
  
  /**
   * Default value for the parameter description
   */
  @SyntheticMember
  @SarlSourceCode("null")
  private final static String $DEFAULT_VALUE$NEW_0 = null;
  
  @Override
  @Pure
  public DefaultQState clone() {
    try {
      Object _clone = super.clone();
      return ((DefaultQState) _clone);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Change the description of the state.
   * 
   * @param description
   */
  public String setDescription(final String description) {
    String _xifexpression = null;
    boolean _isNullOrEmpty = StringExtensions.isNullOrEmpty(description);
    if (_isNullOrEmpty) {
      _xifexpression = this.description = ("STATE_" + Integer.valueOf(this.number));
    } else {
      _xifexpression = this.description = description;
    }
    return _xifexpression;
  }
  
  @Override
  @Pure
  public String toString() {
    return this.description;
  }
  
  @Override
  @Pure
  public int toInt() {
    return this.number;
  }
  
  @Override
  public int compareTo(final QComparable o) {
    return QComparator.QCOMPARATOR.compare(this, o);
  }
  
  /**
   * @optionalparam number is the number associated to this state.
   * @optionalparam description describes the state.
   */
  @DefaultValueUse("int,java.lang.String")
  @SyntheticMember
  public DefaultQState(final int number) {
    this(number, $DEFAULT_VALUE$NEW_0);
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
    DefaultQState other = (DefaultQState) obj;
    if (other.number != this.number)
      return false;
    if (!Objects.equals(this.description, other.description)) {
      return false;
    }
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + this.number;
    result = prime * result + Objects.hashCode(this.description);
    return result;
  }
  
  @SyntheticMember
  private final static long serialVersionUID = -4834525979L;
}
