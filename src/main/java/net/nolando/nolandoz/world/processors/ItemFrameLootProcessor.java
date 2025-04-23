package net.nolando.nolandoz.world.processors;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate.StructureEntityInfo;
import net.nolando.nolandoz.modinit.NZProcessors;

import java.util.List;

public class ItemFrameLootProcessor extends StructureProcessor {
    public static final Codec<ItemFrameLootProcessor> CODEC = Codec.unit(ItemFrameLootProcessor::new);

    private static final List<String> GUN_IDS = List.of(
            "tacz:sks_tactical", "tacz:cz75", "tacz:db_long", "tacz:ak47", "tacz:aug", "tacz:deagle",
            "tacz:db_short", "tacz:glock_17", "tacz:hk416d", "tacz:hk_g3", "tacz:hk_mp5a5",
            "tacz:m107", "tacz:m16a1", "tacz:m16a4", "tacz:m1911", "tacz:m249", "tacz:m320",
            "tacz:m4a1", "tacz:m700", "tacz:m870", "tacz:m95", "tacz:mk14", "tacz:p320", "tacz:p90",
            "tacz:qbz_95", "tacz:rpg7", "tacz:rpk", "tacz:scar_h", "tacz:scar_l", "tacz:type_81",
            "tacz:ump45", "tacz:uzi", "tacz:vector45"
    );

    @Override
    public StructureEntityInfo processEntity(ServerLevelAccessor level, BlockPos pos, StructureEntityInfo entityInfo, StructureTemplate.StructureBlockInfo blockInfo, StructureTemplate.StructureBlockInfo blockInfo2, StructurePlacementData placement) {
        if (entityInfo.entityTag().getString("id").equals(EntityType.ITEM_FRAME.getRegistryName().toString())) {
            RandomSource random = level.getRandom();
            String randomGunId = GUN_IDS.get(random.nextInt(GUN_IDS.size()));

            CompoundTag gunTag = new CompoundTag();
            gunTag.putString("GunId", randomGunId);

            CompoundTag itemTag = new CompoundTag();
            itemTag.putString("id", "tacz:modern_kinetic_gun");
            itemTag.putByte("Count", (byte) 1);
            itemTag.put("tag", gunTag);

            entityInfo.entityTag().putBoolean("Invisible", true);
            entityInfo.entityTag().put("Item", itemTag);
        }
        return entityInfo;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return NZProcessors.ITEM_FRAME_LOOT_PROCESSOR.get();
    }
}