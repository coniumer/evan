package net.seinsturg.efac.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class EvansKeyMappings {
    public static final String KEY_CATEGORY_EFAC_ID = "key.category.efac.evankeys";
    public static final String KEY_CLUMB_ID = "key.efac.clumb";
    public static final String CHARM_CYCLE_ID = "key.efac.charm_cycle";
    public static final String MOUTH_ID = "key.efac.mouth";

    public static KeyMapping CLUMB_KEY = new KeyMapping(
            KEY_CLUMB_ID,
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_LEFT_CONTROL,
            KEY_CATEGORY_EFAC_ID
    );

    public static KeyMapping CHARM_CYCLE = new KeyMapping(
            CHARM_CYCLE_ID,
            KeyConflictContext.UNIVERSAL,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_GRAVE_ACCENT,
            KEY_CATEGORY_EFAC_ID
    );

    public static KeyMapping MOUTH = new KeyMapping(
            MOUTH_ID,
            KeyConflictContext.UNIVERSAL,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_M,
            KEY_CATEGORY_EFAC_ID
    );
}
