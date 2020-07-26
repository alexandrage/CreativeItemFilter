package creativeitemfilter.meta;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class CrossbowMetaCopier implements MetaCopier<CrossbowMeta> {
	public static final CrossbowMetaCopier INSTANCE = new CrossbowMetaCopier();

	@Override
	public ItemMeta copyValidMeta(CrossbowMeta oldMeta, Material material) {
		CrossbowMeta newMeta = (CrossbowMeta) Bukkit.getItemFactory().getItemMeta(material);
		if (oldMeta.hasChargedProjectiles()) {
			newMeta.setChargedProjectiles(oldMeta.getChargedProjectiles());
		}
		return newMeta;
	}
}