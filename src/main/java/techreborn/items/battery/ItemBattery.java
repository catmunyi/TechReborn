package techreborn.items.battery;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reborncore.api.power.IEnergyItemInfo;
import reborncore.common.powerSystem.PowerSystem;
import reborncore.common.powerSystem.PoweredItem;
import techreborn.items.ItemTRNoDestroy;

import javax.annotation.Nullable;

public class ItemBattery extends ItemTRNoDestroy implements IEnergyItemInfo {

	String name;
	int maxEnergy = 0;
	int maxTransfer = 0;
	int tier = 0;

	public ItemBattery(String name, int maxEnergy, int maxTransfer, int tier) {
		super();
		setMaxStackSize(1);
		setMaxDamage(1);
		setUnlocalizedName("techreborn." + name);
		this.name = name;
		this.maxEnergy = maxEnergy;
		this.maxTransfer = maxTransfer;
		this.tier = tier;
		this.addPropertyOverride(new ResourceLocation("techreborn:empty"), new IItemPropertyGetter() {
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack,
			                   @Nullable
				                   World worldIn,
			                   @Nullable
				                   EntityLivingBase entityIn) {
				if (stack != ItemStack.EMPTY && PoweredItem.getEnergy(stack) == 0.0) {
					return 1.0F;
				}
				return 0.0F;
			}
		});
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		double charge = (PoweredItem.getEnergy(stack) / getMaxPower(stack));
		return 1 - charge;

	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}

	@Override
	public int getRGBDurabilityForDisplay(ItemStack stack) {
		return PowerSystem.getDisplayPower().colour;
	}

	@Override
	public double getMaxPower(ItemStack stack) {
		return maxEnergy;
	}

	@Override
	public boolean canAcceptEnergy(ItemStack stack) {
		return true;
	}

	@Override
	public boolean canProvideEnergy(ItemStack stack) {
		return true;
	}

	@Override
	public double getMaxTransfer(ItemStack stack) {
		return maxTransfer;
	}

	@Override
	public int getStackTier(ItemStack stack) {
		return tier;
	}
}
