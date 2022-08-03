package wutian.coppercraft.items.abstractItem;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.util.Lazy;
import wutian.coppercraft.CopperCraft;

import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {
    COPPER("copper", 10, new int[]{2, 3, 4, 2}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.5F, 0.0F, () -> {
        return Ingredient.of(Items.COPPER_INGOT);
    }),
    COPPER_CORROSION("copper_corrosion",10,new int[]{1,3,3,1},9,SoundEvents.ARMOR_EQUIP_IRON,0.0f,0.0f,()->
    {
        return Ingredient.of((Items.COPPER_INGOT));
    }),
    IRON_MIXED_COPPER("iron_mixed_copper",20,new int[]{3,5,6,3},12,SoundEvents.ARMOR_EQUIP_NETHERITE,0.7f,0.2f,()->
    {
        return Ingredient.of((Items.COPPER_INGOT));
    }),
    COPPER_MIXED_GOLD("copper_mixed_gold",15,new int[]{2,5,5,2},28,SoundEvents.ARMOR_EQUIP_GOLD,0.5f,0.0f,()->{
        return Ingredient.of(Items.GOLD_INGOT);
    })
    ;
    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Lazy<Ingredient> repairIngredient;

    ModArmorMaterials(String pName, int pDurabilityMultiplier, int[] pSlotProtections, int pEnchantmentValue, SoundEvent pSound, float pToughness, float pKnockbackResistance, Supplier<Ingredient> pRepairIngredient) {
        this.name = pName;
        this.durabilityMultiplier = pDurabilityMultiplier;
        this.slotProtections = pSlotProtections;
        this.enchantmentValue = pEnchantmentValue;
        this.sound = pSound;
        this.toughness = pToughness;
        this.knockbackResistance = pKnockbackResistance;
        this.repairIngredient = Lazy.of(pRepairIngredient);
    }

    public int getDurabilityForSlot(EquipmentSlot pSlot) {
        return HEALTH_PER_SLOT[pSlot.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlot pSlot) {
        return this.slotProtections[pSlot.getIndex()];
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return CopperCraft.MODID + ":" + this.name; // important
    }

    public float getToughness() {
        return this.toughness;
    }

    /**
     * Gets the percentage of knockback resistance provided by armor of the material.
     */
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
