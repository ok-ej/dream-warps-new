package cc.dreamcode.warp.warp;

import eu.okaeri.persistence.repository.DocumentRepository;
import eu.okaeri.persistence.repository.annotation.DocumentCollection;
import lombok.NonNull;

import java.util.Collection;
import java.util.stream.Collectors;

@DocumentCollection(path = "warps", keyLength = 64)
public interface WarpRepository extends DocumentRepository<String, Warp> {

    default Warp createWarp(@NonNull String name) {
        return this.findOrCreateByPath(name.toLowerCase());
    }

    default Collection<Warp> findPublicWarps() {
        return this.findAll().stream()
                .filter(Warp::isPublicWarp)
                .collect(Collectors.toList());
    }

    default Collection<Warp> findWarpsByCreator(@NonNull String creator) {
        return this.findAll().stream()
                .filter(warp -> creator.equals(warp.getCreator()))
                .collect(Collectors.toList());
    }
}