package se.ffcg.pub.hash;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlController {
	
	@Autowired
	Environment env;

	@RequestMapping(path = "/crunch/{prefix}/{zeroes}", produces = "text/html")
	public String crunchHtml(@PathVariable("prefix") String prefix, @PathVariable("zeroes") int zeroes,
			@PathParam("verbose") boolean verbose, Model model) {
		HashCruncher cruncher = new HashCruncher(prefix);

		long startTime = System.nanoTime();
		String response = cruncher.crunch(zeroes);
		model.addAttribute("result", response);
		model.addAttribute("verbose", verbose);
		model.addAttribute("hash", cruncher.hash(response));
		model.addAttribute("time", (System.nanoTime() - startTime) / 1000000);
		model.addAttribute("instance", env.getProperty("HOSTNAME", "") + " - " + env.getProperty("JAVA_APP_JAR", ""));
		model.addAttribute("production", env.getProperty("KUBERNETES_NAMESPACE", "").toUpperCase().contains("PRODUCTION"));
		
		return "crunch";
	}
}
