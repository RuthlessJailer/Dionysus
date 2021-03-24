package com.ruthlessjailer.api.theseus.io

import com.ruthlessjailer.api.theseus.Common
import java.io.File
import java.io.InputStream
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.util.concurrent.locks.ReentrantLock

/**
 * @author RuthlessJailer
 */
abstract class TextFile(path: String, content: String = "") : IFile {

	companion object {
		@JvmStatic
		fun removeStartingSeparatorChar(path: String): String = if (path.startsWith("/") || path.startsWith("\\") || path.startsWith(File.separator)) path.substring(1) else path
	}

	final override val path = path.replace(Regex("[/\\\\]"), Common.escape(File.separator))

	final override val file: File = File(path)

	@Volatile
	final override var contents: String = content

	var charset: Charset = StandardCharsets.UTF_8

	private val lock = ReentrantLock()

//	val manager = ConfigManager(this)

	override fun load(): IFile {
		setContents(read())

		if (!file.exists()) {
			file.createNewFile()
			val `in` = getResourceAsStream(removeStartingSeparatorChar(path))
			Files.copy(`in` ?: return this, file.toPath(), StandardCopyOption.REPLACE_EXISTING)
		}

		return this
	}

	override fun save(): IFile = write(contents)

	override fun setContents(string: String?, append: Boolean): IFile {
		synchronized(lock) {
			contents = if (append) contents.plus(string) else string ?: "null"
		}

		return this
	}

	override fun read(): String = DiskUtil.read(file, charset) ?: "null"
	override fun write(string: String?, append: Boolean): IFile {
		DiskUtil.write(file, string ?: "null", charset, append)
		return this
	}

	abstract fun getResourceAsStream(path: String): InputStream?

	/**
	 * Utility function to reload the file, fixing default values in the process.
	 *
	 * @return a new instance of the child class.
	 */
	abstract fun reload(): TextFile

	/**
	 * Creates a new instance of the class.
	 *
	 * @return a new instance of the child class
	 */
	abstract fun getNewInstance(content: String): TextFile
}