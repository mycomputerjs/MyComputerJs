package net.youritch.pythonputer.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.youritch.pythonputer.PythonputerMod;
import net.minecraftforge.registries.DeferredRegister;
import net.youritch.pythonputer.block.ModBlocks;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PythonputerMod.MODID);

    public static final RegistryObject<CreativeModeTab> PYTHONPUTER_TAB = CREATIVE_MODE_TABS.register("example", () -> CreativeModeTab.builder()
            .title(Component.translatable("item_group.pythonputer.pythonputer_tab"))
            .icon(() -> new ItemStack(ModItems.PYTHON.get()))
            .displayItems((params, output) -> {
                //items
                output.accept(ModItems.SAPPHIRE.get());
                output.accept(ModItems.RAW_SAPPHIRE.get());
                output.accept(ModItems.PYTHON.get());
                //blocks
                output.accept(ModBlocks.SAPPHIRE_BLOCK.get());
            })
            .build()
    );

    public static void register (IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
