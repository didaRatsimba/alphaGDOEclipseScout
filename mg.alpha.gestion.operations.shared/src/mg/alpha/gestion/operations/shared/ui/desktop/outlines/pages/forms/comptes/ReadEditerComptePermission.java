/**
 * 
 */
package mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.comptes;

import java.security.BasicPermission;

/**
 * @author Dida
 */
public class ReadEditerComptePermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
   * 
   */
  public ReadEditerComptePermission() {
    super("ReadEditerCompte");
  }
}