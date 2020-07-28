package com.jojoreference.allomancy.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class DiamondDuelingPickaxe extends PickaxeItem {

    public DiamondDuelingPickaxe(IItemTier tier, int attackDamageIn, float attackSpeedIn, ItemGroup group) {
        super(tier, attackDamageIn, attackSpeedIn, new Properties().group(group));
        setRegistryName("diamondduelingpickaxe");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        if(heldItem.getItem() instanceof DiamondDuelingPickaxe) {
            ItemStack sword = new ItemStack(ModItems.DIAMONDDUELINGSWORD);
            playerIn.setHeldItem(handIn, sword);
        }
        return ActionResult.resultSuccess(heldItem);
    }
}
