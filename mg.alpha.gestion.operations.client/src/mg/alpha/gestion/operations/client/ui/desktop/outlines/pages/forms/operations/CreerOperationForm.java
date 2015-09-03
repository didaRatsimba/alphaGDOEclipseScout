/**
 *
 */
package mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IValueField;
import org.eclipse.scout.rt.client.ui.form.fields.bigdecimalfield.AbstractBigDecimalField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.IGroupBoxBodyGrid;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.internal.HorizontalGroupBoxBodyGrid;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.client.ui.form.fields.radiobuttongroup.AbstractRadioButtonGroup;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.extension.client.ui.form.fields.button.AbstractExtensibleRadioButton;
import org.eclipse.scout.rt.shared.AbstractIcons;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.service.SERVICES;

import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.comptes.EditerCompteForm;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.CreerOperationForm.MainBox.CancelButton;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.CreerOperationForm.MainBox.InformationsObligatoiresGroupBox;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.CreerOperationForm.MainBox.InformationsObligatoiresGroupBox.CompteSelectionGroupBox;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.CreerOperationForm.MainBox.InformationsObligatoiresGroupBox.CompteSelectionGroupBox.ChoixCompteGroup;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.CreerOperationForm.MainBox.InformationsObligatoiresGroupBox.CompteSelectionGroupBox.ChoixCompteGroup.CompteExistantButton;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.CreerOperationForm.MainBox.InformationsObligatoiresGroupBox.CompteSelectionGroupBox.ChoixCompteGroup.CreerCompteButton;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.CreerOperationForm.MainBox.InformationsObligatoiresGroupBox.CompteSelectionGroupBox.CompteExistantField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.CreerOperationForm.MainBox.InformationsObligatoiresGroupBox.CompteSelectionGroupBox.CreerCompteGroupBox;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.CreerOperationForm.MainBox.InformationsObligatoiresGroupBox.CompteSelectionGroupBox.CreerCompteGroupBox.CompteCreeField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.CreerOperationForm.MainBox.InformationsObligatoiresGroupBox.CompteSelectionGroupBox.CreerCompteGroupBox.CreerNouveauCompteButton;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.CreerOperationForm.MainBox.InformationsObligatoiresGroupBox.CoursColumnHeaderField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.CreerOperationForm.MainBox.InformationsObligatoiresGroupBox.DateOperationField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.CreerOperationForm.MainBox.InformationsObligatoiresGroupBox.DeviseGroup;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.CreerOperationForm.MainBox.InformationsObligatoiresGroupBox.ListeVendreGroupBox;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.CreerOperationForm.MainBox.InformationsObligatoiresGroupBox.ListeVendreGroupBox.AfficherListeVendreButton;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.CreerOperationForm.MainBox.InformationsObligatoiresGroupBox.MontantOperationField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.CreerOperationForm.MainBox.InformationsObligatoiresGroupBox.TypeGroup;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.CreerOperationForm.MainBox.OkButton;
import mg.alpha.gestion.operations.shared.services.code.DeviseCodeType;
import mg.alpha.gestion.operations.shared.services.code.TypeLitteralCodeType;
import mg.alpha.gestion.operations.shared.services.lookup.compte.CompteLookupCall;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.comptes.EditerCompteFormData;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.CreerOperationFormData;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.ICreerOperationService;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.SelectionnerDonneesFormData;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.UpdateCreerOperationPermission;

/**
 * @author Dida
 */
