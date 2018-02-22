/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Emil
 */
@Entity
@Table(name = "ROLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
    , @NamedQuery(name = "Role.findByRoleId", query = "SELECT r FROM Role r WHERE r.roleId = :roleId")
    , @NamedQuery(name = "Role.findByName", query = "SELECT r FROM Role r WHERE r.name = :name")})
public class Role implements Serializable {

    @OneToMany(mappedBy = "roleId")
    private List<Person> personList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ROLE_ID")
    private Long roleId;
    @NotNull
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;

    /**
     * Class Constructor
     */
    public Role() {
    }

    /**
     * Class Constructor
     *
     * @param roleId sets the roleId property
     */
    public Role(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * Gets the value of the roleId property
     *
     * @return roleId as Long object
     */
    public Long getRoleId() {
        return roleId;
    }
    
    /**
     * Sets the roleId property
     *
     * @param roleId the roleId to set
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * Gets the value of the name property
     *
     * @return name as String object
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the name property
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
