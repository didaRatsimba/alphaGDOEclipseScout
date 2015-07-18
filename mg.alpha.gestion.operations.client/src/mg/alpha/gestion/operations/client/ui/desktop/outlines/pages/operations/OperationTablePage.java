/**
 *
 */
package mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.operations;

import java.util.ArrayList;
import java.util.Set;

import org.eclipse.scout.commons.CollectionUtility;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.annotations.PageData;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.cell.Cell;
import org.eclipse.scout.rt.client.ui.basic.cell.ICell;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBigDecimalColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractDateTimeColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.basic.table.internal.InternalTableRow;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.ISearchForm;
import org.eclipse.scout.rt.extension.client.ui.action.menu.AbstractExtensibleMenu;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.shared.AbstractIcons;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.service.SERVICES;

import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.ComptesParticuliersForm;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.CreerOperationForm;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.EditerOperationsForm;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.forms.operations.OperationsSearchForm;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.operations.OperationTablePage.Table;
import mg.alpha.gestion.operations.shared.services.IStandardOutlineService;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.forms.operations.OperationsSearchFormData;
import mg.alpha.gestion.operations.shared.ui.desktop.outlines.pages.operations.OperationsTablePageData;

/**
 * @author Dida
 */
@PageData(OperationsTablePageData.class)
public class OperationTablePage extends AbstractPageWithTable<Table> {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("achatsVentes");
  }

  @Override
  protected void execLoadData(SearchFilter filter) throws ProcessingException {
    OperationsSearchFormData formData = (OperationsSearchFormData) filter.getFormData();
    if (formData == null) {
      formData = new OperationsSearchFormData();
    }

    importPageData(SERVICES.getService(IStandardOutlineService.class).getOperationTableData(formData));
  }

  @Order(1000.0)
  public class Table extends AbstractExtensibleTable {

    /**
     * @return the TypeOperationColumn
     */
    public TypeOperationColumn getTypeOperationColumn() {
      return getColumnSet().getColumnByClass(TypeOperationColumn.class);
    }

    /**
     * @return the CompteParticulierColumn
     */
    public CompteParticulierColumn getCompteParticulierColumn() {
      return getColumnSet().getColumnByClass(CompteParticulierColumn.class);
    }

    /**
     * @return the DeviseColumn
     */
    public DeviseColumn getDeviseColumn() {
      return getColumnSet().getColumnByClass(DeviseColumn.class);
    }

    /**
     * @return the DesignationColumn
     */
    public DesignationColumn getDesignationColumn() {
      return getColumnSet().getColumnByClass(DesignationColumn.class);
    }

    /**
     * @return the MontantOperationColumn
     */
    public MontantOperationColumn getMontantOperationColumn() {
      return getColumnSet().getColumnByClass(MontantOperationColumn.class);
    }

    /**
     * @return the CoursAppliqueColumn
     */
    public CoursAppliqueColumn getCoursAppliqueColumn() {
      return getColumnSet().getColumnByClass(CoursAppliqueColumn.class);
    }

    @Override
    protected boolean getConfiguredMultiCheck() {
      return false;
    }

    @Override
    protected boolean getConfiguredMultiSelect() {
      return false;
    }

    @Override
    protected void execRowAction(ITableRow row) throws ProcessingException {
      EditerOperationsForm form = new EditerOperationsForm();
      form.setEditerOperationsNr(getOpIdColumn().getSelectedValue());
      form.startModify();
      form.waitFor();
      if (form.isFormStored()) {
        reloadPage();
      }
    }

    /**
     * @return the TimestampColumn
     */
    public TimestampColumn getTimestampColumn() {
      return getColumnSet().getColumnByClass(TimestampColumn.class);
    }

    /**
     * @return the CompteOperationColumn
     */
    public CompteOperationColumn getCompteOperationColumn() {
      return getColumnSet().getColumnByClass(CompteOperationColumn.class);
    }

    /**
     * @return the DateOperationColumn
     */
    public DateOperationColumn getDateOperationColumn() {
      return getColumnSet().getColumnByClass(DateOperationColumn.class);
    }

    /**
     * @return the OpIdColumn
     */
    public OpIdColumn getOpIdColumn() {
      return getColumnSet().getColumnByClass(OpIdColumn.class);
    }

    @Order(1000.0)
    public class OpIdColumn extends AbstractLongColumn {

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
    public class DateOperationColumn extends AbstractDateTimeColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("dateOperationColumnHeader");
      }
    }

    @Order(3000.0)
    public class TypeOperationColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("type");
      }
    }

    @Order(4000.0)
    public class CompteOperationColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("compteOperation");
      }

      @Override
      protected int getConfiguredWidth() {
        return 200;
      }
    }

    @Order(5000.0)
    public class CompteParticulierColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("compteParticulier");
      }

      @Override
      protected int getConfiguredWidth() {
        return 200;
      }

      @Override
      protected void execDecorateCell(Cell cell, ITableRow row) throws ProcessingException {
        if (getValue(row) == null) {
          cell.setText(" ");
        }
        if (getValue(row) != null && Integer.valueOf(getValue(row)).intValue() > 0) {
          cell.setText(getValue(row).concat(" compte(s) rattachés"));
//          ComptesParticuliersMenu menu = getMenu(ComptesParticuliersMenu.class);
//          menu.setEnabled(true);
        }
        else {
//          ComptesParticuliersMenu menu = getMenu(ComptesParticuliersMenu.class);
//          menu.setEnabled(false);
        }
        super.execDecorateCell(cell, row);
      }
    }

    @Order(6000.0)
    public class DeviseColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("devise");
      }
    }

    @Order(7000.0)
    public class DesignationColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("designation");
      }

      @Override
      protected int getConfiguredWidth() {
        return 200;
      }
    }

    @Order(8000.0)
    public class MontantOperationColumn extends AbstractBigDecimalColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("montantHeaderColumn");
      }
    }

    @Order(9000.0)
    public class CoursAppliqueColumn extends AbstractBigDecimalColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("coursColumnHeader");
      }
    }

    @Order(1000.0)
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

    @Order(1000.0)
    public class EditerOperationsMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredIconId() {
        return AbstractIcons.FileChooserFieldFile;
      }

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("editerOperations");
      }

      @Override
      protected void execAction() throws ProcessingException {
        EditerOperationsForm form = new EditerOperationsForm();
        form.setEditerOperationsNr(getOpIdColumn().getSelectedValue());
        form.startModify();
        form.waitFor();
        if (form.isFormStored()) {
          reloadPage();
        }
      }
    }

    @Order(2000.0)
    public class CreerOperationMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredIconId() {
        return AbstractIcons.ComposerFieldRoot;
      }

      @Override
      protected Set<? extends IMenuType> getConfiguredMenuTypes() {
        return CollectionUtility.<IMenuType> hashSet(TableMenuType.EmptySpace);
      }

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("creerOperatoin");
      }

      @Override
      protected void execAction() throws ProcessingException {
        CreerOperationForm form = new CreerOperationForm();
        form.startNew();
        form.waitFor();
        if (form.isFormStored()) {
          reloadPage();
        }
      }
    }

    @Order(3000.0)
    public class ComptesParticuliersMenu extends AbstractExtensibleMenu {

      @Override
      protected boolean getConfiguredEnabled() {
        return false;
      }

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("afficherComptesParticuliers");
      }

      @Override
      protected void execAction() throws ProcessingException {
        ComptesParticuliersForm form = new ComptesParticuliersForm();
        Long selectedOperationTimestamp = (Long) getTable().getSelectedRow().getCell(getColumnSet().getColumnByClass(TimestampColumn.class)).getValue();
        form.setIdFromTimestamp(selectedOperationTimestamp);
        form.startModify();
        form.waitFor();
        if (form.isFormStored()) {
          reloadPage();
        }
      }

      @Override
      protected void execOwnerValueChanged(Object newOwnerValue) throws ProcessingException {
        if (newOwnerValue instanceof ArrayList<?>) {
          ArrayList<?> values = (ArrayList<?>) newOwnerValue;
          for (Object eachValue : values) {
            if (eachValue instanceof InternalTableRow) {
              InternalTableRow row = (InternalTableRow) eachValue;
              ICell cell = row.getCell(getColumnSet().getColumnByClass(CompteParticulierColumn.class));
              if (cell.getValue() instanceof String) {
                setEnabled(true);
              }
              else {
                setEnabled(false);
              }
            }
          }
        }
        super.execOwnerValueChanged(newOwnerValue);
      }
    }
  }

  @Override
  protected Class<? extends ISearchForm> getConfiguredSearchForm() {
    return OperationsSearchForm.class;
  }
}
