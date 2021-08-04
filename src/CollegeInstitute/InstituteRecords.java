package CollegeInstitute;

import XgileItJavaAssignments.CourseMapInterface;

import java.util.*;
import java.util.Map.Entry;
public class InstituteRecords implements CourseMapInterface{

        // set for student
        Set<StudentDetails> studentList = new HashSet<>();
        // set for course
        Set<CourseInfo> courselist = new HashSet<>();
        // Map to configure student with course
        HashMap<StudentDetails, CourseInfo> studentCourseMap = new HashMap<StudentDetails, CourseInfo>();

        @Override
        public Set<StudentDetails> searchAllStudents() {
            return studentList;
        }

        @Override
        public void putNewStudent(StudentDetails studentObj) {
            studentList.add(studentObj);
        }

        @Override
        public Set<CollegeInstitute.CourseInfo> searchAllCourse() {
            return courselist;
        }

        @Override
        public void putNewCourse(CourseInfo courseInfo) {
            courselist.add(courseInfo);
        }

        @Override
        public void registerCourseForStudent(StudentDetails studDetails, CourseInfo courseInfo) {
            studentCourseMap.put(studDetails,courseInfo);
        }

    @Override
    public HashMap<StudentDetails, CourseInfo> searchAllRegistration() {
        return studentCourseMap;
    }

    @Override
    public List<StudentDetails> CourseInfo(int CourseId) {
        List<StudentDetails> studentList = new ArrayList<StudentDetails>();
        for(Entry<StudentDetails, CourseInfo> studMap : studentCourseMap.entrySet()) {
            if(studMap.getValue().courseId == CourseId) {
                studentList.add(studMap.getKey());
            }
        }
        return studentList;
    }

        @Override
        public CourseInfo StudentDetails(int studentId) {
            CourseInfo courseInfo = null;
            for(Entry<StudentDetails, CourseInfo> studMap : studentCourseMap.entrySet()) {
                if(studMap.getKey().studentId == studentId) {
                    courseInfo = new CourseInfo(studMap.getValue().getCourseId(), studMap.getValue().getCourseName(), studMap.getValue().getCourseDuration());
                }
            }
            return courseInfo;
        }
    }
