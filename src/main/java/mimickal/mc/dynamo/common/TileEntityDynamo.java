package mimickal.mc.dynamo.common;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityDynamo extends TileEntity implements ITickable {

    private static final float DRAG = 0.1f;
    private static final float ADDED_PER_SPIN = 1f;

    private float spinSpeed = 0f;

    /**
     * On each tick, the dynamo generates a small amount of EU
     * proportional to how fast it's spinning.
     *
     * Drag is also applied so that the dynamo slows to a stop over time.
     */
    @Override
    public void update() {
        if (spinSpeed > 0f) {
            spinSpeed -= DRAG;
            System.out.println("Spin speed " + spinSpeed); //FIXME for debug
        }

        if (spinSpeed <= 0f) {
            spinSpeed = 0f;
        }
    }

    /**
     * "Crank" the dynamo once, increasing the speed it's spinning at.
     */
    public void spin() {
        spinSpeed += ADDED_PER_SPIN;
        System.out.println("Spin speed is " + spinSpeed); // FIXME for debug. Remove when done
    }

}
