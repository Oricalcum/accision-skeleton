package com.accision.qualitymanagementreport.presentation;

import com.accision.base.common.exception.*;

import com.epicentric.components.*;
import com.epicentric.portalbeans.*;
import com.epicentric.portalbeans.beans.jspbean.*;

/**
 * <p>
 * <code>QualityManagementReportView</code> is the presentation layer
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
public class QualityManagementReportView extends JSPView {
  /**
   * Constructor.
   *
   * @param portalBean the portal bean for this portlet
   * @param portalPageContext the portal page context
   * @param jspFileName the name of the jsp file for this view
   */
  public QualityManagementReportView(PortalBean portalBean, PortalPageContext portalPageContext, String jspFileName) {
    super(portalBean, portalPageContext, jspFileName);

    QualityManagementReportBean qualityManagementReportBean = (QualityManagementReportBean)portalBean;

    try {
      qualityManagementReportBean.setReferenceData(portalPageContext, this);
    } catch (Exception e) {
      try {
        qualityManagementReportBean.setErrorData(portalPageContext, this, e);
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
   * Returns the <code>QualityManagementReportForm</code> from the user's session.
   *
   * @return the form
   */
  public QualityManagementReportForm getForm() {
    return ((QualityManagementReportBean)getBean()).getForm(this);
  }
}

