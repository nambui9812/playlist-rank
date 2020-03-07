package nambui9812.playlistrank.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//import java.util.concurrent.TimeUnit;

import nambui9812.playlistrank.services.impl.TagServiceImpl;

@Component
public class UpdateTagPopularTask {
  private static final Logger logger = LoggerFactory.getLogger(UpdatePlaylistPopularTask.class);
  private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

  @Autowired
  private TagServiceImpl tagServiceImpl;

  @Scheduled(fixedRate = 60000)
  public void scheduleTaskWithFixedRate() {
    logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));

    tagServiceImpl.updateTagPopular();
  }

  public void scheduleTaskWithFixedDelay() {}

  public void scheduleTaskWithInitialDelay() {}

  public void scheduleTaskWithCronExpression() {}

}
