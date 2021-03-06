/**
 * 
 */
package mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService;

/**
 * @author Dida
 */
@InputValidation(IValidationStrategy.PROCESS.class)
public interface ISelectionnerDonneesService extends IService {

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  SelectionnerDonneesFormData create(SelectionnerDonneesFormData formData) throws ProcessingException;

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  SelectionnerDonneesFormData load(SelectionnerDonneesFormData formData) throws ProcessingException;

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  SelectionnerDonneesFormData prepareCreate(SelectionnerDonneesFormData formData) throws ProcessingException;

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  SelectionnerDonneesFormData store(SelectionnerDonneesFormData formData) throws ProcessingException;
}