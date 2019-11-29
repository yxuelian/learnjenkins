package com.topcoder.commons.utils;

import java.io.File;
import java.lang.*;
import java.util.Collection;
import java.util.Map;

/**
 * This is a utility class that provides static methods for checking whether
 * some parameter value meets specific criteria (not null, not empty, positive,
 * negative, etc). If criteria is not met, this utility throws
 * IllegalArgumentException.
 * 
 * Thread Safety: This class is immutable and thread safe when collection
 * parameters passed to it are used by the caller in thread safe manner.
 * 
 * @author yangxuelian
 */
public class ParameterCheckUtility {
	/**
	 * Empty private constructor.
	 */
	private ParameterCheckUtility() {
	}

	/**
	 * Checks whether the given value is not null. And if this condition is not
	 * met, the specified exception is thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given value is null
	 * @param name
	 *            the parameter name
	 * @param value
	 *            the value of parameter to be checked
	 */
	public static void checkNotNull(Object value, String name) throws IllegalArgumentException {
		if (value == null) {
			throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should not be null");
		}
	}

	/**
	 * Checks whether the given string value is not empty (without trimming).
	 * And if this condition is not met, the specified exception is thrown. Note
	 * that if value is null, exception is not thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given value is empty
	 * @param name
	 *            the parameter name
	 * @param value
	 *            the value of parameter to be checked
	 */
	public static void checkNotEmpty(String value, String name) throws IllegalArgumentException {
		if (value != null && value.equals("")) {
			throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should not be empty");
		}
	}

	/**
	 * Checks whether the given string value is not empty (after trimming). And
	 * if this condition is not met, the specified exception is thrown. Note
	 * that if value is null, exception is not thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given value is empty
	 * @param name
	 *            the parameter name
	 * @param value
	 *            the value of parameter to be checked
	 */
	public static void checkNotEmptyAfterTrimming(String value, String name) throws IllegalArgumentException {
		if (value != null && value.trim().equals("")) {
			throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should not be empty (trimmed)");
		}
	}

	/**
	 * Checks whether the given string value is not null, nor empty (without
	 * trimming). And if this condition is not met, the specified exception is
	 * thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given value is null or empty
	 * @param name
	 *            the parameter name
	 * @param value
	 *            the value of parameter to be checked
	 */
	public static void checkNotNullNorEmpty(String value, String name) throws IllegalArgumentException {
		checkNotNull(value, name);
		checkNotEmpty(value, name);
	}

	/**
	 * Checks whether the given string value is not null, nor empty (after
	 * trimming). And if this condition is not met, the specified exception is
	 * thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given value is null or empty
	 * @param name
	 *            the parameter name
	 * @param value
	 *            the value of parameter to be checked
	 */
	public static void checkNotNullNorEmptyAfterTrimming(String value, String name) throws IllegalArgumentException {
		checkNotNull(value, name);
		checkNotEmptyAfterTrimming(value, name);
	}

	/**
	 * Checks whether the given value is an instance of the specified type. And
	 * if this condition is not met, the specified exception is thrown. Note
	 * that if value is null, exception is thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given value is not an instance of the expected type
	 * @param expectedType
	 *            the expected type of the value
	 * @param name
	 *            the parameter name
	 * @param value
	 *            the value of parameter to be checked
	 */
	public static void checkInstance(Object value, Class<?> expectedType, String name) throws IllegalArgumentException {
		if (! expectedType.isInstance(value)) {
			throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should be an instance of " + expectedType.getName());
		}
	}

	/**
	 * Checks whether the given value is null or an instance of the specified
	 * type. And if this condition is not met, the specified exception is
	 * thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given value is not null and not an instance of the
	 *             expected type
	 * @param expectedType
	 *            the expected type of the value
	 * @param name
	 *            the parameter name
	 * @param value
	 *            the value of parameter to be checked
	 */
	public static void checkNullOrInstance(Object value, Class<?> expectedType, String name) throws IllegalArgumentException {
		if (value != null && ! expectedType.isInstance(value)) {
			throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should be null or an instance of " + expectedType.getName());
		}
	}

