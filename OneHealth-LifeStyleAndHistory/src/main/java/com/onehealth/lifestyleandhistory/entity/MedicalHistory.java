package com.onehealth.lifestyleandhistory.entity;


import jakarta.persistence.*;

@Entity
//@Table(name = "pastmedicalrecords")
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recordId;

    @Column(name = "allergies")
    private String allergies;

    @Column(name = "currentmedication")
    private String currentMedication;

    @Column(name = "pastmedication")
    private String pastMedication;

    @Column(name = "chronicdiseases")
    private String chronicDiseases;

    @Column(name = "injuries")
    private String injuries;

    @Column(name = "surgeries")
    private String surgeries;

    @Column(name = "patientid", nullable = false)
    private Long patientId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

	public MedicalHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "MedicalHistory [recordId=" + recordId + ", allergies=" + allergies + ", currentMedication="
				+ currentMedication + ", pastMedication=" + pastMedication + ", chronicDiseases=" + chronicDiseases
				+ ", injuries=" + injuries + ", surgeries=" + surgeries + ", patientId=" + patientId + ", userId="
				+ userId + "]";
	}

	public MedicalHistory(Integer recordId, String allergies, String currentMedication, String pastMedication,
			String chronicDiseases, String injuries, String surgeries, Long patientId, Long userId) {
		super();
		this.recordId = recordId;
		this.allergies = allergies;
		this.currentMedication = currentMedication;
		this.pastMedication = pastMedication;
		this.chronicDiseases = chronicDiseases;
		this.injuries = injuries;
		this.surgeries = surgeries;
		this.patientId = patientId;
		this.userId = userId;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getCurrentMedication() {
		return currentMedication;
	}

	public void setCurrentMedication(String currentMedication) {
		this.currentMedication = currentMedication;
	}

	public String getPastMedication() {
		return pastMedication;
	}

	public void setPastMedication(String pastMedication) {
		this.pastMedication = pastMedication;
	}

	public String getChronicDiseases() {
		return chronicDiseases;
	}

	public void setChronicDiseases(String chronicDiseases) {
		this.chronicDiseases = chronicDiseases;
	}

	public String getInjuries() {
		return injuries;
	}

	public void setInjuries(String injuries) {
		this.injuries = injuries;
	}

	public String getSurgeries() {
		return surgeries;
	}

	public void setSurgeries(String surgeries) {
		this.surgeries = surgeries;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

    // Constructors, getters, setters, and other methods as needed.
    
    
}
