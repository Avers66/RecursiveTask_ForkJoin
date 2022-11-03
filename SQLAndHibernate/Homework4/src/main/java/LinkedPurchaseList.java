import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;




@Entity
@Table(name = "linkedpurchaselist")
public class LinkedPurchaseList {


    @EmbeddedId
    private KeyLPL id;

    @Column(name = "student_id", insertable = false, updatable = false)
    private int studentId;

    @Column(name = "course_id", insertable = false, updatable = false)
    private int courseId;

    public LinkedPurchaseList(KeyLPL id) {
        this.id = id;
        studentId = id.getStudentId();
        courseId = id.getCourseId();
    }

    public KeyLPL getId() {
        return id;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }
}
