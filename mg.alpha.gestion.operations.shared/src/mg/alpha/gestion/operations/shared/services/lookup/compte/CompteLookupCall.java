/**
 *
 */
package mg.alpha.gestion.operations.shared.services.lookup.compte;

import org.eclipse.scout.rt.shared.services.lookup.ILookupService;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;

/**
 * @author Dida
 */
public class CompteLookupCall extends LookupCall<Long> {

  private static final long serialVersionUID = 1L;

  @Override
  protected Class<? extends ILookupService<Long>> getConfiguredService() {
    return ICompteLookupService.class;
  }

}
