import environment.Edge;
import environment.Graph;
import environment.Node;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * @author AG
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class MainTest {
  public static void main(final String[] args) {
    final Graph graph = new Graph();
    for (int i = 0; (i < 16); i++) {
      Node _node = new Node(("Node " + Integer.valueOf(i)));
      graph.addNode(_node);
    }
    graph.addEdge(graph.getNodes().get(0), graph.getNodes().get(1));
    graph.addEdge(graph.getNodes().get(1), graph.getNodes().get(2));
    graph.addEdge(graph.getNodes().get(1), graph.getNodes().get(10));
    graph.addEdge(graph.getNodes().get(2), graph.getNodes().get(3));
    graph.addEdge(graph.getNodes().get(4), graph.getNodes().get(5));
    graph.addEdge(graph.getNodes().get(5), graph.getNodes().get(6));
    graph.addEdge(graph.getNodes().get(5), graph.getNodes().get(14));
    graph.addEdge(graph.getNodes().get(6), graph.getNodes().get(7));
    graph.addEdge(graph.getNodes().get(8), graph.getNodes().get(9));
    graph.addEdge(graph.getNodes().get(9), graph.getNodes().get(10));
    graph.addEdge(graph.getNodes().get(9), graph.getNodes().get(6));
    graph.addEdge(graph.getNodes().get(10), graph.getNodes().get(11));
    graph.addEdge(graph.getNodes().get(12), graph.getNodes().get(13));
    graph.addEdge(graph.getNodes().get(13), graph.getNodes().get(14));
    graph.addEdge(graph.getNodes().get(13), graph.getNodes().get(2));
    graph.addEdge(graph.getNodes().get(14), graph.getNodes().get(15));
    LinkedList<Node> _nodes = graph.getNodes();
    for (final Node n : _nodes) {
      HashSet<Edge> _outgoing = n.getOutgoing();
      for (final Edge outN : _outgoing) {
        String _name = n.getName();
        String _plus = (_name + " goes to ");
        String _name_1 = outN.getDestination().getName();
        String _plus_1 = (_plus + _name_1);
        System.out.println(_plus_1);
      }
    }
  }
  
  @SyntheticMember
  public MainTest() {
    super();
  }
}
