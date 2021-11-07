package at.fhv.se.hotel.managementSoftware.application.dto;

import java.util.Objects;

public class RoomCategoryDTO {
	private String categoryID;
	private String name;
	private String description;
	private int bedNumber;
	
	public static Builder builder() {
		return new Builder();
	}

	public String getCategoryID() {
		return categoryID;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getBedNumber() {
		return bedNumber;
	}
	
	private RoomCategoryDTO() {
	}
	
	public static class Builder{
		private RoomCategoryDTO instance;

        private Builder() {
            this.instance = new RoomCategoryDTO();
        }

        public Builder withId(String id) {
            this.instance.categoryID = id;
            return this;
        }

        public Builder withName(String name) {
            this.instance.name = name;
            return this;
        } 
        
        public Builder withDescription(String desc) {
            this.instance.description = desc;
            return this;
        }
        
        public Builder withBedNumber(int number) {
            this.instance.bedNumber = number;
            return this;
        }


        public RoomCategoryDTO build() {
            Objects.requireNonNull(this.instance.categoryID, "categoryID must be set in RoomCategoryDTO");
            Objects.requireNonNull(this.instance.name, "name must be set in RoomCategoryDTO");
            Objects.requireNonNull(this.instance.bedNumber, "bedNumber must be set in RoomCategoryDTO");
            return this.instance;
        }
	}
	
}
