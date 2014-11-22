package be.toune.fabien.beid.util;

//import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.util.List;

import javax.smartcardio.Card;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.TerminalFactory;
import javax.swing.JApplet;
//import javax.swing.JButton;
import javax.swing.JLabel;
import java.security.*;

import be.toune.fabien.beid.model.EID;

public class BeidApplet extends JApplet {

	private static final long serialVersionUID = 2203390337720317227L;

	private JLabel label = new JLabel("Beid Java Applet");
//	private JButton button = new JButton("Lire carte");

	private EID beid;

	public void init() {
		this.setSize(50, 15);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.blue);
		this.getContentPane().add(label);

/*		button.setSize(200, 15);
		button.addActionListener(new ActionListener() {	 
            public void actionPerformed(ActionEvent e)
            {
                System.out.println(beid.getPictureBase64());
            }
        });  
		this.getContentPane().add(button, BorderLayout.SOUTH);
		updateCard(); */
	}

	private void updateCard(){
    	try
        {
    		label.setText("Loading...");
    		// show the list of available terminals
            TerminalFactory factory = TerminalFactory.getDefault();
            List<CardTerminal> terminals = factory.terminals().list();
            
            // get the first terminal
            CardTerminal terminal = terminals.get(0);
            
            // establish a connection with the card
            Card card = terminal.connect("T=0");
            
            this.beid = new EID(card);
    		this.label.setText(this.getNationalNumber());

        } catch (Exception ex)
        {
			ex.printStackTrace();
			label.setText(ex.toString());
		}	
	}

	/**
	 * Méthode utilisée par Javascript pour mettre à jour 
     * Celle-ci doit être public afin que Javascript puisse y avoir accès
	 */
	public void updateCardFromJS(){
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                updateCard();
                return null;
            }
        });
	}

	// National register informations
	public String getCardNumber(){
		return CharsetConverter.toUTF8(this.beid.getData().getCardNumber());
	}
	
	public String getValidityBeginDate() {
		return CharsetConverter.toUTF8(this.beid.getData().getValidityBeginDate());
	}

	public String getValidityEndDate() {
		return CharsetConverter.toUTF8(this.beid.getData().getValidityEndDate());
	}

	public String getDeliveryMunicipality() {
		return CharsetConverter.toUTF8(this.beid.getData().getDeliveryMunicipality());
	}

	public String getNationalNumber() {
		return CharsetConverter.toUTF8(this.beid.getData().getNationalNumber());
	}

	public String getName() {
		return CharsetConverter.toUTF8(this.beid.getData().getName());
	}

	public String getFirstname() {
		return CharsetConverter.toUTF8(this.beid.getData().getFirstname());
	}

	public String getThirdFirstnameInitial() {
		return CharsetConverter.toUTF8(this.beid.getData().getThirdFirstnameInitial());
	}

	public String getNationality() {
		return CharsetConverter.toUTF8(this.beid.getData().getNationality());
	}

	public String getBirthLocation() {
		return CharsetConverter.toUTF8(this.beid.getData().getBirthLocation());
	}

	public String getBirthDate() {
		return CharsetConverter.toUTF8(this.beid.getData().getBirthDate());
	}

	public String getSex() {
		return CharsetConverter.toUTF8(this.beid.getData().getSex());
	}

	public String getNobleCondition() {
		return CharsetConverter.toUTF8(this.beid.getData().getNobleCondition());
	}

	public String getDocumentType() {
		return CharsetConverter.toUTF8(this.beid.getData().getDocumentType());
	}

	public String getSpecialStatus() {
		return CharsetConverter.toUTF8(this.beid.getData().getSpecialStatus());
	}

	// Address informations
	public String getStreetAndNumber() {
		return CharsetConverter.toUTF8(this.beid.getAddress().getStreetAndNumber());
	}

	public String getZipCode() {
		return CharsetConverter.toUTF8(this.beid.getAddress().getZipCode());
	}

	public String getMunicipality() {
		return CharsetConverter.toUTF8(this.beid.getAddress().getMunicipality());
	}
	
	public byte[] getPicture() {
		return this.beid.getPicture();
	}

	public String getPictureBase64() {
		return this.beid.getPictureBase64();
	}

}
