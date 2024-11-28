package entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Room {
    private String id;
    private String name;
    private String type;
    private boolean isAvailable;

    public Room(@JsonProperty("id") String id,
                @JsonProperty("name") String name,
                @JsonProperty("type") String type,
                @JsonProperty("isAvailable") boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.isAvailable = isAvailable;
    }

    public Room() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
