/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import integration.RecruitmentDAO;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import model.Applications;
import model.CompetenceName;
import model.Person;

/**
 *
 * @author Emil
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class Controller {
    
    @EJB
    RecruitmentDAO rdao;
    
    public Person authenticate(String username) {
        return rdao.existsUser(username);
    }
    public Person register(Person newUser) {
        return rdao.registerPerson(newUser);
    }
    
    public List<CompetenceName> getAllCompetences() {
        return rdao.getAllCompetences();
    }
    public List<Applications> getAllApplications(){
        return rdao.getAllApplications();
    }

    public List<Applications> getApplications(Date submissionDate, String periodFrom, String periodTo, long competence, String firstname) {
        
        return rdao.getApplications(submissionDate,periodFrom, periodTo, competence, firstname);
    }
    
}
