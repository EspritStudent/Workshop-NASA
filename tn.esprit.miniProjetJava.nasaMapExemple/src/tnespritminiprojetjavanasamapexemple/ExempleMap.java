package tnespritminiprojetjavanasamapexemple;

import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.examples.ApplicationTemplate;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Offset;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

public class ExempleMap extends ApplicationTemplate {

	public static class AppFrame extends ApplicationTemplate.AppFrame {
		RenderableLayer layer;

		Panel panel = new Panel();

		public void test() {

		}

		public AppFrame() throws IllegalAccessException,
				InstantiationException, ClassNotFoundException {
			super(true, false, false);

			this.getContentPane().add(panel, BorderLayout.WEST);
			panel.getBtnPlacemark().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					layer = new RenderableLayer();
					PointPlacemark pp = new PointPlacemark(Position
					.fromDegrees(Double.parseDouble(panel.getLattitude().getText()), Double.parseDouble(panel.getLongitude().getText())));
					pp.setLabelText(panel.getNameMark().getText());
					pp.setValue(AVKey.DISPLAY_NAME, panel.getDescription()
							.getText());
					pp.setLineEnabled(false);
					pp.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
					PointPlacemarkAttributes attrs = new PointPlacemarkAttributes();
					attrs.setImageAddress("images/pushpins/plain-teal.png");
					attrs.setImageColor(new Color(1f, 1f, 1f, 0.6f));
					attrs.setScale(0.6);
					attrs.setLabelOffset(new Offset(0.9d, 0.6d, AVKey.FRACTION,
							AVKey.FRACTION));
					pp.setAttributes(attrs);
					layer.addRenderable(pp);
					insertBeforeCompass(getWwd(), layer);
//					panel.clear();

				}
			});
			this.getWwd().getInputHandler()
					.addMouseListener(new MouseListener() {
						public void mouseClicked(MouseEvent mouseEvent) {
							if ((mouseEvent.getModifiers() & ActionEvent.SHIFT_MASK) == 0)
								return;
							Position pos = getWwd().getCurrentPosition();
							if (pos == null)
								return;
							LatLon latLon = new LatLon(pos.latitude,
									pos.longitude);

							JOptionPane.showMessageDialog(AppFrame.this,
									"altitude=" + pos.getAltitude()
											+ "\n Latitude(degree)=" + pos.latitude.degrees
											+ "\n Longitude(degree)=" + pos.longitude.degrees,
									"Information",
									JOptionPane.INFORMATION_MESSAGE);

							panel.getLattitude().setText("" + pos.latitude.degrees);
							panel.getLongitude().setText("" + pos.longitude.degrees);
							System.out.println(pos.getLatitude().degrees);

						}

						public void mouseEntered(MouseEvent mouseEvent) {
						}

						public void mouseExited(MouseEvent mouseEvent) {
						}

						public void mousePressed(MouseEvent mouseEvent) {
						}

						public void mouseReleased(MouseEvent mouseEvent) {
						}
					});

		}
	}

	public static void main(String[] args) {
		// zoom to Tunis
		// Configuration.setValue(AVKey.INITIAL_ALTITUDE, 21d);
		// Configuration.setValue(AVKey.INITIAL_LATITUDE, 36.9735d);
		// Configuration.setValue(AVKey.INITIAL_LONGITUDE, 10.1982d);
		//
		ApplicationTemplate.start("Exemple Map ", AppFrame.class);
	}
}
