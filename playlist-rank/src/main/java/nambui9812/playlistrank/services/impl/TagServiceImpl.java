package nambui9812.playlistrank.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nambui9812.playlistrank.entities.Tag;
import nambui9812.playlistrank.repositories.TagRepository;
import nambui9812.playlistrank.services.TagService;
import nambui9812.playlistrank.exceptions.TagNotFoundException;

@Service
public class TagServiceImpl implements TagService {
  @Autowired
  private TagRepository tagRepository;

  @Override
  public List<Tag> findAll() {
    return tagRepository.findAll();
  }

  @Override
  public Tag findById(String id) {
    return tagRepository.findById(id).orElseThrow(() -> new TagNotFoundException());
  }

  @Override
  public List<Tag> findByName(String name) {
    return tagRepository.findByName(name);
  }

  @Override
  public List<Tag> findByPlaylistId(String id) {
    return tagRepository.findByPlaylistId(id);
  }

  @Override
  public Tag createTag(Tag tag) {
    return tagRepository.save(tag);
  }

  @Override
  public void deleteTag(String id) {
    tagRepository.deleteById(id);
  }
}