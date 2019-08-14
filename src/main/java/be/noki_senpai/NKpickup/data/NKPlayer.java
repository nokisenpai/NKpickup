package be.noki_senpai.NKpickup.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;

public class NKPlayer 
{
	private UUID playerUUID;
	private String playerName;
	private List<String> whiteList = new ArrayList<String>();
	private List<String> blackList = new ArrayList<String>();
	
	public NKPlayer(UUID UUID)
	{
		setPlayerUUID(UUID);
		setPlayerName(Bukkit.getOfflinePlayer(playerUUID).getName());
	}
	
	
	
	//######################################
	// Getters & Setters
	//######################################
	
	// Getter & Setter 'playerUUID'
	public UUID getPlayerUUID()
	{
		return playerUUID;
	}

	public void setPlayerUUID(UUID playerUUID)
	{
		this.playerUUID = playerUUID;
	}

	// Getter & Setter 'playerName'
	public String getPlayerName()
	{
		return playerName;
	}

	public void setPlayerName(String playerName)
	{
		this.playerName = playerName;
	}
	
	// Getter & Setter 'whiteList'
	public List<String> getWhiteList()
	{
		return whiteList;
	}
	public void setWhiteList(List<String> whiteList)
	{
		this.whiteList = whiteList;
	}
	
	// Getter & Setter 'blackList'
	public List<String> getBlackList()
	{
		return blackList;
	}
	public void setBlackList(List<String> blackList)
	{
		this.blackList = blackList;
	}
	
	
	
	//######################################
	// Add & Remove item in whiteList
	//######################################
	
	public void addItemWhiteList(String item)
	{
		if(!whiteList.contains(item))
		{
			this.whiteList.add(item);
			blackList.clear();
		}
	}
	
	public void removeItemWhiteList(String item)
	{
		this.whiteList.remove(item);
	}
	
	
	
	//######################################
	// Add & Remove item in blackList
	//######################################
	
	public void addItemBlackList(String item)
	{
		if(!blackList.contains(item))
		{
			this.blackList.add(item);
			whiteList.clear();
		}
	}
	
	public void removeItemBlackList(String item)
	{
		this.blackList.remove(item);
	}
	
	
	
	//######################################
	// Clear WhiteList and BlackList
	//######################################
	
	public void clearList()
	{
		whiteList.clear();
		blackList.clear();
	}
	
	
	
	//######################################
	// Check item in WhiteList and BlackList
	//######################################
	
	public boolean checkItem(String itemName)
	{
		if(blackList.size() > 0)
		{
			if(blackList.contains(itemName))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			if(whiteList.size() > 0)
			{
				if(whiteList.contains(itemName))
				{
					return false;
				}
				else
				{
					return true;
				}
			}
		}
		return false;
	}
}
