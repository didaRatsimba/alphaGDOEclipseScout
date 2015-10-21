/**
 *
 */
package mg.alpha.gestion.operations.client.mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations;

import org.eclipse.scout.commons.StringUtility;
import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;

import mg.alpha.gestion.operations.client.mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.SupprimerOperationForm.MainBox.CancelButton;
import mg.alpha.gestion.operations.client.mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.SupprimerOperationForm.MainBox.MotDePasseAdministrateurField;
import mg.alpha.gestion.operations.client.mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.SupprimerOperationForm.MainBox.MotDePasseErroneField;
import mg.alpha.gestion.operations.client.mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.SupprimerOperationForm.MainBox.OkButton;
import mg.alpha.gestion.operations.shared.mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.ISupprimerOperationService;
import mg.alpha.gestion.operations.shared.mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.SupprimerOperationFormData;
import mg.alpha.gestion.operations.shared.mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.UpdateSupprimerOperationPermission;

/**
 * @author Dida
 */
@FormData(value = SupprimerOperationFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class SupprimerOperationForm extends AbstractForm {

  private Long m_supprimerOperationNr;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public SupprimerOperationForm() throws ProcessingException {
    super();
  }

  @Override
  protected void execInitForm() throws ProcessingException {
    super.execInitForm();
    this.getMotDePasseErroneField().setVisible(false);
  }

  @Override
  protected boolean execValidate() throws ProcessingException {

    if (StringUtility.isNullOrEmpty(getMotDePasseAdministrateurField().getValue())) {
      getMotDePasseErroneField().setVisible(true);
      return false;
    }
    else if (!getMotDePasseAdministrateurField().getValue().equalsIgnoreCase("gomanagergo")) {
      getMotDePasseErroneField().setVisible(true);
      return false;
    }
    else {
      getMotDePasseErroneField().setVisible(false);
      return super.execValidate();
    }

  }

  /**
   * @return the SupprimerOperationNr
   */
  @FormData
  public Long getSupprimerOperationNr() {
    return m_supprimerOperationNr;
  }

  /**
   * @param supprimerOperationNr
   *          the SupprimerOperationNr to set
   */
  @FormData
  public void setSupprimerOperationNr(Long supprimerOperationNr) {
    m_supprimerOperationNr = supprimerOperationNr;
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("supprimerOperation");
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
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the MotDePasseAdministrateurField
   */
  public MotDePasseAdministrateurField getMotDePasseAdministrateurField() {
    return getFieldByClass(MotDePasseAdministrateurField.class);
  }

  /**
   * @return the MotDePasseErroneField
   */
  public MotDePasseErroneField getMotDePasseErroneField() {
    return getFieldByClass(MotDePasseErroneField.class);
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

    @Order(1000.0)
    public class MotDePasseAdministrateurField extends AbstractStringField {

      @Override
      protected boolean getConfiguredInputMasked() {
        return true;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("motDePasse");
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }
    }

    @Order(2000.0)
    public class MotDePasseErroneField extends AbstractLabelField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("erreurMotDePasse");
      }

      @Override
      protected boolean getConfiguredVisible() {
        return false;
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
      ISupprimerOperationService service = SERVICES.getService(ISupprimerOperationService.class);
      SupprimerOperationFormData formData = new SupprimerOperationFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
      setEnabledPermission(new UpdateSupprimerOperationPermission());

    }

    @Override
    protected void execStore() throws ProcessingException {
      ISupprimerOperationService service = SERVICES.getService(ISupprimerOperationService.class);
      SupprimerOperationFormData formData = new SupprimerOperationFormData();
      exportFormData(formData);
      formData = service.store(formData);

    }
  }

  public class NewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      ISupprimerOperationService service = SERVICES.getService(ISupprimerOperationService.class);
      SupprimerOperationFormData formData = new SupprimerOperationFormData();
      exportFormData(formData);
      formData = service.prepareCreate(formData);
      importFormData(formData);

    }

    @Override
    protected void execStore() throws ProcessingException {
      ISupprimerOperationService service = SERVICES.getService(ISupprimerOperationService.class);
      SupprimerOperationFormData formData = new SupprimerOperationFormData();
      exportFormData(formData);
      formData = service.create(formData);

    }
  }
}
