package com.skilldistillery.RainbowRoadtripPlanner.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ActivityRatingId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="activity_id")
	private int activityId;
	
	

	public ActivityRatingId(int userId, int activityId) {
		super();
		this.userId = userId;
		this.activityId = activityId;
	}

	public ActivityRatingId() {
		super();
		
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(activityId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActivityRatingId other = (ActivityRatingId) obj;
		return activityId == other.activityId && userId == other.userId;
	}

	@Override
	public String toString() {
		return "ActivityRatingId [userId=" + userId + ", activityId=" + activityId + "]";
	}
	
	

}