@FormData(value = CreerOperationFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class CreerOperationForm extends AbstractForm {

  private CreerOperationFormData formData;
  private EditerCompteFormData creerCompteformData;
  private SelectionnerDonneesFormData selectionnerDonneesFormData;
  private Map<Long, BigDecimal> m_debitParOperationMap;
  private Long m_idFromTimestamp;
  private String m_compteLabel;
  private Long m_compteId;
  private String m_comptesPartages;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public CreerOperationForm() throws ProcessingException {
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
    return TEXTS.get("creerOperatoin");
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
   * @return the AfficherListeVendreButton
   */
  public AfficherListeVendreButton getAfficherListeVendreButton() {
    return getFieldByClass(AfficherListeVendreButton.class);
  }

  /**
   * @return the CancelButton
   */
  public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }

  /**
   * @return the ChoixCompteGroup
   */
  public ChoixCompteGroup getChoixCompteGroup() {
    return getFieldByClass(ChoixCompteGroup.class);
  }

  /**
   * @return the CompteCreeField
   */
  public CompteCreeField getCompteCreeField() {
    return getFieldByClass(CompteCreeField.class);
  }

  /**
   * @return the CompteExistantButton
   */
  public CompteExistantButton getCompteExistantButton() {
    return getFieldByClass(CompteExistantButton.class);
  }

  /**
   * @return the CompteExistantField
   */
  public CompteExistantField getCompteExistantField() {
    return getFieldByClass(CompteExistantField.class);
  }

  /**
   * @return the CompteSelectionGroupBox
   */
  public CompteSelectionGroupBox getCompteSelectionGroupBox() {
    return getFieldByClass(CompteSelectionGroupBox.class);
  }

  /**
   * @return the CoursColumnHeaderField
   */
  public CoursColumnHeaderField getCoursColumnHeaderField() {
    return getFieldByClass(CoursColumnHeaderField.class);
  }

  /**
   * @return the CreerCompteButton
   */
  public CreerCompteButton getCreerCompteButton() {
    return getFieldByClass(CreerCompteButton.class);
  }

  /**
   * @return the CreerCompteGroupBox
   */
  public CreerCompteGroupBox getCreerCompteGroupBox() {
    return getFieldByClass(CreerCompteGroupBox.class);
  }

  /**
   * @return the CreerNouveauCompteButton
   */
  public CreerNouveauCompteButton getCreerNouveauCompteButton() {
    return getFieldByClass(CreerNouveauCompteButton.class);
  }

  /**
   * @return the DateOperationField
   */
  public DateOperationField getDateOperationField() {
    return getFieldByClass(DateOperationField.class);
  }

  /**
   * @return the DeviseGroup
   */
  public DeviseGroup getDeviseGroup() {
    return getFieldByClass(DeviseGroup.class);
  }

  /**
   * @return the InformationsObligatoiresGroupBox
   */
  public InformationsObligatoiresGroupBox getInformationsObligatoiresGroupBox() {
    return getFieldByClass(InformationsObligatoiresGroupBox.class);
  }

  /**
   * @return the ListeVendreGroupBox
   */
  public ListeVendreGroupBox getListeVendreGroupBox() {
    return getFieldByClass(ListeVendreGroupBox.class);
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
   * @return the OkButton
   */
  public OkButton getOkButton() {
    return getFieldByClass(OkButton.class);
  }

  /**
   * @return the TypeGroup
   */
  public TypeGroup getTypeGroup() {
    return getFieldByClass(TypeGroup.class);
  }

  @Order(1000.0)
  public class MainBox extends AbstractGroupBox {

    @Override
    protected Class<? extends IGroupBoxBodyGrid> getConfiguredBodyGrid() {
      return HorizontalGroupBoxBodyGrid.class;
    }

    @Override
    protected int getConfiguredGridColumnCount() {
      return 1;
    }

    @Order(2000.0)
    public class InformationsObligatoiresGroupBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("informationsObligatoires");
      }

      @Order(1000.0)
      public class DateOperationField extends AbstractDateField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("dateOperation");
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(new GregorianCalendar().getTime());
        }

        @Override
        protected Date execValidateValue(Date rawValue) throws ProcessingException {
          activerBoutonOk();
          return super.execValidateValue(rawValue);
        }
      }

      @Order(2000.0)
      public class TypeGroup extends AbstractRadioButtonGroup<String> {

        @Override
        protected Class<? extends ICodeType<?, String>> getConfiguredCodeType() {
          return TypeLitteralCodeType.class;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("type");
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          activerBoutonRechercheAchats();
          activerBoutonOk();
        }

        @Override
        protected void execInitField() throws ProcessingException {
          super.execInitField();
          setValue("A");
        }
      }

      @Order(2500.0)
      public class CompteSelectionGroupBox extends AbstractGroupBox {

        @Override
        protected String getConfiguredBorderDecoration() {
          return BORDER_DECORATION_SECTION;
        }

        @Order(1000.0)
        public class ChoixCompteGroup extends AbstractRadioButtonGroup<String> {

          @Override
          protected int getConfiguredGridW() {
            return 2;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("choixCompte");
          }

          @Override
          protected void execChangedValue() throws ProcessingException {
            activerBoutonOk();
            super.execChangedValue();
          }

          @Override
          protected void execInitField() throws ProcessingException {
            setValue("EXISTANT");
          }

          @Order(0.0)
          public class CreerCompteButton extends AbstractExtensibleRadioButton<String> {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("creer");
            }

            @Override
            protected String getConfiguredRadioValue() {
              return "CREER";
            }
          }

          @Order(1000.0)
          public class CompteExistantButton extends AbstractExtensibleRadioButton<String> {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("rechercher");
            }

            @Override
            protected String getConfiguredRadioValue() {
              return "EXISTANT";
            }
          }
        }

        @Order(1250.0)
        public class CreerCompteGroupBox extends AbstractGroupBox {

          @Override
          protected boolean getConfiguredBorderVisible() {
            return false;
          }

          @Override
          protected int getConfiguredGridColumnCount() {
            return 2;
          }

          @Order(1000.0)
          public class CompteCreeField extends AbstractLabelField {

            @Override
            protected boolean getConfiguredEnabled() {
              return false;
            }

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("compteCree");
            }

            @Override
            protected boolean getConfiguredVisible() {
              return false;
            }
          }

          @Order(2000.0)
          public class CreerNouveauCompteButton extends AbstractButton {

            @Override
            protected boolean getConfiguredEnabled() {
              return false;
            }

            @Override
            protected boolean getConfiguredFillHorizontal() {
              return false;
            }

            @Override
            protected int getConfiguredHorizontalAlignment() {
              return 0;
            }

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("creerNouveauCompte");
            }

            @Override
            protected Class<? extends IValueField> getConfiguredMasterField() {
              return ChoixCompteGroup.class;
            }

            @Override
            protected boolean getConfiguredMasterRequired() {
              return true;
            }

            @Override
            protected void execChangedMasterValue(Object newMasterValue) throws ProcessingException {
              if (newMasterValue instanceof String) {
                String newValue = (String) newMasterValue;
                setEnabled(newValue.equalsIgnoreCase("creer"));
              }
            }

            @Override
            protected void execClickAction() throws ProcessingException {
              EditerCompteForm editerCompteForm = new EditerCompteForm();
              creerCompteformData = new EditerCompteFormData();

              editerCompteForm.startNew();
              editerCompteForm.waitFor();
              getCompteCreeField().setValue(editerCompteForm.getLibelleCompteField().getValue());
              getCompteCreeField().setVisible(true);
              activerBoutonOk();
            }
          }
        }

        @Order(3000.0)
        public class CompteExistantField extends AbstractSmartField<Long> {

          @Override
          protected boolean getConfiguredEnabled() {
            return false;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("compteExistant");
          }

          @Override
          protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
            return CompteLookupCall.class;
          }

          @Override
          protected Class<? extends IValueField> getConfiguredMasterField() {
            return ChoixCompteGroup.class;
          }

          @Override
          protected boolean getConfiguredMasterRequired() {
            return true;
          }

          @Override
          protected void execChangedMasterValue(Object newMasterValue) throws ProcessingException {
            if (newMasterValue instanceof String) {
              String newValue = (String) newMasterValue;
              setEnabled(newValue.equalsIgnoreCase("existant"));
            }
          }

          @Override
          protected void execChangedValue() throws ProcessingException {
            setCompteId(getValue());
            activerBoutonOk();
            super.execChangedValue();
          }
        }
      }

      @Order(4000.0)
      public class MontantOperationField extends AbstractBigDecimalField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("montantOperation");
        }

        @Override
        protected BigDecimal getConfiguredMinValue() {
          return new BigDecimal("0");
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          activerBoutonRechercheAchats();
          activerBoutonOk();
          super.execChangedValue();
        }

        @Override
        protected BigDecimal execValidateValue(BigDecimal rawValue) throws ProcessingException {
          activerBoutonRechercheAchats();
          return super.execValidateValue(rawValue);
        }
      }

      @Order(5000.0)
      public class CoursColumnHeaderField extends AbstractBigDecimalField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("coursColumnHeader");
        }

        @Override
        protected BigDecimal getConfiguredMinValue() {
          return new BigDecimal("0");
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          activerBoutonRechercheAchats();
          activerBoutonOk();
        }

        @Override
        protected BigDecimal execValidateValue(BigDecimal rawValue) throws ProcessingException {
          activerBoutonRechercheAchats();
          return super.execValidateValue(rawValue);
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

        @Override
        protected void execChangedValue() throws ProcessingException {
          activerBoutonOk();
          super.execChangedValue();
        }
      }

      @Order(6500.0)
      public class ListeVendreGroupBox extends AbstractGroupBox {

        @Override
        protected Class<? extends IGroupBoxBodyGrid> getConfiguredBodyGrid() {
          return HorizontalGroupBoxBodyGrid.class;
        }

        @Override
        protected String getConfiguredBorderDecoration() {
          return BORDER_DECORATION_EMPTY;
        }

        @Override
        protected boolean getConfiguredBorderVisible() {
          return false;
        }

        @Override
        protected int getConfiguredGridColumnCount() {
          return 2;
        }

        @Order(2000.0)
        public class AfficherListeVendreButton extends AbstractButton {

          @Override
          protected boolean getConfiguredEnabled() {
            return false;
          }

          @Override
          protected int getConfiguredGridH() {
            return 2;
          }

          @Override
          protected int getConfiguredHorizontalAlignment() {
            return 1;
          }

          @Override
          protected String getConfiguredIconId() {
            return AbstractIcons.SmartFieldBrowse;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("afficherListeVendre");
          }

          @Override
          protected Class<? extends IValueField> getConfiguredMasterField() {
            return CreerOperationForm.MainBox.InformationsObligatoiresGroupBox.TypeGroup.class;
          }

          @Override
          protected boolean getConfiguredMasterRequired() {
            return true;
          }

          @Override
          protected void execChangedMasterValue(Object newMasterValue) throws ProcessingException {
            activerBoutonRechercheAchats();
          }

          @Override
          protected void execClickAction() throws ProcessingException {
            SelectionnerDonneesForm selectionnerDonnesForm = new SelectionnerDonneesForm();
            selectionnerDonneesFormData = new SelectionnerDonneesFormData();
            selectionnerDonneesFormData.setSommeAVendre(getMontantOperationField().getValue());
            selectionnerDonneesFormData.setCoursChange(getCoursColumnHeaderField().getValue());
            if (getIdFromTimestamp() == null) {
              setIdFromTimestamp(new GregorianCalendar().getTimeInMillis());
            }
            selectionnerDonneesFormData.setSelectionnerDonneesNr(getIdFromTimestamp());
            selectionnerDonnesForm.importFormData(selectionnerDonneesFormData);
            selectionnerDonnesForm.startNew();
            selectionnerDonnesForm.waitFor();

          }
        }
      }

      /**
       *
       */
      public void activerBoutonRechercheAchats() {
        getAfficherListeVendreButton().setEnabled(getMontantOperationField() != null && getMontantOperationField().getValue() != null //
        && getCoursColumnHeaderField() != null && getCoursColumnHeaderField().getValue() != null //
        && getTypeGroup().getValue().equalsIgnoreCase("v"));
      }

      public void activerBoutonOk() {
        getOkButton().setEnabled(getMontantOperationField() != null && getMontantOperationField().getValue() != null && getMontantOperationField().getValue().signum() == 1//
        && getCoursColumnHeaderField() != null && getCoursColumnHeaderField().getValue() != null && getCoursColumnHeaderField().getValue().signum() == 1//
        && getCompteId().longValue() > 0 && !getDateOperationField().isEmpty());
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
      ICreerOperationService service = SERVICES.getService(ICreerOperationService.class);
      formData = new CreerOperationFormData();
      exportFormData(formData);
      formData = service.load(formData);
      setEnabledPermission(new UpdateCreerOperationPermission());

    }

    @Override
    protected void execStore() throws ProcessingException {
      ICreerOperationService service = SERVICES.getService(ICreerOperationService.class);
      formData = new CreerOperationFormData();
      exportFormData(formData);
      formData = service.store(formData);

    }
  }

  public class NewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      ICreerOperationService service = SERVICES.getService(ICreerOperationService.class);
      formData = new CreerOperationFormData();
      exportFormData(formData);
      formData = service.prepareCreate(formData);
      importFormData(formData);

    }

    @Override
    protected void execStore() throws ProcessingException {
      ICreerOperationService service = SERVICES.getService(ICreerOperationService.class);
      formData = new CreerOperationFormData();
      exportFormData(formData);
      try {
        formData = service.create(formData);
      }
      catch (Exception e) {
        e.printStackTrace();
      }

    }
  }

  /**
   * @return the DebitParOperationMap
   */
  @FormData
  public Map<Long, BigDecimal> getDebitParOperationMap() {
    return m_debitParOperationMap;
  }

  /**
   * @param debitParOperationMap
   *          the DebitParOperationMap to set
   */
  @FormData
  public void setDebitParOperationMap(Map<Long, BigDecimal> debitParOperationMap) {
    m_debitParOperationMap = debitParOperationMap;
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

  /**
   * @return the CompteLabel
   */
  @FormData
  public String getCompteLabel() {
    return m_compteLabel;
  }

  /**
   * @param compteLabel
   *          the CompteLabel to set
   */
  @FormData
  public void setCompteLabel(String compteLabel) {
    m_compteLabel = compteLabel;
  }

  /**
   * @return the CompteId
   */
  @FormData
  public Long getCompteId() {
    return m_compteId;
  }

  /**
   * @param compteId
   *          the CompteId to set
   */
  @FormData
  public void setCompteId(Long compteId) {
    m_compteId = compteId;
  }

  /**
   * @return the ComptesPartages
   */
  @FormData
  public String getComptesPartages() {
    return m_comptesPartages;
  }

  /**
   * @param comptesPartages
   *          the ComptesPartages to set
   */
  @FormData
  public void setComptesPartages(String comptesPartages) {
    m_comptesPartages = comptesPartages;
  }
}
