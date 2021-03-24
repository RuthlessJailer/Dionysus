package com.ruthlessjailer.api.theseus;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author RuthlessJailer
 */
public final class Common {

	public static final Random RANDOM = new Random();
//	private static      TPSCounter TPS_COUNTER;

	//	TODO port this over to poseidon
//	static {//this because you can't schedule tasks while the plugin is disabled
//		FutureHandler.async.repeat((id) -> {
//			if (PluginBase.getInstance().isEnabled()) {
//				TPS_COUNTER = new TPSCounter();
//				FutureHandler.async.cancel(id);
//			}
//		}, 1000);
//	}

//	/**
//	 * A lot of {@link Common} methods are called while the plugin is starting up.
//	 *
//	 * @return whether there is a {@link TPSCounter} instance or not yet.
//	 */
//	public static boolean hasTPSCounter() { return TPS_COUNTER != null; }
//
//	/**
//	 * @return the {@link TPSCounter} instance.
//	 */
//	public static TPSCounter getTPSCounter() { return TPS_COUNTER; }
//	/*
//	 *//**
//	 * Cancels a task.
//	 *
//	 * @param taskId the id of the task to cancel
//	 *
//	 * @see BukkitScheduler#cancelTask(int)
//	 *//*
//	public static void cancelTask(final int taskId) {
//		Bukkit.getScheduler().cancelTask(taskId);
//	}
//
//	*//**
//	 * Cancels a task.
//	 *
//	 * @param task the {@link BukkitTask} to cancel
//	 *
//	 * @see BukkitTask#cancel()
//	 *//*
//	public static void cancelTask(@NonNull final BukkitTask task) {
//		task.cancel();
//	}
//
//	*//**
//	 * Cancels a task.
//	 *
//	 * @param task the {@link BukkitRunnable} to cancel
//	 *
//	 * @see BukkitRunnable#cancel()
//	 *//*
//	public static void cancelTask(@NonNull final BukkitRunnable task) {
//		task.cancel();
//	}
//
//	*//**
//	 * Schedule a task to run one tick later.
//	 *
//	 * @param task the task to run
//	 *
//	 * @return the {@link BukkitTask} representing the task
//	 *
//	 * @deprecated see {@link TaskManager}
//	 *//*
//	@Deprecated
//	public static <T extends Runnable> BukkitTask runLater(@NonNull final T task) {
//		return TaskManager.sync.later(task);
//	}
//
//	*//**
//	 * Schedule a task to run asynchronously one tick later.
//	 *
//	 * @param task the task to run
//	 *
//	 * @return the {@link BukkitTask} representing the task
//	 *
//	 * @deprecated see {@link TaskManager}
//	 *//*
//	@Deprecated
//	public static <T extends Runnable> BukkitTask runLaterAsync(@NonNull final T task) {
//		return TaskManager.async.later(task);
//	}
//
//	*//**
//	 * Schedule a task to run repeatedly one tick later.
//	 *
//	 * @param task        the task to run
//	 * @param repeatTicks the delay (in ticks) between each cycle
//	 *
//	 * @return the {@link BukkitTask} representing the task
//	 *
//	 * @deprecated see {@link TaskManager}
//	 *//*
//	@Deprecated
//	public static <T extends Runnable> BukkitTask runLaterTimer(@NonNull final T task, final int repeatTicks) {
//		return TaskManager.sync.repeat(task, repeatTicks);
//	}
//
//	*//**
//	 * Schedule a task to run repeatedly one tick later asynchronously.
//	 *
//	 * @param task        the task to run
//	 * @param repeatTicks the delay (in ticks) between each cycle
//	 *
//	 * @return the {@link BukkitTask} representing the task
//	 *
//	 * @deprecated see {@link TaskManager}
//	 *//*
//	@Deprecated
//	public static <T extends Runnable> BukkitTask runLaterTimerAsync(@NonNull final T task, final int repeatTicks) {
//		return TaskManager.async.repeat(task, repeatTicks);
//	}
//
//	*//**
//	 * Schedule a task to run zero ticks later.
//	 *
//	 * @param task the task to run
//	 *
//	 * @return the {@link BukkitTask} representing the task
//	 *
//	 * @deprecated see {@link TaskManager}
//	 *//*
//	@Deprecated
//	public static <T extends Runnable> BukkitTask runTask(@NonNull final T task) {
//		return TaskManager.sync.delay(task, 0);
//	}
//
//	*//**
//	 * Schedule a task to run asynchronously zero ticks later.
//	 *
//	 * @param task the task to run
//	 *
//	 * @return the {@link BukkitTask} representing the task
//	 *
//	 * @deprecated see {@link TaskManager}
//	 *//*
//	@Deprecated
//	public static <T extends Runnable> BukkitTask runAsync(@NonNull final T task) {
//		return TaskManager.async.delay(task, 0);
//	}
//
//	*//**
//	 * Schedule a task to run repeatedly zero ticks later.
//	 *
//	 * @param task        the task to run
//	 * @param repeatTicks the delay (in ticks) between each cycle
//	 *
//	 * @return the {@link BukkitTask} representing the task
//	 *
//	 * @deprecated see {@link TaskManager}
//	 *//*
//	@Deprecated
//	public static <T extends Runnable> BukkitTask runTimer(@NonNull final T task, final int repeatTicks) {
//		return TaskManager.sync.repeat(task, repeatTicks);
//	}
//
//	*//**
//	 * Schedule a task to run repeatedly zero ticks later asynchronously.
//	 *
//	 * @param task        the task to run
//	 * @param repeatTicks the delay (in ticks) between each cycle
//	 *
//	 * @return the {@link BukkitTask} representing the task
//	 *
//	 * @deprecated see {@link TaskManager}
//	 *//*
//	@Deprecated
//	public static <T extends Runnable> BukkitTask runTimerAsync(@NonNull final T task, final int repeatTicks) {
//		return TaskManager.async.repeat(task, repeatTicks);
//	}
//
//	*//**
//	 * Schedule a task to run.
//	 * If the plugin instance is null the task will run immediately, independent of the Bukkit scheduler.
//	 *
//	 * @param delayTicks how long to wait (in ticks)
//	 * @param task       the task to run
//	 *
//	 * @return the {@link BukkitTask} representing the task
//	 *
//	 * @deprecated see {@link TaskManager}
//	 *//*
//	@Deprecated
//	public static BukkitTask runTaskLater(final int delayTicks, @NonNull final Runnable task) {
//		return TaskManager.sync.delay(task, delayTicks);
//	}
//
//	*//**
//	 * Schedule a task to run asynchronously.
//	 * If the plugin instance is null the task will run immediately, independent of the Bukkit scheduler.
//	 *
//	 * @param delayTicks how long to wait (in ticks)
//	 * @param task       the task to run
//	 *
//	 * @return the {@link BukkitTask} representing the task
//	 *
//	 * @deprecated see {@link TaskManager}
//	 *//*
//	@Deprecated
//	public static BukkitTask runLaterAsync(final int delayTicks, @NonNull final Runnable task) {
//		return TaskManager.async.delay(task, delayTicks);
//	}
//
//	*//**
//	 * Schedule a task to run repeatedly.
//	 * If the plugin instance is null the task will run immediately, independent of the Bukkit scheduler.
//	 *
//	 * @param delayTicks  how long to wait (in ticks) before the first run
//	 * @param repeatTicks how long to wait (in ticks) between each cycle
//	 * @param task        the task to run
//	 *
//	 * @return the {@link BukkitTask} representing the task
//	 *
//	 * @deprecated see {@link TaskManager}
//	 *//*
//	@Deprecated
//	public static BukkitTask runLaterTimer(final int delayTicks, final int repeatTicks, @NonNull final Runnable task) {
//		return TaskManager.sync.repeat(task, repeatTicks);
//	}
//
//	*//**
//	 * Schedule a task to run repeatedly.
//	 * If the plugin instance is null the task will run immediately, independent of the Bukkit scheduler.
//	 *
//	 * @param delayTicks  how long to wait (in ticks) before the first run
//	 * @param repeatTicks how long to wait (in ticks) between each cycle
//	 * @param task        the task to run
//	 *
//	 * @return the {@link BukkitTask} representing the task
//	 *
//	 * @deprecated see {@link TaskManager}
//	 *//*
//	@Deprecated
//	public static BukkitTask runLaterTimerAsync(final int delayTicks, final int repeatTicks, @NonNull final Runnable task) {
//		return TaskManager.async.repeat(task, repeatTicks);
//	}*/

