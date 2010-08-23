package net.avh4.test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import net.avh4.util.StackUtils;

import org.apache.commons.io.IOUtils;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

/**
 * A class containing factory methods for hamcrest matchers contained in {@code
 * net.avh4.test}.
 * 
 * @author avh4
 */
public class Matchers {

	@Factory
	public static Matcher<String> isApproved() throws IOException {
		final StackTraceElement trace = StackUtils
				.getCallingStackTraceElement();
		final String methodName = trace.getMethodName();
		final String className = StackUtils.getCallingClass().getSimpleName();
		String resource = String.format("%s.%s.html", className, methodName);
		String data = getResourceAsString(StackUtils.getCallingClass(),
				resource);
		return equalTo(data);
	}

	private static String getResourceAsString(Class<?> clazz, String resource)
			throws IOException {
		InputStream stream = clazz.getResourceAsStream(resource);
		if (stream == null) {
			fail("Could not find resource " + resource + " from "
					+ clazz.getPackage());
		}
		String songData = IOUtils.toString(stream);
		return songData;
	}

}
