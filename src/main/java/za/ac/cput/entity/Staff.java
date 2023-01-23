package za.ac.cput.entity;

import javax.persistence.Id;

/*Staff.java
  Entity for staff class
  Author: Phindiwe Bambata (217180833)
  Date: 08/06/2021
 */
import javax.persistence.Entity;

import java.io.Serializable;

@Entity
public class Staff implements Serializable {
    @Id
    private String staffId, firstName, lastName;

    private Staff(Builder builder){
        this.staffId = builder.staffId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;

    }


    @Override
    public String toString() {
        return "Staff{" +
                "staffId='" + staffId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName +
                '}';
    }

    public String getStaffId() {
        return staffId;
    }



    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public static class Builder{


        private String staffId,  firstName, lastName;
        public Builder setStaffId(String staffId) {
            this.staffId = staffId;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Staff build(){
            return new Staff(this);
        }

        public Builder copy(Staff staff){
            this.staffId = staff.staffId;

            this.firstName = staff.firstName;
            this.lastName = staff.lastName;

            return this;

        }

    }

}
