package com.accision.qualitymanagementreport.presentation;

import com.accision.base.common.exception.*;
import com.epicentric.components.*;
import com.epicentric.portalbeans.*;
import com.epicentric.portalbeans.beans.jspbean.*;

/**
 * <p>
 * <code>QualityManagementReportAdminEditView</code> is the presentation layer
 * admin edit portal view.
 * </p>
 *
 * <p>
 * $Id$
 * </p>
 *
 * @version  $Revision$, $Author$, $Date$
 * @author   <a href="mailto:tcannon@accision.com">Tom Cannon</a> (tcannon)
 */
public class QualityManagementReportAdminEditView extends JSPView {
  /**
   * Constructor
   *
   * @param portalBean the portal bean for this portlet
   * @param portalPageContext the portal page context
   * @param jspFileName the name of the jsp file for this view
   */
  public QualityManagementReportAdminEditView(PortalBean portalBean, PortalPageContext portalPageContext, String jspFileName) {
    super(portalBean, portalPageContext, jspFileName);

    QualityManagementReportBean qualityManagementReportBean = (QualityManagementReportBean)portalBean;

    try {
      qualityManagementReportBean.setAdminReferenceData(portalPageContext, this);
    } catch (AccisionException e) {
      throw new AccisionRuntimeException(e);
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
   * Returns the view url for ADMIN_BEAN_EDIT_PROCESS_VIEW.
   *
   * @return the view url
   */
  public String getProcessURL() {
    try {
      return getBean().getBaseViewURL(PortalBeanView.ADMIN_BEAN_EDIT_PROCESS_VIEW);
    } catch (Exception ignore) {
      return null;
    }
  }

  /**
   * Returns the view id for ADMIN_BEAN_EDIT_PROCESS_VIEW.
   *
   * @return the view id
   */
  public String getProcessViewID() {
    return PortalBeanView.ADMIN_BEAN_EDIT_PROCESS_VIEW;
  }

  /**
   * Returns the <code>QualityManagementReportAdminForm</code> from the user's session.
   *
   * @return the form
   */
  public QualityManagementReportAdminForm getForm() {
    return ((QualityManagementReportBean)getBean()).getAdminForm(this);
  }
}
