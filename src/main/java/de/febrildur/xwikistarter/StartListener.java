package de.febrildur.xwikistarter;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.xwiki.component.annotation.Component;
import org.xwiki.context.Execution;
import org.xwiki.context.ExecutionContext;
import org.xwiki.observation.EventListener;
import org.xwiki.observation.event.ApplicationStartedEvent;
import org.xwiki.observation.event.Event;

@Component
@Named("StartListener")
@Singleton
public class StartListener implements EventListener {
	@Inject
	private Logger log;

	private static Timer timer = new Timer();

	@Override
	public String getName() {
		return "StartListener";
	}

	@Override
	public List<Event> getEvents() {
		return Arrays.<Event>asList(new ApplicationStartedEvent());
	}

	List<String> getWikis() {
		return Arrays.asList("http://localhost:8080/xwiki/bin/view/Main/", "http://localhost:8080/xwiki/wiki/wowcharaktere/");
	}
	
	@Override
	public void onEvent(Event event, Object source, Object data) {
		log.warn("Initialisiere Wiki-Starter...");
		
		List<String> urls = getWikis();
		for (String urlStr : urls) {
			try {
				TimerTask task = new ConnectJob(urlStr);
				timer.schedule(task, 10_000, 10_000);
			} catch (MalformedURLException e) {
				e.printStackTrace();
				log.warn("Die URL: " + urlStr + " ist ungültig. Timer wird abgebrochen.");
				throw new RuntimeException(e);
			}
		}
	}
}