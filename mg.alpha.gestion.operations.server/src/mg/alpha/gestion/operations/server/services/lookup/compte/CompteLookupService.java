/**
 *
 */
package mg.alpha.gestion.operations.server.services.lookup.compte;

import org.eclipse.scout.rt.server.services.lookup.AbstractSqlLookupService;

import mg.alpha.gestion.operations.shared.services.lookup.compte.ICompteLookupService;

/**
 * @author Dida
 */
public class CompteLookupService extends AbstractSqlLookupService<Long>implements ICompteLookupService {

  @Override
  protected String getConfiguredSqlSelect() {
    StringBuilder req = new StringBuilder("")//
    .append("select c.cpt_id, c.cpt_nom ")//
    .append("from comptes c ")//
    .append("where c.cpt_actif = 1 ")//
    .append("<key> and c.cpt_id = :key </key> ")//
    .append("<text>and upper(c.cpt_nom) like upper(:text )</text> ")//
//    .append("<text> ").append("and c.cpt_nom LIKE '%").append(":text").append("%' ").append("</text> ")//
    .append("<all> </all> ")//
    ;
    return req.toString();

  }

}
