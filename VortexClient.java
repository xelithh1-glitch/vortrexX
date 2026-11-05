package dev.vortex.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;

public final class VortexClient implements ClientModInitializer {
    public static final String MOD_ID = "vortexclient";
    public static final int PURPLE = 0xFFAA55FF;
    public static boolean hudVisible = true;
    @Override public void onInitializeClient() {
        var category = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(MOD_ID, "main"));
        var open = KeyBindingHelper.registerKeyBinding(new KeyMapping(
            "key.vortexclient.open", InputConstants.Type.KEYSYM, InputConstants.KEY_RIGHT_SHIFT, category));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (open.consumeClick()) {
                client.setScreen(new VortexScreen());
            }
        });
        HudElementRegistry.attachElementBefore(VanillaHudElements.CHAT,
            Identifier.fromNamespaceAndPath(MOD_ID, "hud"), (graphics, tracker) -> VortexHud.render(graphics));
    }
}
