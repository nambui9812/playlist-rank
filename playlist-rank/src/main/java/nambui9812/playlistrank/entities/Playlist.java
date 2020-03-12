package nambui9812.playlistrank.entities;

import java.util.ArrayList;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import nambui9812.playlistrank.entities.WebsiteTrack;

@Data
@Document(collection = "playlists")
public class Playlist {
  private @Id String id;

  @NotNull(message = "Author username is mandatory.")
  @Size(min = 4, max = 20, message = "Author username must be from 4 to 20 in size.")
  private String authorUsername;      // Name of person who creates this playlist
  
  private String description;
  
  private String sharedFromUsername;  // Null if not shared from anyone
  
  private ArrayList<String> loves = new ArrayList<>();    // List of users's username who loves this playlist
  
  private ArrayList<String> shares = new ArrayList<>();   // List of users's username who shares this playlist
  
  @NotNull(message = "Playlist must have at least one author - name track pair.")
  @NotEmpty(message = "Playlist must have at least one author - name track pair.")
  private ArrayList<WebsiteTrack> tracks;   // List of name and author of the track

  private float popular = 0f;

  // Default constructor
  public Playlist() {}

  // Custom constructor
  public Playlist(
    String authorUsername,
    String description,
    String sharedFromUsername,
    ArrayList<WebsiteTrack> tracks
  ) {
    this.authorUsername = authorUsername;
    this.description = description;
    this.sharedFromUsername = sharedFromUsername;
    this.tracks = tracks;
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

  public ArrayList<WebsiteTrack> getTracks() {
    return tracks;
  }

  public void setTracks(ArrayList<WebsiteTrack> tracks) {
    this.tracks = tracks;
  }

  public float getPopular() {
    return popular;
  }

  public void setPopular(float popular) {
    this.popular = popular;
  }
}
