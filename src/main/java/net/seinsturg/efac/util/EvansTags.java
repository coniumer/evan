package net.seinsturg.efac.util;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.seinsturg.efac.EFAC;

public class EvansTags {
    public static class Blocks {
        public static final TagKey<Block> PROVIDES_CHARGE_RARE = createTag("provides_charge_rare");
        public static final TagKey<Block> PROVIDES_CHARGE_COMMON = createTag("provides_charge_common");
        public static final TagKey<Block> PROVIDES_CHARGE_ALWAYS = createTag("provides_charge_always");
        public static final TagKey<Block> CONSUMES_CHARGE = createTag("consumes_charge");
        public static final TagKey<Block> INCORRECT_FOR_PHILOSOPHERS_TOOL = createTag("incorrect_for_philosophers_tool");
        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(EFAC.res(name));
        }
    }

    public static class Items {
        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(EFAC.res(name));
        }
    }
}
