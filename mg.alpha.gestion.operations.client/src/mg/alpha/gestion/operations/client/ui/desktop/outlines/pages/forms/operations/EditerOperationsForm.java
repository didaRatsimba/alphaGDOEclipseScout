/**
 *
 */
package mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.bigdecimalfield.AbstractBigDecimalField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.IGroupBoxBodyGrid;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.internal.VerticalSmartGroupBoxBodyGrid;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.client.ui.form.fields.radiobuttongroup.AbstractRadioButtonGroup;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.service.SERVICES;

import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.EditerOperationsForm.MainBox.CancelButton;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.EditerOperationsForm.MainBox.EditerOperationGroupBox;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.EditerOperationsForm.MainBox.EditerOperationGroupBox.CompteOperationField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.EditerOperationsForm.MainBox.EditerOperationGroupBox.CoursAppliqueField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.EditerOperationsForm.MainBox.EditerOperationGroupBox.DateOperationField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.EditerOperationsForm.MainBox.EditerOperationGroupBox.DesignationField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.EditerOperationsForm.MainBox.EditerOperationGroupBox.DeviseGroup;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.EditerOperationsForm.MainBox.EditerOperationGroupBox.TypeAllLitteralGroup;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.EditerOperationsForm.MainBox.OkButton;
import mg.alpha.gestion.operations.shared.services.code.DeviseCodeType;
import mg.alpha.gestion.operations.shared.services.code.TypeLitteralCodeType;
import mg.alpha.gestion.operations.shared.services.lookup.compte.CompteLookupCall;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.EditerOperationsFormData;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.IEditerOperationsService;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.UpdateEditerOperationsPermission;

/**
 * @author Dida
 */
@FormData(value = EditerOperationsFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class EditerOperationsForm extends AbstractForm {

  private Long m_editerOperationsNr;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public EditerOperationsForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredDisplayViewId() {
    return VIEW_ID_CENTER;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("editerOperations");
  }

  /**
   * @return the EditerOperationsNr
   */
  @FormData
  public Long getEditerOperationsNr() {
    return m_editerOperationsNr;
  }

  /**
   * @param editerOperationsNr
   *          the EditerOperationsNr to set
   */
  @FormData
  public void setEditerOperationsNr(Long editerOperationsNr) {
    m_editerOperationsNr = editerOperationsNr;
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
   * @return the CompteOperationField
   */
  public CompteOperationField getCompteOperationField() {
    return getFieldByClass(CompteOperationField.class);
  }

  /**
   * @return the CoursAppliqueField
   */
  public CoursAppliqueField getCoursAppliqueField() {
    return getFieldByClass(CoursAppliqueField.class);
  }

  /**
   * @return the DateOperationField
   */
  public DateOperationField getDateOperationField() {
    return getFieldByClass(DateOperationField.class);
  }

  /**
   * @return the DesignationField
   */
  public DesignationField getDesignationField() {
    return getFieldByClass(DesignationField.class);
  }

  /**
   * @return the DeviseGroup
   */
  public DeviseGroup getDeviseGroup() {
    return getFieldByClass(DeviseGroup.class);
  }

  /**
   * @return the EditerOperationGroupBox
   */
  public EditerOperationGroupBox getEditerOperationGroupBox() {
    return getFieldByClass(EditerOperationGroupBox.class);
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
   * @return the TypeAllLitteralGroup
   */
  public TypeAllLitteralGroup getTypeAllLitteralGroup() {
    return getFieldByClass(TypeAllLitteralGroup.class);
  }

  @Order(1000.0)
  public class MainBox extends AbstractGroupBox {

    @Override
    protected int getConfiguredGridColumnCount() {
      return 1;
    }

    @Override
    protected boolean getConfiguredMandatory() {
      return true;
    }

    @Order(0.0)
    public class EditerOperationGroupBox extends AbstractGroupBox {

      @Override
      protected Class<? extends IGroupBoxBodyGrid> getConfiguredBodyGrid() {
        return VerticalSmartGroupBoxBodyGrid.class;
      }

      @Override
      protected int getConfiguredGridW() {
        return 1;
      }

      @Order(1000.0)
      public class DateOperationField extends AbstractLabelField {

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("dateOperation");
        }

        @Override
        protected boolean getConfiguredSelectable() {
          return false;
        }
      }

      @Order(2000.0)
      public class TypeAllLitteralGroup extends AbstractRadioButtonGroup<String> {

        @Override
        protected Class<? extends ICodeType<?, String>> getConfiguredCodeType() {
          return TypeLitteralCodeType.class;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("type");
        }
      }

      @Order(3000.0)
      public class CompteOperationField extends AbstractSmartField<Long> {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("compteOperation");
        }

        @Override
        protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
          return CompteLookupCall.class;
        }
      }

      @Order(6000.0)
      public class DeviseGroup extends AbstractRadioButtonGroup<String> {

        @Override
        protected Class<? extends ICodeType<?, String>> getConfiguredCodeType() {
          return DeviseCodeType.class;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("devise");
        }
      }

      @Order(6500.0)
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
          return TEXTS.get("montantOperation");
        }
      }

      @Order(8000.0)
      public class CoursAppliqueField extends AbstractBigDecimalField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("coursApplique");
        }
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
      IEditerOperationsService service = SERVICES.getService(IEditerOperationsService.class);
      EditerOperationsFormData formData = new EditerOperationsFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
      setEnabledPermission(new UpdateEditerOperationsPermission());

    }

    @Override
    protected void execStore() throws ProcessingException {
      IEditerOperationsService service = SERVICES.getService(IEditerOperationsService.class);
      EditerOperationsFormData formData = new EditerOperationsFormData();
      exportFormData(formData);
      formData = service.store(formData);

    }
  }

  public class NewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      IEditerOperationsService service = SERVICES.getService(IEditerOperationsService.class);
      EditerOperationsFormData formData = new EditerOperationsFormData();
      exportFormData(formData);
      formData = service.prepareCreate(formData);
      importFormData(formData);

    }

    @Override
    protected void execStore() throws ProcessingException {
      IEditerOperationsService service = SERVICES.getService(IEditerOperationsService.class);
      EditerOperationsFormData formData = new EditerOperationsFormData();
      exportFormData(formData);
      formData = service.create(formData);

    }
  }
}
