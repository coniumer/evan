package net.seinsturg.efac.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.item.EvansItems;

import java.util.function.Supplier;

public class EvansBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
        DeferredRegister.createBlocks(EFAC.MOD_ID);

    public static final DeferredBlock<Block> CLUMB_BLOCK = registerBlock(
            "clumb_block", () -> new Block(BlockBehaviour.Properties.of()
                    .destroyTime(0.4f)
                    .explosionResistance(0.4f)
                    .sound(SoundType.SLIME_BLOCK)
                    .lightLevel(state -> 7))
    );

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        EvansItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
