package capacity;

import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.core.AgentTrait;
import io.sarl.lang.core.Capacity;

/**
 * @author AG
 */
@FunctionalInterface
@SarlSpecification("0.5")
@SuppressWarnings("all")
public interface Alarm extends Capacity {
  public abstract void emitAlarm();
  
  public static class ContextAwareCapacityWrapper<C extends Alarm> extends Capacity.ContextAwareCapacityWrapper<C> implements Alarm {
    public ContextAwareCapacityWrapper(final C capacity, final AgentTrait caller) {
      super(capacity, caller);
    }
    
    public void emitAlarm() {
      try {
        ensureCallerInLocalThread();
        this.capacity.emitAlarm();
      } finally {
        resetCallerInLocalThread();
      }
    }
  }
}
