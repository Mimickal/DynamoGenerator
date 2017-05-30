package mimickal.mc.dynamo.common;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockDynamo extends BlockBase {

    private static final float DRAG = 0.1f;
    private static final float ADDED = 1f;

    private float spinSpeed = 0f;

    public BlockDynamo(Material materialIn, String name) {
        super(materialIn, name);
    }

    @Override
    public boolean onBlockActivated(
            World worldIn,
            BlockPos pos,
            IBlockState state,
            EntityPlayer playerIn,
            EnumHand hand,
            @Nullable ItemStack heldItem,
            EnumFacing side,
            float hitX,
            float hitY,
            float hitZ
    ) {
        spinSpeed += ADDED;
        return true;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (spinSpeed > 0f) {
            spinSpeed -= DRAG;
            System.out.println("Spin speed " + spinSpeed);
        }

        if (spinSpeed <= 0f) {
            spinSpeed = 0f;
        }
    }

}
