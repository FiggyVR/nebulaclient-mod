package com.nebulaclient.mod.client;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

/**
 * Every keybind here shows up under its own "NebulaClient" heading in
 * Options > Controls > Key Binds, the same way Lunar Client's keys get
 * their own section — Fabric does this automatically based on the
 * "category" string, no custom screen needed.
 *
 * Deliberately left out (per the brief):
 *   - Emote Wheel / Spray Snap / Spray Wheel  (emote + spray features, out of scope)
 *   - Skip Node / Reset Counts                 (pathing/node features, out of scope)
 *   - Sneak / Sprint / Third Person Key        (already exist as vanilla keybinds)
 *
 * Each binding's actual behavior is a stub for now — Phase 1 is the
 * foundation (mod loads, category shows up, keys are rebindable and
 * persist to options.txt like any vanilla keybind). Waypoints, the Mod
 * Menu screen, freelook camera behavior, etc. land in their own phases.
 */
public final class NebulaKeybinds {
    private static final String CATEGORY = "key.categories.nebulaclient";

    public static final KeyBinding CREATE_WAYPOINT = register("key.nebulaclient.create_waypoint", InputUtil.GLFW_KEY_RIGHT_CONTROL);
    public static final KeyBinding FORWARD_VIEW = register("key.nebulaclient.forward_view", InputUtil.GLFW_KEY_UNKNOWN);
    public static final KeyBinding FREELOOK = register("key.nebulaclient.freelook", InputUtil.GLFW_KEY_UNKNOWN);
    public static final KeyBinding MOD_MENU = register("key.nebulaclient.mod_menu", InputUtil.GLFW_KEY_UNKNOWN);
    public static final KeyBinding RESET_COUNTS = register("key.nebulaclient.reset_counts", InputUtil.GLFW_KEY_UNKNOWN);
    public static final KeyBinding SEND_COORDINATES = register("key.nebulaclient.send_coordinates", InputUtil.GLFW_KEY_UNKNOWN);
    public static final KeyBinding TOGGLE_CHAT_VISIBILITY = register("key.nebulaclient.toggle_chat_visibility", InputUtil.GLFW_KEY_UNKNOWN);
    public static final KeyBinding TOGGLE_WAYPOINT_DISPLAY = register("key.nebulaclient.toggle_waypoint_display", InputUtil.GLFW_KEY_UNKNOWN);
    public static final KeyBinding WAYPOINT_MENU = register("key.nebulaclient.waypoint_menu", InputUtil.GLFW_KEY_1);
    public static final KeyBinding ZOOM = register("key.nebulaclient.zoom", InputUtil.GLFW_KEY_UNKNOWN);

    private NebulaKeybinds() {}

    private static KeyBinding register(String translationKey, int defaultGlfwKey) {
        return KeyBindingHelper.registerKeyBinding(new KeyBinding(
                translationKey,
                InputUtil.Type.KEYSYM,
                defaultGlfwKey,
                CATEGORY
        ));
    }

    /** Call once from the client entrypoint so the static initializers above actually run. */
    public static void init() {
        NebulaClientModClient.LOGGER.info("Registered {} NebulaClient keybinds", 10);
    }
}
