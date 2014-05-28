package org.xdb.utils;

import java.io.File;
import java.io.IOException;

import org.xdb.Config;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.out.GRAPHtoDOTtoGIF;

public class Dotty {
	public static String dot2Img(Graph graph, String fileName) {
		final String path = Config.DOT_TRACE_PATH;
		final String dotFileName = new File(path, fileName + ".dot")
				.getAbsolutePath();
		final String gifFileName = new File(path, fileName + ".gif")
				.getAbsolutePath();
		final String exeFileName = Config.DOT_EXE;

		try {
			GRAPHtoDOTtoGIF.transform(graph, dotFileName, gifFileName,
					exeFileName);
		} catch (final IOException e) {
			// do nothing
		}

		if (Config.PLATTFORM.equalsIgnoreCase("MAC")) {
			final String cmd = exeFileName+" -Tgif "+dotFileName+" -o "+gifFileName;
			try {
				Process p = Runtime.getRuntime().exec(cmd);
				p.waitFor();
			} catch (Exception e) {
				// do nothing
				e.printStackTrace();
			}
		}

		return gifFileName;
	}
}
