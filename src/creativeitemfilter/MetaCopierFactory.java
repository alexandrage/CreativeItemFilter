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
import org.bukkit.inventory.meta.SuspiciousStewMeta;
import org.bukkit.inventory.meta.TropicalFishBucketMeta;
import creativeitemfilter.meta.BannerMetaCopier;
import creativeitemfilter.meta.BlockStateMetaCopier;
import creativeitemfilter.meta.BookMetaCopier;
import creativeitemfilter.meta.CompassMetaCopier;
import creativeitemfilter.meta.CrossbowMetaCopier;
import creativeitemfilter.meta.NoOpMetaCopier;
import creativeitemfilter.meta.PotionMetaCopier;
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
	private BlockStateMetaCopier bsmc;
	private final HashMap<Class<? extends ItemMeta>, MetaCopier<ItemMeta>> copierCache = new HashMap<>();

	public MetaCopierFactory(CreativeItemFilter plugin) {
		this.plugin = plugin;
		bsmc = new BlockStateMetaCopier(this.plugin);
	}

	public MetaCopier<ItemMeta> getCopier(ItemMeta oldMeta) {
		Class<? extends ItemMeta> metaClass = oldMeta.getClass();
		MetaCopier<ItemMeta> cached = copierCache.get(metaClass);
		if (cached != null) {
			return cached;
		}
		if (oldMeta instanceof BannerMeta) {
			return cache(metaClass, BannerMetaCopier.INSTANCE);
		} else if (oldMeta instanceof EnchantmentStorageMeta) {
			return cache(metaClass, EnchantmentStorageMetaCopier.INSTANCE);
		} else if (oldMeta instanceof BookMeta) {
			return cache(metaClass, BookMetaCopier.INSTANCE);
		} else if (oldMeta instanceof PotionMeta) {
			return cache(metaClass, PotionMetaCopier.INSTANCE);
		} else if (oldMeta instanceof LeatherArmorMeta) {
			return cache(metaClass, LeatherArmorMetaCopier.INSTANCE);
		} else if (oldMeta instanceof TropicalFishBucketMeta) {
			return cache(metaClass, TropicalFishBucketMetaCopier.INSTANCE);
		} else if (oldMeta instanceof FireworkMeta) {
			return cache(metaClass, FireworkMetaCopier.INSTANCE);
		} else if (oldMeta instanceof FireworkEffectMeta) {
			return cache(metaClass, FireworkEffectMetaCopier.INSTANCE);
		} else if (oldMeta instanceof MapMeta) {
			return cache(metaClass, MapMetaCopier.INSTANCE);
		} else if (oldMeta instanceof BlockStateMeta) {
			return cache(metaClass, bsmc);
		} else if (oldMeta instanceof CompassMeta) {
			return cache(metaClass, CompassMetaCopier.INSTANCE);
		} else if (oldMeta instanceof CrossbowMeta) {
			return cache(metaClass, CrossbowMetaCopier.INSTANCE);
		} else if (oldMeta instanceof KnowledgeBookMeta) {
			return cache(metaClass, KnowledgeBookMetaCopier.INSTANCE);
		} else if (oldMeta instanceof SuspiciousStewMeta) {
			return cache(metaClass, SuspiciousStewMetaCopier.INSTANCE);
		}
		return NoOpMetaCopier.INSTANCE;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected MetaCopier<ItemMeta> cache(Class<? extends ItemMeta> clazz, MetaCopier copier) {
		copierCache.put(clazz, copier);
		return copier;
	}
}