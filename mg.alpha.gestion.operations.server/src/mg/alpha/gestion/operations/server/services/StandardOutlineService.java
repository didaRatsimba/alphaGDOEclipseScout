/**
 *
 */
package mg.alpha.gestion.operations.server.services;

import org.eclipse.scout.commons.NumberUtility;
import org.eclipse.scout.commons.StringUtility;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.holders.NVPair;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;
import org.eclipse.scout.service.AbstractService;

import mg.alpha.gestion.operations.shared.services.IStandardOutlineService;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.ComptesParticuliersSearchFormData;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.ComptesParticuliersTablePageData;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.bilan.BilanBeneficesTablePageData;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.comptes.ComptesTablePageData;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.comptes.FiltrerComptesSearchFormData;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.bilan.BilanBeneficesSearchFormData;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.OperationsSearchFormData;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.operations.OperationsTablePageData;

/**
 * @author Dida
 */
public class StandardOutlineService extends AbstractService implements IStandardOutlineService {

  /* (non-Javadoc)
   * @see mg.alpha.gestion.operations.shared.services.IStandardOutlineService#getOperationTableData(mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.OperationsSearchFormData)
   */
  @Override
  public OperationsTablePageData getOperationTableData(OperationsSearchFormData operationsSearchFormData) throws ProcessingException {
    OperationsTablePageData pageData = new OperationsTablePageData();
    StringBuilder req = new StringBuilder();
    req.append("select ")//
    .append("o.op_id, ")//
    .append("o.op_date, ")//
    .append("o.op_type, ")//
    .append("c.cpt_nom, ")//
    .append("o.op_compte_particulier, ").append("o.op_devise, ")//
    .append("o.op_montant, ")//
    .append("o.op_cours_change, ")//
    .append("o.op_timestamp ")//
    .append("from operations o ")//
    .append("inner join comptes c on o.op_compte = c.cpt_id ")//
    .append("where 1 = 1 ");// nécessaire car on ne sait pas si des critères existent
//    .append("and o.op_compte = c.cpt_id ");
    if (operationsSearchFormData.getDateOperationFrom() != null && operationsSearchFormData.getDateOperationTo() != null) {
      if (operationsSearchFormData.getDateOperationFrom().getValue() != null) {
        req.append("and o.op_date >= DATE_FORMAT(:{ope.dateOperationFrom}, '%Y-%m-%d') ");
      }
      if (operationsSearchFormData.getDateOperationTo().getValue() != null) {
        req.append("and o.op_date <= DATE_FORMAT(:{ope.dateOperationTo} , '%Y-%m-%d') ");
      }
    }
    if (operationsSearchFormData.getTypeOperation() != null && !StringUtility.isNullOrEmpty(operationsSearchFormData.getTypeOperation().getValue())) {
      req.append("and UPPER(o.op_type) LIKE UPPER(:{ope.typeOperation}) ");
    }
    if (operationsSearchFormData.getCompteOperation() != null && !StringUtility.isNullOrEmpty(operationsSearchFormData.getCompteOperation().getValue())) {
      String compteLike = operationsSearchFormData.getCompteOperation().getValue();
      req.append("and c.cpt_nom LIKE '%").append(compteLike).append("%' ");
    }
    if (operationsSearchFormData.getDevise() != null && !StringUtility.isNullOrEmpty(operationsSearchFormData.getDevise().getValue())) {
      req.append("and UPPER(o.op_devise) LIKE UPPER(:{ope.devise}) ");
    }
    if (operationsSearchFormData.getMontantOperation() != null && operationsSearchFormData.getMontantOperation().getValue() != null) {
      req.append("and o.op_montant >= ").append(NumberUtility.toDouble(operationsSearchFormData.getMontantOperation().getValue()).doubleValue()).append(" ");
    }
    if (operationsSearchFormData.getCoursApplique() != null && operationsSearchFormData.getCoursApplique().getValue() != null) {
      req.append("and o.op_cours_change >= ").append(NumberUtility.toDouble(operationsSearchFormData.getCoursApplique().getValue()).doubleValue()).append(" ");
    }
    req.append("order by o.op_date desc ")//
    .append("into :{page.opId}, :{page.dateOperation}, :{page.typeOperation}, ")//
    .append(":{page.compteOperation}, :{page.compteParticulier}, :{page.devise}, :{page.montantOperation}, :{page.coursApplique}, :{page.timestamp}");
    SQL.selectInto(req.toString(), new NVPair("ope", operationsSearchFormData), new NVPair("page", pageData));
    return pageData;
  }

