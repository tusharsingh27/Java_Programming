package CollegeInstitute;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Iterator;

public class CollegeStudentInformation {

        public static  void main(String[] args) throws Exception {

            FileWriter fileWriter = new FileWriter("C:\\Users\\asus\\IdeaProjects\\Hello\\src\\CollegeInstitute\\CollegeStudentInformation.txt");
            BufferedWriter bufferWriter= new BufferedWriter(fileWriter);
            InstituteRecords instituteRecords = new InstituteRecords();

            StudentDetails s1= new StudentDetails(1,"Ram","Shukla", "ramshukla@gmail.com", "Male",24, 956471256);
            instituteRecords.putNewStudent(s1);
            StudentDetails s2= new StudentDetails(2,"Shyam","Sharma", "shyamsharma@gmail.com", "Male", 21, 725463216);
            instituteRecords.putNewStudent(s2);
            StudentDetails s3= new StudentDetails(3,"Anita","singh", "anitasingh@gmail.com", "Female", 19, 985462712);
            instituteRecords.putNewStudent(s3);
            StudentDetails s4= new StudentDetails(4,"Ankit","singh", "ankitsingh@gmail.com", "Male",25 , 754629872);
            instituteRecords.putNewStudent(s4);
            StudentDetails s5= new StudentDetails(5,"Deepika","Gupta", "deepikagupta@gmail.com", "Female", 22, 852469712);
            instituteRecords.putNewStudent(s5);
            StudentDetails s6= new StudentDetails(6,"Kuldeep","Tripathi", "kuldeeptripathi@gmail.com", "Male", 25, 968541237);
            instituteRecords.putNewStudent(s6);

            CourseInfo c1 = new CourseInfo(7,"Bachelor in Science:" ,3);
            instituteRecords.putNewCourse(c1);
            CourseInfo  c2 = new CourseInfo (8,"Bachelor of Technology:" ,4);
            instituteRecords.putNewCourse(c2);
            CourseInfo  c3 = new CourseInfo (9,"Bachelor of Technology:" ,4);
            instituteRecords.putNewCourse(c3);
            CourseInfo  c4 = new CourseInfo (10,"Bachelor in Commerce:" ,3);
            instituteRecords.putNewCourse(c4);
            CourseInfo  c5 = new CourseInfo (11,"Bachelor in Arts:" ,3);
            instituteRecords.putNewCourse(c5);
            CourseInfo  c6 = new CourseInfo (12,"Bachelor in Computer Science:" ,3);
            instituteRecords.putNewCourse(c6);

            instituteRecords.registerCourseForStudent(s1,c2);
            instituteRecords.registerCourseForStudent(s2,c1);
            instituteRecords.registerCourseForStudent(s3,c4);
            instituteRecords.registerCourseForStudent(s4,c5);
            instituteRecords.registerCourseForStudent(s5,c2);
            instituteRecords.registerCourseForStudent(s6,c3);

            //set type so to print and return it use iterator
            bufferWriter.write("Display all Student Details:");
            Iterator  it = instituteRecords.searchAllStudents().iterator();
            while(it.hasNext()) {
                bufferWriter.write(it.next().toString());
                bufferWriter.newLine();
            }
            //set type so to print and return it use iterator
            bufferWriter.write("\n"+"Display all Course Details:");
            Iterator  it1 = instituteRecords.searchAllCourse().iterator();
            while(it1.hasNext()) {
                bufferWriter.write(it1.next().toString());
                bufferWriter.newLine();
            }
            //map type to print and return it use bw.write method with toString
            bufferWriter.write("\n"+"Display all Registration Details:" );
            bufferWriter.write(instituteRecords.searchAllRegistration().toString());
            //list type to print and return it we use iterator
            bufferWriter.write("\n"+"Display id 7 Course Information:" );
            Iterator<?> it7 = instituteRecords.CourseInfo(7).iterator();
            while(it7.hasNext()) {
                bufferWriter.write(it7.next().toString());
                bufferWriter.newLine();
            }
            bufferWriter.write("\n"+"Display id 8 Course Information:" );
            Iterator<?> it8 = instituteRecords.CourseInfo(8).iterator();
            while(it8.hasNext()) {
                bufferWriter.write(it8.next().toString());
                bufferWriter.newLine();
            }
            //integer type  to print and return it  use bw.write method with toString
            bufferWriter.write("\n"+"Display id 1 & 5 Student Details:");
            bufferWriter.write( instituteRecords.StudentDetails(1).toString());
            bufferWriter.write(instituteRecords.StudentDetails(5).toString());
            bufferWriter.flush();
            bufferWriter.close();
        }
    }