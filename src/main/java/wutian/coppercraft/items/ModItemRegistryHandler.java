package wutian.coppercraft.items;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import wutian.coppercraft.CopperCraft;
import wutian.coppercraft.enchantment.ModEnchantments;
import wutian.coppercraft.events.Corrosion;
import wutian.coppercraft.items.abstractItem.ModArmorItem;
import wutian.coppercraft.items.abstractItem.ModArmorMaterials;
import wutian.coppercraft.items.abstractItem.ModFoods;
import wutian.coppercraft.items.abstractItem.ModTiers;
import wutian.coppercraft.items.contents.*;

import java.util.Random;

public class ModItemRegistryHandler {

    private static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CopperCraft.MODID);

    public static final RegistryObject<Item> COPPER_SWORD =
            ITEMS.register("copper_sword",
                    CopperSword::new);

    public static final RegistryObject<Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe",() ->new PickaxeItem(ModTiers.COPPER,2,-2.4f,new Item.Properties().stacksTo(1).durability(200)){
        @Override
        public void inventoryTick(@NotNull ItemStack itemStack, @NotNull Level world, @NotNull Entity usingEntity, int slot, boolean isSelecetd) {
            {
                //logger.debug(String.format("%d,and,%b",pSlotId,pIsSelected));
                //((Player)pEntity).getInventory().setItem(pSlotId, new ItemStack(Items.DIRT));
                //pStack.shrink(1);
                //*************************************
                Corrosion.inventoryOnCorrosion(itemStack,world,usingEntity);
                Corrosion.setCorrosionToANewItem(itemStack,usingEntity,new ItemStack(ModItemRegistryHandler.COPPER_PICKAXE_CORROSION.get()),slot);
                super.inventoryTick(itemStack, world, usingEntity, slot, isSelecetd);
            }
        }

        @Override
        public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
            if(!pLevel.isClientSide())
            {
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 1)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 2)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,2));
                    if(new Random().nextInt(100) <= 10)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 3)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,3));
                    if(new Random().nextInt(100) <= 30)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,1));
                }
            }
            //LogUtils.getLogger().debug(String.valueOf(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get())));
            return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
        }
    });

    public static final RegistryObject<Item> COPPER_AXE = ITEMS.register("copper_axe",() ->new AxeItem(ModTiers.COPPER,8f,-2.4f,new Item.Properties().stacksTo(1).durability(220)){
        @Override
        public void inventoryTick(@NotNull ItemStack itemStack, @NotNull Level world, @NotNull Entity usingEntity, int slot, boolean isSelecetd) {
            {
                //logger.debug(String.format("%d,and,%b",pSlotId,pIsSelected));
                //((Player)pEntity).getInventory().setItem(pSlotId, new ItemStack(Items.DIRT));
                //pStack.shrink(1);
                //*************************************
                Corrosion.inventoryOnCorrosion(itemStack, world, usingEntity);
                Corrosion.setCorrosionToANewItem(itemStack, usingEntity, new ItemStack(ModItemRegistryHandler.COPPER_AXE_CORROSION.get()), slot);
                super.inventoryTick(itemStack, world, usingEntity, slot, isSelecetd);
            }
        }

        @Override
        public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
            if(!pLevel.isClientSide())
            {
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 1)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 2)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,2));
                    if(new Random().nextInt(100) <= 10)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 3)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,3));
                    if(new Random().nextInt(100) <= 30)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,1));
                }
            }
            //LogUtils.getLogger().debug(String.valueOf(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get())));
            return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
        }
    });
    public static final RegistryObject<Item> COPPER_SHOVEL = ITEMS.register("copper_shovel",() ->new ShovelItem(ModTiers.COPPER,4f,-2.4f,new Item.Properties().stacksTo(1).durability(200)){
        @Override
        public void inventoryTick(@NotNull ItemStack itemStack, @NotNull Level world, @NotNull Entity usingEntity, int slot, boolean isSelecetd) {
            {
                //logger.debug(String.format("%d,and,%b",pSlotId,pIsSelected));
                //((Player)pEntity).getInventory().setItem(pSlotId, new ItemStack(Items.DIRT));
                //pStack.shrink(1);
                //*************************************
                Corrosion.inventoryOnCorrosion(itemStack, world, usingEntity);
                Corrosion.setCorrosionToANewItem(itemStack, usingEntity, new ItemStack(ModItemRegistryHandler.COPPER_SHOVEL_CORROSION.get()), slot);
                super.inventoryTick(itemStack, world, usingEntity, slot, isSelecetd);
            }
        }

        @Override
        public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
            if(!pLevel.isClientSide())
            {
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 1)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 2)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,2));
                    if(new Random().nextInt(100) <= 10)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 3)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,3));
                    if(new Random().nextInt(100) <= 30)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,1));
                }
            }
            //LogUtils.getLogger().debug(String.valueOf(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get())));
            return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
        }
    });
    public static final RegistryObject<Item> COPPER_HOE = ITEMS.register("copper_hoe",() ->new HoeItem(ModTiers.COPPER,0,-2.4f,new Item.Properties().stacksTo(1).durability(300)){
        @Override
        public void inventoryTick(@NotNull ItemStack itemStack, @NotNull Level world, @NotNull Entity usingEntity, int slot, boolean isSelecetd) {
            {
                //logger.debug(String.format("%d,and,%b",pSlotId,pIsSelected));
                //((Player)pEntity).getInventory().setItem(pSlotId, new ItemStack(Items.DIRT));
                //pStack.shrink(1);
                //*************************************
                Corrosion.inventoryOnCorrosion(itemStack, world, usingEntity);
                Corrosion.setCorrosionToANewItem(itemStack, usingEntity, new ItemStack(ModItemRegistryHandler.COPPER_HOE_CORROSION.get()), slot);
                super.inventoryTick(itemStack, world, usingEntity, slot, isSelecetd);
            }
        }

        @Override
        public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
            if(!pLevel.isClientSide())
            {
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 1)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 2)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,2));
                    if(new Random().nextInt(100) <= 10)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 3)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,3));
                    if(new Random().nextInt(100) <= 30)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,1));
                }
            }
            //LogUtils.getLogger().debug(String.valueOf(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get())));
            return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
        }
    });
    public static final RegistryObject<Item> COPPER_PICKAXE_CORROSION = ITEMS.register("copper_pickaxe_corrosion",() ->new PickaxeItem(ModTiers.COPPER_CORROSION,2,-2.6f,new Item.Properties().stacksTo(1).durability(200)){
        @Override
        public void inventoryTick(@NotNull ItemStack itemStack, @NotNull Level world, @NotNull Entity usingEntity, int slot, boolean isSelecetd) {
            {
                //logger.debug(String.format("%d,and,%b",pSlotId,pIsSelected));
                //((Player)pEntity).getInventory().setItem(pSlotId, new ItemStack(Items.DIRT));
                //pStack.shrink(1);
                //*************************************
                Corrosion.inventoryOnCorrosion(itemStack,world,usingEntity);
                Corrosion.setCorrosionToANewItem(itemStack,usingEntity,new ItemStack(ModItemRegistryHandler.COPPER_PICKAXE_CORROSION_ONE.get()),slot);
                super.inventoryTick(itemStack, world, usingEntity, slot, isSelecetd);
            }
        }

        @Override
        public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
            if(!pLevel.isClientSide())
            {
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 1)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 2)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,2));
                    if(new Random().nextInt(100) <= 10)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 3)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,3));
                    if(new Random().nextInt(100) <= 30)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,1));
                }
            }
            //LogUtils.getLogger().debug(String.valueOf(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get())));
            return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
        }
    });

    public static final RegistryObject<Item> COPPER_AXE_CORROSION = ITEMS.register("copper_axe_corrosion",() ->new AxeItem(ModTiers.COPPER_CORROSION,7f,-2.6f,new Item.Properties().stacksTo(1).durability(220)){
        @Override
        public void inventoryTick(@NotNull ItemStack itemStack, @NotNull Level world, @NotNull Entity usingEntity, int slot, boolean isSelecetd) {
            {
                //logger.debug(String.format("%d,and,%b",pSlotId,pIsSelected));
                //((Player)pEntity).getInventory().setItem(pSlotId, new ItemStack(Items.DIRT));
                //pStack.shrink(1);
                //*************************************
                Corrosion.inventoryOnCorrosion(itemStack, world, usingEntity);
                Corrosion.setCorrosionToANewItem(itemStack, usingEntity, new ItemStack(ModItemRegistryHandler.COPPER_AXE_CORROSION_ONE.get()), slot);
                super.inventoryTick(itemStack, world, usingEntity, slot, isSelecetd);
            }
        }

        @Override
        public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
            if(!pLevel.isClientSide())
            {
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 1)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 2)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,2));
                    if(new Random().nextInt(100) <= 10)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 3)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,3));
                    if(new Random().nextInt(100) <= 30)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,1));
                }
            }
            //LogUtils.getLogger().debug(String.valueOf(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get())));
            return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
        }
    });
    public static final RegistryObject<Item> COPPER_SHOVEL_CORROSION = ITEMS.register("copper_shovel_corrosion",() ->new ShovelItem(ModTiers.COPPER_CORROSION,3f,-2.6f,new Item.Properties().stacksTo(1).durability(200)){
        @Override
        public void inventoryTick(@NotNull ItemStack itemStack, @NotNull Level world, @NotNull Entity usingEntity, int slot, boolean isSelecetd) {
            {
                //logger.debug(String.format("%d,and,%b",pSlotId,pIsSelected));
                //((Player)pEntity).getInventory().setItem(pSlotId, new ItemStack(Items.DIRT));
                //pStack.shrink(1);
                //*************************************
                Corrosion.inventoryOnCorrosion(itemStack, world, usingEntity);
                Corrosion.setCorrosionToANewItem(itemStack, usingEntity, new ItemStack(ModItemRegistryHandler.COPPER_SHOVEL_CORROSION_ONE.get()), slot);
                super.inventoryTick(itemStack, world, usingEntity, slot, isSelecetd);
            }
        }

        @Override
        public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
            if(!pLevel.isClientSide())
            {
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 1)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 2)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,2));
                    if(new Random().nextInt(100) <= 10)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 3)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,3));
                    if(new Random().nextInt(100) <= 30)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,1));
                }
            }
            //LogUtils.getLogger().debug(String.valueOf(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get())));
            return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
        }
    });
    public static final RegistryObject<Item> COPPER_HOE_CORROSION = ITEMS.register("copper_hoe_corrosion",() ->new HoeItem(ModTiers.COPPER_CORROSION,1,-2.6f,new Item.Properties().stacksTo(1).durability(200)){
        @Override
        public void inventoryTick(@NotNull ItemStack itemStack, @NotNull Level world, @NotNull Entity usingEntity, int slot, boolean isSelecetd) {
            {
                //logger.debug(String.format("%d,and,%b",pSlotId,pIsSelected));
                //((Player)pEntity).getInventory().setItem(pSlotId, new ItemStack(Items.DIRT));
                //pStack.shrink(1);
                //*************************************
                Corrosion.inventoryOnCorrosion(itemStack, world, usingEntity);
                Corrosion.setCorrosionToANewItem(itemStack, usingEntity, new ItemStack(ModItemRegistryHandler.COPPER_HOE_CORROSION_ONE.get()), slot);
                super.inventoryTick(itemStack, world, usingEntity, slot, isSelecetd);
            }
        }

        @Override
        public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
            if(!pLevel.isClientSide())
            {
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 1)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 2)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,2));
                    if(new Random().nextInt(100) <= 10)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 3)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,3));
                    if(new Random().nextInt(100) <= 30)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,1));
                }
            }
            //LogUtils.getLogger().debug(String.valueOf(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get())));
            return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
        }
    });


    public static final RegistryObject<Item> COPPER_PICKAXE_CORROSION_ONE = ITEMS.register("copper_pickaxe_corrosion_one",() ->new PickaxeItem(ModTiers.COPPER_CORROSION_ONE,1,-2.8f,new Item.Properties().stacksTo(1).durability(200)){
        @Override
        public void inventoryTick(@NotNull ItemStack itemStack, @NotNull Level world, @NotNull Entity usingEntity, int slot, boolean isSelecetd) {
            {
                //logger.debug(String.format("%d,and,%b",pSlotId,pIsSelected));
                //((Player)pEntity).getInventory().setItem(pSlotId, new ItemStack(Items.DIRT));
                //pStack.shrink(1);
                //*************************************
                Corrosion.inventoryOnCorrosion(itemStack,world,usingEntity);
                Corrosion.setCorrosionToANewItem(itemStack,usingEntity,new ItemStack(ModItemRegistryHandler.COPPER_PICKAXE_CORROSION_TWO.get()),slot);
                super.inventoryTick(itemStack, world, usingEntity, slot, isSelecetd);
            }
        }

        @Override
        public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
            if(!pLevel.isClientSide())
            {
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 1)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 2)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,2));
                    if(new Random().nextInt(100) <= 10)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 3)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,3));
                    if(new Random().nextInt(100) <= 30)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,1));
                }
            }
            //LogUtils.getLogger().debug(String.valueOf(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get())));
            return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
        }
    });

    public static final RegistryObject<Item> COPPER_AXE_CORROSION_ONE = ITEMS.register("copper_axe_corrosion_one",() ->new AxeItem(ModTiers.COPPER_CORROSION_ONE,5f,-2.8f,new Item.Properties().stacksTo(1).durability(220)){
        @Override
        public void inventoryTick(@NotNull ItemStack itemStack, @NotNull Level world, @NotNull Entity usingEntity, int slot, boolean isSelecetd) {
            {
                //logger.debug(String.format("%d,and,%b",pSlotId,pIsSelected));
                //((Player)pEntity).getInventory().setItem(pSlotId, new ItemStack(Items.DIRT));
                //pStack.shrink(1);
                //*************************************
                Corrosion.inventoryOnCorrosion(itemStack, world, usingEntity);
                Corrosion.setCorrosionToANewItem(itemStack, usingEntity, new ItemStack(ModItemRegistryHandler.COPPER_AXE_CORROSION_TWO.get()), slot);
                super.inventoryTick(itemStack, world, usingEntity, slot, isSelecetd);
            }
        }

        @Override
        public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
            if(!pLevel.isClientSide())
            {
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 1)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 2)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,2));
                    if(new Random().nextInt(100) <= 10)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 3)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,3));
                    if(new Random().nextInt(100) <= 30)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,1));
                }
            }
            //LogUtils.getLogger().debug(String.valueOf(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get())));
            return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
        }
    });
    public static final RegistryObject<Item> COPPER_SHOVEL_CORROSION_ONE = ITEMS.register("copper_shovel_corrosion_one",() ->new ShovelItem(ModTiers.COPPER_CORROSION_ONE,2f,-2.8f,new Item.Properties().stacksTo(1).durability(200)){
        @Override
        public void inventoryTick(@NotNull ItemStack itemStack, @NotNull Level world, @NotNull Entity usingEntity, int slot, boolean isSelecetd) {
            {
                //logger.debug(String.format("%d,and,%b",pSlotId,pIsSelected));
                //((Player)pEntity).getInventory().setItem(pSlotId, new ItemStack(Items.DIRT));
                //pStack.shrink(1);
                //*************************************
                Corrosion.inventoryOnCorrosion(itemStack, world, usingEntity);
                Corrosion.setCorrosionToANewItem(itemStack, usingEntity, new ItemStack(ModItemRegistryHandler.COPPER_SHOVEL_CORROSION_TWO.get()), slot);
                super.inventoryTick(itemStack, world, usingEntity, slot, isSelecetd);
            }
        }

        @Override
        public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
            if(!pLevel.isClientSide())
            {
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 1)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 2)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,2));
                    if(new Random().nextInt(100) <= 10)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 3)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,3));
                    if(new Random().nextInt(100) <= 30)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,1));
                }
            }
            //LogUtils.getLogger().debug(String.valueOf(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get())));
            return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
        }
    });
    public static final RegistryObject<Item> COPPER_HOE_CORROSION_ONE = ITEMS.register("copper_hoe_corrosion_one",() ->new HoeItem(ModTiers.COPPER_CORROSION_ONE,0,-2.8f,new Item.Properties().stacksTo(1).durability(200)){
        @Override
        public void inventoryTick(@NotNull ItemStack itemStack, @NotNull Level world, @NotNull Entity usingEntity, int slot, boolean isSelecetd) {
            {
                //logger.debug(String.format("%d,and,%b",pSlotId,pIsSelected));
                //((Player)pEntity).getInventory().setItem(pSlotId, new ItemStack(Items.DIRT));
                //pStack.shrink(1);
                //*************************************
                Corrosion.inventoryOnCorrosion(itemStack, world, usingEntity);
                Corrosion.setCorrosionToANewItem(itemStack, usingEntity, new ItemStack(ModItemRegistryHandler.COPPER_HOE_CORROSION_TWO.get()), slot);
                super.inventoryTick(itemStack, world, usingEntity, slot, isSelecetd);
            }
        }

        @Override
        public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
            if(!pLevel.isClientSide())
            {
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 1)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 2)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,2));
                    if(new Random().nextInt(100) <= 10)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 3)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,3));
                    if(new Random().nextInt(100) <= 30)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,1));
                }
            }
            //LogUtils.getLogger().debug(String.valueOf(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get())));
            return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
        }
    });
    public static final RegistryObject<Item> COPPER_PICKAXE_CORROSION_TWO = ITEMS.register("copper_pickaxe_corrosion_two",() ->new PickaxeItem(ModTiers.COPPER_CORROSION_ONE,1,-3f,new Item.Properties().stacksTo(1).durability(200)){
        @Override
        public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
            if(!pLevel.isClientSide())
            {
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 1)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 2)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,2));
                    if(new Random().nextInt(100) <= 10)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 3)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,3));
                    if(new Random().nextInt(100) <= 30)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,1));
                }
            }
            //LogUtils.getLogger().debug(String.valueOf(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get())));
            return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
        }
    });
    public static final RegistryObject<Item> COPPER_AXE_CORROSION_TWO = ITEMS.register("copper_axe_corrosion_two",() ->new PickaxeItem(ModTiers.COPPER_CORROSION_ONE,4,-3f,new Item.Properties().stacksTo(1).durability(200)){
        @Override
        public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
            if(!pLevel.isClientSide())
            {
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 1)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 2)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,2));
                    if(new Random().nextInt(100) <= 10)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 3)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,3));
                    if(new Random().nextInt(100) <= 30)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,1));
                }
            }
            //LogUtils.getLogger().debug(String.valueOf(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get())));
            return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
        }
    });
    public static final RegistryObject<Item> COPPER_SHOVEL_CORROSION_TWO = ITEMS.register("copper_shovel_corrosion_two",() ->new PickaxeItem(ModTiers.COPPER_CORROSION_ONE,1,-3f,new Item.Properties().stacksTo(1).durability(220)){
        @Override
        public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
            if(!pLevel.isClientSide())
            {
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 1)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 2)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,2));
                    if(new Random().nextInt(100) <= 10)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 3)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,3));
                    if(new Random().nextInt(100) <= 30)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,1));
                }
            }
            //LogUtils.getLogger().debug(String.valueOf(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get())));
            return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
        }
    });
    public static final RegistryObject<Item> COPPER_HOE_CORROSION_TWO = ITEMS.register("copper_hoe_corrosion_two",() ->new PickaxeItem(ModTiers.COPPER_CORROSION_ONE,-1,-3f,new Item.Properties().stacksTo(1).durability(200)){
        @Override
        public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
            if(!pLevel.isClientSide())
            {
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 1)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 2)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,2));
                    if(new Random().nextInt(100) <= 10)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 3)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,3));
                    if(new Random().nextInt(100) <= 30)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,1));
                }
            }
            //LogUtils.getLogger().debug(String.valueOf(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get())));
            return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
        }
    });



    public static final RegistryObject<Item> COPPER_PAXEL = ITEMS.register("copper_paxel", CopperPaxel::new);

    public static final RegistryObject<Item> COPPER_PAXEL_CORROSION = ITEMS.register("copper_paxel_corrosion", CopperPaxelCorrosion::new);

    public static final RegistryObject<Item> COPPER_PAXEL_CORROSION_ONE = ITEMS.register("copper_paxel_corrosion_one", CopperPaxelCorrosionOne::new);
    public static final RegistryObject<Item> COPPER_PAXEL_CORROSION_TWO = ITEMS.register("copper_paxel_corrosion_two", CopperPaxelCorrosionTwo::new);

    public static final RegistryObject<Item> COPPER_HELMET = ITEMS.register("copper_helmet",() -> new ModArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.HEAD,new Item.Properties()));
    public static final RegistryObject<Item> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate",() -> new ModArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> COPPER_LEGGINGS = ITEMS.register("copper_leggings",() -> new ModArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> COPPER_BOOTS = ITEMS.register("copper_boots",() -> new ModArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> COPPER_HELMET_CORROSION = ITEMS.register("copper_helmet_corrosion",() -> new ModArmorItem(ModArmorMaterials.COPPER_CORROSION, EquipmentSlot.HEAD,new Item.Properties()));
    public static final RegistryObject<Item> COPPER_CHESTPLATE_CORROSION = ITEMS.register("copper_chestplate_corrosion",() -> new ModArmorItem(ModArmorMaterials.COPPER_CORROSION, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> COPPER_LEGGINGS_CORROSION = ITEMS.register("copper_leggings_corrosion",() -> new ModArmorItem(ModArmorMaterials.COPPER_CORROSION, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> COPPER_BOOTS_CORROSION = ITEMS.register("copper_boots_corrosion",() -> new ModArmorItem(ModArmorMaterials.COPPER_CORROSION, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> COPPER_HORSE_ARMOR = ITEMS.register("copper_horse_armor",() -> new HorseArmorItem(4,"copper",new Item.Properties()));

    public static final RegistryObject<Item> IRON_MIXED_COPPER_HELMET = ITEMS.register("iron_mixed_copper_helmet",() -> new ModArmorItem(ModArmorMaterials.IRON_MIXED_COPPER, EquipmentSlot.HEAD,new Item.Properties()));
    public static final RegistryObject<Item> IRON_MIXED_COPPER_CHESTPLATE = ITEMS.register("iron_mixed_copper_chestplate",() -> new ModArmorItem(ModArmorMaterials.IRON_MIXED_COPPER, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> IRON_MIXED_COPPER_LEGGINGS = ITEMS.register("iron_mixed_copper_leggings",() -> new ModArmorItem(ModArmorMaterials.IRON_MIXED_COPPER, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> IRON_MIXED_COPPER_BOOTS = ITEMS.register("iron_mixed_copper_boots",() -> new ModArmorItem(ModArmorMaterials.IRON_MIXED_COPPER, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> IRON_MIXED_COPPER_SWORD =
            ITEMS.register("iron_mixed_copper_sword",
                    IronMixedCopperSword::new);
    public static final RegistryObject<Item> IRON_MIXED_COPPER_PICKAXE =
            ITEMS.register("iron_mixed_copper_pickaxe",
                    IronMixedCopperPickAxe::new);
    public static final RegistryObject<Item> IRON_MIXED_COPPER_AXE =
            ITEMS.register("iron_mixed_copper_axe",
                    IronMixedCopperAxe::new);
    public static final RegistryObject<Item> IRON_MIXED_COPPER_SHOVEL =
            ITEMS.register("iron_mixed_copper_shovel",
                    IronMixedCopperShovel::new);
    public static final RegistryObject<Item> IRON_MIXED_COPPER_HOE =
            ITEMS.register("iron_mixed_copper_hoe",
                    IronMixedCopperHoe::new);

    public static final RegistryObject<Item> NETHERITE_IRON_INGOT =
            ITEMS.register("netherite_iron_ingot",
                    () ->new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(64)));

    public static final RegistryObject<Item> IRON_MIXED_COPPER_PAXEL =
            ITEMS.register("iron_mixed_copper_paxel", IronMixedCopperPaxel::new);

    public static final RegistryObject<Item> COPPERY_APPLE =
            ITEMS.register("coppery_apple", CopperyApple::new);
    public static final RegistryObject<Item> ENCHANTED_COPPERY_APPLE =
            ITEMS.register("enchanted_coppery_apple",() ->new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(64).food(ModFoods.ENCHANTED_COPPERY_APPLE)){
                @Override
                public boolean isFoil(ItemStack pStack) {
                    return true;
                }
            });

    public static final RegistryObject<Item> DERUSTING_SHOVEL =
            ITEMS.register("derusting_shovel",()->new Item(new Item.Properties().stacksTo(1).durability(3)));

    public static final RegistryObject<Item> COPPER_MIXED_GOLD_INGOT = ITEMS.register("copper_mixed_gold_ingot",()-> new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> COPPER_MIXED_GOLDEN_SWORD = ITEMS.register("copper_mixed_golden_sword",()-> new SwordItem(ModTiers.COPPER_MIXED_GOLD,5,-2.2f,new Item.Properties().stacksTo(1)));


    public static final RegistryObject<Item> COPPER_MIXED_GOLDEN_PICKAXE = ITEMS.register("copper_mixed_golden_pickaxe",()-> new PickaxeItem(ModTiers.COPPER_MIXED_GOLD,3,-2.2f,new Item.Properties().stacksTo(1)){
        @Override
        public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
            if(!pLevel.isClientSide())
            {
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 1)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 2)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,2));
                    if(new Random().nextInt(100) <= 10)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 3)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,3));
                    if(new Random().nextInt(100) <= 30)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,1));
                }
            }
            //LogUtils.getLogger().debug(String.valueOf(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get())));
            return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
        }
    });

    public static final RegistryObject<Item> COPPER_MIXED_GOLDEN_AXE = ITEMS.register("copper_mixed_golden_axe",()-> new AxeItem(ModTiers.COPPER_MIXED_GOLD,8,-2.2f,new Item.Properties().stacksTo(1)){
        @Override
        public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
            if(!pLevel.isClientSide())
            {
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 1)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 2)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,2));
                    if(new Random().nextInt(100) <= 10)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 3)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,3));
                    if(new Random().nextInt(100) <= 30)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,1));
                }
            }
            //LogUtils.getLogger().debug(String.valueOf(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get())));
            return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
        }
    });

    public static final RegistryObject<Item> COPPER_MIXED_GOLDEN_SHOVEL = ITEMS.register("copper_mixed_golden_shovel",()-> new ShovelItem(ModTiers.COPPER_MIXED_GOLD,1.5f,-2.2f,new Item.Properties().stacksTo(1)){
        @Override
        public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
            if(!pLevel.isClientSide())
            {
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 1)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 2)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,2));
                    if(new Random().nextInt(100) <= 10)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 3)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,3));
                    if(new Random().nextInt(100) <= 30)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,1));
                }
            }
            //LogUtils.getLogger().debug(String.valueOf(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get())));
            return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
        }
    });

    public static final RegistryObject<Item> COPPER_MIXED_GOLDEN_HOE = ITEMS.register("copper_mixed_golden_hoe",()-> new HoeItem(ModTiers.COPPER_MIXED_GOLD,0,-2f,new Item.Properties().stacksTo(1)){
        @Override
        public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
            if(!pLevel.isClientSide())
            {
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 1)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 2)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,2));
                    if(new Random().nextInt(100) <= 10)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,0));
                }
                if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 3)
                {
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,3));
                    if(new Random().nextInt(100) <= 30)
                        pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,1));
                }
            }
            //LogUtils.getLogger().debug(String.valueOf(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get())));
            return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
        }
    });

    public static final RegistryObject<Item> COPPER_MIXED_GOLDEN_HELMET = ITEMS.register("copper_mixed_golden_helmet",()-> new ModArmorItem(ModArmorMaterials.COPPER_MIXED_GOLD,EquipmentSlot.HEAD,new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> COPPER_MIXED_GOLDEN_CHESTPLATE = ITEMS.register("copper_mixed_golden_chestplate",()-> new ModArmorItem(ModArmorMaterials.COPPER_MIXED_GOLD,EquipmentSlot.CHEST,new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> COPPER_MIXED_GOLDEN_LEGGINGS = ITEMS.register("copper_mixed_golden_leggings",()-> new ModArmorItem(ModArmorMaterials.COPPER_MIXED_GOLD,EquipmentSlot.LEGS,new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> COPPER_MIXED_GOLDEN_BOOTS = ITEMS.register("copper_mixed_golden_boots",()-> new ModArmorItem(ModArmorMaterials.COPPER_MIXED_GOLD,EquipmentSlot.FEET,new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> COPPER_NUGGET = ITEMS.register("copper_nugget",()-> new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> MATCHLOCK_GUN_AMMO = ITEMS.register("matchlock_gun_ammo", ()->new Item(new Item.Properties().stacksTo(32)));

    public static final RegistryObject<Item> MATCHLOCK_GUN = ITEMS.register("matchlock_gun", MatchlockGun::new);

    public static final RegistryObject<Item> MATCHLOCK_GUN_CHIP_EMPTY = ITEMS.register("matchlock_gun_chip_empty", ()->new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> MATCHLOCK_GUN_CHIP = ITEMS.register("matchlock_gun_chip", MatchlockGunChip::new);


    public static void register(IEventBus eventBus) {
        //eventBus.register(ITEMS);
        ITEMS.register(eventBus);
    }
}
