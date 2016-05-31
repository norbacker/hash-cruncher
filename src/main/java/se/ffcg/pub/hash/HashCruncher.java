package se.ffcg.pub.hash;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

public class HashCruncher {

	private final String prefix;
	
	public HashCruncher(String prefix) {
		this.prefix = prefix + "-";
	}
	
	public String crunch(int trailingZeroes) {
		String expectedSuffix = StringUtils.repeat('0', trailingZeroes);
		
		String attempt, digest;
		do {
			attempt = prefix + UUID.randomUUID().toString();
			digest = hash(attempt);
		} while (!digest.endsWith(expectedSuffix));
		
		return attempt;
	}

	public String hash(String digest) {
		return DigestUtils.md5DigestAsHex(digest.getBytes());
	}
}
