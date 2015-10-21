/**
 *
 */
package mg.alpha.gestion.operations.server.ui.desktop.outlines.pages.forms.operations;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Map;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.commons.holders.NVPair;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.service.AbstractService;

import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.CreateSelectionnerDonneesPermission;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.ISelectionnerDonneesService;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.ReadSelectionnerDonneesPermission;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.SelectionnerDonneesFormData;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.UpdateSelectionnerDonneesPermission;

/**
 * @author Dida
 */
public class SelectionnerDonneesService extends AbstractService implements ISelectionnerDonneesService {

  @Override
  public SelectionnerDonneesFormData create(SelectionnerDonneesFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateSelectionnerDonneesPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    Map<Long, BigDecimal> mapDebit = formData.getDebitParOperationIdMap();
    Map<Long, BigDecimal> sommePartagee = formData.getSommeAPartagerParCompteIdMap();
    Map<Long, BigDecimal> mapBenef = formData.getBeneficeParAchatId();
    long sommeDejaPartagee = 0L;
    try {
      for (Long opId : mapDebit.keySet()) {

        if (opId != null) {
          StringBuilder reqDebit = new StringBuilder("")//
          .append("insert into debit_par_operation ")//
          .append("(dpo_real_id, dpo_timestamp, dpo_op_id, dpo_debit, dpo_benefice) ")//
          .append("values (null , ")//
          .append(formData.getSelectionnerDonneesNr()).append(" , ").append(opId).append(" , ")//
          .append(mapDebit.get(opId)).append(" , ").append(mapBenef.get(opId))//
          .append(")")//
          ;

          SQL.getConnection().prepareStatement(reqDebit.toString()).executeUpdate();

        }
      }
      for (Long compteId : sommePartagee.keySet()) {
        if (compteId != null) {
          BigDecimal eachsomme = sommePartagee.get(compteId);
          sommeDejaPartagee += eachsomme.longValue();
          StringBuilder reqPartage = new StringBuilder("")//
          .append("insert into somme_par_compte_particulier ")//
          .append("(spcp_real_id, spcp_timestamp, spcp_compte_id, spcp_somme_envoyee) ")//
          .append("values (null , ")//
          .append(formData.getSelectionnerDonneesNr()).append(" , ").append(compteId).append(" , ").append(eachsomme)//
          .append(")")//
          ;

          SQL.getConnection().prepareStatement(reqPartage.toString()).executeUpdate();
        }
      }

      StringBuilder reqVenteInfo = new StringBuilder("")//
      .append("insert into vente_infos_complementaires ")//
      .append("(vic_id, vic_timestamp, vic_somme_a_partager) ")//
      .append("values (null , ")//
      .append(formData.getSelectionnerDonneesNr()).append(" , ").append(new BigDecimal(formData.getSommeAVendre().longValue() - sommeDejaPartagee))//
      .append(")")//
      ;

      SQL.getConnection().prepareStatement(reqVenteInfo.toString()).executeUpdate();

    }
    catch (SQLException e) {
      throw new ProcessingException(" ".concat(e.getMessage()));
    }
    return formData;
  }

  @Override
  public SelectionnerDonneesFormData load(SelectionnerDonneesFormData formData) throws ProcessingException {
    if (!ACCESS.check(new ReadSelectionnerDonneesPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    StringBuilder req = new StringBuilder("")//
    .append("select ")//
    .append("o.op_id, ")//
    .append("c.cpt_nom, ")//
    .append("o.op_cours_change, ")//
    .append("o.op_montant ")//
    .append("from operations o ")//
    .append("inner join comptes c on (o.op_compte = c.cpt_id and c.cpt_actif = 1) ")//
    .append("where UPPER(o.op_type) LIKE UPPER('a') and o.op_actif = 1 and o.op_cours_change <= :{page.coursChange} ")//
    .append("into :{donnees.achatId}, :{donnees.comptes}, :{donnees.valeurAchat}, :{donnees.montantDisponible} ")//
    ;
    SQL.selectInto(req.toString(), new NVPair("page", formData), new NVPair("donnees", formData.getDonneesFiltrees()));
    StringBuilder reqPartage = new StringBuilder("")//
    .append("select ")//
    .append("c.cpt_id, ")//
    .append("c.cpt_nom ")//
    .append("from comptes c ")//
    .append("where c.cpt_particulier = true and c.cpt_actif = 1 ")//
    .append("into :{cptpar.compteParticulierId}, :{cptpar.compteParticulier} ")//
    ;
    SQL.selectInto(reqPartage.toString(), new NVPair("cptpar", formData.getComptesParticuliers()));
    return formData;
  }

  @Override
  public SelectionnerDonneesFormData prepareCreate(SelectionnerDonneesFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateSelectionnerDonneesPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    // TODO [Dida] business logic here.
    return load(formData);
  }

  @Override
  public SelectionnerDonneesFormData store(SelectionnerDonneesFormData formData) throws ProcessingException {
    if (!ACCESS.check(new UpdateSelectionnerDonneesPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    // TODO [Dida] business logic here.
    return formData;
  }
}
