/**
 * 
 */
package mg.alpha.gestion.operations.shared.services.code;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCode;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCodeType;

/**
 * @author Dida
 */
public class DeviseCodeType extends AbstractCodeType<Long, String> {

  private static final long serialVersionUID = 1L;
  /**
   * 
   */
  public static final Long ID = 10200L;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public DeviseCodeType() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredText() {
    return TEXTS.get("devise");
  }

  @Override
  public Long getId() {
    return ID;
  }

  @Order(1000.0)
  public static class UsdCode extends AbstractCode<String> {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    public static final String ID = "USD";

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("usd");
    }

    @Override
    public String getId() {
      return ID;
    }
  }

  @Order(2000.0)
  public static class EurCode extends AbstractCode<String> {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    public static final String ID = "EUR";

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("eur");
    }

    @Override
    public String getId() {
      return ID;
    }
  }
}