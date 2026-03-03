package net.seinsturg.efac.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class EvansKeyMappings {
    public static final String KEY_CATEGORY_EFAC_ID = "key.category.efac.evankeys";
    public static final String KEY_CLUMB_ID = "key.efac.clumb";

    public static KeyMapping CLUMB_KEY = new KeyMapping(
            KEY_CLUMB_ID,
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_LEFT_CONTROL,
            KEY_CATEGORY_EFAC_ID
    );;
}
