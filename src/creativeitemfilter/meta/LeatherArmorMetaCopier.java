package creativeitemfilter.meta;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class LeatherArmorMetaCopier implements MetaCopier<LeatherArmorMeta> {

	public static final LeatherArmorMetaCopier INSTANCE = new LeatherArmorMetaCopier();

	private LeatherArmorMetaCopier() {
	}

	@Override
	public ItemMeta copyValidMeta(LeatherArmorMeta oldMeta, Material material) {
		LeatherArmorMeta newMeta = (LeatherArmorMeta) Bukkit.getItemFactory().getItemMeta(material);
		newMeta.setColor(oldMeta.getColor());
		return newMeta;
	}
}