/**
 *
 */
package mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.comptes;

import java.util.Set;

import org.eclipse.scout.commons.CollectionUtility;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.annotations.PageData;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBooleanColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.ISearchForm;
import org.eclipse.scout.rt.extension.client.ui.action.menu.AbstractExtensibleMenu;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.service.SERVICES;

import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.comptes.ComptesTablePage.Table;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.comptes.EditerCompteForm;
import mg.alpha.gestion.operations.shared.services.IStandardOutlineService;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.comptes.ComptesTablePageData;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.comptes.FiltrerComptesSearchFormData;

/**
 * @author Dida
 */
@PageData(ComptesTablePageData.class)
public class ComptesTablePage extends AbstractPageWithTable<Table> {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("listeDesComptes");
  }

  @Override
  protected void execLoadData(SearchFilter filter) throws ProcessingException {
    FiltrerComptesSearchFormData formData = (FiltrerComptesSearchFormData) filter.getFormData();
    if (formData == null) {
      formData = new FiltrerComptesSearchFormData();
    }

    importPageData(SERVICES.getService(IStandardOutlineService.class).getCompteTableData(formData));
  }

  @Order(1000.0)
  public class Table extends AbstractExtensibleTable {

    /**
     * @return the LibelleCompteColumn
     */
    public LibelleCompteColumn getLibelleCompteColumn() {
      return getColumnSet().getColumnByClass(ComptesTablePage.Table.LibelleCompteColumn.class);
    }

    /**
     * @return the CompteParticulierColumn
     */
    public CompteParticulierColumn getCompteParticulierColumn() {
      return getColumnSet().getColumnByClass(CompteParticulierColumn.class);
    }

    /**
     * @return the IdCompteColumn
     */
    public IdCompteColumn getIdCompteColumn() {
      return getColumnSet().getColumnByClass(IdCompteColumn.class);
    }

    @Order(0.0)
    public class IdCompteColumn extends AbstractLongColumn {

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

    @Order(500.0)
    public class LibelleCompteColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("libelleCompte");
      }

      @Override
      protected int getConfiguredWidth() {
        return 200;
      }
    }

    @Order(1000.0)
    public class CompteParticulierColumn extends AbstractBooleanColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("compteParticulier");
      }
    }

    @Order(1500.0)
    public class EditerCompteMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("editerCompte");
      }

      @Override
      protected void execAction() throws ProcessingException {
        EditerCompteForm form = new EditerCompteForm();
        form.setEditerCompteNr(getIdCompteColumn().getSelectedValue());
        form.startModify();
        form.waitFor();
        if (form.isFormStored()) {
          reloadPage();
        }
      }
    }

    @Order(2000.0)
    public class CreerCompteMenu extends AbstractExtensibleMenu {

      @Override
      protected Set<? extends IMenuType> getConfiguredMenuTypes() {
        return CollectionUtility.<IMenuType> hashSet(TableMenuType.EmptySpace);
      }

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("creerCompte");
      }

      @Override
      protected void execAction() throws ProcessingException {
        EditerCompteForm form = new EditerCompteForm();
        form.startNew();
        form.waitFor();
        if (form.isFormStored()) {
          reloadPage();
        }
      }
    }
  }

  @Override
  protected Class<? extends ISearchForm> getConfiguredSearchForm() {
    return FiltrerComptesSearchForm.class;
  }
}
