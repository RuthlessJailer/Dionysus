package com.ruthlessjailer.api.theseus;

import lombok.SneakyThrows;
import org.bukkit.plugin.PluginBase;

import java.lang.reflect.InvocationTargetException;

/**
 * @author RuthlessJailer
 */
public final class Checks {

	/**
	 * Null checks an object.
	 *
	 * @param object       the object to check
	 * @param falseMessage the message to send if the object is null
	 *
	 * @return the object if it is not null
	 *
	 * @throws NullPointerException if the object is null
	 */
	public static <T> T nullCheck(final T object, final String falseMessage) {
		if (object == null) {
			throw new NullPointerException(falseMessage);
		}
		return object;
	}

	/**
	 * Null checks the instance.
	 *
	 * @return the {@link PluginBase} instance
	 *
	 * @throws IllegalStateException if the instance is null
	 * @deprecated will be deleted soon
	 */
	@Deprecated
	public static PluginBase instanceCheck() {
		return instanceCheck("Plugin instance is null!");
	}

	/**
	 * Null checks the instance.
	 *
	 * @param falseMessage the message to send if the instance is null
	 *
	 * @return the {@link PluginBase} instance
	 *
	 * @throws IllegalStateException if the instance is null
	 * @deprecated will be deleted soon
	 */
	@Deprecated
	public static PluginBase instanceCheck(final String falseMessage) {
//		if (PluginBase.getInstance() == null) {
//			throw new IllegalStateException(falseMessage);
//		}
//		return PluginBase.getInstance();
		return null;
	}

	/**
	 * Null checks and makes sure an array is not empty.
	 *
	 * @param array        the array to check
	 * @param falseMessage the message to send if the array is null or empty
	 *
	 * @return the array if it is not null or empty
	 *
	 * @throws NullPointerException if the array is null
	 * @throws CheckException       if the array is empty
	 */
	public static final <T> T[] arrayCheck(final T[] array, final String falseMessage) {
		if (array == null) {
			throw new NullPointerException(falseMessage);
		}
		if (array.length == 0) {
			throw new CheckException(falseMessage);
		}

		return array;
	}

	/**
	 * Null checks a string and makes sure it is not empty.
	 *
	 * @param string       the string to check
	 * @param falseMessage the message to send if the string is null or empty
	 *
	 * @return the string if it is not null or empty
	 *
	 * @throws NullPointerException if the string is null
	 * @throws CheckException       if the string is empty
	 */
	public static final String stringCheck(final String string, final String falseMessage) {
		return stringCheck(string, falseMessage, true);
	}

	/**
	 * Null checks a string and makes sure it is not empty.
	 *
	 * @param string       the string to check
	 * @param falseMessage the message to send if the string is null or empty
	 * @param trim         trim the string before checking if it is empty (removes whitespace)
	 *
	 * @return the string if it is not null or empty
	 *
	 * @throws NullPointerException if the string is null
	 * @throws CheckException       if the string is empty
	 */
	public static final String stringCheck(final String string, final String falseMessage, final boolean trim) {
		if (string == null) {
			throw new NullPointerException(falseMessage);
		}
		if (string.isEmpty()) {
			throw new CheckException(falseMessage);
		}
		if (string.trim().isEmpty() && trim) {
			throw new CheckException(falseMessage);
		}
		return string;
	}

	/**
	 * Throws an exception if the condition is false.
	 *
	 * @param condition    the condition to check
	 * @param falseMessage the message to send if the condition if false
	 *
	 * @throws CheckException if the condition is false
	 */
	public static void verify(final boolean condition, final String falseMessage) {
		verify(condition, falseMessage, CheckException.class);
	}

	/**
	 * Throws an exception if the condition is false.
	 *
	 * @param condition    the condition to check
	 * @param falseMessage the message to send if the condition if false
	 * @param exception    the exception to throw if the condition is false
	 *
	 * @throws CheckException or given exception if the condition is false
	 */
	@SneakyThrows
	public static void verify(final boolean condition, final String falseMessage, final Class<? extends Throwable> exception) {
		if (!condition) {
//			throw (Throwable) ReflectUtil.newInstanceOf(ReflectUtil.getConstructor(exception.getClass(), String.class), falseMessage);
			try {
				throw exception.getConstructor(String.class).newInstance(falseMessage);
			} catch (final InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
				throw new ReflectUtil.ReflectionException(String.format("Could not throw exception %s. Perhaps it is missing a constructor?",
																		ReflectUtil.getPath(exception)), new CheckException(falseMessage));
			}
		}
	}

	/**
	 * Generic check exception.
	 */
	public static final class CheckException extends RuntimeException {
		private static final long serialVersionUID = 6172883405365570521L;

		public CheckException(final String message) {
			super(message);
		}

		public CheckException(final String message, final Throwable cause) {
			super(message, cause);
		}

		public CheckException(final Throwable cause) {
			super(cause);
		}

		CheckException(final String message, final Exception exception) {
			super(message, exception);
		}

	}

}
