package agent;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import java.util.UUID;
import javax.inject.Inject;

/**
 * @author AG
 */
@SarlSpecification("0.5")
@SarlElementType(16)
@SuppressWarnings("all")
public class Vehicle extends Agent {
  @SyntheticMember
  public Vehicle(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public Vehicle(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
}
