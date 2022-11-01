import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Subscriptions")
public class Subscription {

    @EmbeddedId
    private KeySubscription id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public Subscription() {
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public Student getStudent() {
        return student;
    }
}