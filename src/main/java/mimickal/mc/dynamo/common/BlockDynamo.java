package mimickal.mc.dynamo.common;

import mimickal.mc.dynamo.DynamoMod;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockDynamo extends BlockContainer implements ItemModelProvider {

    private static final float DRAG = 0.1f;
    private static final float ADDED = 1f;

    protected String name;

    private float spinSpeed = 0f;

    public BlockDynamo(Material materialIn, String name) {
        super(materialIn);
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
    }

    @Override
    public void registerItemModel(Item itemBlock) {
        DynamoMod.proxy.registerItemRenderer(itemBlock, 0, name);
    }

    @Override
    public BlockDynamo setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityDynamo();
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
