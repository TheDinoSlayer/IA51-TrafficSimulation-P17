/**
 * $Id$
 * 
 * Copyright (c) 2011-17 Stephane GALLAND <stephane.galland@utbm.fr>.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 * This program is free software; you can redistribute it and/or modify
 */
package fr.utbm.info.ia51.framework.gui;

import fr.utbm.info.ia51.framework.environment.DynamicType;
import fr.utbm.info.ia51.framework.util.Resources;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Dialog box for selecting the type of a behavior.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class BehaviorTypeSelector extends JDialog {
  private DynamicType behaviorType = null;
  
  /**
   * Open the dialog box for selecting a type of behavior.
   * 
   * @return the selected type or <code>null</code> if the dialog box was manually closed.
   */
  public static DynamicType open() {
    BehaviorTypeSelector selector = new BehaviorTypeSelector();
    selector.setVisible(true);
    DynamicType type = selector.getSelectedBehaviorType();
    selector.dispose();
    return type;
  }
  
  protected BehaviorTypeSelector() {
    this.setTitle("Behavior Type Selection");
    this.setModal(true);
    GridLayout _gridLayout = new GridLayout(1, 1);
    this.setLayout(_gridLayout);
    URL imageUrl = Resources.getResource(BehaviorTypeSelector.class, "map.jpeg");
    ImageIcon icon = new ImageIcon(imageUrl);
    JButton kinematicButton = new JButton(icon);
    this.add(kinematicButton);
    this.pack();
    Dimension d = this.getSize();
    this.setLocation(((-d.width) / 2), ((-d.height) / 2));
    this.setLocationRelativeTo(null);
  }
  
  /**
   * Replies the selected behavior type.
   * 
   * @return the selected behavior type. It could be <code>null</code> if the user
   * has close the dialog box without selecting a type.
   */
  @Pure
  public synchronized DynamicType getSelectedBehaviorType() {
    return this.behaviorType;
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
  private final static long serialVersionUID = 2310144473L;
}
