package skill;

import capacity.MoveBody;
import fr.utbm.info.ia51.framework.math.Vector2f;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.Skill;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author AG
 */
@SarlSpecification("0.5")
@SuppressWarnings("all")
public class InfluenceBasedSkill extends Skill implements MoveBody {
  private UUID envID;
  
  @Override
  public void move(final Vector2f linear, final float angular) {
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
    InfluenceBasedSkill other = (InfluenceBasedSkill) obj;
    if (this.envID == null) {
      if (other.envID != null)
        return false;
    } else if (!this.envID.equals(other.envID))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.envID== null) ? 0 : this.envID.hashCode());
    return result;
  }
  
  @SyntheticMember
  public InfluenceBasedSkill() {
    super();
  }
  
  @SyntheticMember
  public InfluenceBasedSkill(final Agent agent) {
    super(agent);
  }
}
