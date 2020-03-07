package nambui9812.playlistrank.services;

import java.util.List;
import java.util.HashMap;

import nambui9812.playlistrank.entities.Tag;

public interface TagService {
  List<Tag> findAll();

  HashMap<String, Integer> findGroupTagName();

  Tag findById(String id);

  List<Tag> findByName(String name);      // Find all tags that have the same name

  List<Tag> findByPlaylistId(String id);  // Find all tags that belong to a playlist

  Tag createTag(Tag tag);

  Tag likeTag(String username, Tag existing);

  Tag dislikeTag(String username, Tag existing);

  void deleteTag(String id);
}