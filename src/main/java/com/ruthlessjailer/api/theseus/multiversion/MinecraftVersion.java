package com.ruthlessjailer.api.theseus.multiversion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Bukkit;

/**
 * @author RuthlessJailer
 */
@AllArgsConstructor
@Getter
public enum MinecraftVersion {

	//Modern
	v1_16(16, "1.16", VersionType.MODERN),
	v1_15(15, "1.15", VersionType.MODERN),
	v1_14(14, "1.14", VersionType.MODERN),
	v1_13(13, "1.13", VersionType.MODERN),
	//Legacy
	v1_12(12, "1.12", VersionType.LEGACY),
	v1_11(11, "1.11", VersionType.LEGACY),
	v1_10(10, "1.10", VersionType.LEGACY),
	v1_9(9, "1.9", VersionType.LEGACY),
	v1_8(8, "1.8", VersionType.LEGACY),
	//Obsolete
	v1_7(7, "1.7", VersionType.OBSOLETE),
	v1_6(6, "1.6", VersionType.OBSOLETE),
	v1_5(5, "1.5", VersionType.OBSOLETE),
	v1_4(4, "1.4", VersionType.OBSOLETE),
	//WAY too old
	v1_3_OR_OLDER(3, "1.3 or older", VersionType.OBSOLETE);

	public static final MinecraftVersion CURRENT_VERSION;
	public static final String           SERVER_VERSION;

	static {
		final String pkg = Bukkit.getServer() == null ? "" :
						   Bukkit.getServer().getClass().getPackage().getName();
		final String  version       = pkg.substring(pkg.lastIndexOf('.') + 1);
		final boolean hasIdentifier = !version.equals("craftbukkit");

		SERVER_VERSION = version;

		if (hasIdentifier) {
			int i = 0;

			for (final char c : version.toCharArray()) {//v1_15_R1
				i++;
				if (i > 2 && c == 'R') {//so that i is not less that 2 (substring will throw err)
					break;
				}
			}

			final String numeric = version.substring(1, i - 2).replace("_", ".");//v1_15_R1 -> 1_15 -> 1.15

			int dots = 0;

			for (final char c : numeric.toCharArray()) {
				if (c == '.') {
					dots++;
				}
			}

			if (dots != 1) {
				throw new IllegalStateException(
						"Unsupported server version. Error parsing: " + version + " -> " + numeric);
			}

			CURRENT_VERSION = MinecraftVersion.fromId(Integer.parseInt(numeric.split("\\.")[1]));//15

		} else {
			CURRENT_VERSION = MinecraftVersion.v1_3_OR_OLDER;
		}
//		Chat.info(String.format("Detected server version %s.", MinecraftVersion.CURRENT_VERSION.getXname()));
		System.out.println(String.format("Detected server version %s.", MinecraftVersion.CURRENT_VERSION.getXname()));
	}

	private final int         id;
	private final String      name;
	private final VersionType type;

	public static boolean atLeast(final MinecraftVersion version) {
		return CURRENT_VERSION.isAtLeast(version);
	}

	public static boolean atMost(final MinecraftVersion version) {
		return CURRENT_VERSION.isAtMost(version);
	}

	public static boolean greaterThan(final MinecraftVersion version) {
		return CURRENT_VERSION.isAfter(version);
	}

	public static boolean lessThan(final MinecraftVersion version) {
		return CURRENT_VERSION.isBefore(version);
	}

	public static MinecraftVersion fromId(final int id) {
		for (final MinecraftVersion version : MinecraftVersion.values()) {
			if (version.id == id) {
				return version;
			}
		}
		throw new IllegalArgumentException("Unknown version identifier " + id);
	}

	public boolean isAtLeast(final MinecraftVersion version) { return this.id >= version.id; }

	public boolean isAtMost(final MinecraftVersion version)  { return this.id <= version.id; }

	public boolean isBefore(final MinecraftVersion version)  { return this.id < version.id; }

	public boolean isAfter(final MinecraftVersion version)   { return this.id > version.id; }

	public boolean equals(final MinecraftVersion version)    { return this.id == version.id; }

	public boolean isModern()                                { return this.type == VersionType.MODERN; }

	public boolean isLegacy()                                { return this.type == VersionType.LEGACY || this.isObsolete(); }

	public boolean isObsolete()                              { return this.type == VersionType.OBSOLETE; }

	public String getXname()                                 { return this.name + ".x"; }

	public MinecraftVersion getPrevious()                    { return MinecraftVersion.fromId(this.id - 1); }

	public MinecraftVersion getNext()                        { return MinecraftVersion.fromId(this.id + 1); }

	@AllArgsConstructor
	@Getter
	public enum VersionType {
		MODERN("Modern"),
		LEGACY("Legacy"),
		OBSOLETE("Obsolete");

		private final String name;
	}

	public static final class UnsupportedServerVersionException extends RuntimeException {
		public UnsupportedServerVersionException() {
			this("");
		}

		public UnsupportedServerVersionException(final String message) {
			this(message, null);
		}

		public UnsupportedServerVersionException(final String message, final Throwable cause) {
			super("Unsupported server version (" + CURRENT_VERSION.getXname() + ") encountered. " + message, cause);
		}

		public UnsupportedServerVersionException(final Throwable cause) {
			this("", cause);
		}

	}
}
