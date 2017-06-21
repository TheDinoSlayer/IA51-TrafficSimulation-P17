package GUI;

import fr.utbm.info.ia51.framework.gui.AbstractFrameworkGUI;
import fr.utbm.info.ia51.framework.math.Point2f;
import fr.utbm.info.ia51.framework.math.Vector2f;
import fr.utbm.info.ia51.framework.time.TimeManager;
import fr.utbm.info.ia51.framework.util.Resources;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.io.Serializable;
import javax.swing.Icon;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Quan
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class Gui extends AbstractFrameworkGUI {
  private final static float DIRECTION_RADIUS = 30f;
  
  private static Icon CAR_ICON;
  
  private static int ICON_WIDTH;
  
  private static int ICON_HEIGHT;
  
  public Gui(final float worldWidth, final float worldHeight, final TimeManager timeManager) {
    super("Project with traffic simulation", worldWidth, worldHeight, Resources.getResource(Gui.class, "map.png"), timeManager);
  }
  
  @Override
  @Pure
  protected boolean isMouseCursorHidden() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Override
  public void paintAgentBody(final Graphics2D g2d, final Point2f positionOnScreen, final Vector2f orientationOnScreen, final Shape shape, final Serializable type, final String name, final Point2f positionInMas, final Shape frustum) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Override
  public void paintSituatedObject(final Graphics2D g2d, final Point2f positionOnScreen, final Vector2f orientationOnScreen, final Shape shape, final Serializable type, final String name, final Point2f positionInMas) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 4928033604L;
}
