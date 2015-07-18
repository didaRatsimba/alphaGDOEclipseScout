/**
 * 
 */
package mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.operations;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.basic.table.AbstractTableRowData;
import org.eclipse.scout.rt.shared.data.page.AbstractTablePageData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 * 
 * @generated
 */
@Generated(value = "org.eclipse.scout.sdk.workspace.dto.pagedata.PageDataDtoUpdateOperation", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class OperationsTablePageData extends AbstractTablePageData {

  private static final long serialVersionUID = 1L;

  public OperationsTablePageData() {
  }

  @Override
  public OperationsTableRowData addRow() {
    return (OperationsTableRowData) super.addRow();
  }

  @Override
  public OperationsTableRowData addRow(int rowState) {
    return (OperationsTableRowData) super.addRow(rowState);
  }

  @Override
  public OperationsTableRowData createRow() {
    return new OperationsTableRowData();
  }

  @Override
  public Class<? extends AbstractTableRowData> getRowType() {
    return OperationsTableRowData.class;
  }

  @Override
  public OperationsTableRowData[] getRows() {
    return (OperationsTableRowData[]) super.getRows();
  }

  @Override
  public OperationsTableRowData rowAt(int index) {
    return (OperationsTableRowData) super.rowAt(index);
  }

  public void setRows(OperationsTableRowData[] rows) {
    super.setRows(rows);
  }

  public static class OperationsTableRowData extends AbstractTableRowData {

    private static final long serialVersionUID = 1L;
    public static final String opId = "opId";
    public static final String timestamp = "timestamp";
    public static final String dateOperation = "dateOperation";
    public static final String typeOperation = "typeOperation";
    public static final String compteOperation = "compteOperation";
    public static final String compteParticulier = "compteParticulier";
    public static final String devise = "devise";
    public static final String designation = "designation";
    public static final String montantOperation = "montantOperation";
    public static final String coursApplique = "coursApplique";
    private Long m_opId;
    private Long m_timestamp;
    private Date m_dateOperation;
    private String m_typeOperation;
    private String m_compteOperation;
    private String m_compteParticulier;
    private String m_devise;
    private String m_designation;
    private BigDecimal m_montantOperation;
    private BigDecimal m_coursApplique;

    public OperationsTableRowData() {
    }

    /**
     * @return the OpId
     */
    public Long getOpId() {
      return m_opId;
    }

    /**
     * @param opId
     *          the OpId to set
     */
    public void setOpId(Long opId) {
      m_opId = opId;
    }

    /**
     * @return the Timestamp
     */
    public Long getTimestamp() {
      return m_timestamp;
    }

    /**
     * @param timestamp
     *          the Timestamp to set
     */
    public void setTimestamp(Long timestamp) {
      m_timestamp = timestamp;
    }

    /**
     * @return the DateOperation
     */
    public Date getDateOperation() {
      return m_dateOperation;
    }

    /**
     * @param dateOperation
     *          the DateOperation to set
     */
    public void setDateOperation(Date dateOperation) {
      m_dateOperation = dateOperation;
    }

    /**
     * @return the TypeOperation
     */
    public String getTypeOperation() {
      return m_typeOperation;
    }

    /**
     * @param typeOperation
     *          the TypeOperation to set
     */
    public void setTypeOperation(String typeOperation) {
      m_typeOperation = typeOperation;
    }

    /**
     * @return the CompteOperation
     */
    public String getCompteOperation() {
      return m_compteOperation;
    }

    /**
     * @param compteOperation
     *          the CompteOperation to set
     */
    public void setCompteOperation(String compteOperation) {
      m_compteOperation = compteOperation;
    }

    /**
     * @return the CompteParticulier
     */
    public String getCompteParticulier() {
      return m_compteParticulier;
    }

    /**
     * @param compteParticulier
     *          the CompteParticulier to set
     */
    public void setCompteParticulier(String compteParticulier) {
      m_compteParticulier = compteParticulier;
    }

    /**
     * @return the Devise
     */
    public String getDevise() {
      return m_devise;
    }

    /**
     * @param devise
     *          the Devise to set
     */
    public void setDevise(String devise) {
      m_devise = devise;
    }

    /**
     * @return the Designation
     */
    public String getDesignation() {
      return m_designation;
    }

    /**
     * @param designation
     *          the Designation to set
     */
    public void setDesignation(String designation) {
      m_designation = designation;
    }

    /**
     * @return the MontantOperation
     */
    public BigDecimal getMontantOperation() {
      return m_montantOperation;
    }

    /**
     * @param montantOperation
     *          the MontantOperation to set
     */
    public void setMontantOperation(BigDecimal montantOperation) {
      m_montantOperation = montantOperation;
    }

    /**
     * @return the CoursApplique
     */
    public BigDecimal getCoursApplique() {
      return m_coursApplique;
    }

    /**
     * @param coursApplique
     *          the CoursApplique to set
     */
    public void setCoursApplique(BigDecimal coursApplique) {
      m_coursApplique = coursApplique;
    }
  }
}