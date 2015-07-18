/**
 * 
 */
package mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.ValidationRule;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 * 
 * @generated
 */
@Generated(value = "org.eclipse.scout.sdk.workspace.dto.formdata.FormDataDtoUpdateOperation", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class OperationsSearchFormData extends AbstractFormData {

  private static final long serialVersionUID = 1L;

  public OperationsSearchFormData() {
  }

  public CompteOperation getCompteOperation() {
    return getFieldByClass(CompteOperation.class);
  }

  public CompteParticulier getCompteParticulier() {
    return getFieldByClass(CompteParticulier.class);
  }

  public CoursApplique getCoursApplique() {
    return getFieldByClass(CoursApplique.class);
  }

  public DateOperationFrom getDateOperationFrom() {
    return getFieldByClass(DateOperationFrom.class);
  }

  public DateOperationTo getDateOperationTo() {
    return getFieldByClass(DateOperationTo.class);
  }

  public Designation getDesignation() {
    return getFieldByClass(Designation.class);
  }

  public Devise getDevise() {
    return getFieldByClass(Devise.class);
  }

  public MontantOperation getMontantOperation() {
    return getFieldByClass(MontantOperation.class);
  }

  public TypeOperation getTypeOperation() {
    return getFieldByClass(TypeOperation.class);
  }

  public static class CompteOperation extends AbstractValueFieldData<String> {

    private static final long serialVersionUID = 1L;

    public CompteOperation() {
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

  public static class CompteParticulier extends AbstractValueFieldData<String> {

    private static final long serialVersionUID = 1L;

    public CompteParticulier() {
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

  public static class CoursApplique extends AbstractValueFieldData<BigDecimal> {

    private static final long serialVersionUID = 1L;

    public CoursApplique() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MAX_VALUE, new BigDecimal("999999999999999999999999999999999999999999999999999999999999"));
      ruleMap.put(ValidationRule.MIN_VALUE, new BigDecimal("-999999999999999999999999999999999999999999999999999999999999"));
    }
  }

  public static class DateOperationFrom extends AbstractValueFieldData<Date> {

    private static final long serialVersionUID = 1L;

    public DateOperationFrom() {
    }
  }

  public static class DateOperationTo extends AbstractValueFieldData<Date> {

    private static final long serialVersionUID = 1L;

    public DateOperationTo() {
    }
  }

  public static class Designation extends AbstractValueFieldData<String> {

    private static final long serialVersionUID = 1L;

    public Designation() {
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

  public static class Devise extends AbstractValueFieldData<String> {

    private static final long serialVersionUID = 1L;

    public Devise() {
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

  public static class MontantOperation extends AbstractValueFieldData<BigDecimal> {

    private static final long serialVersionUID = 1L;

    public MontantOperation() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MAX_VALUE, new BigDecimal("999999999999999999999999999999999999999999999999999999999999"));
      ruleMap.put(ValidationRule.MIN_VALUE, new BigDecimal("-999999999999999999999999999999999999999999999999999999999999"));
    }
  }

  public static class TypeOperation extends AbstractValueFieldData<String> {

    private static final long serialVersionUID = 1L;

    public TypeOperation() {
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