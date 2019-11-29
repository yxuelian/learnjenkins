package com.topcoder.commons.utils;

import java.lang.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import com.topcoder.util.log.*;

/**
 * This is a utility class that provides static methods for logging method
 * entrance, method exit and exception using Logging Wrapper component. It
 * allows to optionally log method input/output parameters, method execution
 * time and timestamps. Exceptions are logged together with stack traces.
 * Default level for method entrance/exit message is DEBUG, for exceptions -
 * ERROR; but both can be overridden.
 * 
 * Thread Safety: This class is immutable and thread safe when array parameters
 * passed to it are used by the caller in thread safe manner.
 * 
 * @author yangxuelian
 */
public class LoggingWrapperUtility {
	/**
	 * The format of timestamps to be put to log messages. Is initialized during
	 * class loading and never changed after that. Is used in logEntrance(),
	 * logExit() and logException().
	 */
	private static final DateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss] ");

	/**
	 * Empty private constructor.
	 */
	private LoggingWrapperUtility() {
	}

	/**
	 * Logs the method entrance together with input parameters (if present).
	 * It's assumed that paramNames and paramValues contain the same number of
	 * elements. When this method is used, timestamp is not logged explicitly
	 * and DEBUG level is used.
	 * 
	 * @param paramNames
	 *            the names of input parameters (null of method doesn't accept
	 *            any parameters)
	 * @param paramValues
	 *            the values of input parameters
	 * @param log
	 *            the logger to be used (null if logging is not required to be
	 *            performed)
	 * @param signature
	 *            the signature that uniquely identifies the method (e.g.
	 *            className#methodName)
	 */
	public static void logEntrance(Log log, String signature, String[] paramNames, Object[] paramValues) {
		logEntrance(log, signature, paramNames, paramValues, false, Level.DEBUG);
	}

	/**
	 * Logs the method entrance together with input parameters (if present) and
	 * timestamp (optionally). It's assumed that paramNames and paramValues
	 * contain the same number of elements.
	 * 
	 * @param paramNames
	 *            the names of input parameters (null of method doesn't accept
	 *            any parameters)
	 * @param paramValues
	 *            the values of input parameters
	 * @param log
	 *            the logger to be used (null if logging is not required to be
	 *            performed)
	 * @param signature
	 *            the signature that uniquely identifies the method (e.g.
	 *            className#methodName)
	 * @param addTimestamp
	 *            true if timestamp must be added to the logged message, false
	 *            otherwise
	 * @param level
	 *            the logging level to be used
	 */
	public static void logEntrance(Log log, String signature, String[] paramNames, Object[] paramValues,
			boolean addTimestamp, Level level) {
		if (log == null) {
			return;
		}
		String message = (addTimestamp ? TIMESTAMP_FORMAT.format(new Date()) : "") + LoggingUtilityHelper.getMethodEntranceMessage(signature);
		log.log(level, message);
		if (paramNames != null) {
			log.log(level, LoggingUtilityHelper.getInputParametersMessage(paramNames, paramValues));
		}
	}

	/**
	 * Logs the method exit together with the returned value (if present). When
	 * this method is used, timestamp is not logged explicitly, method execution
	 * time is not logged and DEBUG level is used.
	 * 
	 * @param log
	 *            the logger to be used (null if logging is not required to be
	 *            performed)
	 * @param signature
	 *            the signature that uniquely identifies the method (e.g.
	 *            className#methodName)
	 * @param value
	 *            the value returned from the method (should contain 1 element
	 *            with the returned value, or should be null if the method
	 *            returns void)
	 */
	public static void logExit(Log log, String signature, Object[] value) {
		logExit(log, signature, value, null);
	}

	/**
	 * Logs the method exit together with the returned value (if present) and
	 * method execution time. When this method is used, timestamp is not logged
	 * explicitly and DEBUG level is used.
	 * 
	 * @param entranceTimestamp
	 *            the method entrance timestamp (null if not available), is used
	 *            for calculating method execution time
	 * @param log
	 *            the logger to be used (null if logging is not required to be
	 *            performed)
	 * @param signature
	 *            the signature that uniquely identifies the method (e.g.
	 *            className#methodName)
	 * @param value
	 *            the value returned from the method (should contain 1 element
	 *            with the returned value, or should be null if the method
	 *            returns void)
	 */
	public static void logExit(Log log, String signature, Object[] value, Date entranceTimestamp) {
		logExit(log, signature, value, entranceTimestamp, false, Level.DEBUG);
	}

	/**
	 * Logs the method exit together with the returned value (if present) and
	 * method execution time.
	 * 
	 * @param entranceTimestamp
	 *            the method entrance timestamp (null if not available), is used
	 *            for calculating method execution time
	 * @param log
	 *            the logger to be used (null if logging is not required to be
	 *            performed)
	 * @param signature
	 *            the signature that uniquely identifies the method (e.g.
	 *            className#methodName)
	 * @param addTimestamp
	 *            true if timestamp must be added to the logged message, false
	 *            otherwise
	 * @param level
	 *            the logging level to be used
	 * @param value
	 *            the value returned from the method (should contain 1 element
	 *            with the returned value, or should be null if the method
	 *            returns void)
	 */
	public static void logExit(Log log, String signature, Object[] value, Date entranceTimestamp, boolean addTimestamp,
			Level level) {
		if (log == null) {
			return;
		}
		String message = (addTimestamp ? TIMESTAMP_FORMAT.format(new Date()) : "") + LoggingUtilityHelper.getMethodExitMessage(signature, entranceTimestamp);
		log.log(level, message);
		if (value != null) {
			log.log(level, LoggingUtilityHelper.getOutputValueMessage(value[0]));
		}
	}

	/**
	 * Logs the given exception. When this method is used, timestamp is not
	 * logged explicitly and ERROR level is used.
	 * 
	 * @param exception
	 *            the exception to be logged (assumed to be not null)
	 * @param log
	 *            the logger to be used (null if logging is not required to be
	 *            performed)
	 * @param signature
	 *            the signature that uniquely identifies the method (e.g.
	 *            className#methodName) where the exception is logged
	 * @return the logged exception
	 */
	public static <T extends Throwable> T logException(Log log, String signature, T exception) {
		return logException(log, signature, exception, false, Level.ERROR);
	}

	/**
	 * Logs the given exception together with timestamp (optionally).
	 * 
	 * @param exception
	 *            the exception to be logged (assumed to be not null)
	 * @param log
	 *            the logger to be used (null if logging is not required to be
	 *            performed)
	 * @param signature
	 *            the signature that uniquely identifies the method (e.g.
	 *            className#methodName) where the exception is logged
	 * @param addTimestamp
	 *            true if timestamp must be added to the logged message, false
	 *            otherwise
	 * @param level
	 *            the logging level to be used
	 * @return the logged exception
	 */
	public static <T extends Throwable> T logException(Log log, String signature, T exception, boolean addTimestamp,
			Level level) {
		if (log == null) {
			return null;
		}
		String message = (addTimestamp ? TIMESTAMP_FORMAT.format(new Date()) : "") + LoggingUtilityHelper.getExceptionMessage(signature, exception);
		log.log(level, message);
		return exception;
	}
}