	/**
	 * Checks whether the given File instance points to an existing file or
	 * directory. And if this condition is not met, the specified exception is
	 * thrown. Note that if file is null, exception is not thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given file value represents a not existing file or
	 *             directory
	 * @param file
	 *            the value of File parameter to be checked
	 * @param name
	 *            the parameter name
	 */
	public static void checkExists(File file, String name) throws IllegalArgumentException {
		if (file != null && ! file.exists()) {
			throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should point to an existing file or directory");
		}
	}

	/**
	 * Checks whether the given File instance points to an existing file. And if
	 * this condition is not met, the specified exception is thrown. Note that
	 * if file is null, exception is not thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given file value represents a not existing file
	 * @param file
	 *            the value of File parameter to be checked
	 * @param name
	 *            the parameter name
	 */
	public static void checkIsFile(File file, String name) throws IllegalArgumentException {
		if (file != null && ! file.isFile()) {
			throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should point to an existing file");
		}
	}

	/**
	 * Checks whether the given File instance points to an existing directory.
	 * And if this condition is not met, the specified exception is thrown. Note
	 * that if file is null, exception is not thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given file value represents a not existing directory
	 * @param file
	 *            the value of File parameter to be checked
	 * @param name
	 *            the parameter name
	 */
	public static void checkIsDirectory(File file, String name) throws IllegalArgumentException {
		if (file != null && ! file.isDirectory()) {
			throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should point to an existing directory");
		}
	}

	/**
	 * Checks whether the given collection is not empty. And if this condition
	 * is not met, the specified exception is thrown. Note that if collection is
	 * null, exception is not thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given collection is empty
	 * @param name
	 *            the parameter name
	 * @param collection
	 *            the value of collection parameter to be checked
	 */
	public static void checkNotEmpty(Collection<?> collection, String name) throws IllegalArgumentException {
		if (collection != null && collection.isEmpty()) {
			throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should not be empty");
		}
	}

	/**
	 * Checks whether the given collection is not null, nor empty. And if this
	 * condition is not met, the specified exception is thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given collection is null or empty
	 * @param name
	 *            the parameter name
	 * @param collection
	 *            the value of collection parameter to be checked
	 */
	public static void checkNotNullNorEmpty(Collection<?> collection, String name) throws IllegalArgumentException {
		checkNotNull(collection, name);
		checkNotEmpty(collection, name);
	}

	/**
	 * Checks whether the given map value is not empty. And if this condition is
	 * not met, the specified IllegalArgumentException is thrown. Note that if map is null,
	 * exception is not thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given map is empty
	 * @param name
	 *            the parameter name
	 * @param map
	 *            the value of map parameter to be checked
	 */
	public static void checkNotEmpty(Map<?, ?> map, String name) throws IllegalArgumentException {
		if (map != null && map.isEmpty()) {
			throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should not be empty");
		}
	}

	/**
	 * Checks whether the given map is not null, nor empty. And if this
	 * condition is not met, the specified exception is thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given map is null or empty
	 * @param name
	 *            the parameter name
	 * @param map
	 *            the value of map parameter to be checked
	 */
	public static void checkNotNullNorEmpty(Map<?, ?> map, String name) throws IllegalArgumentException {
		checkNotNull(map, name);
		checkNotEmpty(map, name);
	}

	/**
	 * Checks whether the given collection doesn't contain null elements. And if
	 * this condition is not met, the specified exception is thrown. Note that
	 * if collection is null, exception is not thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given collection contains null element
	 * @param name
	 *            the parameter name
	 * @param collection
	 *            the value of collection parameter to be checked
	 */
	public static void checkNotNullElements(Collection<?> collection, String name) throws IllegalArgumentException {
		if (collection == null) {
			return;
		}
		boolean containsNull = false;
		for (Object element : collection) {
			if (element == null) {
				containsNull = true;
				break;
			}
		}
		if (containsNull) {
			throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should not contain null");
		}
	}

