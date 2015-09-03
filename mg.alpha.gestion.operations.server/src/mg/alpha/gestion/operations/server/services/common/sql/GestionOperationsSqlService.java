/**
 *
 */
package mg.alpha.gestion.operations.server.services.common.sql;

import com.bsiag.scout.rt.server.jdbc.AbstractMySqlSqlService;

/**
 * @author Dida
 */
public class GestionOperationsSqlService extends AbstractMySqlSqlService {

  @Override
  protected String getConfiguredJdbcMappingName() {
    return "jdbc:mysql://localhost/gestion_operations";
  }

  @Override
  protected String getConfiguredPassword() {
    return "mysql123";
  }

  @Override
  protected String getConfiguredUsername() {
    return "root";
  }
}
