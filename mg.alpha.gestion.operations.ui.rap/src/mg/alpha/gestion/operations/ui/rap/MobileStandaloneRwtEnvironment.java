package mg.alpha.gestion.operations.ui.rap;

import org.eclipse.scout.rt.ui.rap.mobile.AbstractMobileStandaloneRwtEnvironment;

import mg.alpha.gestion.operations.client.ClientSession;

public class MobileStandaloneRwtEnvironment extends AbstractMobileStandaloneRwtEnvironment {

  private static final long serialVersionUID = 1L;

  public MobileStandaloneRwtEnvironment() {
    super(Activator.getDefault().getBundle(), ClientSession.class);
  }
}
