package ru.brikster.chatty.pm;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;

@Singleton
public class PmSoundStorage {

    private final File file;
    private final YamlConfiguration config;

    @Inject
    public PmSoundStorage(Plugin plugin) {
        this.file = new File(plugin.getDataFolder(), "pm-sound.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().severe("Could not create pm-sound.yml: " + e.getMessage());
            }
        }
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public boolean isSoundEnabled(String playerName) {
        return config.getBoolean("players." + playerName + ".pm-sound", true);
    }

    public boolean toggle(String playerName) {
        boolean newState = !isSoundEnabled(playerName);
        config.set("players." + playerName + ".pm-sound", newState);
        try { config.save(file); } catch (IOException ignored) {}
        return newState;
    }

}