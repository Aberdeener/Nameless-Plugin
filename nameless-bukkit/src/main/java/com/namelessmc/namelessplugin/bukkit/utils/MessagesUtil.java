package com.namelessmc.namelessplugin.bukkit.utils;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.bukkit.configuration.file.YamlConfiguration;

import com.namelessmc.namelessplugin.bukkit.NamelessPlugin;

import net.md_5.bungee.api.ChatColor;

public class MessagesUtil {

	NamelessPlugin plugin;
	
	private File config;
	private YamlConfiguration loader;

	public MessagesUtil(NamelessPlugin plugin) {
		this.plugin = plugin;
	}

	public String getMessage(String path){
		return ChatColor.translateAlternateColorCodes('&', loader.getString(path));
	}

	/*
	 * Initialize the Permissions Config.
	 */
	public void initMessages() throws Exception {
		config = new File(plugin.getDataFolder(), "messages.yml");
		loader = YamlConfiguration.loadConfiguration(config);
		InputStream defaultConfig = plugin.getClass().getClassLoader().getResourceAsStream("messages.yml");

		plugin.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&1Loading Messages configuration..."));

		if(!config.exists()){
			plugin.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&1Creating Messages file..."));
			config.createNewFile();
			Files.copy(defaultConfig, config.getAbsoluteFile().toPath(), StandardCopyOption.REPLACE_EXISTING);
		}
	}

}