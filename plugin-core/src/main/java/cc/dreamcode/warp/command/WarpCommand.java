package cc.dreamcode.warp.command;

import cc.dreamcode.command.CommandBase;
import cc.dreamcode.command.annotation.Async;
import cc.dreamcode.command.annotation.Command;
import cc.dreamcode.command.annotation.Executor;
import cc.dreamcode.command.annotation.Permission;
import cc.dreamcode.command.annotation.RequiredSender;
import cc.dreamcode.notice.bukkit.BukkitNotice;
import cc.dreamcode.warp.config.MessageConfig;
import cc.dreamcode.warp.config.PluginConfig;
import cc.dreamcode.warp.warp.Warp;
import cc.dreamcode.warp.warp.WarpService;
import cc.dreamcode.utilities.TimeUtil;
import eu.okaeri.configs.exception.OkaeriException;
import eu.okaeri.injector.annotation.Inject;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Optional;

@Command(name = "warp", description = "Zarzadzanie warpami")
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class WarpCommand implements CommandBase {

    private final PluginConfig pluginConfig;
    private final MessageConfig messageConfig;
    private final WarpService warpService;

    @Async
    @Permission("dream-warp.reload")
    @Executor(path = "reload", description = "Przeladowuje konfiguracje.")
    BukkitNotice reload() {
        final long time = System.currentTimeMillis();

        try {
            this.messageConfig.load();
            this.pluginConfig.load();

            return this.messageConfig.reloaded
                    .with("time", TimeUtil.format(System.currentTimeMillis() - time));
        }
        catch (NullPointerException | OkaeriException e) {
            e.printStackTrace();

            return this.messageConfig.reloadError
                    .with("error", e.getMessage());
        }
    }

    @RequiredSender("CLIENT")
    @Permission("dream-warp.create")
    @Executor(path = "create <name>", description = "Tworzy nowy warp w aktualnej lokalizacji.")
    BukkitNotice createWarp(Player player, String name) {
        Optional<Warp> existingWarp = this.warpService.getWarp(name);
        if (existingWarp.isPresent()) {
            return this.messageConfig.warpAlreadyExists.with("warp", name);
        }

        Warp warp = this.warpService.createWarp(name, player.getLocation(), player.getName());
        return this.messageConfig.warpCreated.with("warp", warp.getName());
    }

    @RequiredSender("CLIENT")
    @Permission("dream-warp.use")
    @Executor(path = "<name>", description = "Teleportuje do warpa.")
    BukkitNotice teleportToWarp(Player player, String name) {
        Optional<Warp> warpOpt = this.warpService.getWarp(name);
        if (!warpOpt.isPresent()) {
            return this.messageConfig.warpNotFound.with("warp", name);
        }

        Warp warp = warpOpt.get();
        if (!this.warpService.canAccessWarp(player, warp)) {
            return this.messageConfig.warpNoAccess.with("warp", name);
        }

        this.warpService.teleportToWarp(player, warp);
        return this.messageConfig.warpTeleported.with("warp", name);
    }

    @Permission("dream-warp.delete")
    @Executor(path = "delete <name>", description = "Usuwa warp.")
    BukkitNotice deleteWarp(Player player, String name) {
        Optional<Warp> warpOpt = this.warpService.getWarp(name);
        if (!warpOpt.isPresent()) {
            return this.messageConfig.warpNotFound.with("warp", name);
        }

        Warp warp = warpOpt.get();
        if (!player.getName().equals(warp.getCreator()) && !player.hasPermission("dream-warp.admin")) {
            return this.messageConfig.warpDeleteNoPermission.with("warp", name);
        }

        this.warpService.deleteWarp(name);
        return this.messageConfig.warpDeleted.with("warp", name);
    }

    @Permission("dream-warp.list")
    @Executor(path = "list", description = "Wyswietla liste dostepnych warpow.")
    BukkitNotice listWarps(Player player) {
        Collection<Warp> warps = this.warpService.getPublicWarps();
        if (warps.isEmpty()) {
            return this.messageConfig.warpListEmpty;
        }

        StringBuilder warpList = new StringBuilder();
        for (Warp warp : warps) {
            if (warpList.length() > 0) {
                warpList.append(", ");
            }
            warpList.append(warp.getName());
        }

        return this.messageConfig.warpList.with("warps", warpList.toString());
    }

    @RequiredSender("CLIENT")
    @Permission("dream-warp.info")
    @Executor(path = "info <name>", description = "Wyswietla informacje o warpie.")
    BukkitNotice warpInfo(Player player, String name) {
        Optional<Warp> warpOpt = this.warpService.getWarp(name);
        if (!warpOpt.isPresent()) {
            return this.messageConfig.warpNotFound.with("warp", name);
        }

        Warp warp = warpOpt.get();
        if (!this.warpService.canAccessWarp(player, warp)) {
            return this.messageConfig.warpNoAccess.with("warp", name);
        }

        return this.messageConfig.warpInfo
                .with("warp", warp.getName())
                .with("creator", warp.getCreator())
                .with("created", warp.getCreatedAt().toString())
                .with("public", warp.isPublicWarp() ? "Tak" : "Nie")
                .with("description", warp.getDescription() != null ? warp.getDescription() : "Brak opisu");
    }
}