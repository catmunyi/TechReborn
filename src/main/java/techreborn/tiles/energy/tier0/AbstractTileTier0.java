package techreborn.tiles.energy.tier0;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import reborncore.api.tile.IContainerProvider;
import reborncore.api.tile.IInventoryProvider;
import reborncore.common.util.inventory.Inventory;
import reborncore.common.util.inventory.InventoryItemHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lordmau5 on 16.06.2016.
 */
public abstract class AbstractTileTier0 extends TileEntity implements ITickable, IInventoryProvider, IContainerProvider {

    private Inventory inventory;
    private int fuelPerOperation;

    private int burnTime;
    private int currentItemBurnTime;
    private int progress;

    protected int fuelSlot = 0;
    protected int outputSlot = 1;

    private Map<EnumFacing, IItemHandler> sidedHandlers = new HashMap<>();

    public AbstractTileTier0(int fuelPerOperation, Inventory inventory) {
        this.fuelPerOperation = fuelPerOperation;
        this.inventory = inventory;

        for (EnumFacing facing : EnumFacing.VALUES) {
            this.sidedHandlers.put(facing, new InventoryItemHandler(getContainer(), facing));
        }
    }

    @Override
    public void update() {
        if (getWorld().isRemote)
            return;

        if (this.burnTime > 0)
            this.burnTime--;

        if (this.burnTime == 0 && canSmelt()) {
            ItemStack fuelStack = getInventory().getStackInSlot(this.fuelSlot);
            if (fuelStack != null) {
                this.burnTime = this.currentItemBurnTime = TileEntityFurnace.getItemBurnTime(fuelStack);
                if (this.burnTime > 0) {
                    if (fuelStack.getItem().hasContainerItem(fuelStack)) {
                        getInventory().setInventorySlotContents(this.fuelSlot, new ItemStack(fuelStack.getItem().getContainerItem()));
                    } else if (fuelStack.stackSize > 1) {
                        getInventory().decrStackSize(this.fuelSlot, 1);
                    } else if (fuelStack.stackSize == 0) {
                        getInventory().setInventorySlotContents(this.fuelSlot, null);
                    }
                }
            }
        }

        if (isBurning() && canSmelt()) {
            if (this.progress++ == this.fuelPerOperation) {
                this.progress = 0;
                processItems();
            }
        } else {
            this.progress = 0;
        }

        markBlockForUpdate();
    }

    public boolean isBurning() {
        return burnTime > 0;
    }

    abstract boolean canSmelt();

    abstract void processItems();

    private void markBlockForUpdate() {
        this.getWorld().notifyBlockUpdate(this.getPos(), this.getWorld().getBlockState(this.getPos()), this.getWorld().getBlockState(this.getPos()), 3);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && this.sidedHandlers.containsKey(facing) ? (T) this.sidedHandlers.get(facing) : super.getCapability(capability, facing);
    }

    @Override
    public IInventory getInventory() {
        return this.inventory;
    }
}
