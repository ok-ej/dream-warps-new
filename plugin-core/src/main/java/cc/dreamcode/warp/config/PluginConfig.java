package cc.dreamcode.warp.config;

import cc.dreamcode.platform.bukkit.component.configuration.Configuration;
import cc.dreamcode.platform.persistence.StorageConfig;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.CustomKey;
import eu.okaeri.configs.annotation.Header;

@Configuration(child = "config.yml")
@Header("## Dream-Warp (Main-Config) ##")
public class PluginConfig extends OkaeriConfig {

    @Comment
    @Comment("Debug pokazuje dodatkowe informacje do konsoli. Lepiej wylaczyc. :P")
    @CustomKey("debug")
    public boolean debug = true;

    @Comment
    @Comment("Ponizej znajduja sie dane do logowania bazy danych:")
    @CustomKey("storage-config")
    public StorageConfig storageConfig = new StorageConfig("dreamwarp");

    @Comment
    @Comment("Maksymalna liczba warpow na gracza (0 = bez limitu)")
    @CustomKey("max-warps-per-player")
    public int maxWarpsPerPlayer = 5;

    @Comment
    @Comment("Czy warpy maja byc domyslnie publiczne")
    @CustomKey("default-public-warps")
    public boolean defaultPublicWarps = true;
}