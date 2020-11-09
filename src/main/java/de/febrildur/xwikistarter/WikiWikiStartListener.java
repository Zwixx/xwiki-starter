package de.febrildur.xwikistarter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;
import org.xwiki.observation.event.ApplicationStartedEvent;

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