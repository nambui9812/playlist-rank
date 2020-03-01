package nambui9812.playlistrank.services;

import java.util.List;

import nambui9812.playlistrank.entities.Tag;

public interface TagService {
  List<Tag> findAll();

  Tag findById(String id);

  List<Tag> findByName(String name);

  List<Tag> findByPlaylistId(String id);

  Tag createTag(Tag tag);

  void deleteTag(String id);
}