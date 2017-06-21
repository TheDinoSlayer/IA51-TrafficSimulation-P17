import GUI.Gui;
import agent.Car;
import environment.WorldModel;
import fr.utbm.info.ia51.framework.FrameworkLauncher;
import fr.utbm.info.ia51.framework.environment.AgentBody;
import fr.utbm.info.ia51.framework.environment.DynamicType;
import fr.utbm.info.ia51.framework.gui.BehaviorTypeSelector;
import fr.utbm.info.ia51.framework.time.TimeManager;
import fr.utbm.info.ia51.framework.util.SpawnMapping;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Quan
 */
@SarlSpecification("0.5")
@SarlElementType(16)
@SuppressWarnings("all")
public class MainProgram extends FrameworkLauncher {
  private final float WORLD_SIZE_X = 700f;
  
  private final float WORLD_SIZE_Y = 700f;
  
  private final int NUMBER_CARS = 1;
  
  @Override
  protected boolean initializeSimulation(final List<Object> parameters) {
    DynamicType type = BehaviorTypeSelector.open();
    if ((type != null)) {
      WorldModel environment = new WorldModel(this.WORLD_SIZE_X, this.WORLD_SIZE_Y);
      IntegerRange _upTo = new IntegerRange(1, this.NUMBER_CARS);
      for (final Integer i : _upTo) {
        environment.createCar();
      }
      TimeManager _timeManager = environment.getTimeManager();
      Gui gui = new Gui(this.WORLD_SIZE_X, this.WORLD_SIZE_Y, _timeManager);
      final SpawnMapping _function = (AgentBody it) -> {
        return Car.class;
      };
      return this.initializeSimulation(environment, type, gui, parameters, _function);
    } else {
      return false;
    }
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
    MainProgram other = (MainProgram) obj;
    if (Float.floatToIntBits(other.WORLD_SIZE_X) != Float.floatToIntBits(this.WORLD_SIZE_X))
      return false;
    if (Float.floatToIntBits(other.WORLD_SIZE_Y) != Float.floatToIntBits(this.WORLD_SIZE_Y))
      return false;
    if (other.NUMBER_CARS != this.NUMBER_CARS)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Float.floatToIntBits(this.WORLD_SIZE_X);
    result = prime * result + Float.floatToIntBits(this.WORLD_SIZE_Y);
    result = prime * result + this.NUMBER_CARS;
    return result;
  }
  
  @SyntheticMember
  public MainProgram(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public MainProgram(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
}
