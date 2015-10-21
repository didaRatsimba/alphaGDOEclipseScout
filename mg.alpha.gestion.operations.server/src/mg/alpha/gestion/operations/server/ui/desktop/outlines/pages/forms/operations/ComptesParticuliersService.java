/**
 *
 */
package mg.alpha.gestion.operations.server.ui.desktop.outlines.pages.forms.operations;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.commons.holders.NVPair;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.service.AbstractService;

import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.ComptesParticuliersFormData;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.CreateComptesParticuliersPermission;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.IComptesParticuliersService;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.ReadComptesParticuliersPermission;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.UpdateComptesParticuliersPermission;

/**
 * @author Dida
 */
public class ComptesParticuliersService extends AbstractService implements IComptesParticuliersService {

  @Override
  public ComptesParticuliersFormData create(ComptesParticuliersFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateComptesParticuliersPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    // TODO [Dida] business logic here.
    return formData;
  }

  @Override
  public ComptesParticuliersFormData load(ComptesParticuliersFormData formData) throws ProcessingException {
    if (!ACCESS.check(new ReadComptesParticuliersPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    StringBuilder req = new StringBuilder("")//
    .append("select ")//
    .append("spcp.spcp_real_id, ")//
    .append("c.cpt_nom, ")//
    .append("spcp.spcp_somme_envoyee ")//
    .append("from somme_par_compte_particulier spcp ")//
    .append("inner join comptes c on (spcp.spcp_compte_id = c.cpt_id and c.cpt_actif = 1) ")//
    .append("where spcp.spcp_timestamp = :{form.idFromTimestamp} and spcp.spcp_actif = 1 ")//
    .append("into :{formresp.sommeParCompteId}, :{formresp.compteParticulier}, :{formresp.sommeEnvoyee} ")//
    ;
    SQL.selectInto(req.toString(), new NVPair("form", formData), new NVPair("formresp", formData.getComptesParticuliers()));
    return formData;
  }

  @Override
  public ComptesParticuliersFormData prepareCreate(ComptesParticuliersFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateComptesParticuliersPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    // TODO [Dida] business logic here.
    return formData;
  }

  @Override
  public ComptesParticuliersFormData store(ComptesParticuliersFormData formData) throws ProcessingException {
    if (!ACCESS.check(new UpdateComptesParticuliersPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    // TODO [Dida] business logic here.
    return formData;
  }
}
