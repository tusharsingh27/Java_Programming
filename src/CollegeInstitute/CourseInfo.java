package CollegeInstitute;

import java.io.Serializable;

    public class CourseInfo implements Serializable {

        private static final long serialVersionUID = 1L;
        int courseId;  // should be unique
        String courseName;
        int courseDuration;

        public CourseInfo(int courseId, String courseName, int courseDuration)
        {
            this.courseId = courseId;
            this.courseName = courseName;
            this.courseDuration = courseDuration;
        }

        public int getCourseId() {
            return courseId;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public int getCourseDuration() {
            return courseDuration;
        }

        public void setCourseDuration(int courseDuration) {
            this.courseDuration = courseDuration;
        }

        @Override
        public int hashCode() {

            return courseId;
        }

        @Override
        public boolean equals(Object obj) {
            CourseInfo courseInfo = (CourseInfo) obj;
            return (courseId == courseInfo.courseId);
        }

        @Override
        public String toString() {
            return "CourseInfo [courseId=" + courseId + ", courseName=" + courseName + ", courseDuration="
                    + courseDuration+"]";
        }
}
