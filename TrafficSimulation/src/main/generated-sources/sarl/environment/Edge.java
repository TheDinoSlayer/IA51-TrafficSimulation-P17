package environment;

import environment.EnvObject;
import environment.Node;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Map;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author AG
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class Edge {
  private final Node origin;
  
  private final Node destination;
  
  private Map<UUID, EnvObject> objects;
  
  public Edge(final Node origin, final Node destination) {
    this.origin = origin;
    this.destination = destination;
  }
  
  @Pure
  public Node getOrigin() {
    return this.origin;
  }
  
  @Pure
  public Node getDestination() {
    return this.destination;
  }
  
  @Pure
  public Map<UUID, EnvObject> getObjects() {
    return this.objects;
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
