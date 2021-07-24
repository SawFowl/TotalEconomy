package com.ericgrandt.player;

import com.ericgrandt.services.TEEconomyService;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ServerSideConnectionEvent;

public class PlayerListener {
    private final TEEconomyService economyService;

    public PlayerListener(TEEconomyService economyService) {
        this.economyService = economyService;
    }

    @Listener
    public void onPlayerJoin(ServerSideConnectionEvent.Join event) {
        Player player = event.player();

        economyService.findOrCreateAccount(player.uniqueId());
    }
}