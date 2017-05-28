package capacity;

import fr.utbm.info.ia51.framework.math.Vector2f;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.core.AgentTrait;
import io.sarl.lang.core.Capacity;

/**
 * @author AG
 */
@FunctionalInterface
@SarlSpecification("0.5")
@SarlElementType(17)
@SuppressWarnings("all")
public interface MoveBody extends Capacity {
  public abstract void move(final Vector2f linear, final float angular);
  
  public static class ContextAwareCapacityWrapper<C extends MoveBody> extends Capacity.ContextAwareCapacityWrapper<C> implements MoveBody {
    public ContextAwareCapacityWrapper(final C capacity, final AgentTrait caller) {
      super(capacity, caller);
    }
    
    public void move(final Vector2f linear, final float angular) {
      try {
        ensureCallerInLocalThread();
        this.capacity.move(linear, angular);
      } finally {
        resetCallerInLocalThread();
      }
    }
  }
}
