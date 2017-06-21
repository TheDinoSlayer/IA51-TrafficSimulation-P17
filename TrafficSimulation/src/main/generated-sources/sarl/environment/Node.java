package environment;

import environment.Edge;
import environment.EnvObject;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
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
  
  private String name;
  
  public Node(final String name) {
    this.name = name;
  }
  
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
  
  @Pure
  public String getName() {
    return this.name;
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
    Node other = (Node) obj;
    if (!Objects.equals(this.name, other.name)) {
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
    result = prime * result + Objects.hashCode(this.name);
    return result;
  }
}
