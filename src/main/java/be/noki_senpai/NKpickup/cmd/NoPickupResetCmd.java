package be.noki_senpai.NKpickup.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import be.noki_senpai.NKpickup.NKpickup;

public class NoPickupResetCmd implements CommandExecutor
{

	@Override public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args)
	{
		// Command called by a player
		if(sender instanceof Player)
		{
			if(!(sender.hasPermission("*") || sender.hasPermission("nkpickup.*") || sender.hasPermission("nkpickup.reset")
					|| sender.hasPermission("nkpickup.user")))
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
					NKpickup.players.get(sender.getName()).clearBlackList();
					sender.sendMessage(ChatColor.GREEN + " Votre blacklist a été réinitialisée.");
					return true;
				}
			}
		}

		// Command called by Console
		if(sender instanceof ConsoleCommandSender)
		{
			sender.sendMessage(ChatColor.RED + " Commande inutile en console.");
			return true;
		}

		return true;
	}
}
