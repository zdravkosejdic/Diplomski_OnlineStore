/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import db.HibernateUtil;
import entiteti.Korisnik;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Toshiba
 */
@ManagedBean
@RequestScoped
public class AdminController {
    private Korisnik korisnik; 

    public List<Korisnik> getOdobreni()
    {
        List<Korisnik> registrations=new ArrayList<>();
        //citanje iz baze
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tr = null;

        try 
        {
            tr = session.beginTransaction();
            registrations = session.createQuery("FROM Korisnik K WHERE status = odobren").list();
            tr.commit();
        } 
        catch (HibernateException e) 
        {
            if (tr != null)
            {
                tr.rollback();
            }
        } 
        finally 
        {
            session.flush();
            session.close();
        }
        return registrations;
    }
    
    public List<Korisnik> getNeodobreni()
    {
        List<Korisnik> registrations=new ArrayList<>();
        //citanje iz baze
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tr = null;

        try 
        {
            tr = session.beginTransaction();
            registrations = session.createQuery("FROM Korisnik K WHERE status = neodobren").list();
            tr.commit();
        } 
        catch (HibernateException e) 
        {
            if (tr != null)
            {
                tr.rollback();
            }
        } 
        finally 
        {
            session.flush();
            session.close();
        }
        return registrations;
    }
    
    public void prihvati()
    {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tr = null;
        Korisnik k = null;
        
        try 
        {
            tr = session.beginTransaction();
            Criteria criteria = session.createCriteria(Korisnik.class);

            k = (Korisnik) criteria.add(Restrictions.eq("username", korisnik.getUsername())).uniqueResult();
            k.setStatus("odobren");
            session.save(k);
            
            tr.commit();
        } 
        catch (HibernateException e) 
        {
            if (tr != null)
            {
                tr.rollback();
            }
        }
        finally 
        {
            session.flush();
            session.close();
        }
        
        
        korisnik=null;
        String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + "/faces/admin.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    public void odbij()
    {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tr = null;
        Korisnik k = null;
        
        try 
        {
            tr = session.beginTransaction();
            Criteria criteria = session.createCriteria(Korisnik.class);

            k = (Korisnik) criteria.add(Restrictions.eq("username", korisnik.getUsername())).uniqueResult();
            k.setStatus("neodobren");
            session.save(k);
            
            tr.commit();
        } 
        catch (HibernateException e) 
        {
            if (tr != null)
            {
                tr.rollback();
            }
        }
        finally 
        {
            session.flush();
            session.close();
        }
        
       korisnik=null;
         String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + "/faces/admin.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
}
