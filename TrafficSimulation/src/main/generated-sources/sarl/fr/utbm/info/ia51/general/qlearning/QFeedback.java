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

import fr.utbm.info.ia51.general.qlearning.QState;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.io.Serializable;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Feedback for the QLearning.
 * 
 * @param <S> is the type of the states
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class QFeedback<S extends QState> implements Cloneable, Serializable {
  /**
   * Score given to the action by the feedback algorithm.
   */
  private float score;
  
  /**
   * Arriving state after the execution of the action.
   */
  private final S newState;
  
  /**
   * @param newState
   * @param score
   */
  public QFeedback(final S newState, final float score) {
    this.newState = newState;
    this.score = score;
  }
  
  @Override
  @Pure
  public QFeedback<S> clone() {
    try {
      Object _clone = super.clone();
      return ((QFeedback<S>) _clone);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Override
  @Pure
  public String toString() {
    String _string = Float.toString(this.score);
    String _plus = (_string + " >> ");
    String _string_1 = this.newState.toString();
    return (_plus + _string_1);
  }
  
  /**
   * Replies the new state after the action commitment.
   * 
   * @return the new state.
   */
  @Pure
  public S getNewState() {
    return this.newState;
  }
  
  /**
   * Replies the feedback score.
   * 
   * @return the feedback score.
   */
  @Pure
  public float getScore() {
    return this.score;
  }
  
  /**
   * Set the feedback score.
   * 
   * @param score is the feedback score.
   */
  public float setScore(final float score) {
    return this.score = score;
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
    QFeedback<S> other = (QFeedback<S>) obj;
    if (Float.floatToIntBits(other.score) != Float.floatToIntBits(this.score))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Float.floatToIntBits(this.score);
    return result;
  }
  
  @SyntheticMember
  private final static long serialVersionUID = -821600233L;
}
