/**
 *
 */
package mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Map;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService;

/**
 * @author Dida
 */
@InputValidation(IValidationStrategy.PROCESS.class)
public interface ICreerOperationService extends IService {

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  CreerOperationFormData create(CreerOperationFormData formData) throws ProcessingException, SQLException;

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

  void createDebitParOperation(Map<Long, BigDecimal> toBeStoredData, Long idFromtimestamp) throws ProcessingException, SQLException;
}
