package mimickal.mc.dynamo.common;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergySource;
import mimickal.mc.dynamo.DynamoMod;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.common.MinecraftForge;

public class TileEntityDynamo extends TileEntity implements ITickable, IEnergySource {

    private static final int LV_TIER = 1; // Because IC2 doesn't have an enum for its power tiers
    private static final int COOLDOWN_MAX = 7;
    private static final float MAX_SPEED = 5.0f;
    private static final float DRAG = 0.05f;
    private static final float ADDED_PER_SPIN = 0.5f;

    private boolean firstUpdate = true;
    private int cooldown = 0;
    private float spinSpeed = 0f;

    /**
     * On each tick, the dynamo generates a small amount of EU
     * proportional to how fast it's spinning.
     *
     * Drag is also applied so that the dynamo slows to a stop over time.
     */
    @Override
    public void update() {
        // IC2 requires this on the first tick to hook into the energy grid.
        if (firstUpdate) {
            MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
            firstUpdate = false;
        }

        // Tick cooldown counter
        if (cooldown > 0) {
            cooldown--;
        }

        // Calculate spin speed reduction
        if (spinSpeed > 0f) {
            spinSpeed -= DRAG;
        }

        if (spinSpeed <= 0f) {
            spinSpeed = 0f;
        }
    }

    /**
     * "Crank" the dynamo once, increasing the speed it's spinning at.
     * The dynamo has a cooldown before it can be cranked a second time.
     */
    public void spin() {
        // Don't spin if we're still on a cooldown
        if (cooldown > 0) {
            return;
        }
        cooldown = COOLDOWN_MAX;

        // Increase the dynamo spin speed
        spinSpeed += ADDED_PER_SPIN;
        if (spinSpeed >= MAX_SPEED) {
            spinSpeed = MAX_SPEED;
        }

        // Play spin sound. Scale pitch to spin speed.
        float pitch = spinSpeed;
        worldObj.playSound(null, pos, DynamoMod.SPIN_SOUND, SoundCategory.BLOCKS, 1.0f, pitch);
    }

    @Override
    public double getOfferedEnergy() {
        // TODO calculate power based on spin speed and config
        return spinSpeed;
    }

    @Override
    public int getSourceTier() {
        return LV_TIER;
    }

    @Override
    public void drawEnergy(double amount) {
        // Noop because dynamos have no energy buffer
    }

    @Override
    public boolean emitsEnergyTo(IEnergyAcceptor var1, EnumFacing var2) {
        return true;
    }

    /*
     * IC2 needs an unload event to be fired when this tile entity stops existing,
     * either because the chunk unloaded or the dynamo was broken.
     * This allows IC2 to unhook the dynamo from the energy grid.
     */
    @Override
    public void onChunkUnload() {
        unloadEnergyTile();
        super.onChunkUnload();
    }

    @Override
    public void invalidate() {
        unloadEnergyTile();
        super.invalidate();
    }

    private void unloadEnergyTile() {
        MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
    }

}
