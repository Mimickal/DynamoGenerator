package mimickal.mc.dynamo;

import ic2.api.item.IC2Items;
import mimickal.mc.dynamo.common.*;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

@Mod(
        modid = DynamoMod.MOD_ID,
        name = DynamoMod.NAME,
        version = DynamoMod.VERSION,
        acceptedMinecraftVersions = "[1.10.2]",
        dependencies = "required-after:IC2@[2.6.210,)"
)
public class DynamoMod {

    public static final String MOD_ID = "dynamo";
    public static final String NAME = "Dynamo";
    public static final String VERSION = "1.10.2-1.0.0";

    public static SoundEvent SPIN_SOUND;
    public static SoundEvent IDLE_SOUND;

    @SidedProxy(
            serverSide = "mimickal.mc.dynamo.common.CommonProxy",
            clientSide = "mimickal.mc.dynamo.client.ClientProxy"
    )
    public static CommonProxy proxy;

    @Instance(MOD_ID)
    public static DynamoMod instance;

    public static BlockDynamo dynamo;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // Noop
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println("Loading " + NAME);
        proxy.init();
        initDynamoBlock();
        initDynamoTileEntity();
        initSounds();
        initRecipes();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        // Noop
    }

    private void initDynamoBlock() {
        BlockDynamo block = new BlockDynamo();

        ItemBlock itemBlock = new ItemBlock(block);
        itemBlock.setRegistryName(block.getRegistryName());

        GameRegistry.register(block);
        GameRegistry.register(itemBlock);
        block.registerItemModel(itemBlock);

        dynamo = block;
    }

    private void initDynamoTileEntity() {
        GameRegistry.registerTileEntity(TileEntityDynamo.class, MOD_ID + "_tile_entity");
    }

    private void initSounds() {
        ResourceLocation spinLocation = new ResourceLocation(MOD_ID, "dynamo_spin");
        ResourceLocation idleLocation = new ResourceLocation(MOD_ID, "dynamo_idle");

        SPIN_SOUND = new SoundEvent(spinLocation);
        IDLE_SOUND = new SoundEvent(idleLocation);

        int id = SoundEvent.REGISTRY.getKeys().size();
        SoundEvent.REGISTRY.register(id, spinLocation, SPIN_SOUND);
        id++;
        SoundEvent.REGISTRY.register(id, idleLocation, IDLE_SOUND);
    }

    private void initRecipes() {
        // IC2 recipes are defined via data, and thus need to be fetched on the fly like this
        ItemStack electricMotor = IC2Items.getItem("crafting", "electric_motor");

        GameRegistry.addRecipe(new ShapedOreRecipe(dynamo,
                " L ",
                "PSP",
                "PMP",
                'L', "logWood",
                'P', "plankWood",
                'S', Items.STICK,
                'M', electricMotor
        ));
    }
}
