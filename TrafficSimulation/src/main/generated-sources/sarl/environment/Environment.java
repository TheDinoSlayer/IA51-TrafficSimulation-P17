package environment;

import environment.AgentBody;
import environment.Graph;
import environment.QuadTree;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.HashSet;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Quan
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class Environment {
  private Graph graph = new Graph();
  
  private QuadTree environmentObjects;
  
  private HashSet<AgentBody> bodies = new HashSet<AgentBody>();
  
  public void computePerception() {
    for (final AgentBody body : this.bodies) {
    }
  }
  
  public void updateEnvironment() {
    for (final AgentBody body : this.bodies) {
    }
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
  public Environment() {
    super();
  }
}