  @Override
  public ComptesTablePageData getCompteTableData(FiltrerComptesSearchFormData searchFormData) throws ProcessingException {
    ComptesTablePageData pageData = new ComptesTablePageData();
    StringBuilder req = new StringBuilder();
    req.append("select c.cpt_id, c.cpt_nom, c.cpt_particulier from comptes c ")//
    .append("where 1=1 ");//
    if (searchFormData.getLibelleCompte() != null && !StringUtility.isNullOrEmpty(searchFormData.getLibelleCompte().getValue())) {
      req.append("and UPPER(c.cpt_nom) LIKE UPPER(:{search.libelleCompte}) ");//
    }
    if (searchFormData.getFiltrerParticulier() != null && searchFormData.getFiltrerParticulier().booleanValue()) {
      if (searchFormData.getCompteParticulier() != null) {
        if (searchFormData.getCompteParticulier().getValue().booleanValue()) {
          req.append("and c.cpt_particulier = true ");//
        }
        else {
          req.append("and c.cpt_particulier = false ");//
        }
      }

    }
    req.append("order by c.cpt_id asc ")//
    .append("into :{page.idCompte}, :{page.libelleCompte}, :{page.compteParticulier}")//
    ;
    SQL.selectInto(req.toString(), new NVPair("search", searchFormData), new NVPair("page", pageData));
    return pageData;
  }

  /* (non-Javadoc)
   * @see mg.alpha.gestion.operations.shared.services.IStandardOutlineService#getBilanBeneficeTablePageData(mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.bilan.BilanBeneficesSearchFormData)
   */
  @Override
  public BilanBeneficesTablePageData getBilanBeneficeTablePageData(BilanBeneficesSearchFormData bilanSearchFormData) throws ProcessingException {
    BilanBeneficesTablePageData pageData = new BilanBeneficesTablePageData();
    StringBuilder req = new StringBuilder();
    req.append("select d.dpo_real_id, o.op_date, d.dpo_debit, c.cpt_nom, d.dpo_benefice from debit_par_operation d ")//
    .append("inner join operations o on d.dpo_timestamp = o.op_timestamp ").append("inner join comptes c on o.op_compte = c.cpt_id ")//
    .append("where d.dpo_benefice > 0 ");//
    if (bilanSearchFormData.getEnvoyeA() != null && !StringUtility.isNullOrEmpty(bilanSearchFormData.getEnvoyeA().getValue())) {
      req.append("and UPPER(c.cpt_nom) LIKE UPPER(:{search.envoyeA}) ");//
    }
    if (bilanSearchFormData.getBenefice() != null && bilanSearchFormData.getBenefice().getValue() != null) {
      req.append("and d.dpo_benefice >= ").append(NumberUtility.toDouble(bilanSearchFormData.getBenefice().getValue()).doubleValue()).append(" ");
    }
    if (bilanSearchFormData.getDateEnvoiFrom() != null && bilanSearchFormData.getDateEnvoiTo() != null) {
      if (bilanSearchFormData.getDateEnvoiFrom().getValue() != null) {
        req.append("and o.op_date >= DATE_FORMAT(:{search.dateEnvoiFrom}, '%Y-%m-%d') ");
      }
      if (bilanSearchFormData.getDateEnvoiTo().getValue() != null) {
        req.append("and o.op_date <= DATE_FORMAT(:{search.dateEnvoiTo} , '%Y-%m-%d') ");
      }
    }
    if (bilanSearchFormData.getMontantDebite() != null && bilanSearchFormData.getMontantDebite().getValue() != null) {
      req.append("and d.dpo_debit >= ").append(NumberUtility.toDouble(bilanSearchFormData.getMontantDebite().getValue()).doubleValue()).append(" ");
    }

    req.append("order by d.dpo_real_id asc ")//
    .append("into :{page.debitParOperation}, :{page.dateEnvoi}, :{page.montantDebite}, :{page.envoyeA}, :{page.benefice}")//
    ;
    SQL.selectInto(req.toString(), new NVPair("search", bilanSearchFormData), new NVPair("page", pageData));
    return pageData;
  }

