package net.seinsturg.efac.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.block.EvansBlocks;
import net.seinsturg.efac.item.EvansItems;

import java.util.LinkedHashMap;

public class EvansItemModelProvider extends ItemModelProvider {private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public EvansItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, EFAC.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //ingredients
        basicItem(EvansItems.CLUMB_MATERIA.get());
        basicItem(EvansItems.ALBY_CLUMB_MATERIA.get());
        basicItem(EvansItems.CITRY_CLUMB_MATERIA.get());
        basicItem(EvansItems.RUBIED_CLUMB_MATERIA.get());
        basicItem(EvansItems.RANDOM_SAUCE.get());
        basicItem(EvansItems.YUMMY_SAUCE.get());
        basicItem(EvansItems.AWESOME_SAUCE.get());
        basicItem(EvansItems.EPIC_SAUCE.get());
        basicItem(EvansItems.GEUMB_SHARD.get());
        basicItem(EvansItems.CLUMBY_GEUMB_SHARD.get());
        basicItem(EvansItems.ALBY_GEUMB_SHARD.get());
        basicItem(EvansItems.CITRY_GEUMB_SHARD.get());
        basicItem(EvansItems.RUBIED_GEUMB_SHARD.get());
        basicItem(EvansItems.ALBY_GEM.get());
        basicItem(EvansItems.CITRY_GEM.get());
        basicItem(EvansItems.RUBY_GEM.get());
        basicItem(EvansItems.PHILOSOPHERS_STONE.get());
        basicItem(EvansItems.GELWOOD_ORB.get());
        basicItem(EvansItems.RAW_SLIPULON.get());
        basicItem(EvansItems.SLIPULON_INGOT.get());
        basicItem(EvansItems.BUTTER_STICK.get());
        basicItem(EvansItems.BLOOD_CANISTER.get());
        basicItem(EvansItems.PHILOSOPHERS_UPGRADE_SMITHING_TEMPLATE.get());

        //foods
        basicItem(EvansItems.CLUMBELON.get());
        basicItem(EvansItems.CLUMBKIE.get());
        basicItem(EvansItems.BLUMB.get());
        basicItem(EvansItems.CLUMBROT.get());
        basicItem(EvansItems.GOLD_CLUMBROT.get());
        basicItem(EvansItems.CLUMBLE.get());
        basicItem(EvansItems.GOLD_CLUMBLE.get());
        basicItem(EvansItems.CLUMBURGER.get());
        basicItem(EvansItems.BURNT_CHICKEN.get());

        //tools
        handheldItem(EvansItems.CLUMBY_WAND.get());
        handheldItem(EvansItems.CLUMBY_PULSAR.get());
        handheldItem(EvansItems.ALBY_WAND.get());
        handheldItem(EvansItems.ALBY_PULSAR.get());
        handheldItem(EvansItems.CITRY_WAND.get());
        handheldItem(EvansItems.CITRY_PULSAR.get());
        handheldItem(EvansItems.RUBIED_WAND.get());
        handheldItem(EvansItems.RUBIED_PULSAR.get());
        handheldItem(EvansItems.PHILOSOPHERS_WAND.get());
        handheldItem(EvansItems.PHILOSOPHERS_PULSAR.get());
        handheldItem(EvansItems.PHILOSOPHERS_SHOVEL.get());
        handheldItem(EvansItems.PHILOSOPHERS_PICKAXE.get());
        handheldItem(EvansItems.PHILOSOPHERS_AXE.get());
        handheldItem(EvansItems.PHILOSOPHERS_HOE.get());

        //charms
        handheldItem(EvansItems.DASH_CHARM.get());
        handheldItem(EvansItems.LIGHTNING_CHARM.get());
        handheldItem(EvansItems.PHILOSOPHERS_CHARM.get());

        //wood items
        buttonItem(EvansBlocks.GELWOOD_BUTTON, EvansBlocks.GELWOOD_PLANKS);
        fenceItem(EvansBlocks.GELWOOD_FENCE, EvansBlocks.GELWOOD_PLANKS);
        basicItem(EvansBlocks.GELWOOD_DOOR.asItem());

        /// i dont know why this doesn't work
        //walls
        //wallItem(EvansBlocks.GEUMB_TILE_WALL, EvansBlocks.GEUMB_TILES);
        //wallItem(EvansBlocks.CLUMBY_GEUMB_TILE_WALL, EvansBlocks.CLUMBY_GEUMB_TILES);
        //wallItem(EvansBlocks.ALBY_GEUMB_TILE_WALL, EvansBlocks.ALBY_GEUMB_TILES);
        //wallItem(EvansBlocks.CITRY_GEUMB_TILE_WALL, EvansBlocks.CITRY_GEUMB_TILES);
        //wallItem(EvansBlocks.RUBY_GEUMB_TILE_WALL, EvansBlocks.RUBY_GEUMB_TILES);
        //wallItem(EvansBlocks.GRONE_BRICK_WALL, EvansBlocks.GRONE_BRICKS);
        //wallItem(EvansBlocks.RONE_BRICK_WALL, EvansBlocks.RONE_BRICKS);
        //wallItem(EvansBlocks.BLONE_BRICK_WALL, EvansBlocks.BLONE_BRICKS);
        //wallItem(EvansBlocks.PLONE_BRICK_WALL, EvansBlocks.PLONE_BRICKS);

        //armor
        trimmedArmorItem(EvansItems.PHILOSOPHERS_HELMET);
        trimmedArmorItem(EvansItems.PHILOSOPHERS_LEGGINGS);
        trimmedArmorItem(EvansItems.PHILOSOPHERS_CHESTPLATE);
        trimmedArmorItem(EvansItems.PHILOSOPHERS_BOOTS);
    }

    // Shoutout to El_Redstoniano for making this
    // Shoutout Kaupenjoe
    private void trimmedArmorItem(DeferredItem<ArmorItem> itemDeferredItem) {
        final String MOD_ID = EFAC.MOD_ID; // Change this to your mod id

        if (itemDeferredItem.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {
                float trimValue = value;

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = armorItem.toString();
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.parse(armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.parse(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = ResourceLocation.parse(currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc.getNamespace() + ":item/" + armorItemResLoc.getPath())
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemDeferredItem.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace()  + ":item/" + trimNameResLoc.getPath()))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                        "item/" + itemDeferredItem.getId().getPath()));
            });
        }
    }

    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(EFAC.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(EFAC.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(EFAC.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }
}
