# Happy Ghast Speed Plugin

A Minecraft Paper plugin for version 26.1.2 that allows server administrators to dynamically control the movement speed of the "Happy Ghast" mob across the entire server.

## Features

- **Global Speed Control:** Instantly updates the movement/flying speed of all existing Happy Ghasts in all worlds.
- **Automatic Enforcement:** Automatically applies the configured speed to any newly spawned Happy Ghasts.
- **In-Game Commands:** Easy-to-use commands to set, increase, or decrease the speed multiplier dynamically.
- **Persistent Configuration:** Saves the chosen speed to `config.yml` so it persists across server restarts.

## Commands

> [!WARNING]
> Minecraft attribute speeds are very sensitive! The default base speed for most flying mobs is around **0.3**. 
> - **Do NOT** increase the speed by large whole numbers (e.g., adding `4.0` will cause them to fly uncontrollably fast like idiots!).
> - Use small decimal increments to adjust the speed safely (e.g., increase by `0.05` or `0.1`).

- `/happyghastspeed <number>` - Sets a specific speed for all Happy Ghasts (e.g., `/happyghastspeed 0.4`).
- `/happyghastspeed increase <number>` - Increases the current speed (e.g., `/happyghastspeed increase 0.1`).
- `/happyghastspeed decrease <number>` - Decreases the current speed (e.g., `/happyghastspeed decrease 0.05`).

**Aliases**: `/hgspeed`

## Permissions

- `happyghast.speed` - Required to use the `/happyghastspeed` command.

## Setup & Compilation

This plugin uses Gradle. To build the plugin locally:

1. Clone or download the repository.
2. Run `./gradlew build` in the project root. (Ensure you are using Java 21+ and have the Paper 26.1.2 API available in your Maven local/remote repositories).
3. The compiled `.jar` file will be generated in `build/libs/`.
4. Place the `.jar` file into your server's `plugins/` directory and restart the server.
