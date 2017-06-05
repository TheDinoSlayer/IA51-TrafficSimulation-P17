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

import fr.utbm.info.ia51.general.qlearning.QAction;
import fr.utbm.info.ia51.general.qlearning.QComparator;
import fr.utbm.info.ia51.general.qlearning.QFeedback;
import fr.utbm.info.ia51.general.qlearning.QProblem;
import fr.utbm.info.ia51.general.qlearning.QState;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import org.eclipse.xtext.xbase.lib.ExclusiveRange;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * This is the QLearning core algorithm.
 * 
 * @param <S> is the type of the states
 * @param <A> is the type of the actions
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class QLearning<S extends QState, A extends QAction> {
  private final Random random = new Random();
  
  private final QProblem<S, A> problem;
  
  private final TreeMap<S, Map<A, Float>> qValues = new TreeMap<S, Map<A, Float>>(new QComparator());
  
  /**
   * @param problem is the problem to learn on.
   */
  public QLearning(final QProblem<S, A> problem) {
    this.problem = problem;
    List<S> _availableStates = problem.getAvailableStates();
    for (final S s : _availableStates) {
      {
        QComparator _qComparator = new QComparator();
        TreeMap<A, Float> m = new TreeMap<A, Float>(_qComparator);
        this.qValues.put(s, m);
        List<A> _availableActionsFor = problem.getAvailableActionsFor(s);
        for (final A a : _availableActionsFor) {
          m.put(a, Float.valueOf(0f));
        }
      }
    }
  }
  
  /**
   * @param numberOfIterations are the number of iterations to execute for this call.
   */
  public void learn(final int numberOfIterations) {
    List<A> actions = null;
    S state = null;
    A action = null;
    QFeedback<S> result = null;
    state = this.problem.getCurrentState();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, numberOfIterations, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        float _nextFloat = this.random.nextFloat();
        float _nu = this.problem.getNu();
        boolean _lessThan = (_nextFloat < _nu);
        if (_lessThan) {
          state = this.problem.getRandomState();
        }
        float _nextFloat_1 = this.random.nextFloat();
        float _rho = this.problem.getRho();
        boolean _lessThan_1 = (_nextFloat_1 < _rho);
        if (_lessThan_1) {
          actions = this.problem.getAvailableActionsFor(state);
          action = actions.get(this.random.nextInt(actions.size()));
        } else {
          action = this.getBestAction(state);
        }
        result = this.forceFeedBack(state, action, Float.NaN);
        state = result.getNewState();
      }
    }
  }
  
  /**
   * Force the update of the QLearning graph with the given feedback.
   * 
   * @param state is the state from which the feedback is given.
   * @param action is the state from which the feedback is given.
   * @param feedbackValue is the feedback value.
   * @return the feedback used.
   */
  public QFeedback<S> forceFeedBack(final S state, final A action, final float feedbackValue) {
    QFeedback<S> result = this.problem.takeAction(state, action);
    boolean _isNaN = Float.isNaN(feedbackValue);
    boolean _not = (!_isNaN);
    if (_not) {
      result.setScore(feedbackValue);
    }
    float q = this.getQ(state, action);
    A futureAction = this.getBestAction(result.getNewState());
    float maxQ = this.getQ(result.getNewState(), futureAction);
    float _alpha = this.problem.getAlpha();
    float _minus = (1f - _alpha);
    float _multiply = (_minus * q);
    float _alpha_1 = this.problem.getAlpha();
    float _score = result.getScore();
    float _gamma = this.problem.getGamma();
    float _multiply_1 = (_gamma * maxQ);
    float _plus = (_score + _multiply_1);
    float _multiply_2 = (_alpha_1 * _plus);
    float _plus_1 = (_multiply + _multiply_2);
    q = _plus_1;
    this.putQ(state, action, q);
    return result;
  }
  
  /**
   * Replies the best action from the QState or a random action
   * if more than 1 best action.
   * 
   * @param state
   * @return the best action
   */
  @Pure
  public A getBestAction(final S state) {
    Map<A, Float> m = this.qValues.get(state);
    ArrayList<A> bestActions = new ArrayList<A>();
    float bestScore = Float.NEGATIVE_INFINITY;
    Set<Map.Entry<A, Float>> _entrySet = m.entrySet();
    for (final Map.Entry<A, Float> entry : _entrySet) {
      Float _value = entry.getValue();
      boolean _greaterThan = ((_value).floatValue() > bestScore);
      if (_greaterThan) {
        bestActions.clear();
        A _key = entry.getKey();
        bestActions.add(_key);
        bestScore = (entry.getValue()).floatValue();
      } else {
        Float _value_1 = entry.getValue();
        boolean _equals = ((_value_1).floatValue() == bestScore);
        if (_equals) {
          A _key_1 = entry.getKey();
          bestActions.add(_key_1);
        }
      }
    }
    return bestActions.get(this.random.nextInt(bestActions.size()));
  }
  
  @Pure
  private float getQ(final S state, final A action) {
    float _xblockexpression = (float) 0;
    {
      Map<A, Float> m = this.qValues.get(state);
      Float q = m.get(action);
      float _floatValue = 0f;
      if (q!=null) {
        _floatValue=q.floatValue();
      }
      _xblockexpression = _floatValue;
    }
    return _xblockexpression;
  }
  
  private Float putQ(final S state, final A action, final float q) {
    Float _xblockexpression = null;
    {
      Map<A, Float> m = this.qValues.get(state);
      _xblockexpression = m.put(action, Float.valueOf(q));
    }
    return _xblockexpression;
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
