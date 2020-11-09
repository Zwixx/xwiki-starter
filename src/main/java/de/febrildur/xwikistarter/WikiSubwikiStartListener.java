package de.febrildur.xwikistarter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.xwiki.bridge.event.ApplicationReadyEvent;
import org.xwiki.component.annotation.Component;
import org.xwiki.wiki.descriptor.WikiDescriptorManager;
import org.xwiki.wiki.manager.WikiManagerException;

@Component
@Named("WikiSubwikiStartListener")
@Singleton
public class WikiSubwikiStartListener extends AbstractStartWikiEventListener {
	@Inject
	private WikiDescriptorManager manager;

	public WikiSubwikiStartListener() {
		super("WikiSubwikiStartListener", Collections.singletonList(new ApplicationReadyEvent()));
	}

	@Override
	public List<String> getWikis() {
		ArrayList<String> result = new ArrayList<>();
		try {
			for (String wiki : manager.getAllIds()) {
				String url = "http://" + InetAddress.getLocalHost().getHostName() + ":8080/xwiki/wiki/" + wiki + "/";
				result.add(url);
				log.warn(url + " wird in die Warteschlange gesetzt.");
			}
		} catch (WikiManagerException | UnknownHostException e) {
			e.printStackTrace();
		}

		return result;
	}
}