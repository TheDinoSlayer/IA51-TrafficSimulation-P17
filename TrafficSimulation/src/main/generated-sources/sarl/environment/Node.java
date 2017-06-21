package environment;

import environment.Edge;
import environment.EnvObject;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author AG
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class Node {
  private final HashSet<Edge> outgoing = new HashSet<Edge>();
  
  private final HashSet<Edge> incoming = new HashSet<Edge>();
  
  private Map<UUID, EnvObject> objects;
  
  @Pure
  public HashSet<Edge> getOutgoing() {
    return this.outgoing;
  }
  
  @Pure
  public HashSet<Edge> getIncoming() {
    return this.incoming;
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
  
  @SyntheticMember
  public Node() {
    super();
  }
}
