package net.nolando.nolandoz.modinit;

import net.nolando.nolandoz.NolandoZ;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.nolando.nolandoz.world.processors.ItemFrameLootProcessor;

public class NZProcessors {
    public static final DeferredRegister<StructureProcessorType<?>> PROCESSORS =
            DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, NolandoZ.MODID);

    public static final RegistryObject<StructureProcessorType<ItemFrameLootProcessor>> ITEM_FRAME_LOOT =
            PROCESSORS.register("item_frame_loot", () -> () -> ItemFrameLootProcessor.CODEC);

    /**
     * Registers all structure processors to the mod event bus. Must be called in your mod constructor.
     */
    public static void register(IEventBus bus) {
        PROCESSORS.register(bus);
    }
}