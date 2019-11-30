package creativeitemfilter.meta;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.TropicalFishBucketMeta;

public class TropicalFishBucketMetaCopier implements MetaCopier<TropicalFishBucketMeta> {
	public static final TropicalFishBucketMetaCopier INSTANCE = new TropicalFishBucketMetaCopier();

	private TropicalFishBucketMetaCopier() {
	}

	@Override
	public ItemMeta copyValidMeta(TropicalFishBucketMeta oldMeta, Material material) {
		TropicalFishBucketMeta newMeta = (TropicalFishBucketMeta) Bukkit.getItemFactory().getItemMeta(material);
		newMeta.setBodyColor(oldMeta.getBodyColor());
		newMeta.setPattern(oldMeta.getPattern());
		newMeta.setPatternColor(oldMeta.getPatternColor());
		return newMeta;
	}
}