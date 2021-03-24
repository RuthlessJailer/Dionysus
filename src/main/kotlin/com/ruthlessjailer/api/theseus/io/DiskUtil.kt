package com.ruthlessjailer.api.theseus.io

import com.ruthlessjailer.api.theseus.Checks
import com.ruthlessjailer.api.theseus.Common
import lombok.SneakyThrows
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.nio.file.Files

object DiskUtil {

	/**
	 * Fetches the file for the given path.
	 *
	 * @param path the path to the file; slashes / will be replaced with [File.separator]
	 *
	 * @return the found file
	 */
	fun parseFile(path: String): File = File(path.replace("/", Common.escape(File.separator)))


	/**
	 * Attempts to fetch or create the given file.
	 *
	 * @param path the path to the file; slashes / will be replaced with [File.separator]
	 *
	 * @return the found or created file
	 *
	 * @throws IOException if the file or its parent files were unable to be created
	 */
	fun getFile(path: String): File {
		val file = File(path.replace("/", Common.escape(File.separator)))
		createFile(file)
		return file
	}

	/**
	 * Attempts to create a file and its parent directories if it doesn't exist.
	 *
	 * @param file the file
	 *
	 * @throws IOException if the file or its parent files were unable to be created
	 */
	@SneakyThrows
	fun createFile(file: File) {
		if (!file.exists()) {
			Checks.verify(if (!file.parentFile.exists()) file.mkdirs() && file.createNewFile() else file.createNewFile(), "Failed to create file.", IOException::class.java)
		}
	}

	/**
	 * Writes to the given file.
	 *
	 * @param file    the [File] to write to
	 * @param content what to write to the file
	 */
	fun write(file: File, content: String) = write(file, content, false)

	/**
	 * Writes to the given file.
	 *
	 * @param file    the [File] to write to
	 * @param content what to write to the file
	 * @param append  whether or not to append to the end of the file or overwrite
	 */
	fun write(file: File, content: String, append: Boolean) = write(file, content, StandardCharsets.UTF_8, append)

	/**
	 * Writes to the given file.
	 *
	 * @param file    the [File] to write to
	 * @param content what to write to the file
	 * @param charset the [Charset] to encode the string with
	 * @param append  whether or not to append to the end of the file or overwrite
	 */
	fun write(file: File, content: String, charset: Charset, append: Boolean) = write(file, content.toByteArray(charset), append)

	/**
	 * Writes to the given file.
	 *
	 * @param file    the [File] to write to
	 * @param content what to write to the file
	 * @param append  whether or not to append to the end of the file or overwrite
	 */
	fun write(file: File, content: ByteArray, append: Boolean) {
//		Files.write(Paths.get(file.toURI()), content, append ? StandardOpenOption.APPEND : StandardOpenOption.WRITE);
// WRITE will just write to front; won't erase whole file, unlike second option
		val out = FileOutputStream(getFile(file.path), append) //using this for append option
		out.write(content)
		out.close()
	}

	/**
	 * Reads in a file as a string.
	 *
	 * @param file the [File] to read
	 *
	 * @return The string representation of the contents or `null` if the file does not exist.
	 */
	fun read(file: File): String? = read(file, StandardCharsets.UTF_8)

	/**
	 * Reads in a file as a string.
	 *
	 * @param file    the [File] to read
	 * @param charset the [Charset] to decode the string with
	 *
	 * @return The string representation of the contents or `null` if the file does not exist.
	 */
	fun read(file: File, charset: Charset): String? = readBytes(file)?.let { String(it, charset) }

	/**
	 * Reads in a file as a byte array.
	 *
	 * @param file the [File] to read
	 *
	 * @return The byte array of the contents or `null` if the file does not exist.
	 */
	fun readBytes(file: File): ByteArray? {
		if (!file.exists()) {
			return ByteArray(0)
		}
		return if (file.length() == 0L) {
			ByteArray(0)
		} else Files.readAllBytes(file.toPath())
	}

}