package net.youritch.pythonputer.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.youritch.pythonputer.PythonputerMod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraft.world.item.Item;
import net.youritch.pythonputer.block.ModBlocks;

import java.util.ArrayList;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PythonputerMod.MODID);

    private static ArrayList<ItemStack> allItems() {
        ArrayList<ItemStack> all_items = new ArrayList<>();
        for (RegistryObject<Item> item : ModItems.ITEMS.getEntries()){
            all_items.add(new ItemStack(item.get()));
        }
        return all_items;
    }

    private static ArrayList<ItemStack> allBlocks() {
        ArrayList<ItemStack> all_blocks = new ArrayList<>();
        for (RegistryObject<Block> item : ModBlocks.BLOCKS.getEntries()){
            all_blocks.add(new ItemStack(item.get()));
        }
        return all_blocks;
    }

    public static final RegistryObject<CreativeModeTab> PYTHONPUTER_TAB = CREATIVE_MODE_TABS.register("example", () -> CreativeModeTab.builder()
            .title(Component.translatable("item_group.pythonputer.pythonputer_tab"))
            .icon(() -> new ItemStack(ModItems.PYTHON.get()))
            .displayItems((params, output) -> {
                //items
                output.acceptAll(allItems());
                //blocks
                output.acceptAll(allBlocks());
            })
            .build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
