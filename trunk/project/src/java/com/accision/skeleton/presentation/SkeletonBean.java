package com.accision.@package.presentation;

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
 * <code>@beanName</code> is the presentation layer bean.
 * </p>
 *
 * <p>
 * $Id$
 * </p>
 *
 * @version  $Revision$, $Author$, $Date$
 * @author   <a href="mailto:tcannon@accision.com">Tom Cannon</a> (tcannon)
 */
public class @beanName extends JSPBean {
  private static final String @sessionForm      = "@sessionForm";
  private static final String @sessionAdminForm = "@sessionAdminForm";
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
      return new @viewName(this, portalPageContext, jspFileName);
    } else {
      return new JSPView(this, portalPageContext, jspFileName);
    }
  }

  /**
   * Sets the reference values on the <code>@formName</code>
   * based on the form inputs.
   *
   * @param portalPageContext the port page context
   * @param @varViewName the jsp view
   * @throws AccisionException
   */
  protected void setReferenceData(PortalPageContext portalPageContext, @viewName @varViewName) throws AccisionException {
    HttpSession     httpSession    = portalPageContext.getTruePageContext().getSession();
    HPPContext      hppContext     = HPPContext.getHPPContext(httpSession);
    PatientContextI patientContext = null;

    try {
      patientContext = hppContext.getPatientContext();
    } catch (ContextException e) {
      String message = "";

      AccisionLogger.error(@beanName.class, "setReferenceData(PortalPageContext portalPageContext, @viewName @varViewName)", message, e);

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
    @formName @varFormName           = getForm(@varViewName);
    
    @varFormName.setExceptionMessage(null);
    @varFormName.setExceptionStackTrace(null);
  }
  
  /**
   * Sets the reference values on the <code>@adminFormName</code>
   * based on the form inputs.
   *
   * @param portalPageContext the port page context
   * @param @varAdminViewName the jsp admin view
   * @throws AccisionException
   */
  protected void setAdminReferenceData(PortalPageContext portalPageContext, @adminViewName @varAdminViewName) throws AccisionException {
    MetaStoreFolder metaStoreFolder = getAdminFolder();
    Integer         showStackTrace  = metaStoreFolder.getIntegerProperty(Preference.META_STORE_SHOW_STACK_TRACE);

    if (showStackTrace == null) {
      showStackTrace = new Integer(1);
    }

    @adminFormName @varAdminFormName = @varAdminViewName.getForm();

    @varAdminFormName.setShowStackTrace(showStackTrace);
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

    @formName @varFormName = getForm(jspView);

    @varFormName.setExceptionMessage(exception.getMessage());
    @varFormName.setExceptionStackTrace(exceptionStackTrace);
    @varFormName.setShowStackTrace((showStackTrace.intValue() == 0) ? Boolean.TRUE : Boolean.FALSE);
  }

  /**
   * Returns the <code>@formName</code> from the user's session.
   *
   * @return the form
   */
  protected @formName getForm(GenericBeanView genericBeanView) {
    @formName @varFormName = (@formName)genericBeanView.getSessionValue(@beanName.@sessionForm);

    if (@varFormName == null) {
      @varFormName = new @formName();

      genericBeanView.putSessionValue(@beanName.@sessionForm, @varFormName);
    }

    return @varFormName;
  }
  
  /**
   * Returns the <code>@adminFormName</code> from the user's session.
   *
   * @return the form
   */
  protected @adminFormName getAdminForm(GenericBeanView genericBeanView) {
    @adminFormName @varAdminFormName = (@adminFormName)genericBeanView.getSessionValue(@beanName.@sessionAdminForm);

    if (@varAdminFormName == null) {
      @varAdminFormName = new @adminFormName();

      genericBeanView.putSessionValue(@beanName.@sessionAdminForm, @varAdminFormName);
    }

    return @varAdminFormName;
  }
}

