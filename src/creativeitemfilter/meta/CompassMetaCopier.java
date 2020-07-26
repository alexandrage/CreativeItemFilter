package creativeitemfilter.meta;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class CompassMetaCopier implements MetaCopier<CompassMeta> {
	public static final CompassMetaCopier INSTANCE = new CompassMetaCopier();

	private CompassMetaCopier() {
	}

	@Override
	public ItemMeta copyValidMeta(CompassMeta oldMeta, Material material) {
		CompassMeta newMeta = (CompassMeta) Bukkit.getItemFactory().getItemMeta(material);
		if (oldMeta.hasLodestone()) {
			newMeta.setLodestone(oldMeta.getLodestone());
			newMeta.setLodestoneTracked(oldMeta.isLodestoneTracked());
		}
		return newMeta;
	}
}