package mimickal.mc.dynamo.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

    public static BlockDynamo dynamo;

    public static void init() {
        dynamo = register(new BlockDynamo(Material.ROCK, "dynamo").setCreativeTab(CreativeTabs.MISC));
    }

    private static <T extends Block>T register(T block, ItemBlock itemBlock) {
        GameRegistry.register(block);

        if (itemBlock != null) {
            GameRegistry.register(itemBlock);
        }

        if (block instanceof ItemModelProvider) {
            ((ItemModelProvider)block).registerItemModel(itemBlock);
        }

        return block;
    }

    private static <T extends Block>T register(T block) {
        ItemBlock itemBlock = new ItemBlock(block);
        itemBlock.setRegistryName(block.getRegistryName());
        return register(block, itemBlock);
    }

}
