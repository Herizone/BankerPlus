package com.horizon.bankerplus.utilities.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class Config {
    private FileConfiguration config;
    private File configFile;
    private String fileName;

    public Config(Plugin plugin, String fileName) {
        this.fileName = fileName;
        plugin.saveResource(fileName, false);
        this.configFile = new File(plugin.getDataFolder(), fileName);
        this.load();
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    public File getConfigFile() {
        return this.configFile;
    }

    public void set(String key, Object value) {
        this.config.set(key, value);

        try {
            this.config.save(this.configFile);
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

    public void load() {
        this.config = YamlConfiguration.loadConfiguration(this.configFile);
    }

    public Object get(String key) {
        return this.config.get(key);
    }

    public String getFileName() {
        return this.fileName;
    }
}
