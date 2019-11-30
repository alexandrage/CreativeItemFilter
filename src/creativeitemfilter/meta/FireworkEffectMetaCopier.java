package creativeitemfilter.meta;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class FireworkEffectMetaCopier implements MetaCopier<FireworkEffectMeta> {
	public static final FireworkEffectMetaCopier INSTANCE = new FireworkEffectMetaCopier();

	private FireworkEffectMetaCopier() {
	}

	@Override
	public ItemMeta copyValidMeta(FireworkEffectMeta oldMeta, Material material) {
		FireworkEffectMeta newMeta = (FireworkEffectMeta) Bukkit.getItemFactory().getItemMeta(material);
		if(oldMeta.hasEffect()) {
			newMeta.setEffect(oldMeta.getEffect());
		}
		return oldMeta;
	}
}