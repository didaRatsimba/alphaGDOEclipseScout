/**
 *
 */
package mg.alpha.gestion.operations.server.services;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.service.AbstractService;

import mg.alpha.gestion.operations.shared.services.CreateRechercherAchatsPermission;
import mg.alpha.gestion.operations.shared.services.IRechercherAchatsService;
import mg.alpha.gestion.operations.shared.services.ReadRechercherAchatsPermission;
import mg.alpha.gestion.operations.shared.services.UpdateRechercherAchatsPermission;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.CreerOperationFormData;

/**
 * @author Dida
 */
public class RechercherAchatsService extends AbstractService implements IRechercherAchatsService {

  @Override
  public CreerOperationFormData create(CreerOperationFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateRechercherAchatsPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    // TODO [Dida] business logic here.
    return formData;
  }

  @Override
  public CreerOperationFormData load(CreerOperationFormData formData) throws ProcessingException {
    if (!ACCESS.check(new ReadRechercherAchatsPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    return formData;
  }

  @Override
  public CreerOperationFormData prepareCreate(CreerOperationFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateRechercherAchatsPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    // TODO [Dida] business logic here.
    return formData;
  }

  @Override
  public CreerOperationFormData store(CreerOperationFormData formData) throws ProcessingException {
    if (!ACCESS.check(new UpdateRechercherAchatsPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    // TODO [Dida] business logic here.
    return formData;
  }

//  @Override
//  public ListeVendreRowData[] rechercherAchats() throws ProcessingException, SQLException {
//    List<ListeVendreRowData> result = new ArrayList<>();
//    ListeVendreRowData[] tempResult = new ListeVendreRowData[]{};
//    StringBuilder req = new StringBuilder("")//
//    .append("select ")//
//    .append("o.op_id ")//
//    .append("from operations o ")//
//    .append("where o.op_cours_change >= 1500")//
//    ;
//    ResultSet r = SQL.getConnection().prepareStatement(req.toString()).executeQuery();
////    int i = 0;
//    while (r.next()) {
//      System.out.println(r.toString());
//      Long id = r.getLong("op_id");
//      System.out.println(id);
//    }
//    return result.toArray(tempResult);
//  }
}
