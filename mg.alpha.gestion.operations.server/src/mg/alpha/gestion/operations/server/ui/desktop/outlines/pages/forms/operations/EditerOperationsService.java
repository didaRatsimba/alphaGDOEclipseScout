/**
 *
 */
package mg.alpha.gestion.operations.server.ui.desktop.outlines.pages.forms.operations;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.service.AbstractService;

import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.CreateEditerOperationsPermission;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.EditerOperationsFormData;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.IEditerOperationsService;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.ReadEditerOperationsPermission;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.UpdateEditerOperationsPermission;

/**
 * @author Dida
 */
public class EditerOperationsService extends AbstractService implements IEditerOperationsService {

  @Override
  public EditerOperationsFormData create(EditerOperationsFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateEditerOperationsPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
//    StringBuilder req = new StringBuilder("")//
//    .append("insert into operations ")//
//    .append("(op_id, op_date, op_type, op_compte, op_compte_particulier, op_devise, op_designation, op_montant, op_cours_change) ")//
//    .append("values (null , ")//
//    .append("date_format(:dateOperation, '%Y-%m-%d %h:%i') , ")//
//    .append(":typeAllLitteralGroup , ")//
//    .append(":compteOperation , ")//
//    .append("null , ")//
//    .append(":deviseGroup , ")//
//    .append(":designation , ")//
//    .append(":montantOperation , ")//
//    .append(":coursApplique")//
//    .append(") ")//
//    ;
//    SQL.insert(req.toString(), formData);
    return formData;
  }

  @Override
  public EditerOperationsFormData load(EditerOperationsFormData formData) throws ProcessingException {
    if (!ACCESS.check(new ReadEditerOperationsPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    StringBuilder req = new StringBuilder("")//
    .append("select date_format(o.op_date, '%d/%m/%Y %h:%i'), ")//DATE_FORMAT(:{ope.dateOperationFrom}, '%Y-%m-%d')
    .append("o.op_type, ")//
    .append("o.op_compte, ")//
    .append("o.op_devise, ")//
    .append("o.op_montant, ")//
    .append("o.op_cours_change, ")//
    .append("o.op_designation ").append("from operations o ")//
    .append("where o.op_id = :editerOperationsNr ")//
    .append("into :dateOperation, :typeAllLitteralGroup, :compteOperation, :deviseGroup, :montantOperation, :coursApplique, :designation");
    SQL.selectInto(req.toString(), formData);
    return formData;
  }

  @Override
  public EditerOperationsFormData prepareCreate(EditerOperationsFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateEditerOperationsPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    // TODO [Dida] business logic here.
    return formData;
  }

  @Override
  public EditerOperationsFormData store(EditerOperationsFormData formData) throws ProcessingException {
    if (!ACCESS.check(new UpdateEditerOperationsPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    StringBuilder req = new StringBuilder("")//
    .append("UPDATE operations SET ")//
    .append("op_type = :typeAllLitteralGroup , ")//
    .append("op_compte = :compteOperation , ")//
    .append("op_devise = :deviseGroup , ")//
    .append("op_cours_change = :coursApplique , ")//
    .append("op_montant = :montantOperation ")//
    .append("where op_id = :editerOperationsNr")//
    ;
    SQL.update(req.toString(), formData);
    return formData;
  }
}
