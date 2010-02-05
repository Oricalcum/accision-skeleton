package com.accision.qualitymanagementreport.presentation;

import java.io.*;

import com.accision.base.common.exception.*;
import com.accision.base.common.logger.*;
import com.accision.base.presentation.*;

import com.epicentric.common.website.*;
import com.epicentric.metastore.*;
import com.epicentric.portalbeans.*;
import com.epicentric.portalbeans.beans.jspbean.*;
import com.epicentric.user.*;

import com.mckesson.hef.context.*;
import com.mckesson.hef.patient.*;

import javax.servlet.http.*;

/**
 * <p>
 * <code>QualityManagementReportBean</code> is the presentation layer bean.
 * </p>
 *
 * <p>
 * $Id$
 * </p>
 *
 * @version  $Revision$, $Author$, $Date$
 * @author   <a href="mailto:tcannon@accision.com">Tom Cannon</a> (tcannon)
 */
public class QualityManagementReportBean extends JSPBean {
  private static final String QUALITY_MANAGEMENT_REPORT_FORM      = "QUALITY_MANAGEMENT_REPORT_FORM";
  private static final String QUALITY_MANAGEMENT_REPORT_ADMIN_FORM = "QUALITY_MANAGEMENT_REPORT_ADMIN_FORM";
  private static final long   serialVersionUID  = 1L;

  /**
   * Returns the requested <code>PortalBeanView</code>.
   *
   * @param portalPageContext the portal page context
   */
  public PortalBeanView getView(PortalPageContext portalPageContext) {
    String viewId      = portalPageContext.getViewID();
    String jspFileName = getJspFilename(viewId, portalPageContext.getDocumentType());

    if (PortalBeanView.MY_PORTAL_VIEW.equals(viewId)) {
      return new QualityManagementReportView(this, portalPageContext, jspFileName);
    } else {
      return new JSPView(this, portalPageContext, jspFileName);
    }
  }

  /**
   * Sets the reference values on the <code>QualityManagementReportForm</code>
   * based on the form inputs.
   *
   * @param portalPageContext the port page context
   * @param qualityManagementReportView the jsp view
   * @throws AccisionException
   */
  protected void setReferenceData(PortalPageContext portalPageContext, QualityManagementReportView qualityManagementReportView) throws AccisionException {
    HttpSession     httpSession    = portalPageContext.getTruePageContext().getSession();
    HPPContext      hppContext     = HPPContext.getHPPContext(httpSession);
    PatientContextI patientContext = null;

    try {
      patientContext = hppContext.getPatientContext();
    } catch (ContextException e) {
      String message = "";

      AccisionLogger.error(QualityManagementReportBean.class, "setReferenceData(PortalPageContext portalPageContext, QualityManagementReportView qualityManagementReportView)", message, e);

      throw new AccisionException(message, e);
    }

    String       medicalRecordNumber = patientContext.getMRN();
    String       visitNumber         = patientContext.getAccountNumber();
    String       name                = patientContext.getName();

    //
    User         user                = SessionUtils.getCurrentUser(httpSession);
    String       userName            = (String)user.getProperty("username");
    String       sessionId           = httpSession.getId();

    //
    QualityManagementReportForm qualityManagementReportForm           = getForm(qualityManagementReportView);
    
    qualityManagementReportForm.setExceptionMessage(null);
    qualityManagementReportForm.setExceptionStackTrace(null);
  }
  
  /**
   * Sets the reference values on the <code>QualityManagementReportAdminForm</code>
   * based on the form inputs.
   *
   * @param portalPageContext the port page context
   * @param qualityManagementReportAdminEditView the jsp admin view
   * @throws AccisionException
   */
  protected void setAdminReferenceData(PortalPageContext portalPageContext, QualityManagementReportAdminEditView qualityManagementReportAdminEditView) throws AccisionException {
    MetaStoreFolder metaStoreFolder = getAdminFolder();
    Integer         showStackTrace  = metaStoreFolder.getIntegerProperty(Preference.META_STORE_SHOW_STACK_TRACE);

    if (showStackTrace == null) {
      showStackTrace = new Integer(1);
    }

    QualityManagementReportAdminForm qualityManagementReportAdminForm = qualityManagementReportAdminEditView.getForm();

    qualityManagementReportAdminForm.setShowStackTrace(showStackTrace);
  }
  
  /**
   * Sets the error values on the form.
   *
   * @param portalPageContext the portal page context
   * @param jspView the jsp view
   * @throws AccisionException
   */
  protected void setErrorData(PortalPageContext portalPageContext, JSPView jspView, Exception exception) {
    Integer showStackTrace = MetaStoreWrapper.getInteger(Preference.META_STORE_SHOW_STACK_TRACE, jspView);

    if (showStackTrace == null) {
      showStackTrace = new Integer(1);
    }

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream           ps   = new PrintStream(baos);

    exception.printStackTrace(ps);
    ps.close();

    String exceptionStackTrace = baos.toString();

    baos = null;
    ps   = null;

    QualityManagementReportForm qualityManagementReportForm = getForm(jspView);

    qualityManagementReportForm.setExceptionMessage(exception.getMessage());
    qualityManagementReportForm.setExceptionStackTrace(exceptionStackTrace);
    qualityManagementReportForm.setShowStackTrace((showStackTrace.intValue() == 0) ? Boolean.TRUE : Boolean.FALSE);
  }

  /**
   * Returns the <code>QualityManagementReportForm</code> from the user's session.
   *
   * @return the form
   */
  protected QualityManagementReportForm getForm(GenericBeanView genericBeanView) {
    QualityManagementReportForm qualityManagementReportForm = (QualityManagementReportForm)genericBeanView.getSessionValue(QualityManagementReportBean.QUALITY_MANAGEMENT_REPORT_FORM);

    if (qualityManagementReportForm == null) {
      qualityManagementReportForm = new QualityManagementReportForm();

      genericBeanView.putSessionValue(QualityManagementReportBean.QUALITY_MANAGEMENT_REPORT_FORM, qualityManagementReportForm);
    }

    return qualityManagementReportForm;
  }
  
  /**
   * Returns the <code>QualityManagementReportAdminForm</code> from the user's session.
   *
   * @return the form
   */
  protected QualityManagementReportAdminForm getAdminForm(GenericBeanView genericBeanView) {
    QualityManagementReportAdminForm qualityManagementReportAdminForm = (QualityManagementReportAdminForm)genericBeanView.getSessionValue(QualityManagementReportBean.QUALITY_MANAGEMENT_REPORT_ADMIN_FORM);

    if (qualityManagementReportAdminForm == null) {
      qualityManagementReportAdminForm = new QualityManagementReportAdminForm();

      genericBeanView.putSessionValue(QualityManagementReportBean.QUALITY_MANAGEMENT_REPORT_ADMIN_FORM, qualityManagementReportAdminForm);
    }

    return qualityManagementReportAdminForm;
  }
}

