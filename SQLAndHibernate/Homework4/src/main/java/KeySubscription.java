import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class KeySubscription implements Serializable {

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "course_id")
    private int courseId;

    public KeySubscription() {
    }

    public KeySubscription(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KeySubscription key)) return false;
        return getStudentId() == key.getStudentId() && getCourseId() == key.getCourseId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentId(), getCourseId());
    }



}