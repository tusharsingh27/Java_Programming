package XgileItJavaAssignments;

import CollegeInstitute.CourseInfo;
import CollegeInstitute.StudentDetails;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

    public interface CourseMapInterface {

        Set<StudentDetails> searchAllStudents();//search all student
        void putNewStudent(StudentDetails student);//put new student

        Set<CourseInfo> searchAllCourse();//search all course details
        void putNewCourse(CourseInfo courseInfo);

        void registerCourseForStudent(StudentDetails studDetails,CourseInfo courseDetails);
        HashMap<StudentDetails, CourseInfo> searchAllRegistration();//search all RegistrationDetails

        List<StudentDetails> CourseInfo (int CourseId);//enter CourseId get details of all student studying
        CourseInfo StudentDetails (int studentId);//enter StudentId and get details of StudentCourseDetails
    }
