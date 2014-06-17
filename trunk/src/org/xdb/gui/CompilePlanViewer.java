package org.xdb.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.xdb.client.MasterTrackerClient;
import org.xdb.funsql.compile.CompilePlan;

/**
 * Displays the last compile plan executed in the XDB cluster
 * 
 * @author cbinnig
 *
 */
public class CompilePlanViewer extends JFrame {
	private static final long serialVersionUID = 6666856440857838422L;

	class ImageComponent extends JComponent {
		private static final long serialVersionUID = 8055865896136562197L;

		private BufferedImage image;

		public void setImage(BufferedImage image) {
			this.image = image;
			setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
			repaint();
			invalidate();
		}

		@Override
		protected void paintComponent(Graphics g) {
			if (image != null)
				g.drawImage(image.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, this);
		}
	}

	private MasterTrackerClient mClient = new MasterTrackerClient();
	private String fileName = "compilePlan";
	private ImageComponent imageComponent = new ImageComponent();
	private int width = 800;
	private int height = 600;
	
	public CompilePlanViewer() {
		this.setTitle("Compile Plan Viewer");

		this.add(new JScrollPane(imageComponent));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width+50, height+50);
		this.setVisible(true);

		Thread runner = new Thread() {
			public void run() {
				boolean wait = true;
				while (wait) {
					CompilePlan cPlan = mClient.getLastCompilePlan();
					if (cPlan != null) {
						try {
							String outFileName = cPlan.tracePlanReturnFile(fileName);
							System.err.println("Displaying compile plan: "+outFileName);
							File file = new File(outFileName);
							BufferedImage image = ImageIO.read(file);
							imageComponent.setImage(image);
							wait = false;
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						System.err.println("No compile plan available");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}

			}
		};

		runner.start();
	}

	public static void main(String[] args) {
		new CompilePlanViewer();
	}

}
