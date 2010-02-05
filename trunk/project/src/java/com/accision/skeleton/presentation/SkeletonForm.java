package com.accision.@package.presentation;

/**
 * <p>
 * <code>@formName</code> is the presentation layer
 * form for all user form requests.
 * </p>
 *
 * <p>
 * $Id$
 * </p>
 *
 * @version  $Revision$, $Author$, $Date$
 * @author   <a href="mailto:tcannon@accision.com">Tom Cannon</a> (tcannon)
 */
public class @formName {
  private String  exceptionMessage;
  private String  exceptionStackTrace;
  private Boolean showStackTrace;
  
  /**
   * Returns the exception message.
   *
   * @return the exception message
   */
  public String getExceptionMessage() {
    return exceptionMessage;
  }

  /**
   * Sets the exception message.
   *
   * @param exceptionMessage the exception message
   */
  public void setExceptionMessage(String exceptionMessage) {
    this.exceptionMessage = exceptionMessage;
  }

  /**
   * Returns the exception stack trace.
   *
   * @return the exception stack trace
   */
  public String getExceptionStackTrace() {
    return exceptionStackTrace;
  }

  /**
   * Sets the exception stack trace.
   *
   * @param exceptionStackTrace the exception stack trace
   */
  public void setExceptionStackTrace(String exceptionStackTrace) {
    this.exceptionStackTrace = exceptionStackTrace;
  }

  /**
   * Returns whether or not to show the stack trace.
   *
   * @return show stack trace
   */
  public Boolean getShowStackTrace() {
    return showStackTrace;
  }

  /**
   * Sets whether or not to show the stack trace.
   *
   * @param showStackTrace show stack trace
   */
  public void setShowStackTrace(Boolean showStackTrace) {
    this.showStackTrace = showStackTrace;
  }
}

