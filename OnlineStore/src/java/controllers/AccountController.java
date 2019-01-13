/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import db.HibernateUtil;
import entiteti.Korisnik;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
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
public class AccountController {

    private String uname, pass, confPass, name, surename, type;
    int typeVal;
    
    public AccountController() {
    }
    
    public String login(){
        System.out.println(org.hibernate.Version.getVersionString());
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < digested.length; i++) {
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            pass = sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tr = null;
        Korisnik korisnik = null;
        try {
            tr = session.beginTransaction();
            Criteria criteria = session.createCriteria(Korisnik.class);
            korisnik = (Korisnik) criteria.add(Restrictions.eq("username", uname)).add(Restrictions.eq("password", pass)).uniqueResult();
            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            session.flush();
            session.close();
        }
        
        if(korisnik == null)
        {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"Username or password is wrong!",null));
            
           return "index";
        }
        
        if (korisnik.getStatus().equals("odobren")) {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session_ = (HttpSession) context.getExternalContext().getSession(false);
            session_.setAttribute("korisnik", korisnik);
            String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

            if (korisnik.getTip().equals("admin")) {
                try {
                    //admin
                    FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + "/faces/admin.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (korisnik.getTip().equals("kupac")) {

                try {
                    //kupac
                    FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + "/faces/kupac.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (korisnik.getTip().equals("prodavac")) {
                try {
                    //prodavac
                    FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + "/faces/prodavac.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No approved user with this username and password", null));

            return "index";
        }
      
        return "index";
    }

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session_ = (HttpSession) context.getExternalContext().getSession(false);
        session_.invalidate();

        String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + "/faces/index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void signup() {
         
        if(!pass.equals(confPass))
        {
             FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"Password and confirming password must be the same",null));
            return ;
        }
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tr = null;
        
        try
        {
            tr = session.beginTransaction();
            Criteria criteria = session.createCriteria(Korisnik.class);

            Korisnik korisnik = (Korisnik) criteria.add(Restrictions.eq("username", uname)).uniqueResult();
            if (korisnik != null) 
            {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "This username is not available", null));
                return ;
            } 
            else 
            {
                MessageDigest md;
                try {
                    md = MessageDigest.getInstance("MD5");
                    byte[] passBytes = pass.getBytes();
                    md.reset();
                    byte[] digested = md.digest(passBytes);
                    StringBuffer sb = new StringBuffer();
                    for(int i=0;i<digested.length;i++){
                        sb.append(Integer.toHexString(0xff & digested[i]));
                    }
                    pass = sb.toString();
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if(typeVal == 0){
                  type = "prodavac"; 
                } else {
                    type = "kupac";
                }
                
                korisnik = new Korisnik(uname, pass, type, name, surename, "neodobren");
                session.save(korisnik);
            }
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
        
        String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + "/faces/index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getConfPass() {
        return confPass;
    }

    public void setConfPass(String confPass) {
        this.confPass = confPass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTypeVal() {
        return typeVal;
    }

    public void setTypeVal(int typeVal) {
        this.typeVal = typeVal;
    }
    
}
