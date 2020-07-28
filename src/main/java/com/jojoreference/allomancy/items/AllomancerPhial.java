package com.jojoreference.allomancy.items;

import com.jojoreference.allomancy.Allomancy;
import com.jojoreference.allomancy.capabilities.Mistborn;
import com.jojoreference.allomancy.capabilities.MistbornProvider;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class AllomancerPhial extends Item {
    public AllomancerPhial() {
        super(new Item.Properties()
        .maxStackSize(64)
        .group(Allomancy.setup.itemGroup));
        setRegistryName("allomancerphial");
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        Mistborn mistborn = (Mistborn) entityLiving.getCapability(MistbornProvider.MISTBORN_CAPABILITY).orElse(new Mistborn());
        mistborn.gain(20, false);
        if(!worldIn.isRemote)
            entityLiving.sendMessage(new StringTextComponent("Amount of metal: " + mistborn.getResource()));
        if (entityLiving instanceof PlayerEntity && !((PlayerEntity)entityLiving).abilities.isCreativeMode) {
            stack.shrink(1);
            ((PlayerEntity) entityLiving).inventory.addItemStackToInventory(new ItemStack(ModItems.EMPTYPHIAL));
        }
        return stack;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.setActiveHand(handIn);
        return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }
}