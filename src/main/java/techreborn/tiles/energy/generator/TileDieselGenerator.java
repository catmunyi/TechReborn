package techreborn.tiles.energy.generator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import reborncore.api.fuel.FluidPowerManager;
import reborncore.api.power.EnumPowerTier;
import reborncore.api.tile.IContainerProvider;
import reborncore.api.tile.IInventoryProvider;
import reborncore.common.container.RebornContainer;
import reborncore.common.util.inventory.Inventory;
import techreborn.client.container.energy.generator.ContainerDieselGenerator;
import techreborn.config.ConfigTechReborn;
import techreborn.init.ModBlocks;

import javax.annotation.Nullable;

public class TileDieselGenerator extends AbstractTileGenerator implements IInventoryProvider, IContainerProvider {

	private Inventory inventory = new Inventory("TileDieselGenerator", 3, 64, this);
	public FluidTank tank = new FluidTank(Fluid.BUCKET_VOLUME * 10);

	private int slotFakeFluid = 2;

	public TileDieselGenerator() {
		super(EnumPowerTier.LOW, ConfigTechReborn.ThermalGeneratorCharge);
	}

	@Override
	public void updateEntity() {
		if (this.tank.getFluid() != null && getInventory().getStackInSlot(this.slotFakeFluid) == null) {
			getInventory().setInventorySlotContents(this.slotFakeFluid, new ItemStack(this.tank.getFluid().getFluid().getBlock()));
		} else if (this.tank.getFluid() == null && getInventory().getStackInSlot(this.slotFakeFluid) != null) {
			getInventory().setInventorySlotContents(this.slotFakeFluid, null);
		}

		if (this.tank.getFluid() != null && FluidPowerManager.fluidPowerValues.containsKey(this.tank.getFluid().getFluid())) {
			double powerIn = FluidPowerManager.fluidPowerValues.get(this.tank.getFluid().getFluid());
			if (canAddEnergy(powerIn)) {
				addEnergy(powerIn);
				this.tank.drain(1, true);
			}
		}
		markBlockForUpdate();
	}

	@Override
	public void updateRequirements() {
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(ModBlocks.dieselGenerator, 1);
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		this.tank.readFromNBT(tagCompound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		this.tank.writeToNBT(tagCompound);
		return tagCompound;
	}

	@Override
	public boolean hasCapability(Capability<?> capability,
	                             @Nullable
		                             EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability,
	                           @Nullable
		                           EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T) this.tank;

		return super.getCapability(capability, facing);
	}

	@Override
	public IInventory getInventory() {
		return this.inventory;
	}

	@Override
	public RebornContainer getContainer() {
		return RebornContainer.getContainerFromClass(ContainerDieselGenerator.class, this);
	}
}
