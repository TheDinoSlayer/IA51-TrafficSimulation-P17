package environment;

import fr.utbm.info.ia51.framework.environment.AbstractEnvironment;
import fr.utbm.info.ia51.framework.environment.AgentBody;
import fr.utbm.info.ia51.framework.environment.Influence;
import fr.utbm.info.ia51.framework.environment.MotionInfluence;
import fr.utbm.info.ia51.framework.environment.Percept;
import fr.utbm.info.ia51.framework.environment.SituatedObject;
import fr.utbm.info.ia51.framework.gui.WorldModelStateProvider;
import fr.utbm.info.ia51.framework.math.Circle2f;
import fr.utbm.info.ia51.framework.math.MathUtil;
import fr.utbm.info.ia51.framework.math.Point2f;
import fr.utbm.info.ia51.framework.math.Rectangle2f;
import fr.utbm.info.ia51.framework.time.StepTimeManager;
import fr.utbm.info.ia51.framework.time.TimeManager;
import fr.utbm.info.ia51.general.frustum.CircleFrustum;
import fr.utbm.info.ia51.labworks.motionbeh.environment.Tree;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Quan
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class WorldModel extends AbstractEnvironment implements WorldModelStateProvider {
  private final static float CAR_SIZE = 20f;
  
  private Tree<AgentBody> spatialTree;
  
  public WorldModel(final float width, final float height) {
    super(width, height, new StepTimeManager(500));
    Rectangle2f _rectangle2f = new Rectangle2f(0, 0, width, height);
    Tree<AgentBody> _tree = new Tree<AgentBody>(_rectangle2f);
    this.spatialTree = _tree;
  }
  
  @Override
  protected void onAgentBodyCreated(final AgentBody body) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Override
  protected void onAgentBodyDestroyed(final AgentBody body) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Override
  protected List<Influence> computeEndogenousBehaviorInfluences() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Override
  protected List<Percept> computePerceptionsFor(final AgentBody agent) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Override
  protected void applyInfluences(final Collection<MotionInfluence> motionInfluences, final Collection<Influence> otherInfluences, final TimeManager timeManager) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Override
  @Pure
  public Iterable<? extends SituatedObject> getAllObjects() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Override
  public void setMouseTarget(final Point2f target) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public String createCar() {
    String _xblockexpression = null;
    {
      UUID id = UUID.randomUUID();
      CircleFrustum frustum = new CircleFrustum(id, (10 * WorldModel.CAR_SIZE));
      Circle2f _circle2f = new Circle2f(0f, 0f, WorldModel.CAR_SIZE);
      AgentBody body = new AgentBody(id, _circle2f, 
        5f, 
        0.5f, 
        (MathUtil.PI / 4f), 
        (MathUtil.PI / 10f), frustum);
      int _agentBodyNumber = this.getAgentBodyNumber();
      int _plus = (_agentBodyNumber + 1);
      _xblockexpression = body.setName(MessageFormat.format("Car #{0}", Integer.valueOf(_plus)));
    }
    return _xblockexpression;
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
}
