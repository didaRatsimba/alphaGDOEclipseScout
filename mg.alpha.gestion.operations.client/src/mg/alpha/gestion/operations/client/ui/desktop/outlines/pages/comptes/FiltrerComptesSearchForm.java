/**
 *
 */
package mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.comptes;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractSearchForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.booleanfield.AbstractBooleanField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractResetButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractSearchButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.comptes.FiltrerComptesSearchForm.MainBox.FieldBox;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.comptes.FiltrerComptesSearchForm.MainBox.FieldBox.FilterByParticulierBox;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.comptes.FiltrerComptesSearchForm.MainBox.FieldBox.FilterByParticulierBox.CompteParticulierField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.comptes.FiltrerComptesSearchForm.MainBox.FieldBox.FilterByParticulierBox.FiltrerSelonTypeDeCompteButton;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.comptes.FiltrerComptesSearchForm.MainBox.FieldBox.LibelleCompteField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.comptes.FiltrerComptesSearchForm.MainBox.ResetButton;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.comptes.FiltrerComptesSearchForm.MainBox.SearchButton;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.comptes.FiltrerComptesSearchFormData;

/**
 * @author Dida
 */
@FormData(value = FiltrerComptesSearchFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class FiltrerComptesSearchForm extends AbstractSearchForm {

  private Boolean m_filtrerParticulier;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public FiltrerComptesSearchForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("filtrerComptes");
  }

  @Override
  protected void execResetSearchFilter(SearchFilter searchFilter) throws ProcessingException {
    super.execResetSearchFilter(searchFilter);
    FiltrerComptesSearchFormData formData = new FiltrerComptesSearchFormData();
    exportFormData(formData);
    searchFilter.setFormData(formData);
  }

  /**
   * @return the FiltrerParticulier
   */
  @FormData
  public Boolean getFiltrerParticulier() {
    return m_filtrerParticulier;
  }

  /**
   * @param filtrerParticulier
   *          the FiltrerParticulier to set
   */
  @FormData
  public void setFiltrerParticulier(Boolean filtrerParticulier) {
    m_filtrerParticulier = filtrerParticulier;
  }

  @Override
  public void startSearch() throws ProcessingException {
    startInternal(new SearchHandler());
  }

  /**
   * @return the CompteParticulierField
   */
  public CompteParticulierField getCompteParticulierField() {
    return getFieldByClass(CompteParticulierField.class);
  }

  /**
   * @return the FieldBox
   */
  public FieldBox getFieldBox() {
    return getFieldByClass(FieldBox.class);
  }

  /**
   * @return the FilterByParticulierBox
   */
  public FilterByParticulierBox getFilterByParticulierBox() {
    return getFieldByClass(FilterByParticulierBox.class);
  }

  /**
   * @return the FiltrerSelonTypeDeCompteButton
   */
  public FiltrerSelonTypeDeCompteButton getFiltrerSelonTypeDeCompteButton() {
    return getFieldByClass(FiltrerSelonTypeDeCompteButton.class);
  }

  /**
   * @return the LibelleCompteField
   */
  public LibelleCompteField getLibelleCompteField() {
    return getFieldByClass(LibelleCompteField.class);
  }

  /**
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
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

    @Order(2000.0)
    public class FieldBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredBorderDecoration() {
        return BORDER_DECORATION_SECTION;
      }

      @Order(1000.0)
      public class LibelleCompteField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("libelleCompte");
        }
      }

      @Order(2000.0)
      public class FilterByParticulierBox extends AbstractGroupBox {

        @Override
        protected int getConfiguredGridColumnCount() {
          return 2;
        }

        @Order(1000.0)
        public class FiltrerSelonTypeDeCompteButton extends AbstractButton {

          @Override
          protected int getConfiguredDisplayStyle() {
            return DISPLAY_STYLE_TOGGLE;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("filtrerSelonTypeDeCompte");
          }

          @Override
          protected void execClickAction() throws ProcessingException {
            getCompteParticulierField().setEnabled(isSelected());
            setFiltrerParticulier(isSelected());
            super.execClickAction();
          }
        }

        @Order(3000.0)
        public class CompteParticulierField extends AbstractBooleanField {

          @Override
          protected boolean getConfiguredEnabled() {
            return false;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("compteParticulier");
          }
        }
      }
    }

    @Order(3000.0)
    public class ResetButton extends AbstractResetButton {
    }

    @Order(4000.0)
    public class SearchButton extends AbstractSearchButton {
    }
  }

  public class SearchHandler extends AbstractFormHandler {
  }
}
