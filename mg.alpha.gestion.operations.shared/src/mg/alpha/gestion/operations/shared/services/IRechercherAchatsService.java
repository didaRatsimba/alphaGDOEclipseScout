/**
 *
 */
package mg.alpha.gestion.operations.shared.services;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService;

import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.CreerOperationFormData;

/**
 * @author Dida
 */
@InputValidation(IValidationStrategy.PROCESS.class)
public interface IRechercherAchatsService extends IService {

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  CreerOperationFormData create(CreerOperationFormData formData) throws ProcessingException;

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  CreerOperationFormData load(CreerOperationFormData formData) throws ProcessingException;

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  CreerOperationFormData prepareCreate(CreerOperationFormData formData) throws ProcessingException;

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  CreerOperationFormData store(CreerOperationFormData formData) throws ProcessingException;

}
