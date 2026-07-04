# lobbytools

A collection of lightweight [PaperMC](https://papermc.io/) plugins, built with
Gradle. Each plugin lives in its own subproject and produces a ready-to-drop-in
`.jar` under `builds/`.

> Requires Java 21 and the Paper API for Minecraft 1.21.11+. The plugins are
> tested against Paper; they may work on other modern Bukkit forks but are not
> officially supported there.

## Plugins

### joinleave

A simple plugin that replaces the default join and quit messages with a
randomly chosen, player-formatted message.

- Random **join** messages (e.g. `"Welcome <player>!"`, `"<player> joined the game."`)
- Random **quit** messages (e.g. `"<player> left the game."`, `"See you later, <player>!"`)
- Uses the Paper [Adventure](https://docs.adventure.kyori.net/) API for message components

The message variants are defined in `joinleave/src/main/java/dev/project516/joinleave/Message.java`.

## Requirements

- **Java 21** (toolchain configured in `gradle.properties` and `joinleave/build.gradle`)
- **Gradle** &mdash; the Gradle wrapper (`./gradlew`) is included, so no local
  Gradle install is required
- A **Paper server** running a 1.21.11-compatible build (for deployment only)

## Building

Build all plugins from the repository root:

```sh
./gradlew build
```

This compiles the source, runs [Spotless](https://github.com/diffplug/spotless)
formatting (`googleJavaFormat` AOSP preset) and produces the plugin jars.

There are also convenience scripts:

| Script | Purpose |
| --- | --- |
| `./buildall.sh` | Runs `./gradlew build` and copies the resulting jars into `builds/` |
| `./initserver.sh` | Builds the plugins and installs them into `server/plugins/` |

> Tip: `initserver.sh` populates `server/plugins/`. The `server/` directory is
> git-ignored except for shell scripts, so your local server data stays out of
> version control.

The compiled jar for each plugin ends up in its respective subproject
`build/libs/` directory (for example `joinleave/build/libs/joinleave.jar`).

## Project structure

```
.
├── buildall.sh          # Build + stage jars into builds/
├── initserver.sh        # Build + install jars into a local test server
├── settings.gradle      # Gradle settings (includes the joinleave subproject)
├── gradle.properties    # Gradle configuration (caching enabled)
├── builds/              # Output directory for built jars
└── joinleave/           # joinleave plugin subproject
    ├── build.gradle     # Paper API dependency, Spotless config
    └── src/main/
        ├── java/dev/project516/joinleave/
        │   ├── JoinLeave.java       # Plugin entry point (registers the listener)
        │   ├── PlayerListener.java  # Handles join/quit events
        │   └── Message.java         # Random message pools
        └── resources/plugin.yml     # Plugin metadata
```

## Development

### Formatting

Code is formatted with Spotless using the Google Java Format 1.17.0 (AOSP)
preset. Formatting is applied automatically as part of `compileJava`, so a plain
`./gradlew build` keeps everything tidy. To run formatting on its own:

```sh
./gradlew spotlessApply
```

### VS Code

Recommended extensions are listed in `.vscode/extensions.json`. Opening the
project in VS Code will prompt you to install the
[Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack),
which provides language support and Gradle integration.

## CI & automation

- **GitHub Actions** &mdash; `.github/workflows/gradle.yml` builds the project
  with the Gradle wrapper on every push and pull request against `main`, and
  submits a dependency graph for Dependabot alerts.
- **Dependabot** &mdash; `.github/dependabot.yml` opens weekly version-update
  PRs for Gradle dependencies and GitHub Actions.
- **Renovate** &mdash; `renovate.json` configures Renovate to keep
  dependencies up to date (auto-merge enabled).

## Contributing

Contributions are welcome! Please:

1. Fork the repository and create a branch for your change.
2. Make sure `./gradlew build` passes (this includes Spotless formatting).
3. Keep changes focused &mdash; one improvement per pull request is ideal.
4. Open a pull request against `main` with a clear description of what and why.

## License

This project is licensed under the [GNU General Public License v3.0](LICENSE).
