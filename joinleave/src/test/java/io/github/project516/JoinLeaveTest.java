package io.github.project516;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.PlayerMock;

class JoinLeaveTest {

    private ServerMock server;
    private JoinLeave plugin;
    private PlayerMock player;
    private final PlainTextComponentSerializer plainSerializer =
            PlainTextComponentSerializer.plainText();

    @BeforeEach
    void setUp() {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(JoinLeave.class);
        server.addSimpleWorld("test");
        player = server.addPlayer("TestPlayer");
    }

    @AfterEach
    void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    void testPlayerJoinMessage() {
        // Simulate player joining
        PlayerJoinEvent joinEvent = new PlayerJoinEvent(player, Component.empty());
        server.getPluginManager().callEvent(joinEvent);

        Component expectedJoinMessage =
                Component.text()
                        .append(Component.text("[+]").color(NamedTextColor.GREEN))
                        .append(Component.text(" "))
                        .append(Component.text("TestPlayer"))
                        .append(Component.text(" joined the game!"))
                        .color(NamedTextColor.YELLOW)
                        .build();

        // Verify the join message was broadcast to all players
        for (PlayerMock onlinePlayer : server.getOnlinePlayers()) {
            Component actualMessage = onlinePlayer.nextComponentMessage();
            assertNotNull(actualMessage, "Join message should not be null");
            assertEquals(
                    expectedJoinMessage,
                    actualMessage,
                    "Join message should match expected format");
        }
    }

    @Test
    void testPlayerWelcomeMessage() {
        // Simulate player joining
        PlayerJoinEvent joinEvent = new PlayerJoinEvent(player, Component.empty());
        server.getPluginManager().callEvent(joinEvent);

        Component expectedWelcomeMessage =
                Component.text()
                        .append(Component.text("Welcome to the server, "))
                        .append(Component.text("TestPlayer"))
                        .append(Component.text("!"))
                        .color(NamedTextColor.AQUA)
                        .build();

        // Debug: Print all messages to understand the sequence
        System.out.println("=== DEBUG: All messages sent to player ===");
        int messageCount = 0;
        Component message;
        while ((message = player.nextComponentMessage()) != null) {
            messageCount++;
            System.out.println(
                    "Message " + messageCount + ": " + plainSerializer.serialize(message));
            System.out.println("  Raw: " + message);

            // Check if this is the welcome message
            if (plainSerializer.serialize(message).contains("Welcome to the server")) {
                System.out.println("  -> Found welcome message!");
                assertEquals(
                        expectedWelcomeMessage,
                        message,
                        "Welcome message should match expected format");
                return; // Test passed
            }
        }

        // If we get here, we didn't find the welcome message
        System.out.println(
                "Expected welcome message: " + plainSerializer.serialize(expectedWelcomeMessage));
        System.out.println("Total messages found: " + messageCount);
        assertEquals(
                expectedWelcomeMessage,
                Component.empty(),
                "Welcome message not found in sent messages");
    }

    @Test
    void testPlayerLeaveMessage() {
        // Simulate player leaving
        PlayerQuitEvent quitEvent = new PlayerQuitEvent(player, Component.empty());
        server.getPluginManager().callEvent(quitEvent);

        Component expectedLeaveMessage =
                Component.text()
                        .append(Component.text("[-]").color(NamedTextColor.RED))
                        .append(Component.text(" "))
                        .append(Component.text("TestPlayer"))
                        .append(Component.text(" left the game!"))
                        .color(NamedTextColor.YELLOW)
                        .build();

        // Verify the leave message was broadcast to all players
        for (PlayerMock onlinePlayer : server.getOnlinePlayers()) {
            if (!onlinePlayer.equals(player)) {
                Component actualMessage = onlinePlayer.nextComponentMessage();
                assertNotNull(actualMessage, "Leave message should not be null");
                assertEquals(
                        expectedLeaveMessage,
                        actualMessage,
                        "Leave message should match expected format");
            }
        }
    }
}
