/**
 *
 */
package mg.alpha.gestion.operations.shared.services;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.service.IService;

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
public interface IStandardOutlineService extends IService {

  /**
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  OperationsTablePageData getOperationTableData(OperationsSearchFormData operationsSearchFormData) throws ProcessingException;

  /**
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  ComptesTablePageData getCompteTableData(FiltrerComptesSearchFormData searchFormData) throws ProcessingException;

  /**
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  BilanBeneficesTablePageData getBilanBeneficeTablePageData(BilanBeneficesSearchFormData bilanSearchFormData) throws ProcessingException;

  /**
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  ComptesParticuliersTablePageData getComptesParticuliersHistorique(ComptesParticuliersSearchFormData compteParticuliersSearchFormData) throws ProcessingException;
}
