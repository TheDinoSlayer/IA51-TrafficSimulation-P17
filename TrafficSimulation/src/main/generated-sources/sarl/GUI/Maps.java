package GUI;

import environment.Graph;
import fr.utbm.info.ia51.framework.util.Resources;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.net.URL;
import java.util.Objects;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Quan
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class Maps extends JPanel {
  private Graph graph;
  
  private ImageIcon map;
  
  private String imageName = "map.png";
  
  private JLabel label;
  
  public Maps() {
    URL _resource = Resources.getResource(Icon.class, this.imageName);
    ImageIcon _imageIcon = new ImageIcon(_resource);
    this.map = _imageIcon;
  }
  
  public void paint(final Graphics g) {
    super.paint(g);
    final Graphics2D g2d = ((Graphics2D) g);
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
    Maps other = (Maps) obj;
    if (!Objects.equals(this.imageName, other.imageName)) {
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
    result = prime * result + Objects.hashCode(this.imageName);
    return result;
  }
  
  @SyntheticMember
  private final static long serialVersionUID = -71361181L;
}
