package environment;

import fr.utbm.info.ia51.framework.math.Point2f;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Objects;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author AG
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class EnvObject {
  private UUID id;
  
  private final Point2f position = new Point2f();
  
  @Pure
  public Point2f getPosition() {
    return this.position;
  }
  
  public float setPosition(final Point2f position) {
    float _xblockexpression = (float) 0;
    {
      this.position.setX(position.getX());
      _xblockexpression = this.position.setY(position.getY());
    }
    return _xblockexpression;
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
    EnvObject other = (EnvObject) obj;
    if (!Objects.equals(this.id, other.id)) {
      return false;
    }
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Objects.hashCode(this.id);
    return result;
  }
  
  @SyntheticMember
  public EnvObject() {
    super();
  }
}
