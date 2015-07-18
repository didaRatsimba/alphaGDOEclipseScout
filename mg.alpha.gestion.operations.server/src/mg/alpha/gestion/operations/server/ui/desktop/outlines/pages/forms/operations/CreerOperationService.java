/**
 *
 */
package mg.alpha.gestion.operations.server.ui.desktop.outlines.pages.forms.operations;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.commons.holders.NVPair;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.service.AbstractService;

import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.CreateCreerOperationPermission;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.CreerOperationFormData;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.ICreerOperationService;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.ReadCreerOperationPermission;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.UpdateCreerOperationPermission;

/**
 * @author Dida
 */
public class CreerOperationService extends AbstractService implements ICreerOperationService {

  @Override
  public CreerOperationFormData create(CreerOperationFormData formData) throws ProcessingException, SQLException {
    if (!ACCESS.check(new CreateCreerOperationPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    if (formData.getCompteId() == null) {
      StringBuilder reqSelect = new StringBuilder("")//
      .append("select c.cpt_id from comptes c where upper(c.cpt_nom) = upper(:{page.compteLabel}) into :{page.compteId}")//
      ;
      SQL.selectInto(reqSelect.toString(), new NVPair("page", formData));
    }
    StringBuilder req = new StringBuilder("");//
    if (formData.getTypeGroup() != null) {
      if (formData.getTypeGroup().getValue().equalsIgnoreCase("A")) {
        req.append("INSERT INTO operations ")//
        .append("(op_id , op_date, op_type, op_compte, ")//
        .append("op_devise, op_montant, op_cours_change, op_timestamp) ")//
        .append("VALUES (null, ")//
        .append("DATE_FORMAT(:dateOperation, '%Y-%m-%d %H:%i'), ")//
        .append("UPPER('a'), ")//
        .append(":compteId, ")//
        .append(":deviseGroup, ")//
        .append(":montantOperation, ")//
        .append(":coursColumnHeader ")//
        .append(":idFromTimestamp")//
        .append(")")//
        ;
        SQL.insert(req.toString(), formData);
      }
      else {
        Long idFromTimeStamp = formData.getIdFromTimestamp();
        StringBuilder reqSelectDebits = new StringBuilder("")//
        .append("select dpo.dpo_op_id, dpo.dpo_debit from debit_par_operation dpo where dpo_timestamp = ").append(idFromTimeStamp)//
        ;
        ResultSet resultSelectDebits = SQL.getConnection().prepareStatement(reqSelectDebits.toString()).executeQuery();
        if (resultSelectDebits != null) {
          while (resultSelectDebits.next()) {
            Long idAchat = resultSelectDebits.getLong("dpo_op_id");
            Long valeurDebit = resultSelectDebits.getLong("dpo_debit");
            StringBuilder reqSelectAchat = new StringBuilder("")//
            .append("select o.op_montant from operations o where o.op_id = ").append(idAchat);
            ResultSet resultSelectAchat = SQL.getConnection().prepareStatement(reqSelectAchat.toString()).executeQuery();
            Long montantRestant = null;
            if (resultSelectAchat != null) {
              while (resultSelectAchat.next()) {
                Long montantActuel = resultSelectAchat.getLong("op_montant");
                montantRestant = montantActuel.longValue() - valeurDebit.longValue();
                StringBuilder reqUpdateAchats = new StringBuilder("")//
                .append("UPDATE operations SET ")//
                .append("op_montant = ").append(montantRestant).append(" ")//
                .append("where op_id = ").append(idAchat)//
                ;
                SQL.getConnection().prepareStatement(reqUpdateAchats.toString()).executeUpdate();
              }
            }

          }

          req.append("INSERT INTO operations ")//
          .append("(op_id , op_date, op_type, op_compte, op_compte_particulier, ")//
          .append("op_devise, op_montant, op_cours_change, op_timestamp) ")//
          .append("VALUES (null, ")//
          .append("DATE_FORMAT(:dateOperation, '%Y-%m-%d %H:%i'), ")//
          .append("UPPER('v'), ")//
          .append(":compteId, ")//
          .append(":comptesPartages, ")//
          .append(":deviseGroup, ")//
          .append(":montantOperation, ")//
          .append(":coursColumnHeader, ")//
          .append(":idFromTimestamp")//
          .append(")")//
          ;
          SQL.insert(req.toString(), formData);
        }

      }

    }
    return formData;
  }

  @Override
  public CreerOperationFormData load(CreerOperationFormData formData) throws ProcessingException {
    if (!ACCESS.check(new ReadCreerOperationPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    // TODO [Dida] business logic here.
    return formData;
  }

  @Override
  public CreerOperationFormData prepareCreate(CreerOperationFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateCreerOperationPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    // TODO [Dida] business logic here.
    return formData;
  }

  @Override
  public CreerOperationFormData store(CreerOperationFormData formData) throws ProcessingException {
    if (!ACCESS.check(new UpdateCreerOperationPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    // TODO [Dida] business logic here.
    return formData;
  }

  @Override
  public void createDebitParOperation(Map<Long, BigDecimal> toBeStoredData, Long idFromtimestamp) throws ProcessingException, SQLException {
    for (Long opId : toBeStoredData.keySet()) {
      if (opId != null) {
        StringBuilder req = new StringBuilder("")//
        .append("insert into debit_par_operation ")//
        .append("(dpo_real_id, dpo_id, dpo_op_id, dpo_debit) ")//
        .append("values (null , ")//
        .append(idFromtimestamp).append(" , ").append(opId).append(" , ").append(toBeStoredData.get(opId))//
        .append(")")//
        ;
        SQL.getConnection().prepareStatement(req.toString()).executeUpdate();
      }
    }

  }
}
