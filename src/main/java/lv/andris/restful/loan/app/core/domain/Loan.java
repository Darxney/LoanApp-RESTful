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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loan_id;

    @Column(name = "loan_amount",  nullable = false)
    private BigDecimal loan_amount;

    @Column(name = "loan_term",  nullable = false)
    private Date loan_term;

    @Column(name = "first_name",  nullable = false)
    private String first_name;

    @Column(name = "last_name",  nullable = false)
    private String last_name;

    @Column(name = "personal_id",  nullable = false)
    private String personal_id;

    @Column(name = "is_approved",  nullable = false)
    private boolean is_approved;

    public Loan() {

    }

    public Loan(BigDecimal loan_amount, Date loan_term, String first_name, String last_name, String personal_id) {
        this.loan_amount = loan_amount;
        this.loan_term = loan_term;
        this.first_name = first_name;
        this.last_name = last_name;
        this.personal_id = personal_id;
    }

    public Loan(BigDecimal loan_amount, Date loan_term, String first_name, String last_name, String personal_id, boolean is_approved) {
        this.loan_amount = loan_amount;
        this.loan_term = loan_term;
        this.first_name = first_name;
        this.last_name = last_name;
        this.personal_id = personal_id;
        this.is_approved = is_approved;
    }

    public Loan(Long loan_id ,BigDecimal loan_amount, Date loan_term, String first_name, String last_name, String personal_id) {
        this.loan_id = loan_id;
        this.loan_amount = loan_amount;
        this.loan_term = loan_term;
        this.first_name = first_name;
        this.last_name = last_name;
        this.personal_id = personal_id;
    }

    public Loan(Long loan_id ,BigDecimal loan_amount, Date loan_term, String first_name, String last_name, String personal_id, boolean is_approved) {
        this.loan_id = loan_id;
        this.loan_amount = loan_amount;
        this.loan_term = loan_term;
        this.first_name = first_name;
        this.last_name = last_name;
        this.personal_id = personal_id;
        this.is_approved = is_approved;
    }

    public Long getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(Long loan_id) {
        this.loan_id = loan_id;
    }

    public BigDecimal getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(BigDecimal loan_amount) {
        this.loan_amount = loan_amount;
    }

    public Date getLoan_term() {
        return loan_term;
    }

    public void setLoan_term(Date loan_term) {
        this.loan_term = loan_term;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPersonal_id() {
        return personal_id;
    }

    public void setPersonal_id(String personal_id) {
        this.personal_id = personal_id;
    }

    public boolean getIs_approved() {
        return is_approved;
    }

    public void setIs_approved(boolean is_approved) {
        this.is_approved = is_approved;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loan_id=" + loan_id +
                ", loan_amount=" + loan_amount +
                ", loan_term=" + loan_term +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", personal_id='" + personal_id + '\'' +
                ", is_approved=" + is_approved +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return is_approved == loan.is_approved && loan_id.equals(loan.loan_id) && loan_amount.equals(loan.loan_amount) && loan_term.equals(loan.loan_term) && first_name.equals(loan.first_name) && last_name.equals(loan.last_name) && personal_id.equals(loan.personal_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loan_id, loan_amount, loan_term, first_name, last_name, personal_id, is_approved);
    }
}
