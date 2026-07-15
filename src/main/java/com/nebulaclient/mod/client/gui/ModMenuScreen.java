package com.nebulaclient.mod.client.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

/**
 * Stub screen for the Mod Menu keybind. This is where the HUD editor,
 * waypoint list, and Nebula Menu options land in later phases — for now
 * it just confirms the keybind and mod are wired up correctly.
 */
public class ModMenuScreen extends Screen {
    public ModMenuScreen() {
        super(Text.literal("NebulaClient"));
    }

    @Override
    protected void init() {
        // Buttons for HUD editor / waypoints / settings get added here in Phase 2+.
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, "NebulaClient", this.width / 2, 20, 0xFFFFFF);
        context.drawCenteredTextWithShadow(
                this.textRenderer,
                "Mod Menu — coming in a later phase.",
                this.width / 2, this.height / 2, 0xAAAAAA
        );
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
