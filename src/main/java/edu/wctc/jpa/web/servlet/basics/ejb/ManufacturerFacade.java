package edu.wctc.jpa.web.servlet.basics.ejb;

import edu.wctc.jpa.web.servlet.basics.model.Manufacturer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * This class is an imlementation of the AbstractFacade, typed to handle
 * Manufacturer entities. It is also a Stateless EJB Session Bean. EJBs have
 * many sophisticated features, but for this demo it's the ability to
 * auto handle JTA transactions (the EJB container does this).
 * 
 * @author jlombardo
 */
@Stateless
public class ManufacturerFacade extends AbstractFacade<Manufacturer> {
    @PersistenceContext(unitName = "sample_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ManufacturerFacade() {
        super(Manufacturer.class);
    }
    public List<Manufacturer> findByState(String state){
//        String jpql = "SELECT ma FROM Manufacturer ma WHERE ma.sate = ?1";
//        
//        TypedQuery<Manufacturer> q = getEntityManager().createQuery(jpql, Manufacturer.class);
//        q.setParameter(1,state);
//        q.setMaxResults(10);
//        return q. getResultList();
        
        String jpql = "SELECT ma FROM Manufacturer ma WHERE ma.state = :state";
        
        TypedQuery<Manufacturer> q = getEntityManager().createQuery(jpql, Manufacturer.class);
        q.setParameter("state",state);
        q.setMaxResults(10);
        return q. getResultList();
    }
    
    public int deleteById(String id){
        String jpql = "DELETE FROM Manufacturer ma WHERE ma.manufacturerId = ?1";
        
        Query qe = getEntityManager().createQuery(jpql);
//             Manufacturer ma = em.find(Manufacturer.class, 1);
//             em.remove(ma);
        qe.setParameter("manufacturerId", new Integer(id));
        //qe.setParameter(1,new Integer(id));
        return qe.executeUpdate();
            
    }
//    public void deleteManufacturerById(){
//        //using the JPA EntityManager remove(Object entity) method
//             em = getEntityManager();
//             Manufacturer ma = em.find(Manufacturer.class, 1);
//             em.remove(ma);
//    }
    public int convertNameById(String id){
        
        String sql = "SELECT ma.manufacturerId "
                    + "FROM Manufacturer ma WHERE UPPER(ma.name) = :name";
        Query qe = getEntityManager().createQuery(sql);
        qe.setParameter("manufacturerId", new Integer(id));
        return qe.executeUpdate();
        
    }
    
    
    
}
