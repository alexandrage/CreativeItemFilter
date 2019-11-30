package creativeitemfilter.meta;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class FireworkMetaCopier implements MetaCopier<FireworkMeta> {

	public static final FireworkMetaCopier INSTANCE = new FireworkMetaCopier();

	private FireworkMetaCopier() {
	}

	@Override
	public ItemMeta copyValidMeta(FireworkMeta oldMeta, Material material) {
		FireworkMeta newMeta = (FireworkMeta) Bukkit.getItemFactory().getItemMeta(material);
		if (oldMeta.getEffectsSize() < 50) {
			newMeta.addEffects(oldMeta.getEffects());
		}
		return oldMeta;
	}
}