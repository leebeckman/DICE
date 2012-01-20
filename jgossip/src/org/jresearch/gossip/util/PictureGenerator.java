/*
 * $$Id: PictureGenerator.java,v 1.3 2005/06/07 12:32:27 bel70 Exp $$
 *
 * ***** BEGIN LICENSE BLOCK *****
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License
 * at http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
 * the License for the specific language governing rights and
 * limitations under the License.
 *
 * The Original Code is JGossip forum code.
 *
 * The Initial Developer of the Original Code is the JResearch, Org.
 * Portions created by the Initial Developer are Copyright (C) 2004
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 *              Dmitry Belov <bel@jresearch.org>
 *
 * ***** END LICENSE BLOCK ***** */

package org.jresearch.gossip.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

import org.jresearch.gossip.IConst;
import org.jresearch.gossip.configuration.Configurator;
import org.jresearch.gossip.exception.SystemException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * Class <code>PictureGenerator</code> singletone for generation picture for
 * sign up screen.
 * 
 * @author <code>$Author: bel70 $</code>
 * @version <code>$Revision: 1.3 $</code>
 */
public class PictureGenerator {

	private static PictureGenerator instance = null;

	private BufferedImage image;

	private Graphics2D g;

	private GradientPaint gradient;

	private int height = 30;

	private int width = 200;

	private int grid = (int) (height / 3.1);

	private int maxH = 150;

	private int maxW = 150;

	private int thumbH = 150;

	private int thumbW = 150;

	private static Object lock = new Object();

	/**
	 * Method <code>getInstance</code> return generator instance
	 * 
	 * @return generator instance
	 * @throws <code>SteedException</code>
	 */
	public static PictureGenerator getInstance() throws SystemException {

		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new PictureGenerator();
				}
			}
		}

		return instance;
	}

	private PictureGenerator() throws SystemException {

		try {
			Configurator config = Configurator.getInstance();
			int newHeight = config
					.getInt(IConst.MISC.REGISTRATION_PICTURE_HEIGHT);
			int newWidth = config
					.getInt(IConst.MISC.REGISTRATION_PICTURE_WIDTH);
			int newGrid = config.getInt(IConst.MISC.REGISTRATION_PICTURE_GRID);

			int newMaxH = config.getInt(IConst.MISC.AVATAR_HEIGHT);
			int newMaxW = config.getInt(IConst.MISC.AVATAR_WIDTH);

			int newThunbH = config.getInt(IConst.MISC.THUMBNAIL_HEIGHT);
			int newThunbW = config.getInt(IConst.MISC.THUMBNAIL_WIDTH);

			maxH = newMaxH;
			maxW = newMaxW;

			thumbH = newThunbH;
			thumbW = newThunbW;

			height = newHeight;
			width = newWidth;
			grid = newGrid;
		} catch (Throwable e) {

		}

		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g = image.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setFont(new Font("Dialog", Font.BOLD, (int) (height * 0.9)));
		gradient = new GradientPaint(0, 0, Color.WHITE, width - 1, height - 1,
				Color.GRAY);
	}

	/**
	 * Method <code>getHeight</code> return picture height
	 * 
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Method <code>getWidth</code> return picture width
	 * 
	 * @return picture width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Method <code>generatePicture</code> write generated picture into given
	 * stream
	 * 
	 * @param <code>label</code> label to write on picture
	 * @param <code>os</code> stream to write picture
	 * @throws <code>IOException</code>
	 */
	public void generatePicture(String label, OutputStream out)
			throws IOException {

		/* background */
		g.setPaint(gradient);
		g.fillRect(0, 0, width - 1, height - 1);
		/* label */
		g.setColor(Color.BLACK);
		g.drawString(label,
				(width - g.getFontMetrics().stringWidth(label)) / 2,
				(int) (height * 0.85));

		/* grid */
		/* horizontal */
		g.setColor(Color.BLACK);
		for (int y = (int) (grid / 2); y < height; y += grid) {
			g.drawLine(0, y, width, y);
		}

		/* vertical */
		for (int x = (int) (grid / 2); x < width; x += grid) {
			g.drawLine(x, 0, x, height);
		}
		ImageIO.write((RenderedImage) image, "jpeg", out);
	}

	/**
	 * @param oldImage
	 * @param os
	 * @throws IOException
	 */
	public void prepareImage(BufferedImage img, OutputStream out)
			throws IOException {
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);

		int h = img.getHeight();
		int w = img.getWidth();

		if (h > this.maxH || w > this.maxW) {
			int fullH = h;
			int fullW = w;
			double d = (double) h / (double) w;
			if (h > this.maxH) {
				h = this.maxH;
				w = (int) Math.round(h / d);
			}
			if (w > this.maxW) {
				w = this.maxW;
				h = (int) Math.round(w * d);
			}

			AffineTransform xform = AffineTransform.getScaleInstance((double) h
					/ (double) fullH, (double) w / (double) fullW);
			AffineTransformOp op = new AffineTransformOp(xform,
					AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			BufferedImage newImage = new BufferedImage(w, h,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D gr = newImage.createGraphics();
			int top = 0;
			int left = 0;
			gr.drawImage(img, op, left, top);
			ImageIO.write((RenderedImage) newImage, "jpeg", out);
		} else {
			ImageIO.write((RenderedImage) img, "jpeg", out);
		}

	}

	/**
	 * @param img
	 * @param out
	 * @throws IOException
	 */
	public void prepareImageThumbnail(BufferedImage img, ServletOutputStream out)
			throws IOException {
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);

		int h = img.getHeight();
		int w = img.getWidth();

		int fullH = h;
		int fullW = w;
		double d = (double) h / (double) w;
		if (h > this.thumbH) {
			h = this.thumbH;
			w = (int) Math.round(h / d);
		}
		if (w > this.thumbW) {
			w = this.thumbW;
			h = (int) Math.round(w * d);
		}

		AffineTransform xform = AffineTransform.getScaleInstance((double) h
				/ (double) fullH, (double) w / (double) fullW);
		AffineTransformOp op = new AffineTransformOp(xform,
				AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		BufferedImage newImage = new BufferedImage(this.thumbW, this.thumbH,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D gr = newImage.createGraphics();
		gr.setColor(Color.BLACK);
		g.fillRect(0, 0, this.thumbH - 1, this.thumbW - 1);
		int top = (this.thumbH - h) / 2;
		int left = (this.thumbW - w) / 2;
		gr.drawImage(img, op, left, top);
		ImageIO.write((RenderedImage) newImage, "jpeg", out);

	}

	public static void main(String[] args) {// for tests only
		String label = "PictureGeneratorTest" + System.currentTimeMillis()
				+ ".jpg";
		File out = new File(label);
		try {
			getInstance().generatePicture(label, new FileOutputStream(out));
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
