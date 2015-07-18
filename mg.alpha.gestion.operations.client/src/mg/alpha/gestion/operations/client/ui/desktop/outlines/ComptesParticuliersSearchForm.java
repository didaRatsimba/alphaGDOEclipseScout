/**
 * 
 */
package mg.alpha.gestion.operations.client.ui.desktop.outlines;

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

import mg.alpha.gestion.operations.client.ui.desktop.outlines.ComptesParticuliersSearchForm.MainBox.ResetButton;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.ComptesParticuliersSearchForm.MainBox.SearchButton;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.ComptesParticuliersSearchForm.MainBox.SearchComptesGroupBox;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.ComptesParticuliersSearchForm.MainBox.SearchComptesGroupBox.CompteDenvoiField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.ComptesParticuliersSearchForm.MainBox.SearchComptesGroupBox.CompteParticulierField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.ComptesParticuliersSearchForm.MainBox.SearchComptesGroupBox.DateEnvoiBox;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.ComptesParticuliersSearchForm.MainBox.SearchComptesGroupBox.DateEnvoiBox.DateEnvoiFrom;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.ComptesParticuliersSearchForm.MainBox.SearchComptesGroupBox.DateEnvoiBox.DateEnvoiTo;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.ComptesParticuliersSearchForm.MainBox.SearchComptesGroupBox.MontantEnvoyeField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.ComptesParticuliersSearchForm.MainBox.SearchComptesGroupBox.PrixDeVenteUniteField;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.ComptesParticuliersSearchFormData;

/**
 * @author Dida
 */
@FormData(value = ComptesParticuliersSearchFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class ComptesParticuliersSearchForm extends AbstractSearchForm {

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public ComptesParticuliersSearchForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("comptesParticuliers");
  }

  @Override
  protected void execResetSearchFilter(SearchFilter searchFilter) throws ProcessingException {
    super.execResetSearchFilter(searchFilter);
    ComptesParticuliersSearchFormData formData = new ComptesParticuliersSearchFormData();
    exportFormData(formData);
    searchFilter.setFormData(formData);
  }

  @Override
  public void startSearch() throws ProcessingException {
    startInternal(new SearchHandler());
  }

  /**
   * @return the CompteDenvoiField
   */
  public CompteDenvoiField getCompteDenvoiField() {
    return getFieldByClass(CompteDenvoiField.class);
  }

  /**
   * @return the CompteParticulierField
   */
  public CompteParticulierField getCompteParticulierField() {
    return getFieldByClass(CompteParticulierField.class);
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
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the MontantEnvoyeField
   */
  public MontantEnvoyeField getMontantEnvoyeField() {
    return getFieldByClass(MontantEnvoyeField.class);
  }

  /**
   * @return the PrixDeVenteUniteField
   */
  public PrixDeVenteUniteField getPrixDeVenteUniteField() {
    return getFieldByClass(PrixDeVenteUniteField.class);
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
   * @return the SearchComptesGroupBox
   */
  public SearchComptesGroupBox getSearchComptesGroupBox() {
    return getFieldByClass(SearchComptesGroupBox.class);
  }

  @Order(1000.0)
  public class MainBox extends AbstractGroupBox {

    @Order(0.0)
    public class SearchComptesGroupBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredBorderDecoration() {
        return BORDER_DECORATION_SECTION;
      }

      @Order(2000.0)
      public class CompteParticulierField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("compteParticulier");
        }
      }

      @Order(3000.0)
      public class CompteDenvoiField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("compteDenvoi");
        }
      }

      @Order(4000.0)
      public class MontantEnvoyeField extends AbstractBigDecimalField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("montantEnvoye");
        }
      }

      @Order(5000.0)
      public class PrixDeVenteUniteField extends AbstractBigDecimalField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("prixDeVenteUnite");
        }
      }

      @Order(6000.0)
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
    }

    @Order(6000.0)
    public class ResetButton extends AbstractResetButton {
    }

    @Order(7000.0)
    public class SearchButton extends AbstractSearchButton {
    }
  }

  public class SearchHandler extends AbstractFormHandler {
  }
}