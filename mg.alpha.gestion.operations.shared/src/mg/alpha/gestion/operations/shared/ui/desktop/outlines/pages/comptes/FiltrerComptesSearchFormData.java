/**
 * 
 */
package mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.comptes;

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
public class FiltrerComptesSearchFormData extends AbstractFormData {

  private static final long serialVersionUID = 1L;

  public FiltrerComptesSearchFormData() {
  }

  public CompteParticulier getCompteParticulier() {
    return getFieldByClass(CompteParticulier.class);
  }

  /**
   * access method for property FiltrerParticulier.
   */
  public Boolean getFiltrerParticulier() {
    return getFiltrerParticulierProperty().getValue();
  }

  /**
   * access method for property FiltrerParticulier.
   */
  public void setFiltrerParticulier(Boolean filtrerParticulier) {
    getFiltrerParticulierProperty().setValue(filtrerParticulier);
  }

  public FiltrerParticulierProperty getFiltrerParticulierProperty() {
    return getPropertyByClass(FiltrerParticulierProperty.class);
  }

  public LibelleCompte getLibelleCompte() {
    return getFieldByClass(LibelleCompte.class);
  }

  public static class CompteParticulier extends AbstractValueFieldData<Boolean> {

    private static final long serialVersionUID = 1L;

    public CompteParticulier() {
    }
  }

  public static class FiltrerParticulierProperty extends AbstractPropertyData<Boolean> {

    private static final long serialVersionUID = 1L;

    public FiltrerParticulierProperty() {
    }
  }

  public static class LibelleCompte extends AbstractValueFieldData<String> {

    private static final long serialVersionUID = 1L;

    public LibelleCompte() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MAX_LENGTH, 4000);
    }
  }
}