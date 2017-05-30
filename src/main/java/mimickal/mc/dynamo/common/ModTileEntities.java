package mimickal.mc.dynamo.common;

import mimickal.mc.dynamo.DynamoMod;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModTileEntities {

    public static void init() {
        GameRegistry.registerTileEntity(TileEntityDynamo.class, DynamoMod.MOD_ID + "_tile_entity");
    }

}
