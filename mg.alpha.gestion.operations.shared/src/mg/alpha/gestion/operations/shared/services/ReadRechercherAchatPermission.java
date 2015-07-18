/**
 * 
 */
package mg.alpha.gestion.operations.shared.services;

import java.security.BasicPermission;

/**
 * @author Dida
 */
public class ReadRechercherAchatPermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
   * 
   */
  public ReadRechercherAchatPermission() {
    super("ReadRechercherAchat");
  }
}