package com.ruthlessjailer.api.theseus.multiversion;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.ruthlessjailer.api.theseus.Common;
import lombok.Getter;
import lombok.NonNull;
import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.ruthlessjailer.api.theseus.multiversion.MinecraftVersion.*;

/**
 * Inspired by and adapted from:
 * https://github.com/CryptoMorin/XSeries/blob/master/src/main/java/com/cryptomorin/xseries/XMaterial.java
 * <p>
 * Minecraft Wiki was of significant help:
 * https://minecraft.gamepedia.com/
 *
 * @author RuthlessJailer
 */
@Getter
public enum XMaterial {

	//<editor-fold desc="Materials" defaultstate="collapsed">
	ACACIA_BOAT(v1_9, "BOAT_ACACIA", "", "BOAT"),//acacia added in 1.7
	ACACIA_BUTTON(v1_13, "", "WOOD_BUTTON"),
	ACACIA_DOOR(v1_8, "ACACIA_DOOR", "ACACIA_DOOR_ITEM", "", "WOOD_DOOR", "WOODEN_DOOR"),
	ACACIA_FENCE(v1_8, "", "FENCE"),//fence variants added in 1.8
	ACACIA_FENCE_GATE(v1_8, "", "FENCE_GATE"),
	ACACIA_LEAVES(v1_7, "LEAVES_2", "", "LEAVES"),
	ACACIA_LOG(v1_7, "LOG_2", "", "LOG"),
	ACACIA_PLANKS(v1_7, 4, "WOOD"),
	ACACIA_PRESSURE_PLATE(v1_13, "", "WOOD_PLATE"),
	ACACIA_SAPLING(v1_7, 4, "SAPLING"),
	ACACIA_SIGN(v1_14, "", "SIGN_POST", "SIGN", "STANDING_SIGN"),
	ACACIA_SLAB(v1_7, 4, "WOOD_STEP", "WOODEN_SLAB", "WOOD_DOUBLE_STEP"),
	ACACIA_STAIRS(v1_7, "", "WOOD_STAIRS"),
	ACACIA_TRAPDOOR(v1_13, "", "TRAP_DOOR"),//trapdoor types added in 1.13
	ACACIA_WALL_SIGN(v1_7, 4, "WALL_SIGN"),
	ACACIA_WOOD(v1_7, "LOG_2", "", "LOG"),
	ACTIVATOR_RAIL(v1_5, "", "RAIL"),
	/**
	 * https://minecraft.gamepedia.com/Air
	 *
	 * @see #CAVE_AIR
	 * @see #VOID_AIR
	 */
	AIR(v1_3_OR_OLDER),
	ALLIUM(v1_7, 2, "RED_ROSE"),
	ANCIENT_DEBRIS(v1_16, "", "QUARTZ_ORE", "NETHERRACK"),
	ANDESITE(v1_8, 5),
	ANDESITE_SLAB(v1_14, 5, "STEP", "DOUBLE_STEP"),
	ANDESITE_STAIRS(v1_14, "", "COBBLESTONE_STAIRS"),
	ANDESITE_WALL(v1_14, "", "COBBLESTONE_WALL", "COBBLE_WALL"),
	ANVIL(v1_4),
	APPLE(v1_3_OR_OLDER),
	ARMOR_STAND(v1_8),
	ARROW(v1_3_OR_OLDER),
	ATTACHED_MELON_STEM(v1_8, 7, "MELON_STEM"),
	ATTACHED_PUMPKIN_STEM(v1_8, 7, "PUMPKIN_STEM"),
	AZURE_BLUET(v1_7, 7, "RED_ROSE"),
	BAKED_POTATO(v1_4),
	BAMBOO(v1_14, "", "SUGAR_CANE"),
	BAMBOO_SAPLING(v1_14, "", "SUGAR_CANE"),
	BARREL(v1_14, "", "CHEST"),
	BARRIER(v1_8, "", "GLASS"),
	BASALT(v1_16, "", "COBBLESTONE"),
	/**
	 * MONSTER_EGG is spawn egg, MONSTER_EGGS is an infested block.
	 */
	BAT_SPAWN_EGG(v1_4, 65, "MONSTER_EGG"),
	BEACON(v1_4, "", "GLASS"),
	BEDROCK(v1_3_OR_OLDER),
	BEEF(v1_3_OR_OLDER, "", "RAW_BEEF"),
	BEEHIVE(v1_15),
	/**
	 * The block variant.
	 *
	 * @see #BEETROOTS for item variant
	 */
	BEETROOT(v1_9, "BEETROOT_BLOCK"),//TODO: unsure
	/**
	 * The item variant.
	 *
	 * @see #BEETROOT for block variant
	 */
	BEETROOTS(v1_9, "BEETROOT"),//TODO: unsure
	BEETROOT_SEEDS(v1_9, "SEEDS", "WHEAT_SEEDS"),
	BEETROOT_SOUP(v1_9, "MUSHROOM_STEW"),
	BEE_NEST(v1_15, 4, "", "YELLOW_WOOL", "WOOL"),
	/**
	 * MONSTER_EGG is spawn egg, MONSTER_EGGS is an infested block.
	 */
	BEE_SPAWN_EGG(v1_15, "SPAWN_EGG"),
	BELL(v1_14),
	BIRCH_BOAT(v1_9, 2, "BOAT_BIRCH", "BOAT"),
	BIRCH_BUTTON(v1_13, "", "WOOD_BUTTON"),
	BIRCH_DOOR(v1_8, "BIRCH_DOOR", "BIRCH_DOOR_ITEM", "", "WOOD_DOOR", "WOODEN_DOOR"),
	BIRCH_FENCE(v1_8, "", "FENCE"),
	BIRCH_FENCE_GATE(v1_8, "", "FENCE_GATE"),
	BIRCH_LEAVES(v1_3_OR_OLDER, 2, "LEAVES"),
	BIRCH_LOG(v1_3_OR_OLDER, 2, "LOG"),//birch added very early
	BIRCH_PLANKS(v1_3_OR_OLDER, 2, "WOOD"),
	BIRCH_PRESSURE_PLATE(v1_13, "", "WOOD_PLATE"),//pressure plate types added in 1.13
	BIRCH_SAPLING(v1_5, 2, "SAPLING"),
	BIRCH_SIGN(v1_14, "", "SIGN_POST", "SIGN"),
	BIRCH_SLAB(v1_3_OR_OLDER, 2, "WOOD_STEP", "WOODEN_SLAB", "WOOD_DOUBLE_STEP"),
	BIRCH_STAIRS(v1_3_OR_OLDER, "BIRCH_WOOD_STAIRS"),
	BIRCH_TRAPDOOR(v1_13, "", "TRAP_DOOR"),
	BIRCH_WALL_SIGN(v1_13, "", "WALL_SIGN"),
	BIRCH_WOOD(v1_3_OR_OLDER, 2, "LOG"),
	BLACKSTONE(v1_16),
	BLACKSTONE_SLAB(v1_16, "", "STEP", "DOUBLE_STEP", "COBBLESTONE_SLAB"),
	BLACKSTONE_STAIRS(v1_16, "", "COBBLESTONE_STAIRS"),
	BLACKSTONE_WALL(v1_16, "", "COBBLESTONE_WALL", "COBBLE_WALL"),
	BLACK_BANNER(v1_8, 0, "STANDING_BANNER", "BANNER"),//banners added in 1.8, 0 is black for banners
	BLACK_BED(v1_12, 15, "BED_BLOCK", "BED"),//dyed beds added in 1.12, beds added very long ago
	BLACK_CARPET(v1_6, 15, "CARPET"),
	BLACK_CONCRETE(v1_12, 15, "CONCRETE"),
	BLACK_CONCRETE_POWDER(v1_12, 15, "CONCRETE_POWDER"),
	BLACK_DYE(v1_14, "INK_SACK"),
	/**
	 * In 1.12, they added COLOR_GLAZED_TERRACOTTA and kept STAINED_CLAY but renamed items to Color Terracotta.
	 */
	BLACK_GLAZED_TERRACOTTA(v1_12, 15, "STAINED_CLAY"),
	BLACK_SHULKER_BOX(v1_11, "", "CHEST"),
	BLACK_STAINED_GLASS(v1_7, 15, "STAINED_GLASS", "", "GLASS"),
	BLACK_STAINED_GLASS_PANE(v1_7, 15, "STAINED_GLASS_PANE", "", "THIN_GLASS"),
	/**
	 * CLAY is smelted into HARD_CLAY which is dyed into STAINED_CLAY.
	 */
	BLACK_TERRACOTTA(v1_12, 15, "STAINED_CLAY"),
	BLACK_WALL_BANNER(v1_8, 0, "WALL_BANNER"),
	BLACK_WOOL(v1_3_OR_OLDER, 15, "WOOL"),
	BLAST_FURNACE(v1_14, "", "FURNACE"),
	BLAZE_POWDER(v1_3_OR_OLDER),
	BLAZE_ROD(v1_3_OR_OLDER, "", "STICK"),
	/**
	 * MONSTER_EGG is spawn egg, MONSTER_EGGS is an infested block.
	 */
	BLAZE_SPAWN_EGG(v1_3_OR_OLDER, 61, "MONSTER_EGG"),
	BLUE_BANNER(v1_8, 4, "STANDING_BANNER", "BANNER"),
	BLUE_BED(v1_12, 11, "BED_BLOCK", "BED"),
	BLUE_CARPET(v1_6, 11, "CARPET"),
	BLUE_CONCRETE(v1_12, 11, "CONCRETE"),
	BLUE_CONCRETE_POWDER(v1_12, 11, "CONCRETE_POWDER"),
	BLUE_DYE(v1_13, 4, "INK_SACK"),
	/**
	 * In 1.12, they added COLOR_GLAZED_TERRACOTTA and kept STAINED_CLAY but renamed items to Color Terracotta.
	 */
	BLUE_GLAZED_TERRACOTTA(v1_12, 11, "STAINED_CLAY"),
	BLUE_ICE(v1_13, 0, "", "PACKED_ICE", "ICE"),

