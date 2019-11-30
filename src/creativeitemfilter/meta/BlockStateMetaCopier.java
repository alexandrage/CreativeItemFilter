package creativeitemfilter.meta;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.ShulkerBox;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;

import creativeitemfilter.CreativeItemFilter;

public class BlockStateMetaCopier implements MetaCopier<BlockStateMeta> {

	private CreativeItemFilter plugin;

	public BlockStateMetaCopier(CreativeItemFilter plugin) {
		this.plugin = plugin;
	}

	@Override
	public ItemMeta copyValidMeta(BlockStateMeta oMeta, Material material) {
		BlockStateMeta nMeta = (BlockStateMeta) Bukkit.getItemFactory().getItemMeta(material);
		BlockState state = oMeta.getBlockState();
		if (state instanceof ShulkerBox) {
			ShulkerBox oldBox = (ShulkerBox) state;
			for (int i = 0; i < oldBox.getInventory().getSize(); i++) {
				ItemStack stack = oldBox.getInventory().getItem(i);
				if (stack != null) {
					oldBox.getInventory().setItem(i, this.plugin.copyItemMeta(stack));
				}
			}
			state.update();
			nMeta.setBlockState(state);
		}
		return nMeta;
	}
}