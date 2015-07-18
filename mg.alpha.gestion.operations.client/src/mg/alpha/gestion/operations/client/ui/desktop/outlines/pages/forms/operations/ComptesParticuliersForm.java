/**
 *
 */
package mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBigIntegerColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;

import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.ComptesParticuliersForm.MainBox.ComptesParticuliersField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.ComptesParticuliersForm.MainBox.OkButton;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.ComptesParticuliersFormData;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.IComptesParticuliersService;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.UpdateComptesParticuliersPermission;

/**
 * @author Dida
 */
@FormData(value = ComptesParticuliersFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class ComptesParticuliersForm extends AbstractForm {

  private Long m_comptesParticuliersNr;
  private Long m_idFromTimestamp;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public ComptesParticuliersForm() throws ProcessingException {
    super();
  }

  /**
   * @return the ComptesParticuliersNr
   */
  @FormData
  public Long getComptesParticuliersNr() {
    return m_comptesParticuliersNr;
  }

  /**
   * @param comptesParticuliersNr
   *          the ComptesParticuliersNr to set
   */
  @FormData
  public void setComptesParticuliersNr(Long comptesParticuliersNr) {
    m_comptesParticuliersNr = comptesParticuliersNr;
  }

  @Override
  protected int getConfiguredDisplayHint() {
    return DISPLAY_HINT_POPUP_WINDOW;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("comptesParticuliers");
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public void startModify() throws ProcessingException {
    startInternal(new ModifyHandler());
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public void startNew() throws ProcessingException {
    startInternal(new NewHandler());
  }

  /**
   * @return the ComptesParticuliersField
   */
  public ComptesParticuliersField getComptesParticuliersField() {
    return getFieldByClass(ComptesParticuliersField.class);
  }

  /**
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the OkButton
   */
  public OkButton getOkButton() {
    return getFieldByClass(OkButton.class);
  }

  @Order(1000.0)
  public class MainBox extends AbstractGroupBox {

    @Order(1000.0)
    public class ComptesParticuliersField extends AbstractTableField<ComptesParticuliersField.Table> {

      @Override
      protected int getConfiguredGridH() {
        return 15;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("comptesParticuliers");
      }

      @Order(1000.0)
      public class Table extends AbstractExtensibleTable {

        /**
         * @return the SommeEnvoyeeColumn
         */
        public SommeEnvoyeeColumn getSommeEnvoyeeColumn() {
          return getColumnSet().getColumnByClass(SommeEnvoyeeColumn.class);
        }

        /**
         * @return the CompteParticulierColumn
         */
        public CompteParticulierColumn getCompteParticulierColumn() {
          return getColumnSet().getColumnByClass(CompteParticulierColumn.class);
        }

        /**
         * @return the SommeParCompteIdColumn
         */
        public SommeParCompteIdColumn getSommeParCompteIdColumn() {
          return getColumnSet().getColumnByClass(SommeParCompteIdColumn.class);
        }

        @Order(1000.0)
        public class SommeParCompteIdColumn extends AbstractLongColumn {

          @Override
          protected boolean getConfiguredDisplayable() {
            return false;
          }

          @Override
          protected boolean getConfiguredPrimaryKey() {
            return true;
          }

          @Override
          protected boolean getConfiguredVisible() {
            return false;
          }
        }

        @Order(2000.0)
        public class CompteParticulierColumn extends AbstractStringColumn {

          @Override
          protected String getConfiguredHeaderText() {
            return TEXTS.get("compteParticulier");
          }

          @Override
          protected int getConfiguredWidth() {
            return 200;
          }
        }

        @Order(3000.0)
        public class SommeEnvoyeeColumn extends AbstractBigIntegerColumn {

          @Override
          protected String getConfiguredHeaderText() {
            return TEXTS.get("sommeEnvoyee");
          }

          @Override
          protected int getConfiguredWidth() {
            return 150;
          }
        }
      }
    }

    @Order(100000.0)
    public class OkButton extends AbstractOkButton {
    }
  }

  public class ModifyHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      IComptesParticuliersService service = SERVICES.getService(IComptesParticuliersService.class);
      ComptesParticuliersFormData formData = new ComptesParticuliersFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
      setEnabledPermission(new UpdateComptesParticuliersPermission());

    }

    @Override
    protected void execStore() throws ProcessingException {
      IComptesParticuliersService service = SERVICES.getService(IComptesParticuliersService.class);
      ComptesParticuliersFormData formData = new ComptesParticuliersFormData();
      exportFormData(formData);
      formData = service.store(formData);

    }
  }

  public class NewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      IComptesParticuliersService service = SERVICES.getService(IComptesParticuliersService.class);
      ComptesParticuliersFormData formData = new ComptesParticuliersFormData();
      exportFormData(formData);
      formData = service.prepareCreate(formData);
      importFormData(formData);

    }

    @Override
    protected void execStore() throws ProcessingException {
      IComptesParticuliersService service = SERVICES.getService(IComptesParticuliersService.class);
      ComptesParticuliersFormData formData = new ComptesParticuliersFormData();
      exportFormData(formData);
      formData = service.create(formData);

    }
  }

  /**
   * @return the IdFromTimestamp
   */
  @FormData
  public Long getIdFromTimestamp() {
    return m_idFromTimestamp;
  }

  /**
   * @param idFromTimestamp
   *          the IdFromTimestamp to set
   */
  @FormData
  public void setIdFromTimestamp(Long idFromTimestamp) {
    m_idFromTimestamp = idFromTimestamp;
  }
}
