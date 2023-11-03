package jpajava;

import domain.Employee;

import javax.persistence.*;
import java.util.List;

public class EmployeeJPQLTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        System.out.println("트랜잭션 시작 !!!");

        String jpql = "select e from Employee e where e.deptId = :deptId";

        TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);
        query.setParameter("deptId", 1);
        List<Employee> resultList = query.getResultList();
        
        jpql = "select count(e), sum(e.salary), avg(e.salary), max(e.salary), min(e.salary) from Employee e" ;
        Object singleResult = em.createQuery(jpql).getSingleResult();

        System.out.println("DB에서 가져옴");
        System.out.println("영속 상태");
        Employee e2 = em.find(Employee.class, "202301");
        System.out.println("1차 캐시에서 가져옴");

        System.out.println("커밋 전");
        tx.commit();
        System.out.println("커밋 후");
        em.close();
        emf.close();
    }
}
