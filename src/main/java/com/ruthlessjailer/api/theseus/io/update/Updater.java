package com.ruthlessjailer.api.theseus.io.update;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ruthlessjailer.api.theseus.Checks;
import com.ruthlessjailer.api.theseus.ReflectUtil;
import com.ruthlessjailer.api.theseus.io.DiskUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;

/**
 * @author RuthlessJailer
 */
@Getter
public class Updater {

	private static final String USER_AGENT = "Theseus API Updater by RuthlessJailer";

	private static final String RESOURCE = "https://api.spiget.org/v2/resources/";
	private static final String DOWNLOAD = "/download";
	private static final String VERSIONS = "/versions";
	private static final String PAGE     = "?page=";
	private static final int    BYTE_BUF = 2048;

	@Setter
	private       Messages   messages = new Messages("Update found!",
													 "No new updates available.",
													 "Checking for updates...",
													 "Error while checking for updates.",
													 "Error while dowloading update.",
													 "Downloading...",
													 "Please report the stacktrace below.",
													 "There is an update available. Do you want to install it?",
													 "(${OLD} -> ${NEW})");
	private final JavaPlugin plugin;
	private final int        id;
	private final File       jar;
	private final File       folder;
	private       Result     result;
	private       String     latest;
	private final String     current;
	@Setter
	private       boolean    log      = true;

	public Updater(final int id) {
		this((JavaPlugin) Checks.instanceCheck(), id);
	}

	public Updater(@NonNull final JavaPlugin plugin, final int id) {
		this.plugin  = plugin;
		this.id      = id;
		this.jar     = ReflectUtil.invokeMethod(JavaPlugin.class, "getFile", plugin);
		this.folder  = DiskUtil.INSTANCE.getFile(Bukkit.getWorldContainer().getPath() + "/plugins");
		this.current = plugin.getDescription().getVersion().replaceAll("[^0-9.]", "");
	}

	public String getResourceURL() {
		return RESOURCE + this.id;
	}

	public boolean isValidResource() {
		try {
			final URL               url        = new URL(getResourceURL());
			final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.addRequestProperty("User-Agent", USER_AGENT);

			final int code = connection.getResponseCode();

			if (code != 200) {//bad id
				connection.disconnect();
				this.result = Result.BAD_ID;
				return false;
			}

		} catch (final IOException e) {
			error(this.messages.ERROR_CHECKING, this.messages.PLEASE_REPORT);
			e.printStackTrace();
			this.result = Result.FAIL;
			return false;
		}

		return true;
	}

	public boolean canUpdate() {
		try {

			info(this.messages.CHECKING);

			final JsonArray array;

			int i = 1;
			while (getPage(i).getAsJsonArray().size() != 0) {
				i++;
			}

			array = getPage(i - 1).getAsJsonArray();

			final JsonObject object = array.get(array.size() - 1).getAsJsonObject();

			final String version = object.get("name").getAsString().replaceAll("[^0-9.]", "");

			this.latest = version;

			final boolean canUpdate = !this.current.equalsIgnoreCase(version);

			this.result = canUpdate ? Result.UPDATE_EXISTS : Result.NO_UPDATE_FOUND;

			return canUpdate;

		} catch (final IOException e) {
			error(this.messages.ERROR_CHECKING, this.messages.PLEASE_REPORT);
			e.printStackTrace();
			this.result = Result.FAIL;
			return false;
		}
	}

	private JsonElement getPage(final int page) throws IOException {

		final URL url = new URL(getResourceURL() + VERSIONS + PAGE + page);

		final HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.addRequestProperty("User-Agent", USER_AGENT);

		return new JsonParser().parse(new InputStreamReader(connection.getInputStream()));
	}

	public void download() {
		BufferedInputStream in   = null;
		FileOutputStream    fout = null;

		try {
			final URL url = new URL(getResourceURL() + DOWNLOAD);

			in   = new BufferedInputStream(url.openStream());//open the streams
			fout = new FileOutputStream(new File(this.folder, this.jar.getName()));

			info(this.messages.DOWNLOADING);
			final byte[] buf = new byte[BYTE_BUF];
			int          i;
			while ((i = in.read(buf, 0, BYTE_BUF)) != -1) {//download the data in chunks
				fout.write(buf, 0, i);
			}


		} catch (final IOException e) {
			error(this.messages.ERROR_DOWNLOADING, this.messages.PLEASE_REPORT);
			e.printStackTrace();
			this.result = Result.FAIL;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (final IOException e) {
				catchDownloadError(e);
			}
			try {
				if (fout != null) {
					fout.close();
				}
			} catch (final IOException e) {
				catchDownloadError(e);
			}
		}
	}

	public void update() {
		if (isValidResource()) {
			if (canUpdate()) {
				info(this.messages.UPDATE_FOUND + this.messages.getComparison(this.current, this.latest));
				download();
			} else {
				info(this.messages.NO_UPDATE);
			}
		}
	}

	public void notifyUpdate() {
		if (isValidResource()) {
			if (canUpdate()) {
				info(this.messages.SHOULD_UPDATE + this.messages.getComparison(this.current, this.latest));
			} else {
				info(this.messages.NO_UPDATE);
			}
		}
	}

	private void catchDownloadError(final IOException e) {
		this.plugin.getLogger().log(Level.SEVERE, null, e);//log the exception; this one's important
		error(this.messages.ERROR_DOWNLOADING, this.messages.PLEASE_REPORT);
		e.printStackTrace();
		this.result = Result.FAIL;
	}

	private void info(@NonNull final String... messages) {
		if (this.log) {
			for (final String message : messages) {
				this.plugin.getLogger().info(message);
			}
		}
	}

	private void warn(@NonNull final String... messages) {
		if (this.log) {
			for (final String message : messages) {
				this.plugin.getLogger().warning(message);
			}
		}
	}

	private void error(@NonNull final String... messages) {
		if (this.log) {
			for (final String message : messages) {
				this.plugin.getLogger().severe(message);
			}
		}
	}

	public enum Result {
		FAIL,
		SUCCESS,
		BAD_ID,
		UPDATE_EXISTS,
		NO_UPDATE_FOUND
	}

	@AllArgsConstructor
	public static final class Messages {
		public final String UPDATE_FOUND;
		public final String NO_UPDATE;
		public final String CHECKING;
		public final String ERROR_CHECKING;
		public final String ERROR_DOWNLOADING;
		public final String DOWNLOADING;
		public final String PLEASE_REPORT;
		public final String SHOULD_UPDATE;
		public final String COMPARISON;

		public String getComparison(@NonNull final String current, @NonNull final String latest) {
			return this.COMPARISON.replace("${OLD}", current).replace("${NEW}", latest);
		}
	}
}
