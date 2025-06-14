package cc.dreamcode.warp.warp;

import eu.okaeri.configs.annotation.CustomKey;
import eu.okaeri.persistence.document.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bukkit.Location;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class Warp extends Document {

    @CustomKey("name")
    private String name;

    @CustomKey("location")
    private Location location;

    @CustomKey("creator")
    private String creator;

    @CustomKey("created-at")
    private LocalDateTime createdAt;

    @CustomKey("description")
    private String description;

    @CustomKey("public")
    private boolean publicWarp;

    public String getName() {
        return this.getPath().toString();
    }

    public Warp() {
        this.createdAt = LocalDateTime.now();
        this.publicWarp = true;
    }
}