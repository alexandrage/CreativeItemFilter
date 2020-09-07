package creativeitemfilter.meta;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullMetaCopier implements MetaCopier<SkullMeta> {
	public static final SkullMetaCopier INSTANCE = new SkullMetaCopier();

	private SkullMetaCopier() {
	}

	@Override
	public SkullMeta copyValidMeta(SkullMeta oldMeta, Material material) {
		SkullMeta newMeta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(material);
		if (oldMeta.hasOwner()) {
			newMeta.setPlayerProfile(oldMeta.getPlayerProfile());
		}
		return newMeta;
	}
}