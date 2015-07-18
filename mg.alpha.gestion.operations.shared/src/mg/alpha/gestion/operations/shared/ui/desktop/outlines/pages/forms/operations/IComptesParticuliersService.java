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
public interface IComptesParticuliersService extends IService {

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  ComptesParticuliersFormData create(ComptesParticuliersFormData formData) throws ProcessingException;

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  ComptesParticuliersFormData load(ComptesParticuliersFormData formData) throws ProcessingException;

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  ComptesParticuliersFormData prepareCreate(ComptesParticuliersFormData formData) throws ProcessingException;

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  ComptesParticuliersFormData store(ComptesParticuliersFormData formData) throws ProcessingException;
}