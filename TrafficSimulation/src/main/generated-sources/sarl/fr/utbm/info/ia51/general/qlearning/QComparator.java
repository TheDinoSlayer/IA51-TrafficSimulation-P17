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
package fr.utbm.info.ia51.general.qlearning;

import fr.utbm.info.ia51.general.qlearning.QComparable;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Comparator;

/**
 * Implementation of a comparator of QComparable.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class QComparator implements Comparator<QComparable> {
  /**
   * Default singleton for this comparator.
   */
  public final static QComparator QCOMPARATOR = new QComparator();
  
  @Override
  public int compare(final QComparable o1, final QComparable o2) {
    if ((o1 == o2)) {
      return 0;
    }
    if ((o1 == null)) {
      return Integer.MIN_VALUE;
    }
    if ((o2 == null)) {
      return Integer.MAX_VALUE;
    }
    int _int = o1.toInt();
    int _int_1 = o2.toInt();
    return (_int - _int_1);
  }
  
  @SyntheticMember
  public QComparator() {
    super();
  }
}
