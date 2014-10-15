/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kiev.doctorvera.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ua.kiev.doctorvera.dao.Identified;

/**
 *
 * @author Bodun
 */
@Entity
@Table(name = "Users")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "User.findByMiddleName", query = "SELECT u FROM User u WHERE u.middleName = :middleName"),
    @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "User.findByBirthDate", query = "SELECT u FROM User u WHERE u.birthDate = :birthDate"),
    @NamedQuery(name = "User.findByPhoneNumberHome", query = "SELECT u FROM User u WHERE u.phoneNumberHome = :phoneNumberHome"),
    @NamedQuery(name = "User.findByPhoneNumberMobile", query = "SELECT u FROM User u WHERE u.phoneNumberMobile = :phoneNumberMobile"),
    @NamedQuery(name = "User.findByDescription", query = "SELECT u FROM User u WHERE u.description = :description"),
    @NamedQuery(name = "User.findByCreatedUserId", query = "SELECT u FROM User u WHERE u.createdUserId = :createdUserId"),
    @NamedQuery(name = "User.findByDeleted", query = "SELECT u FROM User u WHERE u.deleted = :deleted")})
public class Users implements Serializable, Identified<Integer> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "UserId")
    private Integer userId;
    @Basic(optional = false)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @Column(name = "Password")
    private String password;
    @Basic(optional = false)
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "MiddleName")
    private String middleName;
    @Basic(optional = false)
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "BirthDate")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column(name = "PhoneNumberHome")
    private String phoneNumberHome;
    @Column(name = "PhoneNumberMobile")
    private String phoneNumberMobile;
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @Column(name = "CreatedUserId")
    private int createdUserId;
    @Basic(optional = false)
    @Column(name = "Deleted")
    private boolean deleted;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdUserId")
    private Collection<Policy> policyCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paymasterId")
    private Collection<Payment> paymentCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<Payment> paymentCollection1;
    @JoinColumn(name = "UserTypeId", referencedColumnName = "UserTypeId")
    @ManyToOne(optional = false)
    private UserTypes userTypeId;
    @JoinColumn(name = "AddressId", referencedColumnName = "AddressId")
    @ManyToOne
    private Address addressId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctorId")
    private Collection<Plan> planCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCreatedId")
    private Collection<Plan> planCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctorId")
    private Collection<DoctorsHasMethod> doctorsHasMethodCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdUserId")
    private Collection<DoctorsHasMethod> doctorsHasMethodCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdUserId")
    private Collection<PolicyHasUserType> policyHasUserTypeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdUserId")
    private Collection<Room> roomCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdUserId")
    private Collection<Price> priceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patientId")
    private Collection<Schedule> scheduleCollection;
    @OneToMany(mappedBy = "assistantId")
    private Collection<Schedule> scheduleCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctorId")
    private Collection<Schedule> scheduleCollection2;
    @OneToMany(mappedBy = "doctorDirectedId")
    private Collection<Schedule> scheduleCollection3;
    @OneToMany(mappedBy = "createdUserId")
    private Collection<Schedule> scheduleCollection4;
    @OneToMany(mappedBy = "createdUserId")
    private Collection<UserTypes> userTypeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctorId")
    private Collection<Share> shareCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assistantId")
    private Collection<Share> shareCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdUserId")
    private Collection<Share> shareCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdUserId")
    private Collection<Method> methodCollection;

    public Users() {
    }

    public Users(Integer userId) {
        this.userId = userId;
    }

    public Users(Integer userId, String username, String password, String firstName, String lastName, int createdUserId, boolean deleted) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdUserId = createdUserId;
        this.deleted = deleted;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumberHome() {
        return phoneNumberHome;
    }

    public void setPhoneNumberHome(String phoneNumberHome) {
        this.phoneNumberHome = phoneNumberHome;
    }

    public String getPhoneNumberMobile() {
        return phoneNumberMobile;
    }

    public void setPhoneNumberMobile(String phoneNumberMobile) {
        this.phoneNumberMobile = phoneNumberMobile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(int createdUserId) {
        this.createdUserId = createdUserId;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Collection<Policy> getPolicyCollection() {
        return policyCollection;
    }

    public void setPolicyCollection(Collection<Policy> policyCollection) {
        this.policyCollection = policyCollection;
    }

    public Collection<Payment> getPaymentCollection() {
        return paymentCollection;
    }

    public void setPaymentCollection(Collection<Payment> paymentCollection) {
        this.paymentCollection = paymentCollection;
    }

    public Collection<Payment> getPaymentCollection1() {
        return paymentCollection1;
    }

    public void setPaymentCollection1(Collection<Payment> paymentCollection1) {
        this.paymentCollection1 = paymentCollection1;
    }

    public UserTypes getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(UserTypes userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Address getAddressId() {
        return addressId;
    }

    public void setAddressId(Address addressId) {
        this.addressId = addressId;
    }

    public Collection<Plan> getPlanCollection() {
        return planCollection;
    }

    public void setPlanCollection(Collection<Plan> planCollection) {
        this.planCollection = planCollection;
    }

    public Collection<Plan> getPlanCollection1() {
        return planCollection1;
    }

    public void setPlanCollection1(Collection<Plan> planCollection1) {
        this.planCollection1 = planCollection1;
    }

    public Collection<DoctorsHasMethod> getDoctorsHasMethodCollection() {
        return doctorsHasMethodCollection;
    }

    public void setDoctorsHasMethodCollection(Collection<DoctorsHasMethod> doctorsHasMethodCollection) {
        this.doctorsHasMethodCollection = doctorsHasMethodCollection;
    }

    public Collection<DoctorsHasMethod> getDoctorsHasMethodCollection1() {
        return doctorsHasMethodCollection1;
    }

    public void setDoctorsHasMethodCollection1(Collection<DoctorsHasMethod> doctorsHasMethodCollection1) {
        this.doctorsHasMethodCollection1 = doctorsHasMethodCollection1;
    }

    public Collection<PolicyHasUserType> getPolicyHasUserTypeCollection() {
        return policyHasUserTypeCollection;
    }

    public void setPolicyHasUserTypeCollection(Collection<PolicyHasUserType> policyHasUserTypeCollection) {
        this.policyHasUserTypeCollection = policyHasUserTypeCollection;
    }

    public Collection<Room> getRoomCollection() {
        return roomCollection;
    }

    public void setRoomCollection(Collection<Room> roomCollection) {
        this.roomCollection = roomCollection;
    }

    public Collection<Price> getPriceCollection() {
        return priceCollection;
    }

    public void setPriceCollection(Collection<Price> priceCollection) {
        this.priceCollection = priceCollection;
    }

    public Collection<Schedule> getScheduleCollection() {
        return scheduleCollection;
    }

    public void setScheduleCollection(Collection<Schedule> scheduleCollection) {
        this.scheduleCollection = scheduleCollection;
    }

    public Collection<Schedule> getScheduleCollection1() {
        return scheduleCollection1;
    }

    public void setScheduleCollection1(Collection<Schedule> scheduleCollection1) {
        this.scheduleCollection1 = scheduleCollection1;
    }

    public Collection<Schedule> getScheduleCollection2() {
        return scheduleCollection2;
    }

    public void setScheduleCollection2(Collection<Schedule> scheduleCollection2) {
        this.scheduleCollection2 = scheduleCollection2;
    }

    public Collection<Schedule> getScheduleCollection3() {
        return scheduleCollection3;
    }

    public void setScheduleCollection3(Collection<Schedule> scheduleCollection3) {
        this.scheduleCollection3 = scheduleCollection3;
    }

    public Collection<Schedule> getScheduleCollection4() {
        return scheduleCollection4;
    }

    public void setScheduleCollection4(Collection<Schedule> scheduleCollection4) {
        this.scheduleCollection4 = scheduleCollection4;
    }

    public Collection<UserTypes> getUserTypeCollection() {
        return userTypeCollection;
    }

    public void setUserTypeCollection(Collection<UserTypes> userTypeCollection) {
        this.userTypeCollection = userTypeCollection;
    }

    public Collection<Share> getShareCollection() {
        return shareCollection;
    }

    public void setShareCollection(Collection<Share> shareCollection) {
        this.shareCollection = shareCollection;
    }

    public Collection<Share> getShareCollection1() {
        return shareCollection1;
    }

    public void setShareCollection1(Collection<Share> shareCollection1) {
        this.shareCollection1 = shareCollection1;
    }

    public Collection<Share> getShareCollection2() {
        return shareCollection2;
    }

    public void setShareCollection2(Collection<Share> shareCollection2) {
        this.shareCollection2 = shareCollection2;
    }

    public Collection<Method> getMethodCollection() {
        return methodCollection;
    }

    public void setMethodCollection(Collection<Method> methodCollection) {
        this.methodCollection = methodCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.User[ userId=" + userId + " ]";
    }

	@Override
	public Integer getId() {
		return getUserId();
	}

	@Override
	public void setId(Integer id) {
		setUserId(id);
	}
    
}
