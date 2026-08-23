# lobbytools

PaperMC plugins for Minecraft server lobbies.

## Plugins

### JoinLeave

Replaces the default join and leave messages with a randomly selected one from a built-in pool. Uses the Adventure API for text components.

**Default join messages:**

- Welcome %s!
- Hey %s, welcome back!
- Glad to see you, %s!
- %s joined the game.
- Hello %s, enjoy your stay!

**Default leave messages:**

- %s left the game.
- See you later, %s!
- %s has logged off.
- Goodbye %s!
- %s disconnected. Come back soon!

## Requirements

- Java 21 or newer
- Paper 1.21.11+

## Install

Download `joinleave.jar` from the [builds](builds/) folder and drop it into your server's `plugins/` directory. Restart the server.

## Build from source

```bash
./buildall.sh
```

The output jar is copied to `builds/joinleave.jar`.

## Project structure

```
joinleave/
  src/main/java/dev/project516/joinleave/
    JoinLeave.java        Plugin entry point
    Message.java          Random message selection
    PlayerListener.java   Join/leave event handler
  build.gradle            Gradle build with Spotless formatting
builds/
  joinleave.jar           Pre-built jar
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Run `./gradlew build` to verify
5. Open a pull request

The build applies Google Java Format (AOSP style) automatically via Spotless.

## License

GPL-3.0. See [LICENSE](LICENSE).
