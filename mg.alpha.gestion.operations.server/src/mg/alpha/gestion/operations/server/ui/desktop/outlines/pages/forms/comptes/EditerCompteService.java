/**
 *
 */
package mg.alpha.gestion.operations.server.ui.desktop.outlines.pages.forms.comptes;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.commons.holders.NVPair;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.service.AbstractService;

import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.comptes.CreateEditerComptePermission;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.comptes.EditerCompteFormData;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.comptes.IEditerCompteService;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.comptes.ReadEditerComptePermission;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.comptes.UpdateEditerComptePermission;

/**
 * @author Dida
 */
public class EditerCompteService extends AbstractService implements IEditerCompteService {

  @Override
  public EditerCompteFormData create(EditerCompteFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateEditerComptePermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    StringBuilder req = new StringBuilder("")//
    .append("insert into comptes (cpt_id, cpt_nom, cpt_particulier) values (null, :{page.libelleCompte}, :{page.compteParticulier}) ")//
    ;
    if (SQL.insert(req.toString(), new NVPair("page", formData)) != 0) {
      StringBuilder reqSelect = new StringBuilder("")//
      .append("select c.cpt_id from comptes c where upper(c.cpt_nom) = upper(:{page.libelleCompte}) into :{page.editerCompteNr}")//
      ;
      SQL.selectInto(reqSelect.toString(), new NVPair("page", formData));
    }
    return formData;
  }

  @Override
  public EditerCompteFormData load(EditerCompteFormData formData) throws ProcessingException {
    if (!ACCESS.check(new ReadEditerComptePermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    StringBuilder req = new StringBuilder("")//
    .append("select c.cpt_nom, c.cpt_particulier from comptes c where c.cpt_id = :{page.editerCompteNr} into :{page.libelleCompte}, :{page.compteParticulier}")//
    ;
    SQL.selectInto(req.toString(), new NVPair("page", formData));
    return formData;
  }

  @Override
  public EditerCompteFormData prepareCreate(EditerCompteFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateEditerComptePermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    // TODO [Dida] business logic here.
    return formData;
  }

  @Override
  public EditerCompteFormData store(EditerCompteFormData formData) throws ProcessingException {
    if (!ACCESS.check(new UpdateEditerComptePermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    StringBuilder req = new StringBuilder("")//
    .append("update comptes set cpt_nom = :libelleCompte, cpt_particulier = :compteParticulier where cpt_id = :editerCompteNr")//
    ;
    SQL.update(req.toString(), formData);
    return formData;
  }
}
