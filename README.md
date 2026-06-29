# lobbytools

A collection of Minecraft server plugins built with the [Paper](https://papermc.io/) API, designed for lobby servers.

## Plugins

### JoinLeave

A simple plugin that randomizes the join and leave messages shown in chat. Instead of the default "Player joined the game" every time, players see a randomly selected message from a curated list.

**Example join messages:**

- "Welcome Steve!"
- "Hey Steve, welcome back!"
- "Glad to see you, Steve!"

**Example leave messages:**

- "Steve left the game."
- "See you later, Steve!"
- "Goodbye Steve!"

## Requirements

- Java 21 or later
- Paper server (API version 1.21.11+)

## Building

```sh
./gradlew build
```

The plugin JAR is output to `joinleave/build/libs/joinleave.jar`.

You can also use the convenience script:

```sh
./buildall.sh
```

This builds the plugin and copies the JAR to `builds/joinleave.jar`.

## Installation

1. Build the plugin (see above) or download a release JAR
2. Copy the JAR into your Paper server's `plugins/` directory
3. Restart or reload the server

Alternatively, use `initserver.sh` to build the plugin and set up a local test server:

```sh
./initserver.sh
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b my-feature`)
3. Commit your changes
4. Open a pull request against the `main` branch

Please note that the project uses [Spotless](https://github.com/diffplug/spotless) with Google Java Format (AOSP style) — run `./gradlew spotlessApply` before submitting to ensure formatting is consistent.

## License

This project is licensed under the [GNU General Public License v3.0](LICENSE).
