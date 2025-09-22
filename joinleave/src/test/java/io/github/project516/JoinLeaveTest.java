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

/**
 * This class tests the JoinLeave plugin functionality. It verifies that the correct messages are
 * sent when players join and leave the server.
 */
class JoinLeaveTest {

    // Mock server and plugin instances for testing
    private ServerMock server;
    private JoinLeave plugin;
    private PlayerMock player;

    // Helper to convert Component messages to plain text for easier comparison
    private final PlainTextComponentSerializer plainSerializer =
            PlainTextComponentSerializer.plainText();

    /**
     * Set up the test environment before each test. This creates a mock server, loads our plugin,
     * and creates a test player.
     */
    @BeforeEach
    void setUp() {
        // Create a mock server
        server = MockBukkit.mock();

        // Load our plugin for testing
        plugin = MockBukkit.load(JoinLeave.class);

        // Add a simple world and a test player
        server.addSimpleWorld("test");
        player = server.addPlayer("TestPlayer");
    }

    /**
     * Clean up after each test. This unloads the mock server to ensure tests don't interfere with
     * each other.
     */
    @AfterEach
    void tearDown() {
        MockBukkit.unmock();
    }

    /**
     * Test that the correct join message is broadcast when a player joins. Expected format: "[+]
     * TestPlayer joined the game!" in green and yellow colors.
     */
    @Test
    void testPlayerJoinMessage() {
        // Create the expected join message
        Component expectedJoinMessage =
                Component.text()
                        .append(Component.text("[+]").color(NamedTextColor.GREEN))
                        .append(Component.text(" "))
                        .append(Component.text("TestPlayer"))
                        .append(Component.text(" joined the game!"))
                        .color(NamedTextColor.YELLOW)
                        .build();

        // Simulate a player joining the server
        PlayerJoinEvent joinEvent = new PlayerJoinEvent(player, Component.empty());
        server.getPluginManager().callEvent(joinEvent);

        // Check that all online players received the join message
        for (PlayerMock onlinePlayer : server.getOnlinePlayers()) {
            Component actualMessage = onlinePlayer.nextComponentMessage();

            // Verify the message is not null and matches our expected format
            assertNotNull(actualMessage, "Join message should not be null");
            assertEquals(
                    expectedJoinMessage,
                    actualMessage,
                    "Join message should match expected format");
        }
    }

    /**
     * Test that a welcome message is sent to the player who joined. Expected format: "Welcome to
     * the server, TestPlayer!" in aqua color.
     */
    @Test
    void testPlayerWelcomeMessage() {
        // Create the expected welcome message
        Component expectedWelcomeMessage =
                Component.text()
                        .append(Component.text("Welcome to the server, "))
                        .append(Component.text("TestPlayer"))
                        .append(Component.text("!"))
                        .color(NamedTextColor.AQUA)
                        .build();

        // Simulate a player joining the server
        PlayerJoinEvent joinEvent = new PlayerJoinEvent(player, Component.empty());
        server.getPluginManager().callEvent(joinEvent);

        // Get all messages sent to the player and find the welcome message
        Component message;
        Component welcomeMessage = null;

        while ((message = player.nextComponentMessage()) != null) {
            // Check if this is the welcome message
            if (plainSerializer.serialize(message).contains("Welcome to the server")) {
                welcomeMessage = message;
                break;
            }
        }

        // Verify the welcome message was found and matches our expected format
        assertNotNull(welcomeMessage, "Welcome message should be sent to the joining player");
        assertEquals(
                expectedWelcomeMessage,
                welcomeMessage,
                "Welcome message should match expected format");
    }

    /**
     * Test that the correct leave message is broadcast when a player leaves. Expected format: "[-]
     * TestPlayer left the game!" in red and yellow colors.
     */
    @Test
    void testPlayerLeaveMessage() {
        // Create the expected leave message
        Component expectedLeaveMessage =
                Component.text()
                        .append(Component.text("[-]").color(NamedTextColor.RED))
                        .append(Component.text(" "))
                        .append(Component.text("TestPlayer"))
                        .append(Component.text(" left the game!"))
                        .color(NamedTextColor.YELLOW)
                        .build();

        // Simulate a player leaving the server
        PlayerQuitEvent quitEvent = new PlayerQuitEvent(player, Component.empty());
        server.getPluginManager().callEvent(quitEvent);

        // Check that all other online players received the leave message
        for (PlayerMock onlinePlayer : server.getOnlinePlayers()) {
            if (!onlinePlayer.equals(player)) {
                Component actualMessage = onlinePlayer.nextComponentMessage();

                // Verify the message is not null and matches our expected format
                assertNotNull(actualMessage, "Leave message should not be null");
                assertEquals(
                        expectedLeaveMessage,
                        actualMessage,
                        "Leave message should match expected format");
            }
        }
    }
}
