package dev.vortex.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

/** Actual in-game HUD. Gameplay module names are cosmetic labels, not cheat implementations. */
public final class VortexHud {
    private static final int PANEL = 0xD910101A;
    private static final int WHITE = 0xFFF0EAFB;
    public static void render(GuiGraphics g) {
        var mc = Minecraft.getInstance();
        if (!VortexClient.hudVisible || mc.player == null || mc.options.hideGui) return;
        var font = mc.font;
        g.fill(8, 8, 135, 36, PANEL);
        g.fill(8, 8, 11, 36, VortexClient.PURPLE);
        g.drawString(font, "VORTEX CLIENT", 17, 12, WHITE, true);
        g.drawString(font, "1.21.11  |  RSHIFT", 17, 24, 0xFFB6AAC5, false);
        int y=45;
        for (var module: ModuleStore.ALL) {
            if (!module.enabled) continue;
            int width = font.width(module.name) + 13;
            g.fill(8,y,8+width,y+13,PANEL);
            g.fill(8,y,10,y+13,VortexClient.PURPLE);
            g.drawString(font,module.name,14,y+3,WHITE,true);
            y+=15;
            if (y > g.guiHeight()-25) break;
        }
        if (mc.player != null) {
            String coords = String.format("XYZ %.0f / %.0f / %.0f", mc.player.getX(), mc.player.getY(), mc.player.getZ());
            int bottom = g.guiHeight()-20;
            g.fill(8,bottom-4,Math.max(118,font.width(coords)+18),bottom+12,PANEL);
            g.drawString(font,coords,14,bottom,WHITE,false);
        }
    }
    private VortexHud() {}
}
