/**
 * 
 */
package mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractSearchForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.bigdecimalfield.AbstractBigDecimalField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractResetButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractSearchButton;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.OperationsSearchForm.MainBox.FieldBox;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.OperationsSearchForm.MainBox.FieldBox.CompteOperationField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.OperationsSearchForm.MainBox.FieldBox.CompteParticulierField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.OperationsSearchForm.MainBox.FieldBox.CoursAppliqueField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.OperationsSearchForm.MainBox.FieldBox.DateOperationBox;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.OperationsSearchForm.MainBox.FieldBox.DateOperationBox.DateOperationFrom;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.OperationsSearchForm.MainBox.FieldBox.DateOperationBox.DateOperationTo;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.OperationsSearchForm.MainBox.FieldBox.DesignationField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.OperationsSearchForm.MainBox.FieldBox.DeviseField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.OperationsSearchForm.MainBox.FieldBox.MontantOperationField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.OperationsSearchForm.MainBox.FieldBox.TypeOperationField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.OperationsSearchForm.MainBox.ResetButton;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.OperationsSearchForm.MainBox.SearchButton;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.OperationsSearchFormData;

/**
 * @author Dida
 */
@FormData(value = OperationsSearchFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class OperationsSearchForm extends AbstractSearchForm {

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public OperationsSearchForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("filtrerOperations");
  }

  @Override
  protected void execResetSearchFilter(SearchFilter searchFilter) throws ProcessingException {
    super.execResetSearchFilter(searchFilter);
    OperationsSearchFormData formData = new OperationsSearchFormData();
    exportFormData(formData);
    searchFilter.setFormData(formData);
  }

  @Override
  public void startSearch() throws ProcessingException {
    startInternal(new SearchHandler());
  }

  /**
   * @return the CompteOperationField
   */
  public CompteOperationField getCompteOperationField() {
    return getFieldByClass(CompteOperationField.class);
  }

  /**
   * @return the CompteParticulierField
   */
  public CompteParticulierField getCompteParticulierField() {
    return getFieldByClass(CompteParticulierField.class);
  }

  /**
   * @return the CoursAppliqueField
   */
  public CoursAppliqueField getCoursAppliqueField() {
    return getFieldByClass(CoursAppliqueField.class);
  }

  /**
   * @return the DateOperationBox
   */
  public DateOperationBox getDateOperationBox() {
    return getFieldByClass(DateOperationBox.class);
  }

  /**
   * @return the DateOperationFrom
   */
  public DateOperationFrom getDateOperationFrom() {
    return getFieldByClass(DateOperationFrom.class);
  }

  /**
   * @return the DateOperationTo
   */
  public DateOperationTo getDateOperationTo() {
    return getFieldByClass(DateOperationTo.class);
  }

  /**
   * @return the DesignationField
   */
  public DesignationField getDesignationField() {
    return getFieldByClass(DesignationField.class);
  }

  /**
   * @return the DeviseField
   */
  public DeviseField getDeviseField() {
    return getFieldByClass(DeviseField.class);
  }

  /**
   * @return the FieldBox
   */
  public FieldBox getFieldBox() {
    return getFieldByClass(FieldBox.class);
  }

  /**
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the MontantOperationField
   */
  public MontantOperationField getMontantOperationField() {
    return getFieldByClass(MontantOperationField.class);
  }

  /**
   * @return the ResetButton
   */
  public ResetButton getResetButton() {
    return getFieldByClass(ResetButton.class);
  }

  /**
   * @return the SearchButton
   */
  public SearchButton getSearchButton() {
    return getFieldByClass(SearchButton.class);
  }

  /**
   * @return the TypeOperationField
   */
  public TypeOperationField getTypeOperationField() {
    return getFieldByClass(TypeOperationField.class);
  }

  @Order(1000.0)
  public class MainBox extends AbstractGroupBox {

    @Order(0.0)
    public class FieldBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredBorderDecoration() {
        return BORDER_DECORATION_SECTION;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Criteria");
      }

      @Order(1000.0)
      public class DateOperationBox extends AbstractSequenceBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("dateOperationColumnHeader");
        }

        @Order(1000.0)
        public class DateOperationFrom extends AbstractDateField {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("from");
          }
        }

        @Order(2000.0)
        public class DateOperationTo extends AbstractDateField {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("to");
          }
        }
      }

      @Order(2000.0)
      public class TypeOperationField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("type");
        }
      }

      @Order(3000.0)
      public class CompteOperationField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("compteOperation");
        }
      }

      @Order(4000.0)
      public class CompteParticulierField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("compteParticulier");
        }
      }

      @Order(5000.0)
      public class DeviseField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("devise");
        }
      }

      @Order(6000.0)
      public class DesignationField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("designation");
        }
      }

      @Order(7000.0)
      public class MontantOperationField extends AbstractBigDecimalField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("montantHeaderColumn");
        }
      }

      @Order(8000.0)
      public class CoursAppliqueField extends AbstractBigDecimalField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("coursColumnHeader");
        }
      }
    }

    @Order(9000.0)
    public class ResetButton extends AbstractResetButton {
    }

    @Order(10000.0)
    public class SearchButton extends AbstractSearchButton {
    }
  }

  public class SearchHandler extends AbstractFormHandler {
  }
}