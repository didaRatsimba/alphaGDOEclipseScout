/**
 *
 */
package mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.bilan;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.annotations.PageData;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBigDecimalColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractDateColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.ISearchForm;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.service.SERVICES;

import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.bilan.BilanBeneficesTablePage.Table;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.bilan.BilanBeneficesSearchForm;
import mg.alpha.gestion.operations.shared.services.IStandardOutlineService;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.bilan.BilanBeneficesTablePageData;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.bilan.BilanBeneficesSearchFormData;

/**
 * @author Dida
 */
@PageData(BilanBeneficesTablePageData.class)
public class BilanBeneficesTablePage extends AbstractPageWithTable<Table> {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("bilanBenefices");
  }

  @Override
  protected void execLoadData(SearchFilter filter) throws ProcessingException {
    BilanBeneficesSearchFormData formData = (BilanBeneficesSearchFormData) filter.getFormData();
    if (formData == null) {
      formData = new BilanBeneficesSearchFormData();
    }
    importPageData(SERVICES.getService(IStandardOutlineService.class).getBilanBeneficeTablePageData(formData));
  }

  @Order(1000.0)
  public class Table extends AbstractExtensibleTable {

    /**
     * @return the MontantDebiteColumn
     */
    public MontantDebiteColumn getMontantDebiteColumn() {
      return getColumnSet().getColumnByClass(MontantDebiteColumn.class);
    }

    /**
     * @return the EnvoyeAColumn
     */
    public EnvoyeAColumn getEnvoyeAColumn() {
      return getColumnSet().getColumnByClass(EnvoyeAColumn.class);
    }

    /**
     * @return the DateEnvoiColumn
     */
    public DateEnvoiColumn getDateEnvoiColumn() {
      return getColumnSet().getColumnByClass(DateEnvoiColumn.class);
    }

    /**
     * @return the BeneficeColumn
     */
    public BeneficeColumn getBeneficeColumn() {
      return getColumnSet().getColumnByClass(BeneficeColumn.class);
    }

    /**
     * @return the DebitParOperationColumn
     */
    public DebitParOperationColumn getDebitParOperationColumn() {
      return getColumnSet().getColumnByClass(DebitParOperationColumn.class);
    }

    @Order(1000.0)
    public class DebitParOperationColumn extends AbstractLongColumn {

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

    @Order(1500.0)
    public class DateEnvoiColumn extends AbstractDateColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("DateVente");
      }
    }

    @Order(2000.0)
    public class MontantDebiteColumn extends AbstractBigDecimalColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("SommeVendue");
      }

      @Override
      protected int getConfiguredWidth() {
        return 150;
      }
    }

    @Order(3000.0)
    public class EnvoyeAColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("envoyeA");
      }

      @Override
      protected int getConfiguredWidth() {
        return 200;
      }
    }

    @Order(4000.0)
    public class BeneficeColumn extends AbstractBigDecimalColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("benficeRapporte");
      }

      @Override
      protected int getConfiguredWidth() {
        return 150;
      }
    }
  }

  @Override
  protected Class<? extends ISearchForm> getConfiguredSearchForm() {
    return BilanBeneficesSearchForm.class;
  }
}
