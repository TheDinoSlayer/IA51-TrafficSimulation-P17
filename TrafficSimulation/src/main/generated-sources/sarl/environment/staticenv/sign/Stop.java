package environment.staticenv.sign;

import environment.EnvObject;
import environment.staticenv.sign.SignSymbol;
import environment.staticenv.sign.SignType;
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
public class Stop extends EnvObject implements SignSymbol {
  private final SignType signType = SignType.STOP;
  
  @Override
  @Pure
  public SignType getType() {
    return this.signType;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    return result;
  }
  
  @SyntheticMember
  public Stop() {
    super();
  }
}
