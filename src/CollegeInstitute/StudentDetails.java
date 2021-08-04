package CollegeInstitute;

import java.io.Serializable;

public class StudentDetails implements Serializable {

    private static final long serialVersionUID = 25469872314567L;

    int studentId;
    String firstName;
    String lastName;
    String email;
    String gender;
    int age;
    int mobileNumber;
    // Using Constructor
    public StudentDetails(int studentId, String firstName, String lastName, String email, String gender, int age, int mobileNumber)
    {
        super();
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.mobileNumber = mobileNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMobileNum() {
        return mobileNumber;
    }

    public void setMobileNum(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    @Override
    public int hashCode() {
        return studentId;
    }

    @Override
    public boolean equals(Object obj) {
        StudentDetails studentInformation = (StudentDetails) obj;
        return (studentId == studentInformation.studentId);
    }

    @Override
    public String toString() {
        return "StudentDetails [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName
                + ", age=" + age + ", mobileNum=" + mobileNumber + ", email=" + email + ", gender=" + gender + "]";
    }
}
