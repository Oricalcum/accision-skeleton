package com.accision.@package.presentation;

import com.accision.base.common.exception.*;

import com.epicentric.components.*;
import com.epicentric.portalbeans.*;
import com.epicentric.portalbeans.beans.jspbean.*;

/**
 * <p>
 * <code>@viewName</code> is the presentation layer
 * base portal view.
 * </p>
 *
 * <p>
 * $Id$
 * </p>
 *
 * @version  $Revision$, $Author$, $Date$
 * @author   <a href="mailto:tcannon@accision.com">Tom Cannon</a> (tcannon)
 */
public class @viewName extends JSPView {
  /**
   * Constructor.
   *
   * @param portalBean the portal bean for this portlet
   * @param portalPageContext the portal page context
   * @param jspFileName the name of the jsp file for this view
   */
  public @viewName(PortalBean portalBean, PortalPageContext portalPageContext, String jspFileName) {
    super(portalBean, portalPageContext, jspFileName);

    @beanName @varBeanName = (@beanName)portalBean;

    try {
      @varBeanName.setReferenceData(portalPageContext, this);
    } catch (Exception e) {
      try {
        @varBeanName.setErrorData(portalPageContext, this, e);
      } catch (Exception ignore) {
      }
    }
  }

  /**
   * Returns the url path to this portlet's jsp directory.
   *
   * @return path to this portlet's jsp directory
   */
  public String getPathToJSP() {
    return PortalServicesComponent._getPortalHttpRoot() + getJSPDirectory();
  }

  /**
   * Returns the <code>@formName</code> from the user's session.
   *
   * @return the form
   */
  public @formName getForm() {
    return ((@beanName)getBean()).getForm(this);
  }
}