	/**
	 * Checks whether the given collection doesn't contain empty elements
	 * (strings, collections, maps). And if this condition is not met, the
	 * specified exception is thrown. Note that if collection is null, exception
	 * is not thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given collection contains an empty element (string,
	 *             collection or map)
	 * @param trimStrings
	 *            true if strings should be trimmed before emptiness check,
	 *            false otherwise
	 * @param name
	 *            the parameter name
	 * @param collection
	 *            the value of collection parameter to be checked
	 */
	public static void checkNotEmptyElements(Collection<?> collection, boolean trimStrings, String name) throws IllegalArgumentException {
		if (collection == null) {
			return;
		}
		boolean containsEmpty = false;
		for (Object element : collection) {
			if (element instanceof String) {
				String str = (String) element;
				if (trimStrings) {
					str = str.trim();
				}
				if (str.equals("")) {
					containsEmpty = true;
					break;
				}
			} else if (element instanceof Collection) {
				if (((Collection) element).isEmpty()) {
					containsEmpty = true;
					break;
				}
			} else if (element instanceof Map) {
				containsEmpty = true;
				break;
			}
		}
		if (containsEmpty) {
			throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should not contain empty elements");
		}
	}

	/**
	 * Checks whether the given map doesn't contain a null key. And if this
	 * condition is not met, the specified exception is thrown. Note that if map
	 * is null, exception is not thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given map contains null key
	 * @param name
	 *            the parameter name
	 * @param map
	 *            the value to be checked
	 */
	public static void checkNotNullKeys(Map<?, ?> map, String name) throws IllegalArgumentException {
		if (map != null && map.containsKey(null)) {
			throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should not contain null key");
		}
	}

	/**
	 * Checks whether the given map doesn't contain a null value. And if this
	 * condition is not met, the specified exception is thrown. Note that if map
	 * is null, exception is not thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given map contains null value
	 * @param name
	 *            the parameter name
	 * @param map
	 *            the value of map parameter to be checked
	 */
	public static void checkNotNullValues(Map<?, ?> map, String name) throws IllegalArgumentException {
		if (map != null && map.containsValue(null)) {
			throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should not contain null value");
		}
	}

	/**
	 * Checks whether the given map doesn't contain empty keys (strings,
	 * collection, maps). And if this condition is not met, the specified
	 * exception is thrown. Note that if map is null, exception is not thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given map contains an empty key (string, collection or
	 *             map)
	 * @param trimStrings
	 *            true if strings should be trimmed before emptiness check,
	 *            false otherwise
	 * @param name
	 *            the parameter name
	 * @param map
	 *            the value of map parameter to be checked
	 */
	public static void checkNotEmptyKeys(Map<?, ?> map, boolean trimStrings, String name) throws IllegalArgumentException {
		if (map == null) {
			return;
		}
		boolean containsEmpty = false;
		for (Object key : map.keySet()) {
			if (key instanceof String) {
				String str = (String) key;
				if (trimStrings) {
					str = str.trim();
				}
				if (str.equals("")) {
					containsEmpty = true;
					break;
				}
			} else if (key instanceof Collection) {
				if (((Collection) key).isEmpty()) {
					containsEmpty = true;
					break;
				}
			} else if (key instanceof Map) {
				if (((Map) key).isEmpty()) {
					containsEmpty = true;
					break;
				}
			}
		}
		if (containsEmpty) {
			throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should not contain empty keys");
		}
	}

