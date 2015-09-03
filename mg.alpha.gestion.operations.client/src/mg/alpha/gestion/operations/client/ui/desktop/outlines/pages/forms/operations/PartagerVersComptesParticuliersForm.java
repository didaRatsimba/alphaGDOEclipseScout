/**
 *
 */
package mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations;

import java.math.BigDecimal;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.bigdecimalfield.AbstractBigDecimalField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;

import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.PartagerVersComptesParticuliersForm.MainBox.CancelButton;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.PartagerVersComptesParticuliersForm.MainBox.CompteParticulierField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.PartagerVersComptesParticuliersForm.MainBox.OkButton;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.PartagerVersComptesParticuliersForm.MainBox.SommeAEnvoyerField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.PartagerVersComptesParticuliersForm.MainBox.VousPouvezPartagerField;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.IPartagerVersComptesParticuliersService;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.PartagerVersComptesParticuliersFormData;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.UpdatePartagerVersComptesParticuliersPermission;
import mg.alpha.gestion.operations.shared.services.lookup.CompteParticulierLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

/**
 * @author Dida
 */
@FormData(value = PartagerVersComptesParticuliersFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class PartagerVersComptesParticuliersForm extends AbstractForm {

  private Long m_partagerVersComptesParticuliersNr;
  private Long m_creationTimestamp;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public PartagerVersComptesParticuliersForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected boolean getConfiguredMaximizeEnabled() {
    return true;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("partagerVersComptesParticuliers");
  }

  /**
   * @return the CreationTimestamp
   */
  @FormData
  public Long getCreationTimestamp() {
    return m_creationTimestamp;
  }

  /**
   * @param creationTimestamp
   *          the CreationTimestamp to set
   */
  @FormData
  public void setCreationTimestamp(Long creationTimestamp) {
    m_creationTimestamp = creationTimestamp;
  }

  /**
   * @return the PartagerVersComptesParticuliersNr
   */
  @FormData
  public Long getPartagerVersComptesParticuliersNr() {
    return m_partagerVersComptesParticuliersNr;
  }

  /**
   * @param partagerVersComptesParticuliersNr
   *          the PartagerVersComptesParticuliersNr to set
   */
  @FormData
  public void setPartagerVersComptesParticuliersNr(Long partagerVersComptesParticuliersNr) {
    m_partagerVersComptesParticuliersNr = partagerVersComptesParticuliersNr;
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
  public CompteParticulierField getCompteParticulierField() {
    return getFieldByClass(CompteParticulierField.class);
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

  /**
   * @return the SommeAEnvoyerField
   */
  public SommeAEnvoyerField getSommeAEnvoyerField() {
    return getFieldByClass(SommeAEnvoyerField.class);
  }

  /**
   * @return the VousPouvezPartagerField
   */
  public VousPouvezPartagerField getVousPouvezPartagerField() {
    return getFieldByClass(VousPouvezPartagerField.class);
  }

  @Order(1000.0)
  public class MainBox extends AbstractGroupBox {

    @Override
    protected int getConfiguredGridColumnCount() {
      return 1;
    }

    @Order(0.0)
    public class VousPouvezPartagerField extends AbstractBigDecimalField {

      @Override
      protected boolean getConfiguredEnabled() {
        return false;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("vousPouvezPartager");
      }

      @Override
      protected void execInitField() throws ProcessingException {
        setValue(new BigDecimal(getPartagerVersComptesParticuliersNr()));
        super.execInitField();
      }
    }

    @Order(1000.0)
    public class CompteParticulierField extends AbstractSmartField<Long> {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("compteOperation");
      }

      @Override
      protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
        return CompteParticulierLookupCall.class;
      }
    }

    @Order(2000.0)
    public class SommeAEnvoyerField extends AbstractBigDecimalField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("sommeAEnvoyer");
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
      IPartagerVersComptesParticuliersService service = SERVICES.getService(IPartagerVersComptesParticuliersService.class);
      PartagerVersComptesParticuliersFormData formData = new PartagerVersComptesParticuliersFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
      setEnabledPermission(new UpdatePartagerVersComptesParticuliersPermission());

    }

    @Override
    protected void execStore() throws ProcessingException {
      IPartagerVersComptesParticuliersService service = SERVICES.getService(IPartagerVersComptesParticuliersService.class);
      PartagerVersComptesParticuliersFormData formData = new PartagerVersComptesParticuliersFormData();
      exportFormData(formData);
      formData = service.store(formData);
    }
  }

  public class NewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      IPartagerVersComptesParticuliersService service = SERVICES.getService(IPartagerVersComptesParticuliersService.class);
      PartagerVersComptesParticuliersFormData formData = new PartagerVersComptesParticuliersFormData();
      exportFormData(formData);
      formData = service.prepareCreate(formData);
      importFormData(formData);

    }

    @Override
    protected void execStore() throws ProcessingException {
      IPartagerVersComptesParticuliersService service = SERVICES.getService(IPartagerVersComptesParticuliersService.class);
      PartagerVersComptesParticuliersFormData formData = new PartagerVersComptesParticuliersFormData();
      exportFormData(formData);
//      boolean encore = formData.getContinuerPartager().getValue();
      formData = service.create(formData);
//      if (encore) {
//        startNew();
//      }
    }
  }
}