	CAVE_AIR(v1_13),
	VOID_AIR(v1_13);
	//</editor-fold>

	public static final  List<String>               COLORABLE            = Collections.unmodifiableList(Arrays.asList(//TODO lame fix pls
																													  "BANNER", "BED", "CARPET", "CONCRETE", "GLAZED_TERRACOTTA", "SHULKER_BOX",
																													  "STAINED_GLASS", "STAINED_GLASS_PANE", "TERRACOTTA", "WALL_BANNER", "WOOL"));
	private static final Cache<String, XMaterial>   NAME_CACHE           = CacheBuilder.newBuilder().build();
	private static final Cache<XMaterial, Material> MATERIAL_CACHE       = CacheBuilder.newBuilder().build();
	private static final boolean                    ISFLAT               = atLeast(v1_13);
	private static final String                     LAST_RESORT_FALLBACK = "Last Resort Fallback";
	private static final Material                   LAST_RESORT_MATERIAL = Material.STONE;

	static {//populate name cache
		for (final XMaterial x : values()) { NAME_CACHE.put(x.name(), x); }
	}

	private final String[]         legacyNames;
	private final byte             data;
	private final MinecraftVersion added;

	XMaterial(@NonNull final MinecraftVersion added, @NonNull final String... legacyNames) {
		this(added, 0, legacyNames);
	}

