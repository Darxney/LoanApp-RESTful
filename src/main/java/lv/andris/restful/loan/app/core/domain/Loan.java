package lv.andris.restful.loan.app.core.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "loans")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Loan {

    @Id
    @Column(name = "loan_id")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loanId_generator")
    @SequenceGenerator(name="loanId_generator", sequenceName = "id_seq", allocationSize = 1)
    //correct namings loanId
    private Long loanId;

    @Column(name = "loan_amount",  nullable = false)
    private BigDecimal loanAmount;

    @Column(name = "loan_term",  nullable = false)
    private Date loanTerm;

    //names and personal id can be in a different domain
    @Column(name = "first_name",  nullable = false)
    private String firstName;

    @Column(name = "last_name",  nullable = false)
    private String lastName;

    @Column(name = "personal_id",  nullable = false)
    private String personalId;

    @Column(name = "is_approved",  nullable = false)
    private boolean isApproved;

    public Loan() {

    }

    public Loan(BigDecimal loanAmount, Date loanTerm, String firstName, String lastName, String personalId) {
        this.loanAmount = loanAmount;
        this.loanTerm = loanTerm;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
    }
    // there should be one constructor all else should be done through setters

    public Loan(BigDecimal loanAmount, Date loanTerm, String firstName, String lastName, String personalId, boolean isApproved) {
        this.loanAmount = loanAmount;
        this.loanTerm = loanTerm;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
        this.isApproved = isApproved;
    }

    public Loan(Long loanId ,BigDecimal loanAmount, Date loanTerm, String firstName, String lastName, String personalId) {
        this.loanId = loanId;
        this.loanAmount = loanAmount;
        this.loanTerm = loanTerm;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
    }

    public Loan(Long loanId ,BigDecimal loanAmount, Date loanTerm, String firstName, String lastName, String personalId, boolean isApproved) {
        this.loanId = loanId;
        this.loanAmount = loanAmount;
        this.loanTerm = loanTerm;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
        this.isApproved = isApproved;
    }

    public Long getloanId() {
        return loanId;
    }

    public void setloanId(Long loanId) {
        this.loanId = loanId;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Date getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(Date loanTerm) {
        this.loanTerm = loanTerm;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", loanAmount=" + loanAmount +
                ", loanTerm=" + loanTerm +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", personalId='" + personalId + '\'' +
                ", isApproved=" + isApproved +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return isApproved == loan.isApproved && loanId.equals(loan.loanId) && loanAmount.equals(loan.loanAmount) && loanTerm.equals(loan.loanTerm) && firstName.equals(loan.firstName) && lastName.equals(loan.lastName) && personalId.equals(loan.personalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId, loanAmount, loanTerm, firstName, lastName, personalId, isApproved);
    }
}
