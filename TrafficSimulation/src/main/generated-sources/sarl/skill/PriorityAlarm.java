package skill;

import capacity.Alarm;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.Skill;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author AG
 */
@SarlSpecification("0.5")
@SarlElementType(19)
@SuppressWarnings("all")
public class PriorityAlarm extends Skill implements Alarm {
  private boolean status;
  
  @Override
  public void emitAlarm() {
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
    PriorityAlarm other = (PriorityAlarm) obj;
    if (other.status != this.status)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + (this.status ? 1231 : 1237);
    return result;
  }
  
  @SyntheticMember
  public PriorityAlarm() {
    super();
  }
  
  @SyntheticMember
  public PriorityAlarm(final Agent agent) {
    super(agent);
  }
}
