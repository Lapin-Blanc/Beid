package be.toune.fabien.beid.model;

import java.util.Map;

public class Address {

	private byte[] fileStructureVersion;
	private String StreetAndNumber, zipCode, municipality;
	
	public Address(Map<Byte, byte[]> results) {
		fileStructureVersion = results.get((byte)0x00);
		StreetAndNumber = new String(results.get((byte)0x01));
		zipCode = new String(results.get((byte)0x02));
		municipality = new String(results.get((byte)0x03));
	}
	
	public String toString(){
		String result = "";
//		result += "fileStructureVersion: " + new String(fileStructureVersion) + "\r\n";
		result += "Street and number: " + StreetAndNumber + "\r\n";
		result += "Zip Code: " + zipCode + "\r\n";
		result += "Municipality: " + municipality + "\r\n";
		return result;
	}

	public byte[] getFileStructureVersion() {
		return fileStructureVersion;
	}

	public void setFileStructureVersion(byte[] fileStructureVersion) {
		this.fileStructureVersion = fileStructureVersion;
	}

	public String getStreetAndNumber() {
		return StreetAndNumber;
	}

	public void setStreetAndNumber(String streetAndNumber) {
		StreetAndNumber = streetAndNumber;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
}
