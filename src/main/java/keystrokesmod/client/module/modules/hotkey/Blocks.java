package keystrokesmod.client.module.modules.hotkey;

import keystrokesmod.client.module.Module;
import keystrokesmod.client.module.setting.impl.DescriptionSetting;
import keystrokesmod.client.utils.Utils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class Blocks extends Module { //DONT DELETE THIS ITS USEFUL

    public Blocks() {
        super("Blocks", ModuleCategory.hotkey);
        this.registerSetting(new DescriptionSetting("Hotkey for blocks"));
    }

    @Override
    public void onEnable() {
        if (!Utils.Player.isPlayerInGame())
            return;
        if (isPlayerHoldingBlocks()) {
            this.disable();
            return;
        }

        if (!isPlayerHoldingBlocks()) {
            for (int slot = 0; slot <= 8; slot++) {
                ItemStack itemInSlot = mc.thePlayer.inventory.getStackInSlot(slot);
                if (itemInSlot != null && itemInSlot.getItem() instanceof ItemBlock && !itemInSlot.getItem().getRegistryName().equalsIgnoreCase("minecraft:tnt") && (((ItemBlock) itemInSlot.getItem()).getBlock().isFullBlock() || ((ItemBlock) itemInSlot.getItem()).getBlock().isFullCube())) {
                    if (mc.thePlayer.inventory.currentItem != slot) {
                        mc.thePlayer.inventory.currentItem = slot;
                    }
                    this.disable();
                    return;
                }
            }
            this.disable();
        }
    }

    @Override
    public void onDisable(){
        if (!Utils.Player.isPlayerInGame())
            return;
        if (isPlayerHoldingBlocks()) {
            return;
        }
        if (!isPlayerHoldingBlocks()) {
            for (int slot = 0; slot <= 8; slot++) {
                ItemStack itemInSlot = mc.thePlayer.inventory.getStackInSlot(slot);
                if (itemInSlot != null && itemInSlot.getItem() instanceof ItemBlock && !itemInSlot.getItem().getRegistryName().equalsIgnoreCase("minecraft:tnt") && (((ItemBlock) itemInSlot.getItem()).getBlock().isFullBlock() || ((ItemBlock) itemInSlot.getItem()).getBlock().isFullCube())) {
                    if (mc.thePlayer.inventory.currentItem != slot) {
                        mc.thePlayer.inventory.currentItem = slot;
                    }
                }
            }
        }
    }
    public static boolean isPlayerHoldingBlocks() {
        if (mc.thePlayer.getCurrentEquippedItem() == null) {
            return false;
        } else {
            Item item = mc.thePlayer.getCurrentEquippedItem().getItem();
            return item instanceof ItemBlock;
        }
    }
}
