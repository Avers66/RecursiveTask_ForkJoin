
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();
        String sql = "SELECT p.student_name, s.id, p.course_name, c.id\n" +
                "FROM purchaselist AS p\n" +
                "JOIN students AS s ON s.name = p.student_name\n" +
                "JOIN courses AS c ON c.name = p.course_name";

        List<Object[]> persons = session.createNativeQuery(sql).list();

        for(Object[] person : persons) {
            String studentName = (String) person[0];
            Integer studentId = (Integer) person[1];
            String courseName = (String) person[2];
            Integer courseId = (Integer) person[3];

            LinkedPurchaseList lPL = new LinkedPurchaseList(new KeyLPL(studentId, courseId));
//            System.out.println(studentName + " " + studentId + " " + courseName + " " + courseId);
//            session.save(lPL);
            session.persist(lPL);
        }
        transaction.commit();
        sessionFactory.close();

    }


}