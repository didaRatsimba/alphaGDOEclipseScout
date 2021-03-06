/**
 * 
 */
package mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.comptes;

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
public class SupprimerCompteFormData extends AbstractFormData {

  private static final long serialVersionUID = 1L;

  public SupprimerCompteFormData() {
  }

  public ErreurMotDePasse getErreurMotDePasse() {
    return getFieldByClass(ErreurMotDePasse.class);
  }

  public MotDePasse getMotDePasse() {
    return getFieldByClass(MotDePasse.class);
  }

  /**
   * access method for property SupprimerCompteNr.
   */
  public Long getSupprimerCompteNr() {
    return getSupprimerCompteNrProperty().getValue();
  }

  /**
   * access method for property SupprimerCompteNr.
   */
  public void setSupprimerCompteNr(Long supprimerCompteNr) {
    getSupprimerCompteNrProperty().setValue(supprimerCompteNr);
  }

  public SupprimerCompteNrProperty getSupprimerCompteNrProperty() {
    return getPropertyByClass(SupprimerCompteNrProperty.class);
  }

  public static class ErreurMotDePasse extends AbstractValueFieldData<String> {

    private static final long serialVersionUID = 1L;

    public ErreurMotDePasse() {
    }
  }

  public static class MotDePasse extends AbstractValueFieldData<String> {

    private static final long serialVersionUID = 1L;

    public MotDePasse() {
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

  public static class SupprimerCompteNrProperty extends AbstractPropertyData<Long> {

    private static final long serialVersionUID = 1L;

    public SupprimerCompteNrProperty() {
    }
  }
}