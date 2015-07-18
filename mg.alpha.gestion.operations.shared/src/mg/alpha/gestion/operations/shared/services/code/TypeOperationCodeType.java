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
public class TypeOperationCodeType extends AbstractCodeType<Long, Long> {

  private static final long serialVersionUID = 1L;
  /**
   * 
   */
  public static final Long ID = 10000L;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public TypeOperationCodeType() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredText() {
    return TEXTS.get("type");
  }

  @Override
  public Long getId() {
    return ID;
  }

  @Order(1000.0)
  public static class AchatTypeCode extends AbstractCode<Long> {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    public static final Long ID = 10001L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("achatType");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(2000.0)
  public static class VenteTypeCode extends AbstractCode<Long> {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    public static final Long ID = 10002L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("venteType");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }
}