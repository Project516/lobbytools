package io.github.project516;

import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.PlayerMock;
import org.bukkit.Location;
import org.bukkit.World;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JoinLeaveTest {

    World world;
    ServerMock server;
    private JoinLeave plugin;

    @BeforeEach
    void setUp() {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(JoinLeave.class);
        this.world = server.addSimpleWorld("test");
    }

    @AfterEach
    void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    void test() {
        world.createExplosion(new Location(world, 0, 0, 0), 0.2f);
        PlayerMock player = server.addPlayer();
        player.disconnect();
    }
}