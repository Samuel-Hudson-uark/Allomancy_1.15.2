package com.jojoreference.allomancy.blocks.machines;

import com.jojoreference.allomancy.blocks.ModBlocks;
import com.jojoreference.allomancy.items.ModItems;
import com.jojoreference.allomancy.metal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntArray;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.lwjgl.system.CallbackI;

import static com.jojoreference.allomancy.blocks.ModBlocks.ALLOYMIXER_CONTAINER;

public class AlloyMixerContainer extends Container {
    private TileEntity tileEntity;
    private PlayerEntity playerEntity;
    private IItemHandler playerInventory;

    public AlloyMixerContainer(int id, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(ALLOYMIXER_CONTAINER, id);
        tileEntity = world.getTileEntity(pos);
        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);

        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
            //Position of item slot for the alloy mixer. Will have to change x and y to match the GUI.
            //x and y match first light gray pixel of inventory slot.
            for(int i = 0; i < 8; i++) {
                addSlot(new SlotItemHandler(h, i, 19 + 18*i, 53));
            }

        });
        //Position of player's inventory. Change leftcol and toprow if I edit the GUI.
        //x and y match first light gray pixel of inventory slot.
        layoutPlayerInventorySlots(10, 120);

        trackIntArray(tileEntity.getCapability(CapabilityMetal.METAL).map(m -> m.MakeArray()).orElse(new IntArray(8)));

    }

    public int getMetalAmount(int i) {
        return tileEntity.getCapability(CapabilityMetal.METAL).map(m -> m.GetAmount(i)).orElse(0);
    }

    public String getMetalName(int i) {
        return tileEntity.getCapability(CapabilityMetal.METAL).map(m -> m.GetMetalName(i)).orElse("None");
    }

    public int getMaxMetal(int i) {
        return tileEntity.getCapability(CapabilityMetal.METAL).map(IMetalStorageHandler::GetMaxAmount).orElse(400);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerEntity, ModBlocks.ALLOYMIXER);
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
