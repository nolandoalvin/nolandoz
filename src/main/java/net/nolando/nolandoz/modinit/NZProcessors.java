package net.nolando.nolandoz.modinit;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nolando.nolandoz.NolandoZ;
import net.nolando.nolandoz.world.processors.ItemFrameLootProcessor;

public final class NZProcessors {

    public static final DeferredRegister<StructureProcessorType<?>> PROCESSORS =
            DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, NolandoZ.MOD_ID);

    public static final RegistryObject<StructureProcessorType<ItemFrameLootProcessor>> ITEM_FRAME_LOOT_PROCESSOR =
            PROCESSORS.register("itemframe_loot", () -> () -> ItemFrameLootProcessor.CODEC);

    //public static void register() {
        //PROCESSORS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}