/**
 * 
 */
package mg.alpha.gestion.operations.shared.mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations;

import java.util.Map;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.ValidationRule;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 * 
 * @generated
 */
@Generated(value = "org.eclipse.scout.sdk.workspace.dto.formdata.FormDataDtoUpdateOperation", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class SupprimerOperationFormData extends AbstractFormData {

  private static final long serialVersionUID = 1L;

  public SupprimerOperationFormData() {
  }

  public MotDePasseAdministrateur getMotDePasseAdministrateur() {
    return getFieldByClass(MotDePasseAdministrateur.class);
  }

  public MotDePasseErrone getMotDePasseErrone() {
    return getFieldByClass(MotDePasseErrone.class);
  }

  /**
   * access method for property SupprimerOperationNr.
   */
  public Long getSupprimerOperationNr() {
    return getSupprimerOperationNrProperty().getValue();
  }

  /**
   * access method for property SupprimerOperationNr.
   */
  public void setSupprimerOperationNr(Long supprimerOperationNr) {
    getSupprimerOperationNrProperty().setValue(supprimerOperationNr);
  }

  public SupprimerOperationNrProperty getSupprimerOperationNrProperty() {
    return getPropertyByClass(SupprimerOperationNrProperty.class);
  }

  public static class MotDePasseAdministrateur extends AbstractValueFieldData<String> {

    private static final long serialVersionUID = 1L;

    public MotDePasseAdministrateur() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MANDATORY, true);
      ruleMap.put(ValidationRule.MAX_LENGTH, 4000);
    }
  }

  public static class MotDePasseErrone extends AbstractValueFieldData<String> {

    private static final long serialVersionUID = 1L;

    public MotDePasseErrone() {
    }
  }

  public static class SupprimerOperationNrProperty extends AbstractPropertyData<Long> {

    private static final long serialVersionUID = 1L;

    public SupprimerOperationNrProperty() {
    }
  }
}