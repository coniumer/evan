package net.seinsturg.efac.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.seinsturg.efac.EFAC;

public class EvansTags {
    public static class Blocks {
        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(EFAC.res(name));
        }
    }

    public static class Items {
        public static final TagKey<Item> ADD_CHARGE_ON_EAT = createTag("add_charge_on_eat");
        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(EFAC.res(name));
        }
    }
}