	/**
	 * Checks whether the given map doesn't contain empty values (strings,
	 * collection, maps). And if this condition is not met, the specified
	 * exception is thrown. Note that if map is null, exception is not thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given map contains an empty value (string, collection
	 *             or map)
	 * @param trimStrings
	 *            true if strings should be trimmed before emptiness check,
	 *            false otherwise
	 * @param name
	 *            the parameter name
	 * @param map
	 *            the value of map parameter to be checked
	 */
	public static void checkNotEmptyValues(Map<?, ?> map, boolean trimStrings, String name) throws IllegalArgumentException {
		if (map == null) {
			return;
		}
		boolean containsEmpty = false;
		for (Object value : map.values()) {
			if (value instanceof String) {
				String str = (String) value;
				if (trimStrings) {
					str = str.trim();
				}
				if (str.equals("")) {
					containsEmpty = true;
					break;
				}
			} else if (value instanceof Collection) {
				if (((Collection) value).isEmpty()) {
					containsEmpty = true;
					break;
				}
			} else if (value instanceof Map) {
				if (((Map) value).isEmpty()) {
					containsEmpty = true;
					break;
				}
			}
		}
		if (containsEmpty) {
			throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should not contain empty values");
		}
	}

	/**
	 * Checks whether the given value is negative. And if this condition is not
	 * met, the specified exception is thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given value is not negative
	 * @param name
	 *            the parameter name
	 * @param value
	 *            the value of parameter to be checked
	 */
	public static void checkNegative(double value, String name) throws IllegalArgumentException {
		if (value > 0) {
			throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should not contain empty values");
		}
	}

	/**
	 * Checks whether the given value is positive. And if this condition is not
	 * met, the specified exception is thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given value is not positive
	 * @param name
	 *            the parameter name
	 * @param value
	 *            the value of parameter to be checked
	 */
	public static void checkPositive(double value, String name) throws IllegalArgumentException {
		if (value <= 0) {
			throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should be positive");
		}
	}

	/**
	 * Checks whether the given value is not negative. And if this condition is
	 * not met, the specified exception is thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given value is negative
	 * @param name
	 *            the parameter name
	 * @param value
	 *            the value of parameter to be checked
	 */
	public static void checkNotNegative(double value, String name) throws IllegalArgumentException {
		if (value < 0) {
			throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should be not negative");
		}
	}

	/**
	 * Checks whether the given value is not positive. And if this condition is
	 * not met, the specified exception is thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given value is positive
	 * @param name
	 *            the parameter name
	 * @param value
	 *            the value of parameter to be checked
	 */
	public static void checkNotPositive(double value, String name) throws IllegalArgumentException {
		if (value > 0) {
			throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should be not negative");
		}
	}

	/**
	 * Checks whether the given value is not equal to zero. And if this
	 * condition is not met, the specified exception is thrown. Note: Don't
	 * forget about "Floating-Point Accuracy/Comparison" problems when checking
	 * floating point numbers.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given value is equal to 0
	 * @param name
	 *            the parameter name
	 * @param value
	 *            the value of parameter to be checked
	 */
	public static void checkNotZero(double value, String name) throws IllegalArgumentException {
		if (value == 0) {
			throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should not be equal to 0");
		}
	}

	/**
	 * Checks whether the given value is greater than (greater than or equal to,
	 * if inclusive is true) than the specified number. And if this condition is
	 * not met, the specified exception is thrown. Note: Don't forget about
	 * "Floating-Point Accuracy/Comparison" problems when checking floating
	 * point numbers. Inclusive comparison is recommended to be used with
	 * integral types only.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given value is not greater than (not greater than and
	 *             not equal to, if inclusive is true) than the specified number
	 * @param number
	 *            the number the value should be compared to
	 * @param inclusive
	 *            true if "greater than or equal to" check should be performed,
	 *            false if "greater than" check should be performed
	 * @param name
	 *            the parameter name
	 * @param value
	 *            the value of parameter to be checked
	 */
	public static void checkGreaterThan(double value, double number, boolean inclusive, String name) throws IllegalArgumentException {
		if (inclusive) {
			if (value < number) {
				throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should be greater than or equal to " + number);
			}
		} else {
			if (value <= number) {
				throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should be greater than " + number);
			}
		}
	}

