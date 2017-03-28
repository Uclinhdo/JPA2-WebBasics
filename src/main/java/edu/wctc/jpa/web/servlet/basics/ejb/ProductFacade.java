package edu.wctc.jpa.web.servlet.basics.ejb;

import edu.wctc.jpa.web.servlet.basics.model.Manufacturer;
import edu.wctc.jpa.web.servlet.basics.model.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * This class is an imlementation of the AbstractFacade, typed to handle
 * Product entities. It is also a Stateless EJB Session Bean. EJBs have
 * many sophisticated features, but for this demo it's the ability to
 * auto handle JTA transactions (the EJB container does this).
 * 
 * @author jlombardo
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> {
    @PersistenceContext(unitName = "sample_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }
    
    public double getMaxPriceList(){
        String sql = "SELECT MAX(p.purchaseCost) FROM Product p";
        Query q = getEntityManager().createQuery(sql);
        Double result = (Double)q.getSingleResult();
        return result;
        
    }
    public List<Product> getPriceRange(){
        String sql = "SELECT p FROM Product p WHERE p.purchaseCost BETWEEN 100 and 500";
        TypedQuery<Product> qe = getEntityManager().createQuery(sql, Product.class);
        qe.setMaxResults(20);
        return qe.getResultList();
    }
    
}
