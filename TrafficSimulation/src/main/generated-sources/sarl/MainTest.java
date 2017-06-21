import environment.Graph;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;

/**
 * @author AG
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class MainTest {
  public void main(final String[] args) {
    final Graph graph = new Graph();
  }
  
  @SyntheticMember
  public MainTest() {
    super();
  }
}
