/*
 * Desktop Scheduling Application for C195
 * @author Raymond Lanoux <rlanoux@wgu.edu>  * 
 */
package SchedulingApp.Exceptions;

/**
 *
 * @author Raymond
 */
public class AppointmentException extends Exception {
    public AppointmentException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
