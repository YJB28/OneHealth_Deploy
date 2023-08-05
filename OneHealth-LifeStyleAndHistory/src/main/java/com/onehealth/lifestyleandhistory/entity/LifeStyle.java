package com.onehealth.lifestyleandhistory.entity;

import jakarta.persistence.*;

@Entity
//@Table(name = "lifestyle")
public class LifeStyle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lId")
	private Long lId;

	@Column(name = "smoke", length = 255, nullable = false)
	private String smoke;

	@Column(name = "alcohol")
	private String alcohol;

	@Column(name = "exercise")
	private String exercise;

	@Column(name = "food_preferences")
	private String foodPreferences;

	@Column(name = "occupation")
	private String occupation;

	@Column(name = "patient_id", nullable = false)
	private Long patientId;

	@Column(name = "user_Id", nullable = false)
	private Long userId;

	// Constructors, getters, setters, and other methods as needed.

	public LifeStyle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LifeStyle(Long lId, String smoke, String alcohol, String exercise, String foodPreferences, String occupation,
			Long patientId, Long userId) {
		super();
		this.lId = lId;
		this.smoke = smoke;
		this.alcohol = alcohol;
		this.exercise = exercise;
		this.foodPreferences = foodPreferences;
		this.occupation = occupation;
		this.patientId = patientId;
		this.userId = userId;
	}

	public Long getlId() {
		return lId;
	}

	public void setlId(Long lId) {
		this.lId = lId;
	}

	public String getSmoke() {
		return smoke;
	}

	public void setSmoke(String smoke) {
		this.smoke = smoke;
	}

	public String getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(String alcohol) {
		this.alcohol = alcohol;
	}

	public String getExercise() {
		return exercise;
	}

	public void setExercise(String exercise) {
		this.exercise = exercise;
	}

	public String getFoodPreferences() {
		return foodPreferences;
	}

	public void setFoodPreferences(String foodPreferences) {
		this.foodPreferences = foodPreferences;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
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

	@Override
	public String toString() {
		return "LifeStyle [lId=" + lId + ", smoke=" + smoke + ", alcohol=" + alcohol + ", exercise=" + exercise
				+ ", foodPreferences=" + foodPreferences + ", occupation=" + occupation + ", patientId=" + patientId
				+ ", userId=" + userId + "]";
	}

}
