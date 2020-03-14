package nambui9812.playlistrank.controllers;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import nambui9812.playlistrank.entities.Playlist;
import nambui9812.playlistrank.services.impl.PlaylistServiceImpl;
import nambui9812.playlistrank.validations.UpdateDescriptionPlaylistValidation;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/playlists")
public class PlaylistController {
  @Autowired
  private PlaylistServiceImpl playlistServiceImpl;

  // Get all playlists
  @GetMapping("/")
  ResponseEntity<Object> getAllPlaylists() {
    HashMap<String, Object> res = new HashMap<>();

    List<Playlist> playlists = playlistServiceImpl.findAll();

    res.put("success", true);
    res.put("message", "Get all playlists successfully.");
    res.put("playlists", playlists);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Get a playlist by id
  @GetMapping("/id/{id}")
  ResponseEntity<Object> getPlaylist(@PathVariable String id) {
    HashMap<String, Object> res = new HashMap<>();

    Playlist playlist = playlistServiceImpl.findById(id);

    res.put("success", true);
    res.put("message", "Get playlist successfully.");
    res.put("playlist", playlist);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Get all playlist of a user
  @GetMapping("/authorUsername/{authorUsername}")
  ResponseEntity<Object> getAllPlaylistOfUser(@PathVariable String authorUsername) {
    HashMap<String, Object> res = new HashMap<>();

    List<Playlist> playlists = playlistServiceImpl.findByAuthorUsername(authorUsername);

    res.put("success", true);
    res.put("message", "Get all playlists of a user successfully.");
    res.put("playlists", playlists);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Create a new playlist
  @PostMapping("/")
  ResponseEntity<Object> createPlaylist(@Valid @RequestBody Playlist newPlaylist, Authentication authentication) {
    HashMap<String, Object> res = new HashMap<>();

    if (!newPlaylist.getAuthorUsername().equals(authentication.getName())) {
      res.put("success", false);
      res.put("message", "Cannot create playlist for other person.");

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }

    newPlaylist = playlistServiceImpl.createPlaylist(newPlaylist);

    res.put("success", true);
    res.put("message", "Create a new playlist successfully");
    res.put("playlist", newPlaylist);

    return ResponseEntity.status(HttpStatus.CREATED).body(res);
  }

  // Share a playlist
  @PostMapping("/share-playlist/{id}")
  ResponseEntity<Object> sharePlaylist(@PathVariable String id, Authentication authentication) {
    HashMap<String, Object> res = new HashMap<>();

    Playlist existing = playlistServiceImpl.findById(id);

    if (existing == null) {
      res.put("success", false);
      res.put("message", "Cannot share an invalid playlist.");

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    if (existing.getAuthorUsername().equals(authentication.getName())) {
      res.put("success", false);
      res.put("message", "Cannot share your own playlist.");

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    Playlist shared = playlistServiceImpl.sharePlaylist(authentication.getName(), existing);

    res.put("success", true);
    res.put("message", "Share playlist successfully.");
    res.put("playlist", shared);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Update playlist's description
  @PutMapping("/update-description")
  ResponseEntity<Object> updatePlaylist( @Valid @RequestBody UpdateDescriptionPlaylistValidation info, Authentication authentication) {
    HashMap<String, Object> res = new HashMap<>();

    Playlist existing = playlistServiceImpl.findById(info.getId());

    if (existing == null) {
      res.put("success", false);
      res.put("message", "Cannot update an invalid playlist.");

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    if (!existing.getAuthorUsername().equals(authentication.getName())) {
      res.put("success", false);
      res.put("message", "Cannot update a playlist that not belong to you.");

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    Playlist updated = playlistServiceImpl.updateDescription(existing, info);

    res.put("success", true);
    res.put("message", "Update description successfully.");
    res.put("playlist", updated);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Love a playlist
  @PutMapping("/love/{id}")
  ResponseEntity<Object> lovePlaylist(@PathVariable String id, Authentication authentication) {
    HashMap<String, Object> res = new HashMap<>();

    Playlist existing = playlistServiceImpl.findById(id);

    if (existing == null) {
      res.put("success", false);
      res.put("message", "Cannot love an invalid playlist.");

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    Playlist loved = playlistServiceImpl.lovePlaylist(authentication.getName(), existing);

    res.put("success", true);
    res.put("message", "Love a playlist successfully.");
    res.put("playlist", loved);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Unlove a playlist
  @PutMapping("/unlove/{id}")
  ResponseEntity<Object> unlovePlaylist(@PathVariable String id, Authentication authentication) {
    HashMap<String, Object> res = new HashMap<>();

    Playlist existing = playlistServiceImpl.findById(id);

    if (existing == null) {
      res.put("success", false);
      res.put("message", "Cannot unlove an invalid playlist.");

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    Playlist unloved = playlistServiceImpl.lovePlaylist(authentication.getName(), existing);

    res.put("success", true);
    res.put("message", "Unlove a playlist successfully.");
    res.put("playlist", unloved);

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }

  // Delete a playlist
  @DeleteMapping("/{id}")
  ResponseEntity<Object> deletePlaylist(@PathVariable String id, Authentication authentication) {
    HashMap<String, Object> res = new HashMap<>();

    Playlist existing = playlistServiceImpl.findById(id);

    if (existing == null) {
      res.put("success", false);
      res.put("message", "Cannot delete an invalid playlist.");

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    // Username of owner
    String fromExisting = existing.getAuthorUsername();

    // Username from token
    String fromAuth = authentication.getName();

    if (!fromExisting.equals(fromAuth)) {
      res.put("success", false);
      res.put("message", "Unauthorization for deleting playlist.");

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }
    
    playlistServiceImpl.deletePlaylist(id);

    res.put("success", true);
    res.put("message", "Delete playlist successfully.");

    return ResponseEntity.status(HttpStatus.OK).body(res);
  }
}
