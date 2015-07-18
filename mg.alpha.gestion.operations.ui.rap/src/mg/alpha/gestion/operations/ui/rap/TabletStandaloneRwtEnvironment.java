package mg.alpha.gestion.operations.ui.rap;

import org.eclipse.scout.rt.ui.rap.mobile.AbstractTabletStandaloneRwtEnvironment;

import mg.alpha.gestion.operations.client.ClientSession;

public class TabletStandaloneRwtEnvironment extends AbstractTabletStandaloneRwtEnvironment {

  private static final long serialVersionUID = 1L;

  public TabletStandaloneRwtEnvironment() {
    super(Activator.getDefault().getBundle(), ClientSession.class);
  }
}
