package hellojpa;

import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            // 비영속
            Member member1 = em.find(Member.class, 150L);
            member1.setName("John Doe");
//            em.persist(member1);
            // 영속

            tx.commit(); // 여기서 Transaction을 commit한 순간에 위의 내용에 대한 SQL쿼리문을 DB에 전송
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
