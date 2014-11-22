package be.toune.fabien.beid.util;

import javax.smartcardio.CardException;
import javax.smartcardio.ResponseAPDU;

public class ResponseAPDUVerifier {

	public static void VerifySW(ResponseAPDU res) throws CardException{
		if (res.getSW1() != 0x90 && res.getSW2() != 0x00) 
			throw new CardException("Transmittion Error: " + res.getSW1() + " " + res.getSW2());
	}

}
