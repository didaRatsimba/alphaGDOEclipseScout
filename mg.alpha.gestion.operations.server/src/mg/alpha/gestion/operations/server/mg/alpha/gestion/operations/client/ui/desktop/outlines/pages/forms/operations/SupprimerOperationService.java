/**
 *
 */
package mg.alpha.gestion.operations.server.mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.service.AbstractService;

import mg.alpha.gestion.operations.shared.mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.CreateSupprimerOperationPermission;
import mg.alpha.gestion.operations.shared.mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.ISupprimerOperationService;
import mg.alpha.gestion.operations.shared.mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.ReadSupprimerOperationPermission;
import mg.alpha.gestion.operations.shared.mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.SupprimerOperationFormData;
import mg.alpha.gestion.operations.shared.mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.UpdateSupprimerOperationPermission;

/**
 * @author Dida
 */
public class SupprimerOperationService extends AbstractService implements ISupprimerOperationService {

  @Override
  public SupprimerOperationFormData create(SupprimerOperationFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateSupprimerOperationPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    // TODO [Dida] business logic here.
    return formData;
  }

  @Override
  public SupprimerOperationFormData load(SupprimerOperationFormData formData) throws ProcessingException {
    if (!ACCESS.check(new ReadSupprimerOperationPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    // TODO [Dida] business logic here.
    return formData;
  }

  @Override
  public SupprimerOperationFormData prepareCreate(SupprimerOperationFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateSupprimerOperationPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    // TODO [Dida] business logic here.
    return formData;
  }

  @Override
  public SupprimerOperationFormData store(SupprimerOperationFormData formData) throws ProcessingException {
    if (!ACCESS.check(new UpdateSupprimerOperationPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    StringBuilder req = new StringBuilder("")//
    .append("update operations set op_actif = 0 where op_id = :supprimerOperationNr")//
    ;
    SQL.update(req.toString(), formData);
    return formData;
  }
}
