package environment.staticenv;

import environment.staticenv.ColourLight;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author AG
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class TrafficLight {
  private Boolean status;
  
  private int time;
  
  private ColourLight colour;
  
  @Pure
  public Boolean getStatus() {
    return this.status;
  }
  
  @Pure
  public ColourLight getColour() {
    return this.colour;
  }
  
  public void checkStatus() {
  }
  
  @Pure
  private void toGreen() {
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
    TrafficLight other = (TrafficLight) obj;
    if (other.status != this.status)
      return false;
    if (other.time != this.time)
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
    result = prime * result + this.time;
    return result;
  }
  
  @SyntheticMember
  public TrafficLight() {
    super();
  }
}
