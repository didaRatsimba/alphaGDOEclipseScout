/**
 * 
 */
package mg.alpha.gestion.operations.shared.services;

import java.security.BasicPermission;

/**
 * @author Dida
 */
public class CreateRechercherAchatPermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
   * 
   */
  public CreateRechercherAchatPermission() {
    super("CreateRechercherAchat");
  }
}