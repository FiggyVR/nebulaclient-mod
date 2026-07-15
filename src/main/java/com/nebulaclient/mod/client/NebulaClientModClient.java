package com.nebulaclient.mod.client;

import com.nebulaclient.mod.client.gui.ModMenuScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NebulaClientModClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("NebulaClient");

    // Some keys (Freelook, Zoom) are meant to be held rather than pressed —
    // this tracks which are currently held down so later phases (camera
    // handling, HUD "held vs toggled" indicator) have something to read.
    public static boolean freelookHeld = false;
    public static boolean zoomHeld = false;
    public static boolean chatHidden = false;
    public static boolean waypointsVisible = true;

    @Override
    public void onInitializeClient() {
        NebulaKeybinds.init();

        ClientTickEvents.END_CLIENT_TICK.register(NebulaClientModClient::onClientTick);
        LOGGER.info("NebulaClient client init complete");
    }

    private static void onClientTick(MinecraftClient client) {
        // Held-style keys
        freelookHeld = NebulaKeybinds.FREELOOK.isPressed();
        zoomHeld = NebulaKeybinds.ZOOM.isPressed();

        // Press-style keys (wasPressed() drains one "click" per call, so this
        // is safe to poll every tick without double-firing)
        while (NebulaKeybinds.CREATE_WAYPOINT.wasPressed()) {
            // TODO(Phase 3): actually record a waypoint at the player's position.
            sendFeedback(client, "Waypoint created (placeholder — full waypoints land in Phase 3).");
        }
        while (NebulaKeybinds.MOD_MENU.wasPressed()) {
            client.setScreen(new ModMenuScreen());
        }
        while (NebulaKeybinds.RESET_COUNTS.wasPressed()) {
            sendFeedback(client, "Counts reset (placeholder).");
        }
        while (NebulaKeybinds.SEND_COORDINATES.wasPressed()) {
            sendFeedback(client, "Send Coordinates (placeholder — will post your position to chat).");
        }
        while (NebulaKeybinds.TOGGLE_CHAT_VISIBILITY.wasPressed()) {
            chatHidden = !chatHidden;
            sendFeedback(client, "Chat visibility: " + (chatHidden ? "hidden" : "shown"));
        }
        while (NebulaKeybinds.TOGGLE_WAYPOINT_DISPLAY.wasPressed()) {
            waypointsVisible = !waypointsVisible;
            sendFeedback(client, "Waypoint display: " + (waypointsVisible ? "on" : "off"));
        }
        while (NebulaKeybinds.WAYPOINT_MENU.wasPressed()) {
            sendFeedback(client, "Waypoint Menu (placeholder — lands in Phase 3).");
        }
        while (NebulaKeybinds.FORWARD_VIEW.wasPressed()) {
            sendFeedback(client, "Forward View (placeholder).");
        }
    }

    private static void sendFeedback(MinecraftClient client, String message) {
        if (client.player != null) {
            client.player.sendMessage(Text.literal("[NebulaClient] " + message), false);
        }
    }
}
