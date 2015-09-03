/**
 *
 */
package mg.alpha.gestion.operations.server.services.lookup;

import org.eclipse.scout.rt.server.services.lookup.AbstractSqlLookupService;

import mg.alpha.gestion.operations.shared.services.lookup.ICompteParticulierLookupService;

/**
 * @author Dida
 */
public class CompteParticulierLookupService extends AbstractSqlLookupService<Long>implements ICompteParticulierLookupService {

  @Override
  protected String getConfiguredSqlSelect() {
    StringBuilder req = new StringBuilder("")//
    .append("select c.cpt_id, c.cpt_nom ")//
    .append("from comptes c ")//
    .append("where 1=1 ")//
    .append("and c.cpt_particulier = 1").append("<key> and c.cpt_id = :key </key> ")//
    .append("<text>and upper(c.cpt_nom) like upper(:text )</text> ")//
//        .append("<text> ").append("and c.cpt_nom LIKE '%").append(":text").append("%' ").append("</text> ")//
    .append("<all> </all> ")//
    ;
    return req.toString();

  }
}
