package ru.zubentsov.service;

import java.text.DateFormat;
import java.util.Date;

public class MyService {
	
	public String getTime() {
		Date now = new Date();
		return DateFormat.getDateTimeInstance().format(now);
	}

}
