package net.nolando.nolandoz.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.List;
import java.util.Random;

public class ItemFrameLootProcessor extends StructureProcessor {
    public static final Codec<ItemFrameLootProcessor> CODEC = RecordCodecBuilder.create(inst ->
            inst.group(
                    Codec.STRING.listOf().fieldOf("gun_ids").forGetter(p -> p.gunIds)
            ).apply(inst, ItemFrameLootProcessor::new)
    );

    private final List<String> gunIds;

    public ItemFrameLootProcessor(List<String> gunIds) {
        this.gunIds = gunIds;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return net.nolando.nolandoz.modinit.NZProcessors.ITEM_FRAME_LOOT.get();
    }

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(
            LevelReader world,
            BlockPos structurePos,
            BlockPos blockPos,
            StructureTemplate.StructureBlockInfo rawBlockInfo,
            StructureTemplate.StructureBlockInfo transformedBlockInfo,
            StructurePlaceSettings settings
    ) {
        // Leave all blocks unchanged
        return transformedBlockInfo;
    }

    @Override
    public StructureTemplate.StructureEntityInfo processEntity(
            LevelReader world,
            BlockPos structurePos,
            StructureTemplate.StructureEntityInfo rawEntityInfo,
            StructureTemplate.StructureEntityInfo entityInfo,
            StructurePlaceSettings settings,
            StructureTemplate template
    ) {
        CompoundTag nbt = entityInfo.nbt;
        if ("minecraft:item_frame".equals(nbt.getString("id"))) {
            // Randomly pick a gun ID
            Random rand = new Random(structurePos.asLong() ^ nbt.hashCode());
            String selected = gunIds.get(rand.nextInt(gunIds.size()));

            // Build the ItemStack NBT
            CompoundTag itemTag = new CompoundTag();
            itemTag.putString("id", "tacz:modern_kinetic_gun");
            CompoundTag tag = new CompoundTag();
            tag.putString("GunId", selected);
            itemTag.put("tag", tag);
            itemTag.putByte("Count", (byte) 1);

            // Set invisibility and assign the item
            nbt.putBoolean("Invisible", true);
            nbt.put("Item", itemTag);

            return new StructureTemplate.StructureEntityInfo(
                    entityInfo.pos,
                    entityInfo.blockPos,
                    nbt
            );
        }
        return entityInfo;
    }
}