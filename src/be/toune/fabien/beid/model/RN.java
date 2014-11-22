package be.toune.fabien.beid.model;

import java.util.Map;

public class RN {

	private byte[] fileStructureVersion, chipNumber, hashPhoto;
	
	private String cardNumber, validityBeginDate, validityEndDate, 
		deliveryMunicipality, nationalNumber, name, firstname, 
		thirdFirstnameInitial, nationality, birthLocation, birthDate, sex, 
		nobleCondition, documentType, specialStatus;
	
	public RN(Map<Byte, byte[]> results) {
		fileStructureVersion = results.get((byte)0x00);
		cardNumber = new String(results.get((byte)0x01));
		chipNumber = results.get((byte)0x02);
		validityBeginDate = new String(results.get((byte)0x03));
		validityEndDate = new String(results.get((byte)0x04));
		deliveryMunicipality = new String(results.get((byte)0x05));
		nationalNumber = new String(results.get((byte)0x06));
		name = new String(results.get((byte)0x07));
		firstname = new String(results.get((byte)0x08));
		thirdFirstnameInitial = new String(results.get((byte)0x09));
		nationality = new String(results.get((byte)0x0A));
		birthLocation = new String(results.get((byte)0x0B));
		birthDate = new String(results.get((byte)0x0C));
		sex = new String(results.get((byte)0x0D));
		nobleCondition = new String(results.get((byte)0x0E));
		documentType = new String(results.get((byte)0x0F));
		specialStatus = new String(results.get((byte)0x10));
		hashPhoto = results.get((byte)0x11);
	}
	
	public String toString(){
		String result = "";
//		result += "File Structure Version: " + fileStructureVersion + "\n";
		result += "Card Number: " + cardNumber + "\r\n";
//		result += "Chip Number: " + chipNumber + "\r\n";
		result += "Validity Begin Date: " + validityBeginDate + "\r\n";
		result += "Validity End Date: " + validityEndDate + "\r\n";
		result += "Delivery Municipality: " + deliveryMunicipality + "\r\n";
		result += "National Number :" + nationalNumber + "\r\n";
		result += "Name: " + name + "\r\n";
		result += "Firstname: " + firstname + "\r\n";
		result += "Third Firstname Initial: " + thirdFirstnameInitial + "\r\n";
		result += "Nationality: " + nationality + "\r\n";
		result += "BirthLocation: " + birthLocation + "\r\n";
		result += "BirthDate: " + birthDate + "\r\n";
		result += "Sex: " + sex + "\r\n";
		result += "Noble Condition: " + nobleCondition + "\r\n";
		result += "Document Type: " + documentType + "\r\n";
//		result += "Hash Photo: " + hashPhoto + "\r\n";
		return result;
	}

	public byte[] getFileStructureVersion() {
		return fileStructureVersion;
	}

	public void setFileStructureVersion(byte[] fileStructureVersion) {
		this.fileStructureVersion = fileStructureVersion;
	}

	public byte[] getChipNumber() {
		return chipNumber;
	}

	public void setChipNumber(byte[] chipNumber) {
		this.chipNumber = chipNumber;
	}

	public byte[] getHashPhoto() {
		return hashPhoto;
	}

	public void setHashPhoto(byte[] hashPhoto) {
		this.hashPhoto = hashPhoto;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getValidityBeginDate() {
		return validityBeginDate;
	}

	public void setValidityBeginDate(String validityBeginDate) {
		this.validityBeginDate = validityBeginDate;
	}

	public String getValidityEndDate() {
		return validityEndDate;
	}

	public void setValidityEndDate(String validityEndDate) {
		this.validityEndDate = validityEndDate;
	}

	public String getDeliveryMunicipality() {
		return deliveryMunicipality;
	}

	public void setDeliveryMunicipality(String deliveryMunicipality) {
		this.deliveryMunicipality = deliveryMunicipality;
	}

	public String getNationalNumber() {
		return nationalNumber;
	}

	public void setNationalNumber(String nationalNumber) {
		this.nationalNumber = nationalNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getThirdFirstnameInitial() {
		return thirdFirstnameInitial;
	}

	public void setThirdFirstnameInitial(String thirdFirstnameInitial) {
		this.thirdFirstnameInitial = thirdFirstnameInitial;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getBirthLocation() {
		return birthLocation;
	}

	public void setBirthLocation(String birthLocation) {
		this.birthLocation = birthLocation;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNobleCondition() {
		return nobleCondition;
	}

	public void setNobleCondition(String nobleCondition) {
		this.nobleCondition = nobleCondition;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getSpecialStatus() {
		return specialStatus;
	}

	public void setSpecialStatus(String specialStatus) {
		this.specialStatus = specialStatus;
	}
}
