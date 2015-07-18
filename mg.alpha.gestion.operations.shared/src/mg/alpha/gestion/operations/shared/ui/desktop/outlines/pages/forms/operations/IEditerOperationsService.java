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
public interface IEditerOperationsService extends IService {

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  EditerOperationsFormData create(EditerOperationsFormData formData) throws ProcessingException;

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  EditerOperationsFormData load(EditerOperationsFormData formData) throws ProcessingException;

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  EditerOperationsFormData prepareCreate(EditerOperationsFormData formData) throws ProcessingException;

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  EditerOperationsFormData store(EditerOperationsFormData formData) throws ProcessingException;
}