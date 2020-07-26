package creativeitemfilter.meta;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SuspiciousStewMeta;
import org.bukkit.potion.PotionEffect;

public class SuspiciousStewMetaCopier implements MetaCopier<SuspiciousStewMeta> {
	public static final SuspiciousStewMetaCopier INSTANCE = new SuspiciousStewMetaCopier();

	@Override
	public ItemMeta copyValidMeta(SuspiciousStewMeta oldMeta, Material material) {
		SuspiciousStewMeta newMeta = (SuspiciousStewMeta) Bukkit.getItemFactory().getItemMeta(material);
		if (oldMeta.hasCustomEffects()) {
			for (PotionEffect effects : oldMeta.getCustomEffects()) {
				newMeta.addCustomEffect(effects, false);
			}
		}
		return newMeta;
	}
}