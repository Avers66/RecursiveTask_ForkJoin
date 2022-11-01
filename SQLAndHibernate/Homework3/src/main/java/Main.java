import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
                Course course = session.get(Course.class, 1);
        System.out.println("Курс : " + course.getName());
        System.out.println("Количество студентов : " + course.getStudentCount());
        System.out.println("Учитель: " + course.getTeacher().getName());
        course.getSubscriptions().forEach(s -> System.out.println("Подписки: " + s.getSubscriptionDate()
                                                                + " Студенты: " + s.getStudent().getName()));
        course.getStudents().forEach(s -> System.out.println("Студенты: " + s.getName()));
        transaction.commit();
        sessionFactory.close();

    }
}