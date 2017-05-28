package mimickal.mc.dynamo.common;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

    public static ItemBase dynamo;

    public static void init() {
        dynamo = register(new ItemBase("dynamo"));
    }

    private static <T extends Item>T register(T item) {
        GameRegistry.register(item);

        if (item instanceof ItemModelProvider) {
            ((ItemModelProvider)item).registerItemModel(item);
        }

        return item;
    }

}
