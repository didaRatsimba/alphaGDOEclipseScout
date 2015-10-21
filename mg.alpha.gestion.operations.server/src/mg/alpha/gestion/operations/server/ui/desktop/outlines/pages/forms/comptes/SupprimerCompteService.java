/**
 *
 */
package mg.alpha.gestion.operations.server.ui.desktop.outlines.pages.forms.comptes;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.service.AbstractService;

import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.comptes.CreateSupprimerComptePermission;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.comptes.ISupprimerCompteService;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.comptes.ReadSupprimerComptePermission;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.comptes.SupprimerCompteFormData;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.comptes.UpdateSupprimerComptePermission;

/**
 * @author Dida
 */
public class SupprimerCompteService extends AbstractService implements ISupprimerCompteService {

  @Override
  public SupprimerCompteFormData create(SupprimerCompteFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateSupprimerComptePermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    // TODO [Dida] business logic here.
    return formData;
  }

  @Override
  public SupprimerCompteFormData load(SupprimerCompteFormData formData) throws ProcessingException {
    if (!ACCESS.check(new ReadSupprimerComptePermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    // TODO [Dida] business logic here.
    return formData;
  }

  @Override
  public SupprimerCompteFormData prepareCreate(SupprimerCompteFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateSupprimerComptePermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    // TODO [Dida] business logic here.
    return formData;
  }

  @Override
  public SupprimerCompteFormData store(SupprimerCompteFormData formData) throws ProcessingException {
    if (!ACCESS.check(new UpdateSupprimerComptePermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    StringBuilder req = new StringBuilder("")//
    .append("update comptes set cpt_actif = 0 where cpt_id = :supprimerCompteNr")//
    ;
    int delete = SQL.update(req.toString(), formData);
    if (delete > 0) {
      StringBuilder reqDeleteOperation = new StringBuilder("")//
      .append("update operations set op_actif = 0 where op_compte = :supprimerCompteNr")//
      ;
      SQL.update(reqDeleteOperation.toString(), formData);
    }
    return formData;
  }
}
