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
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockDynamo extends BlockContainer implements ItemModelProvider {

    protected String name;

    public BlockDynamo(Material materialIn, String name) {
        super(materialIn);
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
        setHardness(2.0f);
        setResistance(6.0f);
        setHarvestLevel("pickaxe", 1);
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
        // Spin the dynamo
        TileEntityDynamo tileEntityDynamo = (TileEntityDynamo)worldIn.getTileEntity(pos);
        tileEntityDynamo.spin();

        // Play spin sound
        float pitch = tileEntityDynamo.getSpeed();
        worldIn.playSound(playerIn, pos, DynamoMod.SPIN_SOUND, SoundCategory.BLOCKS, 1.0f, pitch);

        return true;
    }

}