	/**
	 * Returns the string or a blank string if it is null.
	 *
	 * @param string the string to check
	 *
	 * @return the string or {@code ""}
	 */
	public static String getString(final String string) { return string == null ? "" : string; }

	/**
	 * Checks if a string starts with another, ignoring
	 *
	 * @param string the string to check
	 * @param prefix the string to check with
	 *
	 * @return the string or {@code ""}
	 */
	public static boolean startsWithIgnoreCase(final String string, final String prefix) { return getString(string).toLowerCase().startsWith(getString(prefix).toLowerCase()); }

	/**
	 * Returns the string or a blank string if it is null.
	 *
	 * @param string the string to check
	 * @param prefix the string to check with
	 *
	 * @return the string or {@code ""}
	 */
	public static boolean endsWithIgnoreCase(final String string, final String prefix) { return getString(string).toLowerCase().endsWith(getString(prefix).toLowerCase()); }

	/**
	 * Performs a regex escape on each character in the string i.e. places {@literal "\\"} before each reserved (regex) character.
	 *
	 * @param string the string to escape
	 *
	 * @return the escaped string or {@code ""}
	 */
	public static String escape(final String string) {
		final StringBuilder result = new StringBuilder();

		for (final char c : getString(string).toCharArray()) {
			if (c == '\\'
				|| c == '^'
				|| c == '$'
				|| c == '.'
				|| c == '|'
				|| c == '?'
				|| c == '*'
				|| c == '+'
				|| c == '-'
				|| c == '('
				|| c == ')'
				|| c == '{'
				|| c == '[') {
				result.append("\\");
			}
			result.append(c);
		}

		return result.toString();
	}

