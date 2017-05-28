package environment.staticenv.sign;

import environment.staticenv.sign.SignType;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;

/**
 * @author AG
 */
@FunctionalInterface
@SarlSpecification("0.5")
@SarlElementType(9)
@SuppressWarnings("all")
public interface SignSymbol {
  public abstract SignType getType();
}
