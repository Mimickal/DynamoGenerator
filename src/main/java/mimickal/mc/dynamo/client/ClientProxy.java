package mimickal.mc.dynamo.client;

import mimickal.mc.dynamo.DynamoMod;
import mimickal.mc.dynamo.common.CommonProxy;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        String locationStr = DynamoMod.MOD_ID + ":" + id;
        ModelResourceLocation location = new ModelResourceLocation(locationStr, "inventory");
        ModelLoader.setCustomModelResourceLocation(item, meta, location);
    }

}
