package de.febrildur.xwikistarter;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.xwiki.observation.AbstractEventListener;
import org.xwiki.observation.event.Event;

public abstract class AbstractStartWikiEventListener extends AbstractEventListener {
	@Inject
	protected Logger log;

	private static Timer timer = new Timer();

	public AbstractStartWikiEventListener(String name, List<? extends Event> events) {
		super(name, events);
	}

	public abstract List<String> getWikis();

	@Override
	public String getName() {
		return this.getClass().getName();
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