	/**
	 * Checks whether the given value is less than (less than or equal to, if
	 * inclusive is true) than the specified number. And if this condition is
	 * not met, the specified exception is thrown. Note: Don't forget about
	 * "Floating-Point Accuracy/Comparison" problems when checking floating
	 * point numbers. Inclusive comparison is recommended to be used with
	 * integral types only.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given value is not less than (not less than and not
	 *             equal to, if inclusive is true) than the specified number
	 * @param number
	 *            the number the value should be compared to
	 * @param inclusive
	 *            true if "less than or equal to" check should be performed,
	 *            false if "less than" check should be performed
	 * @param name
	 *            the parameter name
	 * @param value
	 *            the value of parameter to be checked
	 */
	public static void checkLessThan(double value, double number, boolean inclusive, String name) throws IllegalArgumentException {
		if (inclusive) {
			if (value > number) {
				throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should be less than or equal to " + number);
			}
		} else {
			if (value >= number) {
				throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should be less than " + number);
			}
		}
	}

	/**
	 * Checks whether the given value is in the specified range. And if this
	 * condition is not met, the specified exception is thrown. Note: Don't
	 * forget about "Floating-Point Accuracy/Comparison" problems when checking
	 * floating point numbers. Inclusive comparison is recommended to be used
	 * with integral types only.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given value is out of the specified range
	 * @param name
	 *            the parameter name
	 * @param toInclusive
	 *            true if end value is included into the range, false otherwise
	 * @param from
	 *            the start value of the range
	 * @param to
	 *            the end value of the range
	 * @param fromInclusive
	 *            true if start value is included into the range, false
	 *            otherwise
	 * @param value
	 *            the value of parameter to be checked
	 */
	public static void checkInRange(double value, double from, double to, boolean fromInclusive, boolean toInclusive,
			String name) throws IllegalArgumentException {
		boolean valid;
		if (fromInclusive) {
			valid = (value >= from);
		} else {
			valid = (value > from);
		}
		if (valid) {
			if (toInclusive) {
				valid = (value <= to);
			} else {
				valid = (value < to);
			}
		}
		if (! valid) {
			String message = name + " should be in the range " + (fromInclusive ? "[" : "(") + from + ", " + to + (toInclusive ? "]" : ")");
			throw ExceptionHelper.constructException(IllegalArgumentException.class, message);
		}
	}

	/**
	 * Checks whether the given value is negative. And if this condition is not
	 * met, the specified exception is thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given value is not negative
	 * @param name
	 *            the parameter name
	 * @param value
	 *            the value of parameter to be checked
	 */
	public static void checkNegative(long value, String name) throws IllegalArgumentException {
		if (value >= 0) {
			throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should be negative");
		}
	}

	/**
	 * Checks whether the given value is positive. And if this condition is not
	 * met, the specified exception is thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given value is not positive
	 * @param name
	 *            the parameter name
	 * @param value
	 *            the value of parameter to be checked
	 */
	public static void checkPositive(long value, String name) throws IllegalArgumentException {
		if (value <= 0) {
			throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should be positive");
		}
	}

	/**
	 * Checks whether the given value is not negative. And if this condition is
	 * not met, the specified exception is thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given value is negative
	 * @param name
	 *            the parameter name
	 * @param value
	 *            the value of parameter to be checked
	 */
	public static void checkNotNegative(long value, String name) throws IllegalArgumentException {
		if (value < 0) {
			throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should be positive");
		}
	}

	/**
	 * Checks whether the given value is not positive. And if this condition is
	 * not met, the specified exception is thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given value is positive
	 * @param name
	 *            the parameter name
	 * @param value
	 *            the value of parameter to be checked
	 */
	public static void checkNotPositive(long value, String name) throws IllegalArgumentException {
		if (value > 0) {
			throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should be not positive");
		}
	}

	/**
	 * Checks whether the given value is not equal to zero. And if this
	 * condition is not met, the specified exception is thrown. Note: Don't
	 * forget about "Floating-Point Accuracy/Comparison" problems when checking
	 * floating point numbers.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given value is equal to 0
	 * @param name
	 *            the parameter name
	 * @param value
	 *            the value of parameter to be checked
	 */
	public static void checkNotZero(long value, String name) throws IllegalArgumentException {
		if (value == 0) {
			throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should be not positive");
		}
	}

