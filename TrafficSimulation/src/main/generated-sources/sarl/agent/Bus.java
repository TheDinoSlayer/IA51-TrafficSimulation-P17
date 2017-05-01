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
public class Bus extends Vehicle {
  @SyntheticMember
  public Bus(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public Bus(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
}
