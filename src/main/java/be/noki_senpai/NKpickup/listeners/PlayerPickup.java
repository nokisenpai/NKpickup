package be.noki_senpai.NKpickup.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

import be.noki_senpai.NKpickup.NKpickup;

public class PlayerPickup implements Listener 
{
	//Event déclenché à chaque fois qu'un joueur ramasse un item
	@EventHandler
	public void OnPlayerPickup(EntityPickupItemEvent event)
	{
		if(event.getEntity() instanceof Player)
		{
			Player player = (Player) event.getEntity();
			
			if(player.hasPermission("*") || player.hasPermission("nkpickup.*") || player.hasPermission("nkpickup.check") || player.hasPermission("nkpickup.user"))
			{
				//On récupère le nom de l'item que le joueur ramasse (dirt? stone? diorite?)
				String itemName = event.getItem().getItemStack().getType().toString().toLowerCase();
				
				if(NKpickup.players.get(player.getName()).checkItem(itemName))
				{
					// On ne veut pas de l'item
					
					// On annule le ramassage de l'item
					event.setCancelled(true);
					
					// On met un timer sur l'item en question pour qu'il ne puisse plus être ramassé pendant quelques secondes (ici 30 x 20 ticks = 30 secondes)
					event.getItem().setPickupDelay(30 * 20);
				}
			}
		}
	}
}
