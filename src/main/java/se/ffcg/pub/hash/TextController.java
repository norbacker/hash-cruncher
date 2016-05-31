package se.ffcg.pub.hash;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextController {

	@RequestMapping(path = "/crunch/{prefix}/{zeroes}", produces = "text/plain")
	public String crunchPlain(@PathVariable("prefix") String prefix, @PathVariable("zeroes") int zeroes) {
		HashCruncher cruncher = new HashCruncher(prefix);
		
		return  cruncher.crunch(zeroes);
	}
}
