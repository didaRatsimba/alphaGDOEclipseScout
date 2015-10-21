/**
 *
 */
package mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.comptes;

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

import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.comptes.SupprimerCompteForm.MainBox.CancelButton;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.comptes.SupprimerCompteForm.MainBox.ErreurMotDePasseField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.comptes.SupprimerCompteForm.MainBox.MotDePasseField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.comptes.SupprimerCompteForm.MainBox.OkButton;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.comptes.ISupprimerCompteService;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.comptes.SupprimerCompteFormData;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.comptes.UpdateSupprimerComptePermission;

/**
 * @author Dida
 */
@FormData(value = SupprimerCompteFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class SupprimerCompteForm extends AbstractForm {

  private Long m_supprimerCompteNr;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public SupprimerCompteForm() throws ProcessingException {
    super();
  }

  @Override
  protected void execInitForm() throws ProcessingException {
    super.execInitForm();
    this.getErreurMotDePasseField().setVisible(false);
  }

  @Override
  protected boolean execValidate() throws ProcessingException {
    if (StringUtility.isNullOrEmpty(getMotDePasseField().getValue())) {
      getErreurMotDePasseField().setVisible(true);
      return false;
    }
    else if (!getMotDePasseField().getValue().equalsIgnoreCase("gomanagergo")) {
      getErreurMotDePasseField().setVisible(true);
      return false;
    }
    else {
      getErreurMotDePasseField().setVisible(false);
      return super.execValidate();
    }
  }

  /**
   * @return the SupprimerCompteNr
   */
  @FormData
  public Long getSupprimerCompteNr() {
    return m_supprimerCompteNr;
  }

  /**
   * @param supprimerCompteNr
   *          the SupprimerCompteNr to set
   */
  @FormData
  public void setSupprimerCompteNr(Long supprimerCompteNr) {
    m_supprimerCompteNr = supprimerCompteNr;
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("supprimerCompte");
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
   * @return the ErreurMotDePasseField
   */
  public ErreurMotDePasseField getErreurMotDePasseField() {
    return getFieldByClass(ErreurMotDePasseField.class);
  }

  /**
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the MotDePasseField
   */
  public MotDePasseField getMotDePasseField() {
    return getFieldByClass(MotDePasseField.class);
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
    public class MotDePasseField extends AbstractStringField {

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
    public class ErreurMotDePasseField extends AbstractLabelField {

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
      ISupprimerCompteService service = SERVICES.getService(ISupprimerCompteService.class);
      SupprimerCompteFormData formData = new SupprimerCompteFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
      setEnabledPermission(new UpdateSupprimerComptePermission());

    }

    @Override
    protected void execStore() throws ProcessingException {
      ISupprimerCompteService service = SERVICES.getService(ISupprimerCompteService.class);
      SupprimerCompteFormData formData = new SupprimerCompteFormData();
      exportFormData(formData);
      formData = service.store(formData);

    }
  }

  public class NewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      ISupprimerCompteService service = SERVICES.getService(ISupprimerCompteService.class);
      SupprimerCompteFormData formData = new SupprimerCompteFormData();
      exportFormData(formData);
      formData = service.prepareCreate(formData);
      importFormData(formData);

    }

    @Override
    protected void execStore() throws ProcessingException {
      ISupprimerCompteService service = SERVICES.getService(ISupprimerCompteService.class);
      SupprimerCompteFormData formData = new SupprimerCompteFormData();
      exportFormData(formData);
      formData = service.create(formData);

    }
  }
}
