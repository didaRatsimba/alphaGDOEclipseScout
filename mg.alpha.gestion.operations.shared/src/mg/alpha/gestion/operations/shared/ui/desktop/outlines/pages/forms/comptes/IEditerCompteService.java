/**
 * 
 */
package mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.comptes;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService;

/**
 * @author Dida
 */
@InputValidation(IValidationStrategy.PROCESS.class)
public interface IEditerCompteService extends IService {

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  EditerCompteFormData create(EditerCompteFormData formData) throws ProcessingException;

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  EditerCompteFormData load(EditerCompteFormData formData) throws ProcessingException;

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  EditerCompteFormData prepareCreate(EditerCompteFormData formData) throws ProcessingException;

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  EditerCompteFormData store(EditerCompteFormData formData) throws ProcessingException;
}