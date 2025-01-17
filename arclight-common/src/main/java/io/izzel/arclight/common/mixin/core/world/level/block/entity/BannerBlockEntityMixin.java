package io.izzel.arclight.common.mixin.core.world.level.block.entity;

import io.izzel.arclight.common.mod.server.ArclightServer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(BannerBlockEntity.class)
public abstract class BannerBlockEntityMixin extends BlockEntity {

    @Shadow private BannerPatternLayers patterns;
    @Shadow private Component name;

    public BannerBlockEntityMixin(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    /**
     * @author sj-hub9796
     * @reason
     */
    @Overwrite
    protected void loadAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.loadAdditional(compoundTag, provider);
        if (compoundTag.contains("CustomName", 8)) {
            this.name = parseCustomNameSafe(compoundTag.getString("CustomName"), provider);
        }

        if (compoundTag.contains("patterns")) {
            BannerPatternLayers.CODEC.parse(provider.createSerializationContext(NbtOps.INSTANCE), compoundTag.get("patterns")).resultOrPartial((string) -> {
                ArclightServer.LOGGER.error("Failed to parse banner patterns: '{}'", string);
            }).ifPresent((bannerPatternLayers) -> {
                this.setPatterns(bannerPatternLayers); // CraftBukkit - apply limits
            });
        }
    }

    /**
     * @author sj-hub9796
     * @reason
     */
    @Overwrite
    protected void applyImplicitComponents(BlockEntity.DataComponentInput dataComponentInput) {
        super.applyImplicitComponents(dataComponentInput);
        this.setPatterns((BannerPatternLayers) dataComponentInput.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)); // CraftBukkit - apply limits
        this.name = (Component)dataComponentInput.get(DataComponents.CUSTOM_NAME);
    }

    // CraftBukkit start
    public void setPatterns(BannerPatternLayers bannerpatternlayers) {
        if (bannerpatternlayers.layers().size() > 20) {
            bannerpatternlayers = new BannerPatternLayers(List.copyOf(bannerpatternlayers.layers().subList(0, 20)));
        }
        this.patterns = bannerpatternlayers;
    }
    // CraftBukkit end
}
