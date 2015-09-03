/**
 *
 */
package mg.alpha.gestion.operations.client.ui.desktop.outlines;

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

import mg.alpha.gestion.operations.client.ui.desktop.outlines.ComptesParticuliersTablePage.Table;
import mg.alpha.gestion.operations.shared.services.IStandardOutlineService;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.ComptesParticuliersSearchFormData;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.ComptesParticuliersTablePageData;

/**
 * @author Dida
 */
@PageData(ComptesParticuliersTablePageData.class)
public class ComptesParticuliersTablePage extends AbstractPageWithTable<Table> {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("historiqueComptesParticuliers");
  }

  @Override
  protected void execLoadData(SearchFilter filter) throws ProcessingException {
    ComptesParticuliersSearchFormData formData = (ComptesParticuliersSearchFormData) filter.getFormData();
    if (formData == null) {
      formData = new ComptesParticuliersSearchFormData();
    }
    importPageData(SERVICES.getService(IStandardOutlineService.class).getComptesParticuliersHistorique(formData));
  }

  @Order(1000.0)
  public class Table extends AbstractExtensibleTable {

    /**
     * @return the MontantEnvoyeColumn
     */
    public MontantEnvoyeColumn getMontantEnvoyeColumn() {
      return getColumnSet().getColumnByClass(MontantEnvoyeColumn.class);
    }

    /**
     * @return the PrixDeVenteUniteColumn
     */
    public PrixDeVenteUniteColumn getPrixDeVenteUniteColumn() {
      return getColumnSet().getColumnByClass(PrixDeVenteUniteColumn.class);
    }

    /**
     * @return the DateEnvoiColumn
     */
    public DateEnvoiColumn getDateEnvoiColumn() {
      return getColumnSet().getColumnByClass(DateEnvoiColumn.class);
    }

    /**
     * @return the TimestampColumn
     */
    public TimestampColumn getTimestampColumn() {
      return getColumnSet().getColumnByClass(TimestampColumn.class);
    }

    /**
     * @return the CompteDenvoiColumn
     */
    public CompteDenvoiColumn getCompteDenvoiColumn() {
      return getColumnSet().getColumnByClass(CompteDenvoiColumn.class);
    }

    /**
     * @return the CompteParticulierColumn
     */
    public CompteParticulierColumn getCompteParticulierColumn() {
      return getColumnSet().getColumnByClass(CompteParticulierColumn.class);
    }

    /**
     * @return the SommeParCompteParticulierIdColumn
     */
    public SommeParCompteParticulierIdColumn getSommeParCompteParticulierIdColumn() {
      return getColumnSet().getColumnByClass(SommeParCompteParticulierIdColumn.class);
    }

    @Order(1000.0)
    public class SommeParCompteParticulierIdColumn extends AbstractLongColumn {

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

    @Order(3000.0)
    public class CompteDenvoiColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("compteDenvoi");
      }

      @Override
      protected int getConfiguredWidth() {
        return 200;
      }
    }

    @Order(4000.0)
    public class MontantEnvoyeColumn extends AbstractBigDecimalColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("montantEnvoye");
      }

      @Override
      protected int getConfiguredWidth() {
        return 150;
      }
    }

    @Order(5000.0)
    public class PrixDeVenteUniteColumn extends AbstractBigDecimalColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("prixDeVenteUnite");
      }

      @Override
      protected int getConfiguredWidth() {
        return 200;
      }
    }

    @Order(6000.0)
    public class DateEnvoiColumn extends AbstractDateColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("dateEnvoi");
      }

      @Override
      protected int getConfiguredWidth() {
        return 200;
      }
    }

    @Order(7000.0)
    public class TimestampColumn extends AbstractLongColumn {

      @Override
      protected boolean getConfiguredDisplayable() {
        return false;
      }

      @Override
      protected boolean getConfiguredVisible() {
        return false;
      }
    }
  }

  @Override
  protected Class<? extends ISearchForm> getConfiguredSearchForm() {
    return ComptesParticuliersSearchForm.class;
  }
}
