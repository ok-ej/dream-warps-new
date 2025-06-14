package cc.dreamcode.warp.warp;

import cc.dreamcode.warp.config.MessageConfig;
import eu.okaeri.injector.annotation.Inject;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class WarpService {

    private final WarpRepository warpRepository;
    private final MessageConfig messageConfig;

    public Optional<Warp> getWarp(@NonNull String name) {
        return Optional.ofNullable(this.warpRepository.findByPath(name.toLowerCase()));
    }

    public Warp createWarp(@NonNull String name, @NonNull Location location, @NonNull String creator) {
        Warp warp = this.warpRepository.createWarp(name);
        warp.setLocation(location);
        warp.setCreator(creator);
        this.warpRepository.save(warp);
        return warp;
    }

    public boolean deleteWarp(@NonNull String name) {
        return this.warpRepository.deleteByPath(name.toLowerCase());
    }

    public Collection<Warp> getPublicWarps() {
        return this.warpRepository.findPublicWarps();
    }

    public Collection<Warp> getWarpsByCreator(@NonNull String creator) {
        return this.warpRepository.findWarpsByCreator(creator);
    }

    public void teleportToWarp(@NonNull Player player, @NonNull Warp warp) {
        Location location = warp.getLocation();
        if (location == null || location.getWorld() == null) {
            this.messageConfig.warpLocationInvalid.send(player);
            return;
        }

        player.teleport(location);
        this.messageConfig.warpTeleported.send(player, "warp", warp.getName());
    }

    public boolean canAccessWarp(@NonNull Player player, @NonNull Warp warp) {
        return warp.isPublicWarp() || 
               player.getName().equals(warp.getCreator()) || 
               player.hasPermission("dream-warp.admin");
    }
}