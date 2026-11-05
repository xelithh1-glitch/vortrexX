package dev.vortex.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Ported names/categories from original src/data/modules.ts. These toggles are UI-only. */
public final class ModuleStore {
    public static final class Module {
        public final String id, name, category;
        public boolean enabled;
        Module(String id, String name, String category) { this.id=id; this.name=name; this.category=category; }
    }
    public static final List<Module> ALL = new ArrayList<>(Arrays.asList(
        new Module("killaura", "Killaura", "combat"),
        new Module("triggerbot", "Triggerbot", "combat"),
        new Module("autototem", "AutoTotem", "combat"),
        new Module("velocity", "Velocity (Anti-KB)", "combat"),
        new Module("criticals", "Criticals", "combat"),
        new Module("reach", "Reach", "combat"),
        new Module("hitboxes", "Hitboxes", "combat"),
        new Module("maceExploit", "Mace Exploit (1.21)", "combat"),
        new Module("autoweapon", "AutoWeapon", "combat"),
        new Module("bowaim", "BowAim & Predict", "combat"),
        new Module("speed", "Speed", "movement"),
        new Module("fly", "Fly (Flight)", "movement"),
        new Module("jesus", "Jesus (WaterWalk)", "movement"),
        new Module("step", "Step", "movement"),
        new Module("noslowdown", "NoSlowdown", "movement"),
        new Module("spider", "Spider (WallClimb)", "movement"),
        new Module("safewalk", "SafeWalk", "movement"),
        new Module("elytrafly", "ElytraFly (1.21)", "movement"),
        new Module("autosprint", "AutoSprint", "movement"),
        new Module("esp", "ESP (Wallhack)", "render"),
        new Module("tracers", "Tracers", "render"),
        new Module("fullbright", "FullBright", "render"),
        new Module("nametags", "Nametags", "render"),
        new Module("storageesp", "StorageESP", "render"),
        new Module("xray", "X-Ray (1.21 Ores)", "render"),
        new Module("targethud", "Target HUD", "render"),
        new Module("scaffold", "Scaffold (Bridge)", "player"),
        new Module("fastplace", "FastPlace", "player"),
        new Module("autoeat", "AutoEat", "player"),
        new Module("nofall", "NoFall", "player"),
        new Module("cheststealer", "ChestStealer", "player"),
        new Module("autoarmor", "AutoArmor & InvManager", "player"),
        new Module("timer", "Timer", "world"),
        new Module("nuker", "Nuker (FastBreak)", "world"),
        new Module("trialsAutoLoot", "Trial AutoLoot (1.21.11)", "world")
    ));
    public static List<Module> category(String category) {
        if (category.equals("all")) return ALL;
        return ALL.stream().filter(m -> m.category.equals(category)).toList();
    }
    public static int enabled() { return (int) ALL.stream().filter(m -> m.enabled).count(); }
    public static void toggle(Module m) { m.enabled = !m.enabled; }
    private ModuleStore() {}
}