	/**
	 * Checks whether the given value is greater than (greater than or equal to,
	 * if inclusive is true) than the specified number. And if this condition is
	 * not met, the specified exception is thrown. Note: Don't forget about
	 * "Floating-Point Accuracy/Comparison" problems when checking floating
	 * point numbers. Inclusive comparison is recommended to be used with
	 * integral types only.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given value is not greater than (not greater than and
	 *             not equal to, if inclusive is true) than the specified number
	 * @param number
	 *            the number the value should be compared to
	 * @param inclusive
	 *            true if "greater than or equal to" check should be performed,
	 *            false if "greater than" check should be performed
	 * @param name
	 *            the parameter name
	 * @param value
	 *            the value of parameter to be checked
	 */
	public static void checkGreaterThan(long value, long number, boolean inclusive, String name) throws IllegalArgumentException {
		if (inclusive) {
			if (value < number) {
				throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should be greater than or equal to " + number);
			}
		} else {
			if (value <= number) {
				throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should be greater than " + number);
			}
		}
	}

	/**
	 * Checks whether the given value is less than (less than or equal to, if
	 * inclusive is true) than the specified number. And if this condition is
	 * not met, the specified exception is thrown. Note: Don't forget about
	 * "Floating-Point Accuracy/Comparison" problems when checking floating
	 * point numbers. Inclusive comparison is recommended to be used with
	 * integral types only.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given value is not less than (not less than and not
	 *             equal to, if inclusive is true) than the specified number
	 * @param number
	 *            the number the value should be compared to
	 * @param inclusive
	 *            true if "less than or equal to" check should be performed,
	 *            false if "less than" check should be performed
	 * @param name
	 *            the parameter name
	 * @param value
	 *            the value of parameter to be checked
	 */
	public static void checkLessThan(long value, long number, boolean inclusive, String name) throws IllegalArgumentException {
		if (inclusive) {
			if (value > number) {
				throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should be less than or equal to " + number);
			}
		} else {
			if (value >= number) {
				throw ExceptionHelper.constructException(IllegalArgumentException.class, name + " should be less than " + number);
			}
		}
	}

	/**
	 * Checks whether the given value is in the specified range. And if this
	 * condition is not met, the specified exception is thrown. Note: Don't
	 * forget about "Floating-Point Accuracy/Comparison" problems when checking
	 * floating point numbers. Inclusive comparison is recommended to be used
	 * with integral types only.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given value is out of the specified range
	 * @param name
	 *            the parameter name
	 * @param toInclusive
	 *            true if end value is included into the range, false otherwise
	 * @param from
	 *            the start value of the range
	 * @param to
	 *            the end value of the range
	 * @param fromInclusive
	 *            true if start value is included into the range, false
	 *            otherwise
	 * @param value
	 *            the value of parameter to be checked
	 */
	public static void checkInRange(long value, long from, long to, boolean fromInclusive, boolean toInclusive,
			String name) throws IllegalArgumentException {
		boolean valid;
		if (fromInclusive) {
			valid = (value >= from);
		} else {
			valid = (value > from);
		}
		if (valid) {
			if (toInclusive) {
				valid = (value <= to);
			} else {
				valid = (value < to);
			}
		}
		if (! valid) {
			String message = name + " should be in the range " + (fromInclusive ? "[" : "(") + from + ", " + to + (toInclusive ? "]" : ")");
			throw ExceptionHelper.constructException(IllegalArgumentException.class, message);
		}
	}

	/**
	 * Retrieves the parameter value name from the specified parameter name.
	 * 
	 * @param paramName
	 *            the parameter name
	 * @return the constructed parameter value name
	 */
	private static String getParamValueName(String paramName) {
		return "The '" + paramName + "' parameter";
	}
}
