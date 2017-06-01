package mimickal.mc.dynamo;

import ic2.api.item.IC2Items;
import mimickal.mc.dynamo.common.CommonProxy;
import mimickal.mc.dynamo.common.ModBlocks;
import mimickal.mc.dynamo.common.ModTileEntities;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(
        modid = DynamoMod.MOD_ID,
        name = DynamoMod.NAME,
        version = DynamoMod.VERSION,
        acceptedMinecraftVersions = "[1.10.2]"
)
public class DynamoMod {

    public static final String MOD_ID = "dynamo";
    public static final String NAME = "Dynamo Generator";
    public static final String VERSION = "1.0.0";

    @SidedProxy(
            serverSide = "mimickal.mc.dynamo.common.CommonProxy",
            clientSide = "mimickal.mc.dynamo.client.ClientProxy"
    )
    public static CommonProxy proxy;

    @Instance(MOD_ID)
    public static DynamoMod instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println("Loading " + NAME);
        ModBlocks.init();
        ModTileEntities.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // IC2 recipes are defined via data, and thus need to be fetched on the fly like this
        ItemStack electricMotor = IC2Items.getItem("crafting", "electric_motor");

        GameRegistry.addRecipe(new ItemStack(ModBlocks.dynamo),
                "P P",
                "PSP",
                "PMP",
                'P', Blocks.PLANKS,
                'S', Items.STICK,
                'M', electricMotor
        );

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

}
