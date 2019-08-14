package be.noki_senpai.NKpickup.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import be.noki_senpai.NKpickup.NKpickup;
import be.noki_senpai.NKpickup.data.NKPlayer;;



public class PlayerConnectionListener implements Listener 
{
    @EventHandler
    public void PlayerJoinEvent(final PlayerJoinEvent event) 
    {
    	NKpickup.players.putIfAbsent(event.getPlayer().getName(),new NKPlayer(event.getPlayer().getUniqueId()));
    }

    @EventHandler
    public void onPlayerQuitEvent(final PlayerQuitEvent event) 
    {	
    	NKpickup.players.remove(Bukkit.getOfflinePlayer(event.getPlayer().getUniqueId()).getName());
    }
}
