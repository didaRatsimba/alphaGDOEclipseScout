/**
 * 
 */
package mg.alpha.gestion.operations.shared.services;

import java.security.BasicPermission;

/**
 * @author Dida
 */
public class CreateRechercherAchatsPermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
   * 
   */
  public CreateRechercherAchatsPermission() {
    super("CreateRechercherAchats");
  }
}