
			
					package org.xdb.utils;

			import java.io.File;
import java.io.FileOutputStream;

			import org.xdb.Config;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.out.GRAPHtoDOT;

			public class Dotty {
				/**
				 * Renders dot graph as GIF image
				 * @param graph
				 * @param fileName
				 * @return
				 */
				public static String dot2Img(Graph graph, String fileName) {
					return dot2Img(graph, fileName, "gif");
				}

				/**
				 * Renders dot graph as image of given Type
				 * @param graph
				 * @param fileName
				 * @param imgType
				 * @return
				 */
				public static String dot2Img(Graph graph, String fileName, String imgType) {
					final String path = Config.DOT_TRACE_PATH;
					final String dotFileName = new File(path, fileName + ".dot")
							.getAbsolutePath();
					final String gifFileName = new File(path, fileName + "."+imgType)
							.getAbsolutePath();
					final String exeFileName = Config.DOT_EXE;

					final String cmd = exeFileName + " -T"+imgType+ " " + dotFileName + " -o "
							+ gifFileName;
					try {
						GRAPHtoDOT.transform(graph, new FileOutputStream(dotFileName));
						Process p = Runtime.getRuntime().exec(cmd);
						p.waitFor();
					} catch (Exception e) {
						// do nothing
						e.printStackTrace();
					}

					return gifFileName;
				}
			}
