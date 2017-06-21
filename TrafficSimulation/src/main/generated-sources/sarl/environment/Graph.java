package environment;

import environment.Edge;
import environment.Node;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.LinkedList;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author AG
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class Graph {
  private final LinkedList<Edge> edges = new LinkedList<Edge>();
  
  private final LinkedList<Node> nodes = new LinkedList<Node>();
  
  public boolean addEdge(final Node origin, final Node destination) {
    boolean _xblockexpression = false;
    {
      final Edge edge = new Edge(origin, destination);
      origin.getOutgoing().add(edge);
      destination.getIncoming().add(edge);
      _xblockexpression = this.edges.add(edge);
    }
    return _xblockexpression;
  }
  
  public boolean addNode(final Node node) {
    boolean _xifexpression = false;
    boolean _contains = this.nodes.contains(node);
    boolean _not = (!_contains);
    if (_not) {
      _xifexpression = this.nodes.add(node);
    }
    return _xifexpression;
  }
  
  public boolean addIncomingNode(final Node node, final Node outNode) {
    boolean _xblockexpression = false;
    {
      this.addNode(node);
      this.addNode(outNode);
      _xblockexpression = this.addEdge(outNode, node);
    }
    return _xblockexpression;
  }
  
  public boolean addOutgoingNode(final Node node, final Node inNode) {
    boolean _xblockexpression = false;
    {
      this.addNode(node);
      this.addNode(inNode);
      _xblockexpression = this.addEdge(node, inNode);
    }
    return _xblockexpression;
  }
  
  @Pure
  public LinkedList<Node> getNodes() {
    return this.nodes;
  }
  
  @Pure
  public Node getNode(final int index) {
    return this.nodes.get(index);
  }
  
  @Pure
  public LinkedList<Edge> getEdges() {
    return this.edges;
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
  public Graph() {
    super();
  }
}
