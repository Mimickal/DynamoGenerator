package mimickal.mc.dynamo.common;

import ic2.core.IC2;
import mimickal.mc.dynamo.DynamoMod;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockDynamo extends BlockContainer implements ItemModelProvider {

    public static final String NAME = "dynamo";

    public BlockDynamo() {
        super(Material.IRON);
        setUnlocalizedName(NAME);
        setRegistryName(NAME);
        setHardness(2.0f);
        setResistance(6.0f);
        setHarvestLevel("pickaxe", 1);
        setCreativeTab(IC2.tabIC2);
    }

    @Override
    public void registerItemModel(Item itemBlock) {
        DynamoMod.proxy.registerItemRenderer(itemBlock, 0, NAME);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityDynamo();
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
        return face == EnumFacing.DOWN; // Only cull bottom face
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
        TileEntityDynamo tileEntityDynamo = (TileEntityDynamo)worldIn.getTileEntity(pos);
        tileEntityDynamo.spin();
        return true;
    }

}
