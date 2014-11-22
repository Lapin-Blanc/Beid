package be.toune.fabien.beid.model;

import java.util.ArrayList;
import java.util.List;

import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;

import be.toune.fabien.beid.model.Address;
import be.toune.fabien.beid.model.RN;
import be.toune.fabien.beid.util.ResponseAPDUVerifier;
import be.toune.fabien.beid.util.TLVParser;

import be.toune.fabien.beid.util.Base64;
import be.toune.fabien.beid.util.IO;
import java.io.IOException;


public class EID {
    static byte[] ID 		= new byte[] {0x3F, 0x00, (byte) 0xDF, 0x01, 0x40, 0x31};
    static byte[] ADDRESS	= new byte[] {0x3F, 0x00, (byte) 0xDF, 0x01, 0x40, 0x33};
    static byte[] PICTURE 	= new byte[] {0x3F, 0x00, (byte) 0xDF, 0x01, 0x40, 0x35};
    
    static int BUFFSIZE = 256;
    
    private CardChannel channel;
    private RN data;
    private Address address;
    private byte[] picture;
    
    public EID(Card card) throws CardException {
    	channel = card.getBasicChannel();
    	data = new RN(TLVParser.parse(this.readFile(ID)));
    	address = new Address(TLVParser.parse(this.readFile(ADDRESS)));
/*    	picture = readFile(PICTURE); trying to call this one only on demand to save time...*/
	}
    
    public String toString(){
    	return this.data.toString() + this.address.toString();
    }
      
    /**
     * Read the card content in BUFFSIZE bytes chunks then assemble them.
     * 
     * @param file: Address of the data to read
     * @return datas in byte array
     * @throws CardException
     */
	private byte[] readFile(byte[] file) throws CardException {
		int offset = 0;
		byte[] chunk;
		List<byte[]> chunks = new ArrayList<byte[]>();
		do {
			chunk = readFile(file, offset);
			chunks.add(chunk);
			offset++;
		} while (chunk.length == BUFFSIZE);
		
		int lastChunkSize = chunks.get(chunks.size()-1).length;
		int totalsize = (BUFFSIZE*(chunks.size()-1))+lastChunkSize;
		byte[] result = new byte[totalsize];
		for (int i = 0; i < chunks.size(); i++) {
			System.arraycopy(chunks.get(i), 0, result, i*BUFFSIZE, chunks.get(i).length);
		}

		return result;
	}
    
	/**
	 * Read BUFFSIZE bytes at file+offset and return result in byte array.
	 * @param file: address of the file to read
	 * @param offset: reading starting point
	 * @return data in byte array
	 * @throws CardException
	 */
    private byte[] readFile(byte[] file, int offset) throws CardException{
        ResponseAPDU res;
        CommandAPDU cmd;
        
        // SELECT FILE
		cmd = new CommandAPDU((byte) 0x00, (byte) 0xA4, (byte) 0x08, (byte) 0x0C, file);
		res = channel.transmit(cmd);
		ResponseAPDUVerifier.VerifySW(res);
		
		/** READ DATA
			! using 0x00 as response Expected Length (le) is not converted to 
			0x00 as states the convention. However, asking to read more than 
			available return the whole content without error. */
		cmd = new CommandAPDU((byte) 0x00, (byte) 0xB0, (byte) offset, (byte) 0x00, BUFFSIZE);
		res = channel.transmit(cmd);
		ResponseAPDUVerifier.VerifySW(res);
		
		return res.getData();
    }

	public RN getData() {
		return data;
	}

	public void setData(RN data) {
		this.data = data;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public byte[] getPicture() {
		try {
			return readFile(PICTURE);
		} catch (CardException e) {
			e.printStackTrace();
			return picture;
		}
	}
	
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getPictureBase64() {
		try {
			return Base64.encodeBytes(readFile(PICTURE));
		} catch (CardException e) {
			e.printStackTrace();
			return "";
		}		
	}

	public void savePicture(String path) throws IOException{
    	IO.save(path, getPicture());
    }

}
