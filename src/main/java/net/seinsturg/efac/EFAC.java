package net.seinsturg.efac;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.seinsturg.efac.block.EvansBlocks;
import net.seinsturg.efac.util.EvansRegistries;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(EFAC.MOD_ID)
public class EFAC {
    public static final String MOD_ID = "efac";
    public static final Logger LOGGER = LogUtils.getLogger();

    public EFAC(IEventBus eventBus, ModContainer modContainer) {
        eventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);

        EvansRegistries.registerRegistries(eventBus);
        eventBus.addListener(this::addCreative);

        modContainer.registerConfig(net.neoforged.fml.config.ModConfig.Type.COMMON, EFACConfig.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("HELLO EVAN WORLD");
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(EvansBlocks.GRONE);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO EVAN SERVER");
    }
}
