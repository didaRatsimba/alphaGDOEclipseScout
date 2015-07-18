/**
 * 
 */
package mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.comptes;

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
public class ComptesTablePageData extends AbstractTablePageData {

  private static final long serialVersionUID = 1L;

  public ComptesTablePageData() {
  }

  @Override
  public ComptesTableRowData addRow() {
    return (ComptesTableRowData) super.addRow();
  }

  @Override
  public ComptesTableRowData addRow(int rowState) {
    return (ComptesTableRowData) super.addRow(rowState);
  }

  @Override
  public ComptesTableRowData createRow() {
    return new ComptesTableRowData();
  }

  @Override
  public Class<? extends AbstractTableRowData> getRowType() {
    return ComptesTableRowData.class;
  }

  @Override
  public ComptesTableRowData[] getRows() {
    return (ComptesTableRowData[]) super.getRows();
  }

  @Override
  public ComptesTableRowData rowAt(int index) {
    return (ComptesTableRowData) super.rowAt(index);
  }

  public void setRows(ComptesTableRowData[] rows) {
    super.setRows(rows);
  }

  public static class ComptesTableRowData extends AbstractTableRowData {

    private static final long serialVersionUID = 1L;
    public static final String idCompte = "idCompte";
    public static final String libelleCompte = "libelleCompte";
    public static final String compteParticulier = "compteParticulier";
    private Long m_idCompte;
    private String m_libelleCompte;
    private Boolean m_compteParticulier;

    public ComptesTableRowData() {
    }

    /**
     * @return the IdCompte
     */
    public Long getIdCompte() {
      return m_idCompte;
    }

    /**
     * @param idCompte
     *          the IdCompte to set
     */
    public void setIdCompte(Long idCompte) {
      m_idCompte = idCompte;
    }

    /**
     * @return the LibelleCompte
     */
    public String getLibelleCompte() {
      return m_libelleCompte;
    }

    /**
     * @param libelleCompte
     *          the LibelleCompte to set
     */
    public void setLibelleCompte(String libelleCompte) {
      m_libelleCompte = libelleCompte;
    }

    /**
     * @return the CompteParticulier
     */
    public Boolean getCompteParticulier() {
      return m_compteParticulier;
    }

    /**
     * @param compteParticulier
     *          the CompteParticulier to set
     */
    public void setCompteParticulier(Boolean compteParticulier) {
      m_compteParticulier = compteParticulier;
    }
  }
}