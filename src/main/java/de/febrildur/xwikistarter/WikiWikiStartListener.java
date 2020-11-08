package de.febrildur.xwikistarter;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.xwiki.bridge.event.ApplicationReadyEvent;
import org.xwiki.component.annotation.Component;
import org.xwiki.observation.AbstractEventListener;
import org.xwiki.observation.event.ApplicationStartedEvent;
import org.xwiki.observation.event.Event;
import org.xwiki.wiki.descriptor.WikiDescriptor;
import org.xwiki.wiki.descriptor.WikiDescriptorManager;
import org.xwiki.wiki.manager.WikiManagerException;

@Component
@Named("WikiWikiStartListener")
@Singleton
public class WikiWikiStartListener extends AbstractStartWikiEventListener {
	public WikiWikiStartListener() {
		super("WikiWikiStartListener", Collections.singletonList(new ApplicationStartedEvent()));
	}

	
	@Override
	public List<String> getWikis() {
		try {
			return Arrays.asList("http://" + InetAddress.getLocalHost().getHostName() + ":8080/");
		} catch (UnknownHostException e) {
			return Arrays.asList("http://localhost:8080/");
		}
	}
}