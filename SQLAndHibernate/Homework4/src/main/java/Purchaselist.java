import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name ="purchaselist")
public class Purchaselist {

    @EmbeddedId
    private KeyPurchaselist id;

    @Column(name = "student_name", insertable = false, updatable = false)
    private String studentName;

    @Column(name = "course_name", insertable = false, updatable = false)
    private String courseName;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public Purchaselist() {

    }
}



