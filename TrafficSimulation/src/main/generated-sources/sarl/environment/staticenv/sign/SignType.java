package environment.staticenv.sign;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;

/**
 * @author AG
 */
@SarlSpecification("0.5")
@SarlElementType(10)
@SuppressWarnings("all")
public enum SignType {
  PROHIBITION,
  
  STOP,
  
  GIVE_WAY;
}
