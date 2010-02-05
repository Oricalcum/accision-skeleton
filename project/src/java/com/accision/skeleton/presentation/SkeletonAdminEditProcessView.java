package com.accision.@package.presentation;

import java.io.*;

import org.apache.commons.lang.*;

import com.accision.base.common.exception.*;
import com.accision.base.common.logger.*;
import com.accision.base.presentation.*;
import com.epicentric.metastore.*;
import com.epicentric.portalbeans.*;

/**
 * <p>
 * <code>@adminProcessViewName</code> is the presentation layer
 * secondary process portal page for handling admin edit form requests.
 * </p>
 *
 * <p>
 * $Id$
 * </p>
 *
 * @version  $Revision$, $Author$, $Date$
 * @author   <a href="mailto:tcannon@accision.com">Tom Cannon</a> (tcannon)
 */
public class @adminProcessViewName extends AbstractProcessView {
  public static final String TASK_ADD    = "TASK_ADD";
  public static final String TASK_DELETE = "TASK_DELETE";

  /**
   * Constructor.
   *
   * @param portalBean the portal bean for this portlet
   * @param portalPageContext the portal page context
   */
  public @adminProcessViewName(PortalBean portalBean, PortalPageContext portalPageContext) {
    super(portalBean, portalPageContext);
  }

  /**
   * Handles user form posts and populates the preferences.
   */
  public void pageStart() {
    String  task   = request("task");
    boolean save   = false;

    AccisionLogger.debug(@adminProcessViewName.class, "pageStart()", "task=" + task);

    if (! StringUtils.isEmpty(task)) {
      if (TASK_SAVE.equals(task)) {
        save = true;
      } else if (TASK_CANCEL.equals(task)) {
        return;
      }
    }

    if (save) {
      String showStackTrace = request("showStackTrace");

      AccisionLogger.debug(@adminProcessViewName.class, "pageStart()", "showStackTrace=" + showStackTrace);

      MetaStoreFolder metaStoreFolder = getBean().getAdminFolder();

      metaStoreFolder.setIntegerProperty(Preference.META_STORE_SHOW_STACK_TRACE, Integer.parseInt(showStackTrace));
    }
  }

  /**
   * Redirects back to ADMIN_BEAN_EDIT_VIEW.
   */
  public void serviceHTML() {
    try {
      getPortalPageContext().sendRedirect(getBean().getFullViewURL(PortalBeanView.ADMIN_BEAN_EDIT_VIEW));
    } catch (IOException e) {
      String message = "An error occurred while redirecting to " + PortalBeanView.ADMIN_BEAN_EDIT_VIEW;

      AccisionLogger.error(@adminProcessViewName.class, "serviceHTML()", message, e);

      throw new AccisionRuntimeException(message, e);
    }
  }
}
