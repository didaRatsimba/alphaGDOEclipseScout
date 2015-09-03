/**
 *
 */
package mg.alpha.gestion.operations.server.ui.desktop.outlines.pages.forms.operations;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.commons.holders.NVPair;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.service.AbstractService;

import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.CreatePartagerVersComptesParticuliersPermission;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.IPartagerVersComptesParticuliersService;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.PartagerVersComptesParticuliersFormData;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.ReadPartagerVersComptesParticuliersPermission;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.UpdatePartagerVersComptesParticuliersPermission;

/**
 * @author Dida
 */
public class PartagerVersComptesParticuliersService extends AbstractService implements IPartagerVersComptesParticuliersService {

  @Override
  public PartagerVersComptesParticuliersFormData create(PartagerVersComptesParticuliersFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreatePartagerVersComptesParticuliersPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    StringBuilder reqUpdateVic = new StringBuilder()//
    .append("update vente_infos_complementaires set vic_somme_a_partager = :{page.vousPouvezPartager} - :{page.sommeAEnvoyer} ")//
    .append("where vic_timestamp = :{page.creationTimestamp}")//
    ;
    SQL.update(reqUpdateVic.toString(), new NVPair("page", formData));
    StringBuilder reqInsertSpcp = new StringBuilder().append("insert into somme_par_compte_particulier ")//
    .append("(spcp_real_id, spcp_timestamp, spcp_compte_id, spcp_somme_envoyee) ")//
    .append("values (null, :{page.creationTimestamp}, :{page.compteParticulier}, :{page.sommeAEnvoyer})")//
    ;

    SQL.insert(reqInsertSpcp.toString(), new NVPair("page", formData));
    Long timestamp = formData.getCreationTimestamp();
    StringBuilder reqSelectIntoCompte = new StringBuilder()//
    .append("select op.op_compte_particulier from operations op where op.op_timestamp = ").append(timestamp);
    ResultSet result = null;
    try {
      result = SQL.getConnection().prepareStatement(reqSelectIntoCompte.toString()).executeQuery();
    }
    catch (SQLException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
//    select(reqSelectIntoCompte.toString(), new NVPair("page", formData));
    int compteParticulier = 0;
    String compteValue = null;
    if (result != null) {
      try {
        while (result.next()) {
          String resulttemp = result.getString("op_compte_particulier");
          if (resulttemp != null) {
            System.out.println(resulttemp);
            compteParticulier = Integer.valueOf(resulttemp).intValue() + 1;
            compteValue = String.valueOf(compteParticulier);
          }
          else {
            compteValue = "1";
          }
        }
      }

      catch (NumberFormatException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    else {
      compteValue = "0";
    }

    StringBuilder reqUpdateOp = new StringBuilder().append("update operations set op_compte_particulier = ")//
    .append(compteValue)//
    .append(" where op_timestamp = :{page.creationTimestamp}")//
    ;
    SQL.update(reqUpdateOp.toString(), new NVPair("page", formData));

    return formData;
  }

  @Override
  public PartagerVersComptesParticuliersFormData load(PartagerVersComptesParticuliersFormData formData) throws ProcessingException {
    if (!ACCESS.check(new ReadPartagerVersComptesParticuliersPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    // TODO [Dida] business logic here.
    return formData;
  }

  @Override
  public PartagerVersComptesParticuliersFormData prepareCreate(PartagerVersComptesParticuliersFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreatePartagerVersComptesParticuliersPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    // TODO [Dida] business logic here.
    return formData;
  }

  @Override
  public PartagerVersComptesParticuliersFormData store(PartagerVersComptesParticuliersFormData formData) throws ProcessingException {
    if (!ACCESS.check(new UpdatePartagerVersComptesParticuliersPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    // TODO [Dida] business logic here.
    return formData;
  }
}