	/**
	 * Capitalizes all words and replaces underscores ({@literal "_"}) with spaces ({@literal " "}).
	 *
	 * @param input the input string
	 *
	 * @return the bountified string
	 */
	public static String bountify(final String input) {
		return Arrays.stream(input.toLowerCase().split("_")).map(StringUtils::capitalize).collect(Collectors.joining(" "));
	}

	/**
	 * Copies an array from the given start index to the end.
	 *
	 * @param array the array to copy
	 * @param start the index to copy from, inclusive
	 *
	 * @return the new array
	 */
	public static <T> T[] copyToEnd(@NonNull final T[] array, final int start) {
		return Arrays.copyOfRange(array, start, array.length);
	}

	/**
	 * Copies an array from the start until the given ending index.
	 *
	 * @param array the array to copy
	 * @param end   the index to copy to, exclusive
	 *
	 * @return the new array
	 */
	public static <T> T[] copyFromStart(@NonNull final T[] array, final int end) {
		return Arrays.copyOfRange(array, 0, end);
	}

	/**
	 * Convenience method to convert varags to array.
	 *
	 * @param objects the objects to put into an array
	 *
	 * @return the objects as an array
	 */
	@SafeVarargs
	public static <T> T[] asArray(@NonNull final T... objects) {
		return objects;
	}

	/**
	 * Appends given objects to given array.
	 *
	 * @param array   the array to append to
	 * @param objects the objects to appended to the array
	 *
	 * @return the array with appended values
	 */
	@SafeVarargs
	public static <T> T[] append(@NonNull final T[] array, final T... objects) {
		final T[] copied = Arrays.copyOf(array, array.length + objects.length);//clone

		int i = array.length;
		for (final T object : objects) {//fill
			copied[i++] = object;
		}

		return copied;
	}

	/**
	 * Prepends given objects to given array.
	 *
	 * @param array   the array to prepend to
	 * @param objects the objects to prepended to the array
	 *
	 * @return the array with prepended values
	 */
	@SafeVarargs
	public static <T> T[] prepend(@NonNull final T[] array, final T... objects) {

		T[] copied = Arrays.copyOf(array, array.length);//clone

		for (int i = objects.length - 1; i >= 0; i--) {//reverse fill so it's in order
			copied = prepend(copied, objects[i]);//add an element
		}

		return copied;
	}

	/**
	 * Prepends given object to given array.
	 *
	 * @param array  the array to prepend to
	 * @param object the object to prepended to the array
	 *
	 * @return the array with the prepended value
	 */
	public static <T> T[] prepend(@NonNull final T[] array, @NonNull final T object) {

		final T[] copied = Arrays.copyOf(array, array.length + 1);

		copied[0] = object;

		System.arraycopy(array, 0, copied, 1, array.length);

		return copied;
	}


	/**
	 * Throws an exception, regardless of its constructor visibility.
	 *
	 * @param exception the class to instantiate a new instance of and throw
	 */
	@SneakyThrows
	public static void exception(@NonNull final Class<? extends Throwable> exception) {
		throw ReflectUtil.newInstanceOf(exception);
	}

	/**
	 * Throws an exception, regardless of its constructor visibility.
	 *
	 * @param exception the class to instantiate a new instance of and throw
	 * @param message   the message to with which to instantiate the exception
	 */
	@SneakyThrows
	public static void exception(@NonNull final Class<? extends Throwable> exception, @NonNull final String message) {
		throw ReflectUtil.newInstanceOf(exception, message);
	}

