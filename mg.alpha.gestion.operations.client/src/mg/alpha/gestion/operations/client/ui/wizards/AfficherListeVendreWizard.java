/**
 *
 */
package mg.alpha.gestion.operations.client.ui.wizards;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Map;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.wizard.AbstractWizard;
import org.eclipse.scout.rt.client.ui.wizard.AbstractWizardStep;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;

import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.SelectionnerDonneesForm;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.ICreerOperationService;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.SelectionnerDonneesFormData;

/**
 * @author Dida
 */
public class AfficherListeVendreWizard extends AbstractWizard {

  private BigDecimal m_coursChangeValue;
  private BigDecimal m_sommeAVendre;
  private Map<Long, BigDecimal> m_debitParOpIdMapResult;
  private Long m_idFromTimestamp;

  /**
   *
   */
  public AfficherListeVendreWizard() {
    super();
  }

  @Override
  protected boolean getConfiguredModal() {
    return true;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("afficherListeVendre");
  }

  @Override
  protected void execFinish() throws ProcessingException {
    Map<Long, BigDecimal> result = null;
    SelectionnerDonneesFirstStep step = null;
    if (getDebitParOpIdMapResult() != null && !getDebitParOpIdMapResult().isEmpty()) {
      result = getDebitParOpIdMapResult();
    }
    else {
      step = (SelectionnerDonneesFirstStep) getStepByClassName(SelectionnerDonneesFirstStep.class.getName());
      result = step.getForm().getDebitParOperationIdMap();
    }
    if (result != null) {
      try {
        SERVICES.getService(ICreerOperationService.class).createDebitParOperation(result, getIdFromTimestamp());
      }
      catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    }
    super.execFinish();
  }

  /**
   * @return the SelectionnerDonneesFirstStep
   */
  public SelectionnerDonneesFirstStep getSelectionnerDonneesFirstStep() {
    return getStep(AfficherListeVendreWizard.SelectionnerDonneesFirstStep.class);
  }

  @Order(1000.0)
  public class SelectionnerDonneesFirstStep extends AbstractWizardStep<SelectionnerDonneesForm> {

    @Override
    protected String getConfiguredTitle() {
      return TEXTS.get("selectionnerDonnees");
    }

    @Override
    protected void execActivate(int stepKind) throws ProcessingException {
      SelectionnerDonneesForm form = getForm();
      if (form == null) {
        form = new SelectionnerDonneesForm();
        form.setCoursChange(getCoursChangeValue());
        SelectionnerDonneesFormData formData = new SelectionnerDonneesFormData();
        formData.setCoursChange(getCoursChangeValue());
        formData.setSommeAVendre(getSommeAVendre());
        formData.setDebitParOperationIdMap(getDebitParOpIdMapResult());
        form.importFormData(formData);
        // start the form by executing the form handler
        form.startWizardStep(this, SelectionnerDonneesForm.ModifyHandler.class);
        // Register the form on the step
        setForm(form);
      }
      // Set the form on the wizard
      // This will automatically display it as inner form of the wizard container form
      getWizard().setWizardForm(form);
    }

    @Override
    protected void execDeactivate(int stepKind) throws ProcessingException {
      // Save the form if the user clicks next
      if (stepKind == STEP_NEXT) {
        SelectionnerDonneesForm form = getForm();
        if (form != null) {
          form.doSave();
        }
      }
    }
  }

  /**
   * @return the CoursChangeValue
   */
  @FormData
  public BigDecimal getCoursChangeValue() {
    return m_coursChangeValue;
  }

  /**
   * @param coursChangeValue
   *          the CoursChangeValue to set
   */
  @FormData
  public void setCoursChangeValue(BigDecimal coursChangeValue) {
    m_coursChangeValue = coursChangeValue;
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
   * @return the DebitParOpIdMapResult
   */
  @FormData
  public Map<Long, BigDecimal> getDebitParOpIdMapResult() {
    return m_debitParOpIdMapResult;
  }

  /**
   * @param debitParOpIdMapResult
   *          the DebitParOpIdMapResult to set
   */
  @FormData
  public void setDebitParOpIdMapResult(Map<Long, BigDecimal> debitParOpIdMapResult) {
    m_debitParOpIdMapResult = debitParOpIdMapResult;
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
