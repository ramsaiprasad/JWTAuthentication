package net.codejava.StoredProcedures;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class BlogService {
   @Autowired
   private BlogDao blogRepository;
   @Autowired
   @PersistenceContext
   private EntityManager em;
   @SuppressWarnings("unchecked")
public List<UserRequest> getTotalBlogs(int id){
       return em.createNamedStoredProcedureQuery("getAllBlogs").setParameter(id, blogRepository)
    		   .setParameter("tblogId", id).getResultList();
   }
//   public List getBlogsByTitle(String title) {
//       return em.createNamedStoredProcedureQuery("getBlogsByTitle").setParameter("tblogTitle",title).getResultList();
//   }x`
}
