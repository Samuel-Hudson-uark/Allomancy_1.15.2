package com.jojoreference.allomancy.blocks.machines;

import com.jojoreference.allomancy.blocks.ModBlocks;
import com.jojoreference.allomancy.metal.CapabilityMetal;
import com.jojoreference.allomancy.metal.IMetalStorageHandler;
import com.jojoreference.allomancy.util.CapabilityCustomFurnace;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import static com.jojoreference.allomancy.blocks.ModBlocks.MELTER_CONTAINER;

public class MelterContainer extends Container {
    private TileEntity tileEntity;
    private PlayerEntity playerEntity;
    private IItemHandler playerInventory;

    public MelterContainer(int id, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(MELTER_CONTAINER, id);
        tileEntity = world.getTileEntity(pos);
        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);

        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
            //Position of item slot for the melter. Will have to change x and y to match the GUI.
            //x and y match first light gray pixel of inventory slot.
            addSlot(new SlotItemHandler(h, 0, 56, 17));
            addSlot(new SlotItemHandler(h, 1, 56, 53));
            addSlot(new SlotItemHandler(h, 2, 143, 53));
        });
        //Position of player's inventory. Change leftcol and toprow if I edit the GUI.
        //x and y match first light gray pixel of inventory slot.
        layoutPlayerInventorySlots(8, 84);

    }

    public String getFluidName() {
        return tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).map(f ->
                f.getFluidInTank(0).getDisplayName().getFormattedText()).orElse("Empty");
    }

    public int getFluidAmount() {
        return tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).map(f ->
                f.getFluidInTank(0).getAmount()).orElse(0);
    }

    public float getBurnTimeRatio() {
        return tileEntity.getCapability(CapabilityCustomFurnace.CUSTOM_FURNACE_CAPABILITY).map(c ->
                (float)c.get(0)/(float)c.get(1)).orElse(0f);
    }

    public float getProcessingTimeRatio() {
        return tileEntity.getCapability(CapabilityCustomFurnace.CUSTOM_FURNACE_CAPABILITY).map(c ->
                1f-((float)c.get(2)/(float)c.get(3))).orElse(0f);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerEntity, ModBlocks.MELTER);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if(slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            itemStack = stack.copy();
            if(index == 0) {
                if(!this.mergeItemStack(stack, 1, 37, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(stack, itemStack);
            } else {
                //if(stack.getItem() == some type of item)
                if(!this.mergeItemStack(stack, 0, 1, false)) {
                    return ItemStack.EMPTY;
                } else if(index < 28) {
                    if(!this.mergeItemStack(stack, 28, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if(index < 37 && this.mergeItemStack(stack, 1, 28, false)) {
                    return ItemStack.EMPTY;
                }
            }
            if(stack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
            if(stack.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }
        return itemStack;
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for(int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for(int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        //Player Inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        //Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }

}
