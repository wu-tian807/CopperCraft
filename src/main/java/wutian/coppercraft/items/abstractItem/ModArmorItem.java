package wutian.coppercraft.items.abstractItem;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import wutian.coppercraft.events.Corrosion;
import wutian.coppercraft.items.ModItemRegistryHandler;

public class ModArmorItem extends ArmorItem {
    public ModArmorItem(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties) {
        super(pMaterial, pSlot, pProperties);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if(((ArmorItem)pStack.getItem()).getMaterial() == ModArmorMaterials.COPPER)
        {
            Corrosion.inventoryOnCorrosion(pStack,pLevel,pEntity);
            if((pStack.is(ModItemRegistryHandler.COPPER_HELMET.get())))
                Corrosion.setCorrosionToANewItem(pStack,pEntity,new ItemStack(ModItemRegistryHandler.COPPER_HELMET_CORROSION.get()),pSlotId);
            else if((pStack.is(ModItemRegistryHandler.COPPER_CHESTPLATE.get())))
                Corrosion.setCorrosionToANewItem(pStack,pEntity,new ItemStack(ModItemRegistryHandler.COPPER_CHESTPLATE_CORROSION.get()),pSlotId);
            if((pStack.is(ModItemRegistryHandler.COPPER_LEGGINGS.get())))
                Corrosion.setCorrosionToANewItem(pStack,pEntity,new ItemStack(ModItemRegistryHandler.COPPER_LEGGINGS_CORROSION.get()),pSlotId);
            if((pStack.is(ModItemRegistryHandler.COPPER_BOOTS.get())))
                Corrosion.setCorrosionToANewItem(pStack,pEntity,new ItemStack(ModItemRegistryHandler.COPPER_BOOTS_CORROSION.get()),pSlotId);
        }
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if(!level.isClientSide())
        {
            int count = getTheCountOfSuit(player);
            switch (count)
            {
                case 1 ->
                        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,1,0,false,false,false));
                case 2,3 ->
                        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,1,1,false,false,false));
                case 4 ->
                        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,1,2,false,false,false));
            }
        }
    }

    @Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer) {
        return stack.getItem() instanceof ArmorItem && ((ArmorItem) stack.getItem()).getMaterial() == ArmorMaterials.GOLD || ((ArmorItem) stack.getItem()).getMaterial() == ModArmorMaterials.COPPER_MIXED_GOLD;
    }

    public static int getTheCountOfSuit(Player player)
    {
        int count =0;
        if(!player.getInventory().getArmor(0).isEmpty() && player.getInventory().getArmor(0).getItem() instanceof ArmorItem)
        {
            if(((ArmorItem) player.getInventory().getArmor(0).getItem()).getMaterial() == ModArmorMaterials.COPPER_CORROSION)
                count++;
        }
        if(!player.getInventory().getArmor(1).isEmpty() && player.getInventory().getArmor(1).getItem() instanceof ArmorItem)
        {
            if(((ArmorItem) player.getInventory().getArmor(1).getItem()).getMaterial() == ModArmorMaterials.COPPER_CORROSION)
                count++;
        }
        if(!player.getInventory().getArmor(2).isEmpty() && player.getInventory().getArmor(2).getItem() instanceof ArmorItem)
        {
            if(((ArmorItem) player.getInventory().getArmor(2).getItem()).getMaterial() == ModArmorMaterials.COPPER_CORROSION)
                count++;
        }
        if(!player.getInventory().getArmor(3).isEmpty() && player.getInventory().getArmor(3).getItem() instanceof ArmorItem)
        {
            if(((ArmorItem) player.getInventory().getArmor(3).getItem()).getMaterial() == ModArmorMaterials.COPPER_CORROSION)
                count++;
        }
        return count;
    }
}
