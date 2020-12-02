package creativeitemfilter;

import java.util.HashMap;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.KnowledgeBookMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.inventory.meta.SuspiciousStewMeta;
import org.bukkit.inventory.meta.TropicalFishBucketMeta;
import creativeitemfilter.meta.BannerMetaCopier;
import creativeitemfilter.meta.BlockStateMetaCopier;
import creativeitemfilter.meta.BookMetaCopier;
import creativeitemfilter.meta.CompassMetaCopier;
import creativeitemfilter.meta.CrossbowMetaCopier;
import creativeitemfilter.meta.NoOpMetaCopier;
import creativeitemfilter.meta.PotionMetaCopier;
import creativeitemfilter.meta.SkullMetaCopier;
import creativeitemfilter.meta.SuspiciousStewMetaCopier;
import creativeitemfilter.meta.TropicalFishBucketMetaCopier;
import creativeitemfilter.meta.EnchantmentStorageMetaCopier;
import creativeitemfilter.meta.FireworkEffectMetaCopier;
import creativeitemfilter.meta.FireworkMetaCopier;
import creativeitemfilter.meta.KnowledgeBookMetaCopier;
import creativeitemfilter.meta.LeatherArmorMetaCopier;
import creativeitemfilter.meta.MapMetaCopier;
import creativeitemfilter.meta.MetaCopier;

public class MetaCopierFactory {
	private CreativeItemFilter plugin;
	private final HashMap<Class<? extends ItemMeta>, MetaCopier<ItemMeta>> copierCache = new HashMap<>();

	public MetaCopierFactory(CreativeItemFilter plugin) {
		this.plugin = plugin;
	}

	public MetaCopier<ItemMeta> getCopier(ItemMeta oldMeta) {
		Class<? extends ItemMeta> metaClass = oldMeta.getClass();
		MetaCopier<ItemMeta> cached = this.copierCache.get(metaClass);
		if (cached != null) {
			return cached;
		}
		if (oldMeta instanceof BannerMeta) {
			return this.cache(metaClass, BannerMetaCopier.INSTANCE);
		} else if (oldMeta instanceof EnchantmentStorageMeta) {
			return this.cache(metaClass, EnchantmentStorageMetaCopier.INSTANCE);
		} else if (oldMeta instanceof BookMeta) {
			return this.cache(metaClass, BookMetaCopier.INSTANCE);
		} else if (oldMeta instanceof PotionMeta) {
			return this.cache(metaClass, PotionMetaCopier.INSTANCE);
		} else if (oldMeta instanceof LeatherArmorMeta) {
			return this.cache(metaClass, LeatherArmorMetaCopier.INSTANCE);
		} else if (oldMeta instanceof TropicalFishBucketMeta) {
			return this.cache(metaClass, TropicalFishBucketMetaCopier.INSTANCE);
		} else if (oldMeta instanceof FireworkMeta) {
			return this.cache(metaClass, FireworkMetaCopier.INSTANCE);
		} else if (oldMeta instanceof FireworkEffectMeta) {
			return this.cache(metaClass, FireworkEffectMetaCopier.INSTANCE);
		} else if (oldMeta instanceof MapMeta) {
			return this.cache(metaClass, MapMetaCopier.INSTANCE);
		} else if (oldMeta instanceof BlockStateMeta) {
			return this.cache(metaClass, new BlockStateMetaCopier(this.plugin));
		} else if (oldMeta instanceof CompassMeta) {
			return this.cache(metaClass, CompassMetaCopier.INSTANCE);
		} else if (oldMeta instanceof CrossbowMeta) {
			return this.cache(metaClass, CrossbowMetaCopier.INSTANCE);
		} else if (oldMeta instanceof KnowledgeBookMeta) {
			return this.cache(metaClass, KnowledgeBookMetaCopier.INSTANCE);
		} else if (oldMeta instanceof SuspiciousStewMeta) {
			return this.cache(metaClass, SuspiciousStewMetaCopier.INSTANCE);
		} else if (oldMeta instanceof SkullMeta) {
			return this.cache(metaClass, SkullMetaCopier.INSTANCE);
		}
		return NoOpMetaCopier.INSTANCE;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected MetaCopier<ItemMeta> cache(Class<? extends ItemMeta> clazz, MetaCopier copier) {
		this.copierCache.put(clazz, copier);
		return copier;
	}
}