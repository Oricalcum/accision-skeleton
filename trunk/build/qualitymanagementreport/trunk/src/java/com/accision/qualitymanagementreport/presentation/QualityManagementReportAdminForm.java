package com.accision.qualitymanagementreport.presentation;

/**
 * <p>
 * <code>QualityManagementReportAdminForm</code> is the presentation layer
 * form for all admin form requests.
 * </p>
 *
 * <p>
 * $Id$
 * </p>
 *
 * @version  $Revision$, $Author$, $Date$
 * @author   <a href="mailto:tcannon@accision.com">Tom Cannon</a> (tcannon)
 */
public class QualityManagementReportAdminForm {
  private Integer showStackTrace;

  /**
   * Returns show stack trace.
   *
   * @return show stack trace
   */
  public Integer getShowStackTrace() {
    return showStackTrace;
  }

  /**
   * Sets show stack trace.
   *
   * @param showStackTrace show stack trace
   */
  public void setShowStackTrace(Integer showStackTrace) {
    this.showStackTrace = showStackTrace;
  }
}

