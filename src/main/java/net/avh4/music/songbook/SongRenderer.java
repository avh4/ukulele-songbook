package net.avh4.music.songbook;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class SongRenderer {

	static final Template template;

	static {
		VelocityEngine velocity = new VelocityEngine();
		velocity.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		velocity.setProperty("classpath.resource.loader.class",
				ClasspathResourceLoader.class.getName());

		try {
			velocity.init();
			template = velocity.getTemplate("song.template");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static String format(Song song) {
		try {
			VelocityContext context = new VelocityContext();
			context.put("song", song);

			StringWriter sw = new StringWriter();
			template.merge(context, sw);
			return sw.toString();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