	XMaterial(@NonNull final MinecraftVersion added, final int data, @NonNull final String... legacyNames) {
		this.added       = added;
		this.data        = (byte) data;
		this.legacyNames = legacyNames == null || legacyNames.length == 0
						   ? Common.asArray("", LAST_RESORT_FALLBACK)
						   : Common.append(legacyNames, "", LAST_RESORT_FALLBACK);
	}

	/**
	 * Returns {@code true} if the material is air, i.e. it does not have {@link org.bukkit.inventory.meta.ItemMeta}, or {@code false} if it isn't.
	 *
	 * @param material the material to check
	 *
	 * @return true if the material is one of the following: AIR, CAVE_AIR, VOID_AIR, LEGACY_AIR
	 */
	public static boolean isAir(@NonNull final Material material) {
		return material.name().equals("AIR") || material.name().endsWith("_AIR");
	}//TODO add all materials and change these methods to be XMaterial instead of Material

	/**
	 * Returns {@code true} if the material is colorable, i.e. if it is included in the COLORABLE list, or {@code false} if it isn't.
	 *
	 * @param material the material to check
	 *
	 * @return true if the material's name is included in the COLORABLE list
	 */
	public static boolean isColorable(@NonNull final Material material) {
		for (final String colorable : COLORABLE) {
			if (material.name().endsWith("_".concat(colorable))) {
				return true;
			}
		}
		return false;
	}

