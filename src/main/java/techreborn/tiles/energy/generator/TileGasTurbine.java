package techreborn.tiles.energy.generator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidTank;
import reborncore.api.power.EnumPowerTier;
import reborncore.api.tile.IContainerProvider;
import reborncore.api.tile.IInventoryProvider;
import reborncore.common.container.RebornContainer;
import reborncore.common.util.inventory.Inventory;
import techreborn.client.container.energy.generator.ContainerGasTurbine;
import techreborn.config.ConfigTechReborn;
import techreborn.init.ModBlocks;

import java.util.HashMap;
import java.util.Map;

public class TileGasTurbine extends AbstractTileGenerator implements IInventoryProvider, IContainerProvider {

	// TODO: run this off config
	//public static final int euTick = 16;
	//public Tank tank = new Tank("TileGasTurbine", FluidContainerRegistry.BUCKET_VOLUME * 10, this);
	public FluidTank tank = new FluidTank(Fluid.BUCKET_VOLUME * 10);
	private Inventory inventory = new Inventory("TileGasTurbine", 3, 64, this);
	private Map<String, Integer> fluids = new HashMap<>();

	private int slotFakeFluid = 2;

	// We use this to keep track of fractional millibuckets, allowing us to hit
	// our eu/bucket targets while still only ever removing integer millibucket
	// amounts.
	private double pendingWithdraw = 0.0;

	public TileGasTurbine() {
		//super(ConfigTechReborn.ThermalGeneratorTier);
		super(EnumPowerTier.LOW, ConfigTechReborn.ThermalGeneratorCharge);
		// TODO: fix this to have Gas Turbine generator values

		this.fluids.put("fluidhydrogen", 15000);
		this.fluids.put("fluidmethane", 45000);
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(ModBlocks.gasTurbine, 1);
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
	public void updateRequirements() {
	}

	@Override
	public void updateEntity() {
		//super.updateEntity();

		//		if (!worldObj.isRemote) {
		//			FluidUtils.drainContainers(this, inventory, 0, 1);
		//			tank.compareAndUpdate();
		//		}

		int euPerTick = 16;
		if (this.tank.getFluid() != null && this.tank.getFluidAmount() > 0 && canAddEnergy(euPerTick)) {
			if (this.fluids.containsKey(this.tank.getFluid().getFluid().getName())) {
				int euPerBucket = this.fluids.get(this.tank.getFluid().getFluid().getName());

				// float totalTicks = (float)euPerBucket / 8f; //x eu per bucket / 8
				// eu per tick
				// float millibucketsPerTick = 1000f / totalTicks;

				float millibucketsPerTick = 16000f / (float) euPerBucket;
				this.pendingWithdraw += millibucketsPerTick;

				int currentWithdraw = (int) this.pendingWithdraw; // float --> int

				// conversion floors
				// the float

				this.pendingWithdraw -= currentWithdraw;

				this.tank.drain(currentWithdraw, true);
				addEnergy(euPerTick);
			}
		}

		if (this.tank.getFluid() != null && getInventory().getStackInSlot(this.slotFakeFluid) == null) {
			getInventory().setInventorySlotContents(this.slotFakeFluid, new ItemStack(this.tank.getFluid().getFluid().getBlock()));
		} else if (this.tank.getFluid() == null && getInventory().getStackInSlot(this.slotFakeFluid) != null) {
			getInventory().setInventorySlotContents(this.slotFakeFluid, null);
		}

		markBlockForUpdate();
	}

	@Override
	public Inventory getInventory() {
		return this.inventory;
	}

	@Override
	public RebornContainer getContainer() {
		return RebornContainer.getContainerFromClass(ContainerGasTurbine.class, this);
	}
}
