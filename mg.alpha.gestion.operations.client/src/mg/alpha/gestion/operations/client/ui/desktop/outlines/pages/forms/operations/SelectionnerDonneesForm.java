/**
 *
 */
package mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBigDecimalColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IFormField;
import org.eclipse.scout.rt.client.ui.form.fields.bigdecimalfield.AbstractBigDecimalField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;

import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.SelectionnerDonneesForm.MainBox.CancelButton;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.SelectionnerDonneesForm.MainBox.ComptesParticuliersField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.SelectionnerDonneesForm.MainBox.DonneesFiltreesField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.SelectionnerDonneesForm.MainBox.OkButton;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.SelectionnerDonneesForm.MainBox.SommesGroupBox;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.SelectionnerDonneesForm.MainBox.SommesGroupBox.SommeACompleterField;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.SelectionnerDonneesForm.MainBox.SommesGroupBox.SommeManquanteField;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.ISelectionnerDonneesService;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.SelectionnerDonneesFormData;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.UpdateSelectionnerDonneesPermission;

/**
 * @author Dida
 */
@FormData(value = SelectionnerDonneesFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class SelectionnerDonneesForm extends AbstractForm {

  private Long m_selectionnerDonneesNr;
  private BigDecimal m_coursChange;
  private BigDecimal m_sommeAVendre;
  private Map<Long, BigDecimal> m_debitParOperationIdMap;
  private Map<Long, BigDecimal> m_sommeAPartagerParCompteIdMap;
  private Map<Long, BigDecimal> m_beneficeParAchatId;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public SelectionnerDonneesForm() throws ProcessingException {
    super();
  }

  /**
   * @return the SelectionnerDonneesNr
   */
  @FormData
  public Long getSelectionnerDonneesNr() {
    return m_selectionnerDonneesNr;
  }

  /**
   * @param selectionnerDonneesNr
   *          the SelectionnerDonneesNr to set
   */
  @FormData
  public void setSelectionnerDonneesNr(Long selectionnerDonneesNr) {
    m_selectionnerDonneesNr = selectionnerDonneesNr;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("selectionnerDonnees");
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
   * @return the ComptesParticuliersField
   */
  public ComptesParticuliersField getComptesParticuliersField() {
    return getFieldByClass(ComptesParticuliersField.class);
  }

  /**
   * @return the DonneesFiltreesField
   */
  public DonneesFiltreesField getDonneesFiltreesField() {
    return getFieldByClass(DonneesFiltreesField.class);
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
      return 2;
    }

    @Override
    protected int getConfiguredHeightInPixel() {
      return 500;
    }

    @Override
    protected int getConfiguredWidthInPixel() {
      return 700;
    }

    @Order(-1000.0)
    public class SommesGroupBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredBorderDecoration() {
        return BORDER_DECORATION_SECTION;
      }

      @Override
      protected int getConfiguredGridColumnCount() {
        return 2;
      }

      @Override
      protected int getConfiguredGridH() {
        return 2;
      }

      @Order(1000.0)
      public class SommeACompleterField extends AbstractBigDecimalField {

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("sommeACompleter");
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(getSommeAVendre());
          super.execInitField();
        }
      }

      @Order(2000.0)
      public class SommeManquanteField extends AbstractBigDecimalField {

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("sommeManquante");
        }
      }
    }

    @Order(1000.0)
    public class DonneesFiltreesField extends AbstractTableField<DonneesFiltreesField.Table> {

      @Override
      protected boolean getConfiguredFillVertical() {
        return false;
      }

      @Override
      protected int getConfiguredGridH() {
        return 15;
      }

      @Override
      protected int getConfiguredGridW() {
        return 2;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("donneesFiltrees");
      }

      @Override
      protected int getConfiguredLabelPosition() {
        return LABEL_POSITION_TOP;
      }

      @Order(1000.0)
      public class Table extends AbstractExtensibleTable {

        /**
         * @return the ComptesColumn
         */
        public ComptesColumn getComptesColumn() {
          return getColumnSet().getColumnByClass(ComptesColumn.class);
        }

        /**
         * @return the MontantDisponibleColumn
         */
        public MontantDisponibleColumn getMontantDisponibleColumn() {
          return getColumnSet().getColumnByClass(MontantDisponibleColumn.class);
        }

        /**
         * @return the MontantADebiterColumn
         */
        public MontantADebiterColumn getMontantADebiterColumn() {
          return getColumnSet().getColumnByClass(MontantADebiterColumn.class);
        }

        /**
         * @return the BeneficeColumn
         */
        public BeneficeColumn getBeneficeColumn() {
          return getColumnSet().getColumnByClass(BeneficeColumn.class);
        }

        /**
         * @return the ValeurAchatColumn
         */
        public ValeurAchatColumn getValeurAchatColumn() {
          return getColumnSet().getColumnByClass(SelectionnerDonneesForm.MainBox.DonneesFiltreesField.Table.ValeurAchatColumn.class);
        }

        /**
         * @return the AchatIdColumn
         */
        public AchatIdColumn getAchatIdColumn() {
          return getColumnSet().getColumnByClass(AchatIdColumn.class);
        }

        @Order(1000.0)
        public class AchatIdColumn extends AbstractLongColumn {

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
        public class ComptesColumn extends AbstractStringColumn {

          @Override
          protected String getConfiguredHeaderText() {
            return TEXTS.get("comptes");
          }
        }

        @Order(2500.0)
        public class ValeurAchatColumn extends AbstractBigDecimalColumn {

          @Override
          protected String getConfiguredHeaderText() {
            return TEXTS.get("valeurAchat");
          }
        }

        @Order(3000.0)
        public class MontantDisponibleColumn extends AbstractBigDecimalColumn {

          @Override
          protected String getConfiguredHeaderText() {
            return TEXTS.get("montantDisponible");
          }
        }

        @Order(4000.0)
        public class MontantADebiterColumn extends AbstractBigDecimalColumn {

          @Override
          protected boolean getConfiguredEditable() {
            return true;
          }

          @Override
          protected String getConfiguredHeaderText() {
            return TEXTS.get("montantADebiter");
          }

          @Override
          protected void execCompleteEdit(ITableRow row, IFormField editingField) throws ProcessingException {
            super.execCompleteEdit(row, editingField);
            long sommeDebit = 0L;
            for (BigDecimal eachValue : this.getValues()) {
              if (eachValue != null) {
                sommeDebit += eachValue.longValue();
              }
            }
            if (sommeDebit == getSommeAVendre().longValue()) {
//              SelectionnerDonneesForm.this.getSommeACompleterField().setBackgroundColor("00FF00");
//              SelectionnerDonneesForm.this.getSommeACompleterField().setLabelForegroundColor("000000");
              getOkButton().setEnabled(true);
            }
            else {
//              SelectionnerDonneesForm.this.getSommeACompleterField().setBackgroundColor("FF0000");
              if (sommeDebit < getSommeAVendre().longValue()) {
                getSommeManquanteField().setValue(BigDecimal.valueOf(getSommeAVendre().longValue() - sommeDebit));
              }
              getOkButton().setEnabled(false);
            }
          }

          @Override
          protected BigDecimal execValidateValue(ITableRow row, BigDecimal rawValue) throws ProcessingException {
            if (getDebitParOperationIdMap() == null) {
              setDebitParOperationIdMap(new HashMap<>());
            }
            if (getBeneficeParAchatId() == null) {
              setBeneficeParAchatId(new HashMap<>());
            }
            if (rawValue == null) {
              setBackgroundColor("FFFFFF");
            }
            else {
              if (rawValue.longValue() <= getMontantDisponibleColumn().getValue(row).longValue()) {
                getDebitParOperationIdMap().put(getAchatIdColumn().getValue(row), BigDecimal.valueOf(rawValue.longValue()));
                setBackgroundColor("FFFFFF");
                BigDecimal benef = BigDecimal.valueOf(calculerBenefices(getValeurAchatColumn().getValue(row).longValue(), //
                getCoursChange().longValue(), rawValue.longValue()));
                getBeneficeColumn().setValue(row, benef);
                getBeneficeParAchatId().put(getAchatIdColumn().getValue(row), benef);
              }
              else {
                setBackgroundColor("FF0000");
              }
            }
            return super.execValidateValue(row, rawValue);
          }

        }

        @Order(5000.0)
        public class BeneficeColumn extends AbstractBigDecimalColumn {

          @Override
          protected String getConfiguredHeaderText() {
            return TEXTS.get("benefice");
          }
        }
      }
    }

    @Order(2000.0)
    public class ComptesParticuliersField extends AbstractTableField<ComptesParticuliersField.Table> {

      @Override
      protected boolean getConfiguredFillVertical() {
        return false;
      }

      @Override
      protected int getConfiguredGridH() {
        return 15;
      }

      @Override
      protected int getConfiguredGridW() {
        return 2;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("comptesParticuliers");
      }

      @Override
      protected int getConfiguredLabelPosition() {
        return LABEL_POSITION_TOP;
      }

      @Order(1000.0)
      public class Table extends AbstractExtensibleTable {

        /**
         * @return the SommeAEnvoyerColumn
         */
        public SommeAEnvoyerColumn getSommeAEnvoyerColumn() {
          return getColumnSet().getColumnByClass(SommeAEnvoyerColumn.class);
        }

        /**
         * @return the CompteParticulierIdColumn
         */
        public CompteParticulierIdColumn getCompteParticulierIdColumn() {
          return getColumnSet().getColumnByClass(CompteParticulierIdColumn.class);
        }

        /**
         * @return the CompteParticulierColumn
         */
        public CompteParticulierColumn getCompteParticulierColumn() {
          return getColumnSet().getColumnByClass(CompteParticulierColumn.class);
        }

        @Order(0.0)
        public class CompteParticulierIdColumn extends AbstractLongColumn {

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

        @Order(1000.0)
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

        @Order(2000.0)
        public class SommeAEnvoyerColumn extends AbstractBigDecimalColumn {

          @Override
          protected boolean getConfiguredEditable() {
            return true;
          }

          @Override
          protected String getConfiguredHeaderText() {
            return TEXTS.get("sommeAEnvoyer");
          }

          @Override
          protected int getConfiguredWidth() {
            return 100;
          }

          @Override
          protected BigDecimal execValidateValue(ITableRow row, BigDecimal rawValue) throws ProcessingException {
            if (getSommeAPartagerParCompteIdMap() == null) {
              setSommeAPartagerParCompteIdMap(new HashMap<Long, BigDecimal>());
            }
            if (rawValue == null) {
              setBackgroundColor("FFFFFF");
            }
            else {
              if (rawValue.longValue() <= getSommeAVendre().longValue()) {
                getSommeAPartagerParCompteIdMap().put(getCompteParticulierIdColumn().getValue(row), BigDecimal.valueOf(rawValue.longValue()));
                setBackgroundColor("FFFFFF");
              }
              else {
                setBackgroundColor("FF0000");
              }
            }
            return super.execValidateValue(row, rawValue);
          }
        }
      }
    }

    @Order(100000.0)
    public class OkButton extends AbstractOkButton {

      @Override
      protected boolean getConfiguredEnabled() {
        return false;
      }
    }

    @Order(101000.0)
    public class CancelButton extends AbstractCancelButton {
    }
  }

  public class ModifyHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      ISelectionnerDonneesService service = SERVICES.getService(ISelectionnerDonneesService.class);
      SelectionnerDonneesFormData formData = new SelectionnerDonneesFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
      setEnabledPermission(new UpdateSelectionnerDonneesPermission());

    }

    @Override
    protected void execStore() throws ProcessingException {
      ISelectionnerDonneesService service = SERVICES.getService(ISelectionnerDonneesService.class);
      SelectionnerDonneesFormData formData = new SelectionnerDonneesFormData();
      exportFormData(formData);
      formData = service.store(formData);

    }
  }

  public class NewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      ISelectionnerDonneesService service = SERVICES.getService(ISelectionnerDonneesService.class);
      SelectionnerDonneesFormData formData = new SelectionnerDonneesFormData();
      exportFormData(formData);
      formData = service.prepareCreate(formData);
      importFormData(formData);

    }

    @Override
    protected void execStore() throws ProcessingException {
      ISelectionnerDonneesService service = SERVICES.getService(ISelectionnerDonneesService.class);
      SelectionnerDonneesFormData formData = new SelectionnerDonneesFormData();
      exportFormData(formData);
      formData = service.create(formData);
      CreerOperationForm creerOperationForm = null;
      if ((creerOperationForm = getDesktop().findForm(CreerOperationForm.class)) != null) {
        if (creerOperationForm.isFormOpen() && !formData.getSommeAPartagerParCompteIdMap().isEmpty()) {
          creerOperationForm.setComptesPartages(String.valueOf(formData.getSommeAPartagerParCompteIdMap().size()));
        }
        else {
          creerOperationForm.setComptesPartages(null);
        }
      }

    }
  }

  /**
   * @return the CoursChange
   */
  @FormData
  public BigDecimal getCoursChange() {
    return m_coursChange;
  }

  /**
   * @param coursChange
   *          the CoursChange to set
   */
  @FormData
  public void setCoursChange(BigDecimal coursChange) {
    m_coursChange = coursChange;
  }

  /**
   * @return the SommeAVendre
   */
  @FormData
  public BigDecimal getSommeAVendre() {
    return m_sommeAVendre;
  }

  /**
   * @param sommeAVendre
   *          the SommeAVendre to set
   */
  @FormData
  public void setSommeAVendre(BigDecimal sommeAVendre) {
    m_sommeAVendre = sommeAVendre;
  }

  /**
   * @return the DebitParOperationIdMap
   */
  @FormData
  public Map<Long, BigDecimal> getDebitParOperationIdMap() {
    return m_debitParOperationIdMap;
  }

  /**
   * @param debitParOperationIdMap
   *          the DebitParOperationIdMap to set
   */
  @FormData
  public void setDebitParOperationIdMap(Map<Long, BigDecimal> debitParOperationIdMap) {
    m_debitParOperationIdMap = debitParOperationIdMap;
  }

  /**
   * @return the SommeACompleterField
   */
  public SommeACompleterField getSommeACompleterField() {
    return getFieldByClass(SommeACompleterField.class);
  }

  /**
   * @return the SommeManquanteField
   */
  public SommeManquanteField getSommeManquanteField() {
    return getFieldByClass(SommeManquanteField.class);
  }

  /**
   * @return the SommesGroupBox
   */
  public SommesGroupBox getSommesGroupBox() {
    return getFieldByClass(SommesGroupBox.class);
  }

  private Long calculerBenefices(Long prixAchat, Long prixVente, Long qteVente) {
    Long benef = (prixVente - prixAchat) * qteVente;
    return benef;
  }

  /**
   * @return the SommeAPartagerParCompteIdMap
   */
  @FormData
  public Map<Long, BigDecimal> getSommeAPartagerParCompteIdMap() {
    return m_sommeAPartagerParCompteIdMap;
  }

  /**
   * @param sommeAPartagerParCompteIdMap
   *          the SommeAPartagerParCompteIdMap to set
   */
  @FormData
  public void setSommeAPartagerParCompteIdMap(Map<Long, BigDecimal> sommeAPartagerParCompteIdMap) {
    m_sommeAPartagerParCompteIdMap = sommeAPartagerParCompteIdMap;
  }

  /**
   * @return the BeneficeParAchatId
   */
  @FormData
  public Map<Long, BigDecimal> getBeneficeParAchatId() {
    return m_beneficeParAchatId;
  }

  /**
   * @param beneficeParAchatId
   *          the BeneficeParAchatId to set
   */
  @FormData
  public void setBeneficeParAchatId(Map<Long, BigDecimal> beneficeParAchatId) {
    m_beneficeParAchatId = beneficeParAchatId;
  }
}
