package com.jojoreference.allomancy.entity;

import com.jojoreference.allomancy.items.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class CopperClipEntity extends ProjectileItemEntity {

    public CopperClipEntity(World worldIn) {
        super(ModEntities.COPPERCLIP_ENTITY, worldIn);
    }

    public CopperClipEntity(EntityType<CopperClipEntity> type, World world) {
        super(type, world);

    }

    public CopperClipEntity(World worldIn, LivingEntity throwerIn) {
        super(ModEntities.COPPERCLIP_ENTITY, throwerIn, worldIn);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.COPPERCLIP;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)3);
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }
    }

}
