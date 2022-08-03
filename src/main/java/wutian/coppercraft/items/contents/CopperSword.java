package wutian.coppercraft.items.contents;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import wutian.coppercraft.events.Corrosion;
import wutian.coppercraft.items.abstractItem.ModTiers;


public class CopperSword extends SwordItem {
    public CopperSword() {
        super(ModTiers.COPPER,4,-2.2f,
                new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).
                        stacksTo(1));

    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level world, Entity usingEntity, int slot, boolean isSelected) {
        try {
            if(itemStack.getTag().getInt("CustomModelData")<=3)
                Corrosion.inventoryOnCorrosion(itemStack, world, usingEntity);
        }
        catch(NullPointerException e)
        {
            itemStack.getTag().putInt("CustomModelData",0);
        }
        //set attribute for change sword
        if (itemStack.getTag().getInt("theDegreeOfCorrosion") > 5) {
            if (itemStack.getTag().contains("CustomModelData")) {
                if (itemStack.getTag().getInt("CustomModelData") <= 3)
                    itemStack.getTag().putInt("CustomModelData", itemStack.getTag().getInt("CustomModelData") + 1);
                itemStack.getTag().putInt("theDegreeOfCorrosion", 0);
                switch (itemStack.getTag().getInt("CustomModelData")) {
                    case 1 -> {
                        itemStack.addAttributeModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier("Attack_Damage", 3, AttributeModifier.Operation.ADDITION), EquipmentSlot.MAINHAND);
                        itemStack.addAttributeModifier(Attributes.ATTACK_SPEED, new AttributeModifier("Attack_Speed", -2.6, AttributeModifier.Operation.ADDITION), EquipmentSlot.MAINHAND);
                    }
                    case 2, 3 -> {
                        itemStack.addAttributeModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier("Attack_Damage", -1, AttributeModifier.Operation.ADDITION), EquipmentSlot.MAINHAND);
                        itemStack.addAttributeModifier(Attributes.ATTACK_SPEED, new AttributeModifier("Attack_Speed", -0.2, AttributeModifier.Operation.ADDITION), EquipmentSlot.MAINHAND);
                    }
                }

            } else
                itemStack.getTag().putInt("CustomModelData", 0);
        }
        super.inventoryTick(itemStack, world, usingEntity, slot, isSelected);
    }
}
