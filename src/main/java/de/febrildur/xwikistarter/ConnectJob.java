package de.febrildur.xwikistarter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.TimerTask;
import java.util.logging.Logger;

public class ConnectJob extends TimerTask {
	private static Logger log = Logger.getLogger(ConnectJob.class.getName());
	private URL url;
	
	public ConnectJob(String urlStr) throws MalformedURLException {
		url = new URL(urlStr);
	}

	@Override
	public void run() {
		try {
			URLConnection con = url.openConnection();
			con.setConnectTimeout(2000);
			con.setReadTimeout(1000);
			con.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while (in.readLine() != null) {
				// Do Nothing
			}
			in.close();
			
			log.warning(url.toExternalForm() + " wurde gestartet.");
			cancel();
		} catch (IOException e) {
			log.warning(url.toExternalForm() + " ist noch nicht gestartet.");
		}
	}
}