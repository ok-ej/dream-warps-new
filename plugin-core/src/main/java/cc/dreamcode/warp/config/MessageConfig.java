package cc.dreamcode.warp.config;

import cc.dreamcode.notice.bukkit.BukkitNotice;
import cc.dreamcode.platform.bukkit.component.configuration.Configuration;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.CustomKey;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.Headers;

@Configuration(child = "message.yml")
@Headers({
        @Header("## Dream-Warp (Message-Config) ##"),
        @Header("Dostepne type: (DO_NOT_SEND, CHAT, ACTION_BAR, SUBTITLE, TITLE, TITLE_SUBTITLE)")
})
public class MessageConfig extends OkaeriConfig {

    @CustomKey("command-usage")
    public BukkitNotice usage = BukkitNotice.chat("&7Przyklady uzycia komendy: &c{label}");
    @CustomKey("command-usage-help")
    public BukkitNotice usagePath = BukkitNotice.chat("&f{usage} &8- &7{description}");

    @CustomKey("command-usage-not-found")
    public BukkitNotice usageNotFound = BukkitNotice.chat("&cNie znaleziono pasujacych do kryteriow komendy.");
    @CustomKey("command-path-not-found")
    public BukkitNotice pathNotFound = BukkitNotice.chat("&cTa komenda jest pusta lub nie posiadasz dostepu do niej.");
    @CustomKey("command-no-permission")
    public BukkitNotice noPermission = BukkitNotice.chat("&cNie posiadasz uprawnien.");
    @CustomKey("command-not-player")
    public BukkitNotice notPlayer = BukkitNotice.chat("&cTa komende mozna tylko wykonac z poziomu gracza.");
    @CustomKey("command-not-console")
    public BukkitNotice notConsole = BukkitNotice.chat("&cTa komende mozna tylko wykonac z poziomu konsoli.");
    @CustomKey("command-invalid-format")
    public BukkitNotice invalidFormat = BukkitNotice.chat("&cPodano nieprawidlowy format argumentu komendy. ({input})");

    @CustomKey("player-not-found")
    public BukkitNotice playerNotFound = BukkitNotice.chat("&cPodanego gracza nie znaleziono.");
    @CustomKey("world-not-found")
    public BukkitNotice worldNotFound = BukkitNotice.chat("&cPodanego swiata nie znaleziono.");
    @CustomKey("cannot-do-at-my-self")
    public BukkitNotice cannotDoAtMySelf = BukkitNotice.chat("&cNie mozesz tego zrobic na sobie.");
    @CustomKey("number-is-not-valid")
    public BukkitNotice numberIsNotValid = BukkitNotice.chat("&cPodana liczba nie jest cyfra.");

    @CustomKey("config-reloaded")
    public BukkitNotice reloaded = BukkitNotice.chat("&aPrzeladowano! &7({time})");
    @CustomKey("config-reload-error")
    public BukkitNotice reloadError = BukkitNotice.chat("&cZnaleziono problem w konfiguracji: &6{error}");

    // Warp specific messages
    @CustomKey("warp-created")
    public BukkitNotice warpCreated = BukkitNotice.chat("&aWarp &e{warp} &azostal utworzony!");
    @CustomKey("warp-deleted")
    public BukkitNotice warpDeleted = BukkitNotice.chat("&aWarp &e{warp} &azostal usuniety!");
    @CustomKey("warp-teleported")
    public BukkitNotice warpTeleported = BukkitNotice.chat("&aTeleportowano do warpa &e{warp}&a!");
    @CustomKey("warp-not-found")
    public BukkitNotice warpNotFound = BukkitNotice.chat("&cWarp &e{warp} &cnie istnieje!");
    @CustomKey("warp-already-exists")
    public BukkitNotice warpAlreadyExists = BukkitNotice.chat("&cWarp &e{warp} &cjuz istnieje!");
    @CustomKey("warp-no-access")
    public BukkitNotice warpNoAccess = BukkitNotice.chat("&cNie masz dostepu do warpa &e{warp}&c!");
    @CustomKey("warp-delete-no-permission")
    public BukkitNotice warpDeleteNoPermission = BukkitNotice.chat("&cNie mozesz usunac warpa &e{warp}&c!");
    @CustomKey("warp-location-invalid")
    public BukkitNotice warpLocationInvalid = BukkitNotice.chat("&cLokalizacja warpa jest nieprawidlowa!");
    @CustomKey("warp-list-empty")
    public BukkitNotice warpListEmpty = BukkitNotice.chat("&cBrak dostepnych warpow!");
    @CustomKey("warp-list")
    public BukkitNotice warpList = BukkitNotice.chat("&aDostepne warpy: &e{warps}");
    @CustomKey("warp-info")
    public BukkitNotice warpInfo = BukkitNotice.chat("&aInformacje o warpie &e{warp}&a:\n&7Tworca: &f{creator}\n&7Utworzony: &f{created}\n&7Publiczny: &f{public}\n&7Opis: &f{description}");
}