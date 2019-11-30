package creativeitemfilter;

import java.util.stream.Collectors;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;
import org.bukkit.plugin.java.JavaPlugin;
import creativeitemfilter.utils.StringUtils;

public class CreativeItemFilter extends JavaPlugin implements Listener {
	public CreativeItemFilter plugin;

	@Override
	public void onEnable() {
		plugin = this;
	}

	private final MetaCopierFactory metaCopierFactory = new MetaCopierFactory(this);

	@EventHandler
	public void onCreativeItemEvent(InventoryCreativeEvent event) {
		ItemStack oldItem = event.getCursor();
		// set new item
		event.setCursor(copyItemMeta(oldItem));
		event.setCurrentItem(copyItemMeta(oldItem));
	}

	public ItemStack copyItemMeta(ItemStack oldItem) {
		ItemStack newItem = new ItemStack(oldItem.getType(), oldItem.getAmount());
		// handle meta
		if (oldItem.hasItemMeta()) {
			// get meta
			ItemMeta oldMeta = oldItem.getItemMeta();
			// copy specific meta
			ItemMeta newMeta = metaCopierFactory.getCopier(oldMeta).copyValidMeta(oldMeta, newItem.getType());
			// copy displayname
			if (oldMeta.hasDisplayName()) {
				newMeta.setDisplayName(StringUtils.clampString(oldMeta.getDisplayName()));
			}
			// copy lore
			if (oldMeta.hasLore()) {
				newMeta.setLore(oldMeta.getLore().stream().map(StringUtils::clampString).collect(Collectors.toList()));
			}
			// copy enchantments
			oldItem.getEnchantments().entrySet().stream()
					.filter(entry -> entry.getValue() <= entry.getKey().getMaxLevel())
					.forEach(entry -> newItem.addUnsafeEnchantment(entry.getKey(), entry.getValue()));
			// copy modifier
			if (oldMeta.hasAttributeModifiers()) {
				oldMeta.getAttributeModifiers().asMap().entrySet()
						.forEach(entry -> entry.getValue().stream().filter(mod -> mod.getAmount() <= 10)
								.forEach(atr -> newMeta.addAttributeModifier(entry.getKey(), atr)));
			}
			// set new meta
			newItem.setItemMeta(newMeta);
		}
		// copy damage
		if (oldItem.getItemMeta() instanceof Damageable) {
			Damageable dOmeta = (Damageable) oldItem.getItemMeta();
			Damageable dNmeta = (Damageable) newItem.getItemMeta();
			if (dOmeta.hasDamage()) {
				dNmeta.setDamage(dOmeta.getDamage());
				newItem.setItemMeta((ItemMeta) dNmeta);
			}
		}
		// copy repaircost
		if (oldItem.getItemMeta() instanceof Repairable) {
			Repairable rOmeta = (Repairable) oldItem.getItemMeta();
			Repairable rNmeta = (Repairable) newItem.getItemMeta();
			if (rOmeta.hasRepairCost()) {
				rNmeta.setRepairCost(rOmeta.getRepairCost());
				newItem.setItemMeta((ItemMeta) rNmeta);
			}
		}
		return newItem;
	}
}