	public static XMaterial getXMaterial(@NonNull final String name) {
		return NAME_CACHE.getIfPresent(name);
	}


	private Material parseMaterial(final boolean suggest) {
		final Material material;

		if (atLeast(this.added)) {//server version is new enough
			if (ISFLAT) {//1.13+; get from name
				material = Material.getMaterial(name());
			} else {//old version; get legacy
				material = parseLegacy(suggest);
			}
		} else {//item added in newer version and does not exist
			material = parseLegacy(suggest);
		}

		return material;
	}

	public Material toMaterial() {
		return toMaterial(true);
	}

	public Material toMaterial(final boolean suggest) {
		Material material = MATERIAL_CACHE.getIfPresent(this);

		if (material != null) {
			return material;
		}

		material = parseMaterial(suggest);

		if (material == null) {
			MATERIAL_CACHE.put(this, LAST_RESORT_MATERIAL);//tried all possibilities; add it to the cache
			return LAST_RESORT_MATERIAL;
		} else {
			MATERIAL_CACHE.put(this, material);//save found material
			return material;
		}
	}

	private Material parseLegacy(final boolean suggest) {
		Material material = Material.getMaterial(name());

		if (material != null) {//try the name first
			return material;
		}

		for (final String legacyName : this.legacyNames) {

			if (legacyName.isEmpty()) {//reached the end of actual legacy names. everything to the right of the first empty string is a suggestion
				if (suggest) {
					continue;
				} else {
					break;
				}
			}

			if (legacyName.equals(LAST_RESORT_FALLBACK)) {//last one, and it's the default stone fallback (ie no data)
				return null;
			}

			material = Material.getMaterial(legacyName);

			if (material != null) {
				return material;
			}
		}

		return null;//nothing was found
	}

	public ItemStack toItemStack() {
		final Material material = parseMaterial(true);
		final ItemStack item = ISFLAT
							   ? new ItemStack(toMaterial())
							   : new ItemStack(material == null
											   ? LAST_RESORT_MATERIAL
											   : material, 1, material == null
															  ? 0
															  : this.data);//if material is null then default to STONE(0)
		if (material == null || parseMaterial(false) == null && item.hasItemMeta()) {//set name for old item
			final ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.RESET + prettyName());
			item.setItemMeta(meta);
		}

		return item;
	}

	public String prettyName() {
		return WordUtils.capitalizeFully(name().replaceAll("_", " "));
	}

	public static String prettyName(@NonNull final Material material) {
		return WordUtils.capitalizeFully(material.name().replaceAll("_", ""));
	}

}