package agent;

import agent.Vehicle;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import java.util.UUID;
import javax.inject.Inject;

/**
 * @author AG
 */
@SarlSpecification("0.5")
@SuppressWarnings("all")
public class Priority extends Vehicle {
  @SyntheticMember
  public Priority(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public Priority(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
}
