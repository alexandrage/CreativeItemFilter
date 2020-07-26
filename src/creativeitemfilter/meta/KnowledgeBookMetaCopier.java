package creativeitemfilter.meta;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.KnowledgeBookMeta;

public class KnowledgeBookMetaCopier implements MetaCopier<KnowledgeBookMeta> {
	public static final KnowledgeBookMetaCopier INSTANCE = new KnowledgeBookMetaCopier();

	@Override
	public ItemMeta copyValidMeta(KnowledgeBookMeta oldMeta, Material material) {
		KnowledgeBookMeta newMeta = (KnowledgeBookMeta) Bukkit.getItemFactory().getItemMeta(material);
		if (oldMeta.hasRecipes()) {
			newMeta.setRecipes(oldMeta.getRecipes());
		}
		return newMeta;
	}
}