package org.miniats.candidateservice.util;

import java.util.UUID;

public class UidGenerator {

	public static String generateUid() {
		return UUID.randomUUID().toString();
	}
}
