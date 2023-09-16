package com.example.pythonputer;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllItems {
    public static void registerAllBlocks() {
        // The variable of the defer register class extend blocks
        final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PythonputerMod.MODID);

        // Creates a new Block with the id "examplemod:example_block", combining the namespace and path
        //final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item());
    }
}
