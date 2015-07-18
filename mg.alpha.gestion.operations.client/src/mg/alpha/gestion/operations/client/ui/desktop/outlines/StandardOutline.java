/**
 *
 */
package mg.alpha.gestion.operations.client.ui.desktop.outlines;

import java.util.List;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.extension.client.ui.desktop.outline.AbstractExtensibleOutline;
import org.eclipse.scout.rt.shared.AbstractIcons;
import org.eclipse.scout.rt.shared.TEXTS;

import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.comptes.ComptesTablePage;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.operations.OperationTablePage;
import mg.alpha.gestion.operations.client.ui.desktop.outlines.pages.bilan.BilanBeneficesTablePage;

/**
 * @author Dida
 */
public class StandardOutline extends AbstractExtensibleOutline {

  @Override
  protected boolean getConfiguredAutoTitle() {
    return true;
  }

  @Override
  protected String getConfiguredIconId() {
    return AbstractIcons.Gears;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("gestionDesOperationsTitle");
  }

  @Override
  protected void execCreateChildPages(List<IPage> pageList) throws ProcessingException {
    OperationTablePage operationsPageTitleTablePage = new OperationTablePage();
    pageList.add(operationsPageTitleTablePage);
    ComptesTablePage comptesTablePage = new ComptesTablePage();
    pageList.add(comptesTablePage);
    BilanBeneficesTablePage bilanBeneficesTablePage = new BilanBeneficesTablePage();
    pageList.add(bilanBeneficesTablePage);
    ComptesParticuliersTablePage comptesParticuliersTablePage = new ComptesParticuliersTablePage();
    pageList.add(comptesParticuliersTablePage);
  }
}
