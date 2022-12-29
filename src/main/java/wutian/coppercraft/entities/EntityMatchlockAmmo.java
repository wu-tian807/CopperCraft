package wutian.coppercraft.entities;

import com.mojang.logging.LogUtils;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LightBlock;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;
import wutian.coppercraft.items.ModItemRegistryHandler;
import wutian.coppercraft.util.ModTags;

import java.util.Random;

public class EntityMatchlockAmmo extends ThrowableItemProjectile {
    private int life;
    private BlockPos lightBlockPos;
    private boolean firstFlag;
    public double damage;
    private int ticksInGround;
    private static final EntityDataAccessor<Integer> ID_EFFECT_COLOR = SynchedEntityData.defineId(EntityMatchlockAmmo.class, EntityDataSerializers.INT);

    public EntityMatchlockAmmo(EntityType<?> entityIn, Level level) {

        super((EntityType<? extends EntityMatchlockAmmo>) entityIn, level);


        life =0;

        firstFlag = true;

        this.damage = 12D;

        // TODO Auto-generated constructor stub

        int r = random.nextInt(5);
        //LogUtils.getLogger().debug(String.valueOf(r));
        if(r<4)
            damage *=0.8;
        else
            damage *=1.3;

        //LogUtils.getLogger().debug(String.valueOf(life));
        lightBlockPos = this.getOnPos();
        LogUtils.getLogger().debug(String.valueOf(level.getBlockState(lightBlockPos).getBlock()));
        if(!level.getBlockState(lightBlockPos).is(Blocks.AIR) && !level.getBlockState(lightBlockPos).is(ModTags.Blocks.GUN_CAN_DESTROY)) return;
        level.destroyBlock(lightBlockPos,true);
        level.setBlock(lightBlockPos, Blocks.LIGHT.defaultBlockState().setValue(LightBlock.LEVEL, 8), 2);
    }

    public EntityMatchlockAmmo(Level world, LivingEntity entity) {

        super(ModEntityTypes.MATCHLOCK_GUN_AMMO.get(), entity, world);

        life =0;

        firstFlag = true;

        this.damage = 12D;

        // TODO Auto-generated constructor stub

        int r = random.nextInt(5);
        //LogUtils.getLogger().debug(String.valueOf(r));
        if(r<4)
            damage *=0.8;
        else
            damage *=1.3;

        //LogUtils.getLogger().debug(String.valueOf(life));
        lightBlockPos = this.getOnPos();
        LogUtils.getLogger().debug(String.valueOf(level.getBlockState(lightBlockPos).getBlock()));
        if(!level.getBlockState(lightBlockPos).is(Blocks.AIR) && !level.getBlockState(lightBlockPos).is(ModTags.Blocks.GUN_CAN_DESTROY)) return;
        level.destroyBlock(lightBlockPos,true);
        level.setBlock(lightBlockPos, Blocks.LIGHT.defaultBlockState().setValue(LightBlock.LEVEL, 8), 2);

    }

    @Override
    protected Item getDefaultItem() {
        return ModItemRegistryHandler.MATCHLOCK_GUN_CHIP.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {

//        if(Math.sqrt(this.getOnPos().distToCenterSqr(lightBlockPos.getX(),lightBlockPos.getY(),lightBlockPos.getZ())) >= 1)
//        {
//            damage -= random.nextInt(1,3)*new Random().nextFloat(1,1.8f);
//            LogUtils.getLogger().debug(String.valueOf(damage));
//        }
//        else if(Math.sqrt(this.getOnPos().distToCenterSqr(lightBlockPos.getX(),lightBlockPos.getY(),lightBlockPos.getZ())) >= 2)
//        {
//            damage -= random.nextInt(3,5)*new Random().nextFloat(1,1.8f);
//        }
//        else if(Math.sqrt(this.getOnPos().distToCenterSqr(lightBlockPos.getX(),lightBlockPos.getY(),lightBlockPos.getZ())) >= 3)
//        {
//            damage -= random.nextInt(4,7)*new Random().nextFloat(1,1.8f);
//        }
//        else if(Math.sqrt(this.getOnPos().distToCenterSqr(lightBlockPos.getX(),lightBlockPos.getY(),lightBlockPos.getZ())) >= 6)
//        {
//            damage = 0;
//        }
        for(int i=life;i>=0;i-=5)
        {
            damage -= new Random().nextInt(2,5);
        }
        if(!level.isClientSide())
        {
            if(firstFlag && level.getBlockState(lightBlockPos).is(Blocks.LIGHT))
            {
                firstFlag = false;
                level.setBlock(lightBlockPos, Blocks.AIR.defaultBlockState(), 2);
            }
        }
//        //LogUtils.getLogger().debug(String.valueOf(damage));
        if(result.getEntity().getLevel().isClientSide) return;
        Entity entity = result.getEntity();

        if(entity instanceof EnderDragon || entity instanceof Warden || entity instanceof WitherBoss)
            return;
        entity.hurt(DamageSource.thrown(this, this.getOwner()), (float)(this.damage));
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);

        if (!this.level.isClientSide) {

            this.level.broadcastEntityEvent(this, (byte)3);

            this.remove(RemovalReason.KILLED);

        }
    }
    @Override

