package com.isimple.intelijpos_lite.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Hashan Chamikara
 */
@Entity
@Table(name = "invoiceReturn")
@NamedQueries({
    @NamedQuery(name = "InvoiceReturn.findAll", query = "SELECT i FROM InvoiceReturn i")})
public class InvoiceReturn implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @Column(name = "value")
    private double value;
    @Basic(optional = false)
    @Lob
    @Column(name = "reason")
    private String reason;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Basic(optional = false)
    @Column(name = "modify")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modify;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoiceReturn", fetch = FetchType.LAZY)
    private List<InvoiceReturnDetail> invoiceReturnDetailList;
    @JoinColumn(name = "invoice", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Invoice invoice;

    public InvoiceReturn() {
    }

    public InvoiceReturn(Integer id) {
        this.id = id;
    }

    public InvoiceReturn(Integer id, Date date, double value, String reason, int status, Date created, Date modify) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.reason = reason;
        this.status = status;
        this.created = created;
        this.modify = modify;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModify() {
        return modify;
    }

    public void setModify(Date modify) {
        this.modify = modify;
    }

    public List<InvoiceReturnDetail> getInvoiceReturnDetailList() {
        return invoiceReturnDetailList;
    }

    public void setInvoiceReturnDetailList(List<InvoiceReturnDetail> invoiceReturnDetailList) {
        this.invoiceReturnDetailList = invoiceReturnDetailList;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvoiceReturn)) {
            return false;
        }
        InvoiceReturn other = (InvoiceReturn) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.isimple.intelijpos_lite.models.InvoiceReturn[ id=" + id + " ]";
    }

}
