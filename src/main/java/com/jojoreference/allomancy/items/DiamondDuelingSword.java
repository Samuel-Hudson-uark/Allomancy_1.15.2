package com.jojoreference.allomancy.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class DiamondDuelingSword extends SwordItem {

    public DiamondDuelingSword(IItemTier tier, int attackDamageIn, float attackSpeedIn, ItemGroup group) {
        super(tier, attackDamageIn, attackSpeedIn, new Properties().group(group));
        setRegistryName("diamondduelingsword");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        if(heldItem.getItem() instanceof DiamondDuelingSword) {
            ItemStack sword = new ItemStack(ModItems.DIAMONDDUELINGPICKAXE);
            playerIn.setHeldItem(handIn, sword);
        }
        return ActionResult.resultSuccess(heldItem);
    }
}
