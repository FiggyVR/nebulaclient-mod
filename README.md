# NebulaClient Mod (Phase 1: foundation)

Fabric mod for Minecraft 1.21.11. This is what the launcher auto-downloads
into Fabric/Quilt instances.

## What's actually working right now
- Mod loads and registers under its own **NebulaClient** heading in
  Options > Controls > Key Binds, with every keybind from your screenshots
  except: Emote Wheel, Spray Snap, Spray Wheel, Skip Node (out of scope
  per the brief), and Sneak/Sprint/Third Person Key (already exist as
  vanilla keybinds).
- Keys are fully rebindable and persist to `options.txt` like any vanilla
  keybind — that part is real, not a stub.
- Pressing a key currently sends a `[NebulaClient] ...` chat message
  confirming it fired (except Mod Menu, which opens a real placeholder
  screen). This is intentional — it proves the wiring works end-to-end
  before the real features (waypoints, HUDs, freelook camera, etc.) get
  built on top in later phases.

## Building it
You'll need a JDK 21 and Gradle (or just open the folder in IntelliJ with
the Minecraft Development plugin, which handles both automatically).

```
./gradlew build
```

The jar comes out at `build/libs/nebulaclient-0.1.0.jar`.

**First-time setup:** this repo doesn't include the Gradle wrapper jar
(it's a binary and I can't fetch it from this environment). Before your
first build, run once:

```
gradle wrapper --gradle-version 8.10
```

(or just open in IntelliJ — it generates the wrapper for you automatically).

**Double check version strings before building:** `gradle.properties` pins
`yarn_mappings` and `fabric_version` for 1.21.11. I can't query
maven.fabricmc.net from where I built this, so verify those two values
against https://fabricmc.net/develop/ before your first build — if
they're stale, Loom will just fail to resolve dependencies (loud, obvious
error) rather than build something broken silently.

## Publishing a release the launcher can download
Push a version tag and CI builds + publishes it automatically:

```
git tag v0.1.0
git push --tags
```

That runs `.github/workflows/release.yml`, which attaches the built jar to
a GitHub Release. The launcher's `ensureNebulaMod()` always pulls the
newest release's `.jar` asset — see the launcher's `NEBULA_MOD_REPO`
constant in `src/main/main.js`, which needs to point at wherever you push
this repo (`yourname/nebulaclient-mod` or similar).

## Roadmap (matches the phases discussed with the launcher)
- **Phase 2** — HUDs: status (flying/sneaking/sprinting, toggled-vs-held),
  armor + offhand/mainhand, coords/FPS/facing, potion effects. All
  draggable, all hidden (showing the NebulaClient logo instead) whenever a
  screen/inventory is open.
- **Phase 3** — Waypoints (manual + auto death waypoints), Waypoint Menu,
  Nebula Menu on the pause screen.
- **Phase 4** — Custom main menu, F3 username line, repositionable vanilla
  boss bar, black-instead-of-gray widget reskin.
- **Phase 5** — Scoped in-game account switching.
- **Not started / needs a design decision** — showing a NebulaClient icon
  next to other players who are also running the mod. This needs a small
  backend (auth + a "who's online with the mod" endpoint) since there's no
  way to detect that client-side only. Flagging this again so it doesn't
  get lost — let me know if/when you want to scope that out separately.
