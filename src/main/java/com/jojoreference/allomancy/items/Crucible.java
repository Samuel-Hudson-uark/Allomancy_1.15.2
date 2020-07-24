package com.jojoreference.allomancy.items;

import com.jojoreference.allomancy.Allomancy;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;

public class Crucible extends Item {
    public Crucible() {
        super(new Item.Properties()
        .maxStackSize(1).setNoRepair()
        .group(Allomancy.setup.itemGroup));
        setRegistryName("crucible");
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        FluidTank tank = new FluidTank(FluidAttributes.BUCKET_VOLUME);
        tank.readFromNBT(stack.getTag());
        if(tank.isEmpty()) {
            tooltip.add(new TranslationTextComponent("allomancy.empty"));
        } else {
            tooltip.add(tank.getFluid().getDisplayName()
                    .appendText(": " + tank.getFluidAmount() + "/" + tank.getCapacity()));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn) {
        if(!worldIn.isRemote) {
            CompoundNBT tag = new CompoundNBT();
            FluidTank tank = new FluidTank(FluidAttributes.BUCKET_VOLUME);
            FluidStack lava = new FluidStack(Fluids.LAVA, FluidAttributes.BUCKET_VOLUME);
            tank.fill(lava, IFluidHandler.FluidAction.EXECUTE);
            tank.writeToNBT(tag);
            stack.setTag(tag);
        }
        super.onCreated(stack, worldIn, playerIn);
    }
}