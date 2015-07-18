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
public class TypeLitteralCodeType extends AbstractCodeType<Long, String> {

  private static final long serialVersionUID = 1L;
  /**
   * 
   */
  public static final Long ID = 10100L;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public TypeLitteralCodeType() throws ProcessingException {
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
  public static class AchatTypeCode extends AbstractCode<String> {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    public static final String ID = "A";

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("achatType");
    }

    @Override
    public String getId() {
      return ID;
    }
  }

  @Order(2000.0)
  public static class VenteTypeCode extends AbstractCode<String> {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    public static final String ID = "V";

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("venteType");
    }

    @Override
    public String getId() {
      return ID;
    }
  }
}