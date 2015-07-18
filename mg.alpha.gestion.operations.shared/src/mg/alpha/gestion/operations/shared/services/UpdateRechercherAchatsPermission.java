/**
 * 
 */
package mg.alpha.gestion.operations.shared.services;

import java.security.BasicPermission;

/**
 * @author Dida
 */
public class UpdateRechercherAchatsPermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
   * 
   */
  public UpdateRechercherAchatsPermission() {
    super("UpdateRechercherAchats");
  }
}