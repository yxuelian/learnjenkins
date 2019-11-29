package com.topcoder.commons.utils;

import java.lang.*;
import java.lang.reflect.Constructor;



/**
 * This is a static helper class that provides methods for constructing
 * exception instances using reflection. It is used by ValidationUtility,
 * PropertiesUtility and JDBCUtility.
 * 
 * Thread Safety: This class is immutable and thread safe when array parameters
 * passed to it are used by the caller in thread safe manner.
 * 
 * @author yangxuelian
 */
class ExceptionHelper {
	/**
	 * Empty private constructor.
	 */
	private ExceptionHelper() {
	}

	/**
	 * Constructs an exception of the specified type with the given message.
	 * 
	 * @param exceptionClass
	 *            the exception class
	 * @param message
	 *            the message
	 * @return the constructed exception instance (not null)
	 */
	static <T extends Throwable> T constructException(Class<T> exceptionClass, String message) {
		
		try {
			Constructor<T> constructor = exceptionClass.getConstructor(String.class);
			T result = constructor.newInstance(message);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Constructs an exception of the specified type with the given message and
	 * cause.
	 * 
	 * @param cause
	 *            the exception cause
	 * @param exceptionClass
	 *            the exception class
	 * @param message
	 *            the message
	 * @return the constructed exception instance (not null)
	 */
	static <T extends Throwable> T constructException(Class<T> exceptionClass, String message, Throwable cause) {
		
		try {
			Constructor<T> constructor = exceptionClass.getConstructor(String.class, Throwable.class);
			T result = constructor.newInstance(message, cause);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
