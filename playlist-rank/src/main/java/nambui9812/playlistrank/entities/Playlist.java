package nambui9812.playlistrank.entities;

import java.util.ArrayList;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "playlists")
public class Playlist {
  private @Id String id;

  @NotNull(message = "Author username is mandatory.")
  @NotBlank
  private String authorUsername;      // Name of person who creates this playlist
  
  private String description;
  
  private String sharedFromUsername;  // Null if not shared from anyone
  
  private ArrayList<String> loves;    // List of users's username who loves this playlist
  
  private ArrayList<String> shares;   // List of users's username who shares this playlist
  
  private ArrayList<String> tracks;   // List of name and author of the track

  private Integer popular;

  // Default constructor
  public Playlist() {}

  // Custom constructor
  public Playlist(
    String authorUsername,
    String description,
    String sharedFromUsername,
    ArrayList<String> tracks
  ) {
    this.authorUsername = authorUsername;
    this.description = description;
    this.sharedFromUsername = sharedFromUsername;
    this.loves = new ArrayList<>();
    this.shares = new ArrayList<>();
    this.tracks = tracks;
    this.popular = 0;
  }

  // All get methods
  public String getId() {
    return id;
  }

  public String getAuthorUsername() {
    return authorUsername;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getSharedFromUsername() {
    return sharedFromUsername;
  }

  public void setSharedFromUsername(String username) {
    this.sharedFromUsername = username;
  }

  public ArrayList<String> getLoves() {
    return loves;
  }
  
  public void setLoves(ArrayList<String> loves) {
    this.loves = loves;
  }

  public ArrayList<String> getShares() {
    return shares;
  }

  public void setShares(ArrayList<String> shares) {
    this.shares = shares;
  }

  public ArrayList<String> getTracks() {
    return tracks;
  }

  public void setTracks(ArrayList<String> tracks) {
    this.tracks = tracks;
  }

  public Integer getPopular() {
    return popular;
  }

  public void setPopular(Integer popular) {
    this.popular = popular;
  }
}
