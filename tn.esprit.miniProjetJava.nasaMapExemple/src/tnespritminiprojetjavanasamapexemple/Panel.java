package tnespritminiprojetjavanasamapexemple;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.swing.JTextPane;

import java.awt.Font;

public class Panel extends JPanel {
	private JTextField adresse;
	private JTextField lattitude;
	private JTextField longitude;
	private JTextPane description;
	private JButton btnPlacemark;
	private JTextField nameMark;
	static  BufferedImage image;
	JLabel icone;
    String[] result ;
	public JTextField getAdresse() {
		return adresse;
	}


	public void setAdresse(JTextField adresse) {
		this.adresse = adresse;
	}


	public JTextField getLattitude() {
		return lattitude;
	}


	public void setLattitude(JTextField lattitude) {
		this.lattitude = lattitude;
	}


	public JTextField getLongitude() {
		return longitude;
	}


	public void setLongitude(JTextField longitude) {
		this.longitude = longitude;
	}


	public JTextPane getDescription() {
		return description;
	}


	public void setDescription(JTextPane description) {
		this.description = description;
	}


	public JButton getBtnPlacemark() {
		return btnPlacemark;
	}


	public void setBtnPlacemark(JButton btnPlacemark) {
		this.btnPlacemark = btnPlacemark;
	}


	public JTextField getNameMark() {
		return nameMark;
	}


	public void setNameMark(JTextField nameMark) {
		this.nameMark = nameMark;
	}


	/**
	 * Create the panel.
	 */
	public Panel() {
		
		setBorder(new TitledBorder(null, "Tag", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblTest = new JLabel("Adresse");
		
		adresse = new JTextField();
		adresse.setColumns(10);
		
		JButton btnNewButton = new JButton("Position via l'adresse");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String code =adresse.getText();
try{
			        String response = GoogleGeoCode.getLocation(code);

			        result = GoogleGeoCode.parseLocation(response);
lattitude.setText(result[0]);
longitude.setText(result[1]);
			        System.out.println("Latitude: " + result[0]);
			        System.out.println("Longitude: " + result[1]);
			}catch(Exception exception){
				JOptionPane.showMessageDialog(Panel.this,
						"Impossible d'�tablir la connexion avec le serveur ",
						"Message",
						JOptionPane.ERROR_MESSAGE);
			}
			}
		});
		
		JLabel lblNewLabel = new JLabel("Latitude");
		
		JLabel lblLongitude = new JLabel("Longitude");
		
		lattitude = new JTextField();
		lattitude.setColumns(10);
		
		longitude = new JTextField();
		longitude.setColumns(10);
		
		 btnPlacemark = new JButton("Placemark");
		
		
		JLabel lblName = new JLabel("Name");
		
		nameMark = new JTextField();
		nameMark.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description");
		
		 description = new JTextPane();
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		
		JButton btnRecuprationIcone = new JButton("recup\u00E9ration icone");
		btnRecuprationIcone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ur="http://maps.googleapis.com/maps/api/staticmap?center="+Double.parseDouble(lattitude.getText())+","+Double.parseDouble(longitude.getText())+"&zoom=14&size=150x150" +
						"&markers=color:blue%7Clabel:S%7C"+Double.parseDouble(lattitude.getText())+","+Double.parseDouble(longitude.getText())+
				        "&sensor=false";

				        try {
				            image = ImageIO.read(new URL(ur));
				        } catch (IOException ex) {
				        	
				        	JOptionPane.showMessageDialog(Panel.this,
									"Impossible de lire l'image ",
									"Message",
									JOptionPane.ERROR_MESSAGE);
				        }
				        
				icone.setIcon(new ImageIcon(image));

			}
		});
		
		icone = new JLabel("");
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBorder(new TitledBorder(null, "R\u00E9cup\u00E9ration de la position", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addGap(10)
								.addComponent(lblTest)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(adresse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addComponent(panel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 184, Short.MAX_VALUE)
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addGap(60)
								.addComponent(icone))
							.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(btnNewButton))
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel)
								.addGap(18)
								.addComponent(lattitude, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(8)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblLongitude)
										.addComponent(lblName))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(nameMark, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(longitude, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblDescription)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(description, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnPlacemark)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnReset))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(10)
									.addComponent(btnRecuprationIcone)))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(adresse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTest))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lattitude, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(longitude, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLongitude))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(nameMark, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDescription)
						.addComponent(description, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPlacemark)
						.addComponent(btnReset))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnRecuprationIcone)
					.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
					.addComponent(icone)
					.addGap(64))
		);
		
		JLabel label = new JLabel("SHIFT+clic\r\n");
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblSurLaMap = new JLabel("Sur la map");
		lblSurLaMap.setForeground(Color.BLACK);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblSurLaMap)
					.addGap(175))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(lblSurLaMap))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

	}
	public void clear(){
		adresse.setText("");
		description.setText("");
		lattitude.setText("");
		longitude.setText("");
		nameMark.setText("");
	}
}
