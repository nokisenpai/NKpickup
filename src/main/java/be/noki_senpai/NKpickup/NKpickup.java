package be.noki_senpai.NKpickup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import be.noki_senpai.NKpickup.cmd.NoPickupCmd;
import be.noki_senpai.NKpickup.cmd.PickupCmd;
import be.noki_senpai.NKpickup.cmd.PickupResetCmd;
import be.noki_senpai.NKpickup.data.NKPlayer;
import be.noki_senpai.NKpickup.listeners.ItemCompleter;
import be.noki_senpai.NKpickup.listeners.PlayerConnectionListener;
import be.noki_senpai.NKpickup.listeners.PlayerPickup;

public class NKpickup extends JavaPlugin
{
	public final static String PName = "[NKpickup]";

	// Options
	public static int maxList = 10;

	// Players datas
	public static Map<String, NKPlayer> players = new TreeMap<String, NKPlayer>(String.CASE_INSENSITIVE_ORDER);

	//Material list
	public static List<String> material = new ArrayList<String>();

	private static NKpickup instance;
	private ConsoleCommandSender console = getServer().getConsoleSender();

	// Fired when plugin is first enabled
	@Override public void onEnable()
	{
		instance = this;

		this.saveDefaultConfig();

		maxList = this.getConfig().getInt("max-list");

		// On command
		getServer().getPluginManager().registerEvents(new PlayerConnectionListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerPickup(), this);
		this.getCommand("nopickup").setTabCompleter(new ItemCompleter());
		this.getCommand("pickup").setTabCompleter(new ItemCompleter());
		getCommand("nopickup").setExecutor(new NoPickupCmd());
		getCommand("pickup").setExecutor(new PickupCmd());
		getCommand("pickupreset").setExecutor(new PickupResetCmd());
		getCommand("nopickupreset").setExecutor(new PickupResetCmd());

		for(Material materialType : Material.values())
		{
			material.add(materialType.toString().toUpperCase());
		}

		// Get all connected players
		Bukkit.getOnlinePlayers().forEach(player -> players.putIfAbsent(player.getDisplayName(), new NKPlayer(player.getUniqueId())));

		console.sendMessage(ChatColor.WHITE + "     .--. ");
		console.sendMessage(ChatColor.WHITE + "     |    '.   " + ChatColor.GREEN + PName + " by NoKi_senpai - successfully enabled !");
		console.sendMessage(ChatColor.WHITE + "'-..____.-'");
	}

	// Fired when plugin is disabled
	@Override public void onDisable()
	{
		players.clear();
		console.sendMessage(ChatColor.GREEN + PName + " has been disable.");
	}

	//######################################
	// Getters & Setters
	//######################################

	// Getter 'instance'
	public static NKpickup getInstance()
	{
		return instance;
	}

	// Getter & setter 'console'
	public ConsoleCommandSender getConsole()
	{
		return console;
	}

	public void setConsole(ConsoleCommandSender console)
	{
		this.console = console;
	}

	//######################################
	// Disable this plugin
	//######################################

	public void disablePlugin()
	{
		getServer().getPluginManager().disablePlugin(this);
	}

	//######################################
	// Utils functions
	//######################################

}
