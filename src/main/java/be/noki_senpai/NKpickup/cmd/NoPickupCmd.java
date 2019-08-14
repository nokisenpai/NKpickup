package be.noki_senpai.NKpickup.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import be.noki_senpai.NKpickup.NKpickup;

public class NoPickupCmd implements CommandExecutor
{
	
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) 
	{	
		// Command called by a player
		if (sender instanceof Player) 
		{
			if(!(sender.hasPermission("*") || sender.hasPermission("nkpickup.*") || sender.hasPermission("nkpickup.nopickup") || sender.hasPermission("nkpickup.user")))
			{
				// Send that the player does not have the permission
				sender.sendMessage(ChatColor.RED + " Vous n'avez pas la permission !");
				return true;
			}
			else
			{
				//if no argument
				if(args.length == 0)
				{
					sender.sendMessage(ChatColor.RED + " Vous devez spécifier au moins un nom d'item");
					return true;
				}
				else
				{
					if(NKpickup.players.get(sender.getName()).getBlackList().size() < NKpickup.maxList)
					{
						if(NKpickup.material.contains(args[0].toString().toLowerCase()))
						{
							NKpickup.players.get(sender.getName()).addItemBlackList(args[0].toString().toLowerCase());
							sender.sendMessage(ChatColor.GREEN + " L'item " + ChatColor.BOLD + args[0].toString().toLowerCase() + ChatColor.RESET + ChatColor.RED + " a été ajouté à votre blacklist.");
							return true;
						}
						else
						{
							sender.sendMessage(ChatColor.RED + " L'item spécifié n'existe pas.");
							return true;
						}
					}
					else
					{
						sender.sendMessage(ChatColor.RED + " Vous ne pouvez pas avoir plus de " + ChatColor.BOLD + NKpickup.maxList + ChatColor.RESET + ChatColor.RED + " item(s) dans votre liste.");
						return true;
					}
				}
			}
		}
		
		
		// Command called by Console
		if (sender instanceof ConsoleCommandSender)
		{
			sender.sendMessage(ChatColor.RED + " Commande inutile en console.");
			return true;
		}
		
		
		return true;
	}
}
