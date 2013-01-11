package org.xdb.utils;

import java.io.IOException;

import org.xdb.Config;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.out.GRAPHtoDOTtoGIF;

public class Dotty {
	public static void dot2Img(Graph graph, String fileName){
		final String path = Config.DOT_TRACE_PATH;
		final String dotFileName = path + fileName + ".dot";
		final String gifFileName = path + fileName + ".gif";
		final String exeFileName = Config.DOT_EXE;
		try {
			GRAPHtoDOTtoGIF.transform(graph, dotFileName, gifFileName, exeFileName);
		} catch (final IOException e) {
			// do nothing
		}
	}
}
