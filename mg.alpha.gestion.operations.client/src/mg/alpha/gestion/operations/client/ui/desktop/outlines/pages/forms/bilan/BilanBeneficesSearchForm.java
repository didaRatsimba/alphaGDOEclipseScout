/**
 * 
 */
package mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.bilan;

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

import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.bilan.BilanBeneficesSearchForm.MainBox.BilanGroupBox;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.bilan.BilanBeneficesSearchForm.MainBox.BilanGroupBox.BeneficeField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.bilan.BilanBeneficesSearchForm.MainBox.BilanGroupBox.DateEnvoiBox;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.bilan.BilanBeneficesSearchForm.MainBox.BilanGroupBox.DateEnvoiBox.DateEnvoiFrom;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.bilan.BilanBeneficesSearchForm.MainBox.BilanGroupBox.DateEnvoiBox.DateEnvoiTo;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.bilan.BilanBeneficesSearchForm.MainBox.BilanGroupBox.EnvoyeAField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.bilan.BilanBeneficesSearchForm.MainBox.BilanGroupBox.MontantDebiteField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.bilan.BilanBeneficesSearchForm.MainBox.ResetButton;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.bilan.BilanBeneficesSearchForm.MainBox.SearchButton;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.bilan.BilanBeneficesSearchFormData;

/**
 * @author Dida
 */
@FormData(value = BilanBeneficesSearchFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class BilanBeneficesSearchForm extends AbstractSearchForm {

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public BilanBeneficesSearchForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("bilanBenefices");
  }

  @Override
  protected void execResetSearchFilter(SearchFilter searchFilter) throws ProcessingException {
    super.execResetSearchFilter(searchFilter);
    BilanBeneficesSearchFormData formData = new BilanBeneficesSearchFormData();
    exportFormData(formData);
    searchFilter.setFormData(formData);
  }

  @Override
  public void startSearch() throws ProcessingException {
    startInternal(new SearchHandler());
  }

  /**
   * @return the BeneficeField
   */
  public BeneficeField getBeneficeField() {
    return getFieldByClass(BeneficeField.class);
  }

  /**
   * @return the BilanGroupBox
   */
  public BilanGroupBox getBilanGroupBox() {
    return getFieldByClass(BilanGroupBox.class);
  }

  /**
   * @return the DateEnvoiBox
   */
  public DateEnvoiBox getDateEnvoiBox() {
    return getFieldByClass(DateEnvoiBox.class);
  }

  /**
   * @return the DateEnvoiFrom
   */
  public DateEnvoiFrom getDateEnvoiFrom() {
    return getFieldByClass(DateEnvoiFrom.class);
  }

  /**
   * @return the DateEnvoiTo
   */
  public DateEnvoiTo getDateEnvoiTo() {
    return getFieldByClass(DateEnvoiTo.class);
  }

  /**
   * @return the EnvoyeAField
   */
  public EnvoyeAField getEnvoyeAField() {
    return getFieldByClass(EnvoyeAField.class);
  }

  /**
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the MontantDebiteField
   */
  public MontantDebiteField getMontantDebiteField() {
    return getFieldByClass(MontantDebiteField.class);
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

  @Order(1000.0)
  public class MainBox extends AbstractGroupBox {

    @Order(0.0)
    public class BilanGroupBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredBorderDecoration() {
        return BORDER_DECORATION_SECTION;
      }

      @Order(2000.0)
      public class DateEnvoiBox extends AbstractSequenceBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("dateEnvoi");
        }

        @Order(1000.0)
        public class DateEnvoiFrom extends AbstractDateField {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("from");
          }
        }

        @Order(2000.0)
        public class DateEnvoiTo extends AbstractDateField {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("to");
          }
        }
      }

      @Order(3000.0)
      public class MontantDebiteField extends AbstractBigDecimalField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("montantDebite");
        }
      }

      @Order(4000.0)
      public class EnvoyeAField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("envoyeA");
        }
      }

      @Order(5000.0)
      public class BeneficeField extends AbstractBigDecimalField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("benficeRapporte");
        }
      }
    }

    @Order(5000.0)
    public class ResetButton extends AbstractResetButton {
    }

    @Order(6000.0)
    public class SearchButton extends AbstractSearchButton {
    }
  }

  public class SearchHandler extends AbstractFormHandler {
  }
}