    public Packet<ClientGamePacketListener> getAddEntityPacket() {

        FriendlyByteBuf pack = new FriendlyByteBuf(Unpooled.buffer());

        pack.writeDouble(getX());

        pack.writeDouble(getY());

        pack.writeDouble(getZ());

        pack.writeInt(getId());

        pack.writeUUID(getUUID());

        return NetworkHooks.getEntitySpawningPacket(this);

    }

    @Override
    public void tick() {

//            LogUtils.getLogger().debug(String.valueOf(life));
//            LogUtils.getLogger().debug(String.valueOf("forstFlag" + firstFlag));
//            LogUtils.getLogger().debug(String.valueOf(life == 3 && firstFlag));
            if(!level.isClientSide())
            {
                if(life == 3 && firstFlag && level.getBlockState(lightBlockPos).is(Blocks.LIGHT))
                {

                    //LogUtils.getLogger().debug(String.valueOf("replace"));
                    firstFlag = false;
                    level.setBlock(lightBlockPos, Blocks.AIR.defaultBlockState(), 2);
                }
                if(damage>0)
                {
                    if(level.getBlockState(new BlockPos(getBlockX(),getBlockY(),getBlockZ())).is(ModTags.Blocks.GUN_CAN_DESTROY))
                        level.destroyBlock(new BlockPos(getBlockX(),getBlockY(),getBlockZ()),true,null);
                    else if(level.getBlockState(new BlockPos(getBlockX(),getBlockY()+1,getBlockZ())).is(ModTags.Blocks.GUN_CAN_DESTROY))
                        level.destroyBlock(new BlockPos(getBlockX(),getBlockY()+1,getBlockZ()),true,null);
                    else if(level.getBlockState(new BlockPos(getBlockX(),getBlockY()-1,getBlockZ())).is(ModTags.Blocks.GUN_CAN_DESTROY))
                        level.destroyBlock(new BlockPos(getBlockX(),getBlockY()-1,getBlockZ()),true,null);
                    else if(level.getBlockState(new BlockPos(getBlockX(),getBlockY(),getBlockZ()+1)).is(ModTags.Blocks.GUN_CAN_DESTROY))
                        level.destroyBlock(new BlockPos(getBlockX(),getBlockY(),getBlockZ()+1),true,null);
                    else if(level.getBlockState(new BlockPos(getBlockX(),getBlockY(),getBlockZ()-1)).is(ModTags.Blocks.GUN_CAN_DESTROY))
                        level.destroyBlock(new BlockPos(getBlockX(),getBlockY(),getBlockZ()-1),true,null);
                    else if(level.getBlockState(new BlockPos(getBlockX()+1,getBlockY(),getBlockZ())).is(ModTags.Blocks.GUN_CAN_DESTROY))
                        level.destroyBlock(new BlockPos(getBlockX()+1,getBlockY(),getBlockZ()),true,null);
                    else if(level.getBlockState(new BlockPos(getBlockX()-1,getBlockY(),getBlockZ())).is(ModTags.Blocks.GUN_CAN_DESTROY))
                        level.destroyBlock(new BlockPos(getBlockX()-1,getBlockY(),getBlockZ()),true,null);
                }
                life++;

            }
            if(random.nextInt(100) <=20)
                level.addParticle(ParticleTypes.CLOUD,position().x ,position().y ,position().z,0,0.05,0);

        super.tick();
    }

    @Override
    public void remove(RemovalReason pReason) {
        if(!level.isClientSide())
        {
            if(firstFlag && level.getBlockState(lightBlockPos).is(Blocks.LIGHT))
            {
                firstFlag = false;
                level.setBlock(lightBlockPos, Blocks.AIR.defaultBlockState(), 2);
            }
        }
        super.remove(pReason);
    }

    @Override
    protected float getGravity() {
        return 0.05f;
    }
}
