package creativeitemfilter.meta;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.MapMeta;

public class MapMetaCopier implements MetaCopier<MapMeta> {

	public static final MapMetaCopier INSTANCE = new MapMetaCopier();

	private MapMetaCopier() {

	}

	@Override
	public ItemMeta copyValidMeta(MapMeta oldMeta, Material material) {
		MapMeta newMeta = (MapMeta) Bukkit.getItemFactory().getItemMeta(material);
		if (oldMeta.hasColor()) {
			newMeta.setColor(oldMeta.getColor());
		}
		if (oldMeta.hasLocationName()) {
			newMeta.setLocationName(oldMeta.getLocationName());
		}
		if (oldMeta.hasMapView()) {
			newMeta.setMapView(oldMeta.getMapView());
		}
		if (oldMeta.isScaling()) {
			newMeta.setScaling(true);
		}
		return newMeta;
	}
}