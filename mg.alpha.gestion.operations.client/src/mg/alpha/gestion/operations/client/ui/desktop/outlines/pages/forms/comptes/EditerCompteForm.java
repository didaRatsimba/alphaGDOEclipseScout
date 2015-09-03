/**
 *
 */
package mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.comptes;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.checkbox.AbstractCheckBox;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;

import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.comptes.EditerCompteForm.MainBox.CancelButton;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.comptes.EditerCompteForm.MainBox.CompteParticulierField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.comptes.EditerCompteForm.MainBox.LibelleCompteField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.comptes.EditerCompteForm.MainBox.OkButton;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.CreerOperationForm;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.comptes.EditerCompteFormData;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.comptes.IEditerCompteService;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.comptes.UpdateEditerComptePermission;

/**
 * @author Dida
 */
@FormData(value = EditerCompteFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class EditerCompteForm extends AbstractForm {

  private Long m_editerCompteNr;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public EditerCompteForm() throws ProcessingException {
    super();
  }

  /**
   * @return the EditerCompteNr
   */
  @FormData
  public Long getEditerCompteNr() {
    return m_editerCompteNr;
  }

  /**
   * @param editerCompteNr
   *          the EditerCompteNr to set
   */
  @FormData
  public void setEditerCompteNr(Long editerCompteNr) {
    m_editerCompteNr = editerCompteNr;
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("editerCompte");
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
   * @return the CancelButton
   */
  public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }

  /**
   * @return the CompteParticulierField
   */
  public CompteParticulierField getCompteParticulierField(){
    return getFieldByClass(CompteParticulierField.class);
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
   * @return the OkButton
   */
  public OkButton getOkButton() {
    return getFieldByClass(OkButton.class);
  }

  @Order(1000.0)
  public class MainBox extends AbstractGroupBox {

    @Override
    protected int getConfiguredGridColumnCount() {
      return 1;
    }

    @Override
    protected int getConfiguredWidthInPixel() {
      return 350;
    }

    @Order(1000.0)
    public class LibelleCompteField extends AbstractStringField {

      @Override
      protected double getConfiguredGridWeightX() {
        return 200.0;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("libelleCompte");
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }

      @Override
      protected boolean getConfiguredMultilineText() {
        return true;
      }

      @Override
      protected int getConfiguredWidthInPixel() {
        return 100;
      }
    }

    @Order(2000.0)
    public class CompteParticulierField extends AbstractCheckBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("compteParticulier");
      }
    }

    @Order(100000.0)
    public class OkButton extends AbstractOkButton {
    }

    @Order(101000.0)
    public class CancelButton extends AbstractCancelButton {
    }
  }

  public class ModifyHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      IEditerCompteService service = SERVICES.getService(IEditerCompteService.class);
      EditerCompteFormData formData = new EditerCompteFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
      setEnabledPermission(new UpdateEditerComptePermission());

    }

    @Override
    protected void execStore() throws ProcessingException {
      IEditerCompteService service = SERVICES.getService(IEditerCompteService.class);
      EditerCompteFormData formData = new EditerCompteFormData();
      exportFormData(formData);
      formData = service.store(formData);

    }
  }

  public class NewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      IEditerCompteService service = SERVICES.getService(IEditerCompteService.class);
      EditerCompteFormData formData = new EditerCompteFormData();
      exportFormData(formData);
      formData = service.prepareCreate(formData);
      importFormData(formData);

    }

    @Override
    protected void execStore() throws ProcessingException {
      IEditerCompteService service = SERVICES.getService(IEditerCompteService.class);
      EditerCompteFormData formData = new EditerCompteFormData();
      exportFormData(formData);
      formData = service.create(formData);
      CreerOperationForm formCreerOperationForm = null;
      if ((formCreerOperationForm = getDesktop().findForm(CreerOperationForm.class)) != null) {
        if (formCreerOperationForm.isFormOpen()) {
          formCreerOperationForm.setCompteId(formData.getEditerCompteNr().longValue());
        }
      }
    }
  }
}
