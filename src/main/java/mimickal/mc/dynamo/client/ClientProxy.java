package mimickal.mc.dynamo.client;

import mimickal.mc.dynamo.DynamoMod;
import mimickal.mc.dynamo.common.CommonProxy;
import mimickal.mc.dynamo.common.TileEntityDynamo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void init() {
        OBJLoader.INSTANCE.addDomain(DynamoMod.MOD_ID);
    }

    @Override
    public void registerItemRenderer(Item item, int meta, String name) {
        String locationStr = DynamoMod.MOD_ID + ":" + name;
        ModelResourceLocation location = new ModelResourceLocation(locationStr, "inventory");

        ModelLoader.setCustomModelResourceLocation(item, meta, location);

        ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        mesher.register(item, meta, location);

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDynamo.class, new TileEntityDynamoRenderer());
    }

}