  /* (non-Javadoc)
   * @see mg.alpha.gestion.operations.shared.services.IStandardOutlineService#getComptesParticuliersHistorique(mg.alpha.gestion.operations.shared.ui.desktop.outlines.ComptesParticuliersSearchFormData)
   */
  @Override
  public ComptesParticuliersTablePageData getComptesParticuliersHistorique(ComptesParticuliersSearchFormData compteParticuliersSearchFormData) throws ProcessingException {
    ComptesParticuliersTablePageData pageData = new ComptesParticuliersTablePageData();
    StringBuilder req = new StringBuilder();
    req.append("select spcp.spcp_real_id, ")//
    .append("spcp.spcp_timestamp, ")//
    .append("spcp.spcp_somme_envoyee, ")//
    .append("cpar.cpt_nom, ")//
    .append("cenv.cpt_nom, ")//
    .append("o.op_cours_change, ")//
    .append("o.op_date ")//
    .append("from somme_par_compte_particulier spcp ")//
    .append("inner join comptes cpar on spcp.spcp_compte_id = cpar.cpt_id ")//
    .append("inner join operations o on spcp.spcp_timestamp = o.op_timestamp ")//
    .append("inner join comptes cenv on o.op_compte = cenv.cpt_id ")//
    .append("where UPPER(o.op_type) LIKE UPPER('v') ");//

    if (compteParticuliersSearchFormData.getCompteDenvoi() != null && !StringUtility.isNullOrEmpty(compteParticuliersSearchFormData.getCompteDenvoi().getValue())) {
      req.append("and UPPER(cenv.cpt_nom) LIKE UPPER(:{search.compteDenvoi}) ");//
    }
    if (compteParticuliersSearchFormData.getCompteParticulier() != null && !StringUtility.isNullOrEmpty(compteParticuliersSearchFormData.getCompteParticulier().getValue())) {
      req.append("and UPPER(cpar.cpt_nom) LIKE UPPER(:{search.compteParticulier}) ");//
    }
    if (compteParticuliersSearchFormData.getMontantEnvoye() != null && compteParticuliersSearchFormData.getMontantEnvoye().getValue() != null) {
      req.append("and spcp.spcp_somme_envoyee >= ").append(NumberUtility.toDouble(compteParticuliersSearchFormData.getMontantEnvoye().getValue()).doubleValue()).append(" ");
    }
    if (compteParticuliersSearchFormData.getPrixDeVenteUnite() != null && compteParticuliersSearchFormData.getPrixDeVenteUnite().getValue() != null) {
      req.append("and o.op_cours_change >= ").append(NumberUtility.toDouble(compteParticuliersSearchFormData.getPrixDeVenteUnite().getValue()).doubleValue()).append(" ");
    }
    if (compteParticuliersSearchFormData.getDateEnvoiFrom() != null && compteParticuliersSearchFormData.getDateEnvoiTo() != null) {
      if (compteParticuliersSearchFormData.getDateEnvoiFrom().getValue() != null) {
        req.append("and o.op_date >= DATE_FORMAT(:{search.dateEnvoiFrom}, '%Y-%m-%d') ");
      }
      if (compteParticuliersSearchFormData.getDateEnvoiTo().getValue() != null) {
        req.append("and o.op_date <= DATE_FORMAT(:{search.dateEnvoiTo} , '%Y-%m-%d') ");
      }
    }

    req.append("order by spcp.spcp_real_id asc ")//
    .append("into :{page.sommeParCompteParticulierId}, ")//
    .append(":{page.timestamp}, ")//
    .append(":{page.montantEnvoye}, ")//
    .append(":{page.compteParticulier}, ")//
    .append(":{page.compteDenvoi}, ")//
    .append(":{page.prixDeVenteUnite}, ")//
    .append(":{page.dateEnvoi}")//
    ;
    SQL.selectInto(req.toString(), new NVPair("search", compteParticuliersSearchFormData), new NVPair("page", pageData));
    return pageData;
  }
}