	/**
	 * Throws an exception, regardless of its constructor visibility.
	 *
	 * @param exception the class to instantiate a new instance of and throw
	 * @param message   the message to with which to instantiate the exception
	 * @param cause     the {@link Throwable} that caused it
	 */
	@SneakyThrows
	public static void exception(@NonNull final Class<? extends Throwable> exception, @NonNull final String message, final Throwable cause) {
		throw ReflectUtil.newInstanceOf(exception, message, cause);
	}

	/**
	 * Throws an exception, regardless of its constructor visibility.
	 *
	 * @param exception the class to instantiate a new instance of and throw
	 * @param cause     the {@link Throwable} that caused it
	 */
	@SneakyThrows
	public static void exception(@NonNull final Class<? extends Throwable> exception, final Throwable cause) {
		throw ReflectUtil.newInstanceOf(exception, cause);
	}

	/**
	 * Converts an array into a different type.
	 *
	 * @param array       the array to convert
	 * @param destination the destination array; if it is of bigger size it will be used, otherwise a new array of the same class type will be returned
	 * @param converter   the {@link TypeConverter} used to convert
	 *
	 * @return the converted array
	 */
	public static <O, N> N[] convert(@NonNull final O[] array, @NonNull final N[] destination, @NonNull final TypeConverter<O, N> converter) {

		N[] result = destination;

		if (destination.length < array.length) {//create new array with needed size
			result = Arrays.copyOf(destination, array.length);
		}

		int i = 0;
		for (final O old : array) {
			result[i] = converter.convert(old);
			i++;
		}

		return result;
	}

	/**
	 * Converts an iterable into a different type iterable.
	 *
	 * @param iterable  the {@link Iterable} to convert
	 * @param converter the {@link TypeConverter} used to convert
	 *
	 * @return the converted iterable
	 */
	public static <O, N> List<N> convert(@NonNull final Iterable<O> iterable,
										 @NonNull final TypeConverter<O, N> converter) {

		final List<N> result = new ArrayList<>();

		for (final O old : iterable) {
			result.add(converter.convert(old));
		}

		return result;
	}

	/**
	 * Gets the names of all online players.
	 *
	 * @return a {@link List} containing all the names of current online players
	 */
	public static List<String> getPlayerNames() {
		return convert(Bukkit.getOnlinePlayers(), Player::getName);
	}

	/**
	 * Gets the display names of all online players.
	 *
	 * @return a {@link List} containing all the display names of current online players
	 */
	public static List<String> getPlayerDisplayNames() {
		return convert(Bukkit.getOnlinePlayers(), Player::getDisplayName);
	}

//	/**
//	 * Checks if given {@link Permissible} {@link Permissible#isOp() is op}, has the {@link CommandBase#getStarPermissionSyntax() star permission}, or has the given
//	 * permission.
//	 *
//	 * @param permissible the {@link Permissible} to check
//	 * @param permission  the permission to check (last resort)
//	 *
//	 * @return {@code true} if the {@link Permissible} {@link Permissible#isOp() is op}, has the {@link CommandBase#getStarPermissionSyntax() star permission}, or
//	 * 		has the
//	 * 		given permission; {@code false} if either argument is null or the given requirements are not met
//	 */
//	public static boolean hasPermission(final Permissible permissible, final String permission) {
//		if (permissible == null) {
//			return false;
//		}
//
//		return permissible.isOp() ||
//			   permissible.hasPermission(CommandBase.getStarPermissionSyntax()) ||
//			   (permission != null &&
//				permissible.hasPermission(permission));
//	}

	/**
	 * Returns whether or not the given {@link ItemStack item} is capable of having {@link ItemMeta meta}.
	 *
	 * @param item the {@link ItemStack} to check
	 *
	 * @return {@code true} if the {@link ItemStack item} can have {@link ItemMeta meta}, {@code false} if the {@link ItemStack item} is null or cannot have
	 *        {@link ItemMeta meta}
	 */
	public static boolean hasItemMeta(final ItemStack item) {
		if (item == null) {
			return false;
		}

		return Bukkit.getItemFactory().getItemMeta(item.getType()) != null;
	}

	/**
	 * Returns a random item from the list.
	 *
	 * @param list the {@link List} to choose from
	 *
	 * @return a random item from the list
	 */
	public static <T> T selectRandom(@NonNull final List<T> list) {
		if (list.isEmpty()) {
			return null;
		}
		return list.get(RANDOM.nextInt(list.size()));
	}

	/**
	 * Returns a random item from the array.
	 *
	 * @param array the array to choose from
	 *
	 * @return a random item from the array
	 */
	@SafeVarargs
	public static <T> T selectRandom(@NonNull final T... array) {
		if (array.length == 0) {
			return null;
		}
		return array[RANDOM.nextInt(array.length)];
	}

}
