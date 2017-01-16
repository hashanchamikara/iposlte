package com.isimple.intelijpos_lite.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Hashan Chamikara
 */
@Entity
@Table(name = "invoiceReturnDetail")
@NamedQueries({
    @NamedQuery(name = "InvoiceReturnDetail.findAll", query = "SELECT i FROM InvoiceReturnDetail i")})
public class InvoiceReturnDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "qty")
    private int qty;
    @JoinColumn(name = "invoiceDetail", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InvoiceDetail invoiceDetail;
    @JoinColumn(name = "invoiceReturn", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InvoiceReturn invoiceReturn;

    public InvoiceReturnDetail() {
    }

    public InvoiceReturnDetail(Integer id) {
        this.id = id;
    }

    public InvoiceReturnDetail(Integer id, int qty) {
        this.id = id;
        this.qty = qty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public InvoiceDetail getInvoiceDetail() {
        return invoiceDetail;
    }

    public void setInvoiceDetail(InvoiceDetail invoiceDetail) {
        this.invoiceDetail = invoiceDetail;
    }

    public InvoiceReturn getInvoiceReturn() {
        return invoiceReturn;
    }

    public void setInvoiceReturn(InvoiceReturn invoiceReturn) {
        this.invoiceReturn = invoiceReturn;
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
        if (!(object instanceof InvoiceReturnDetail)) {
            return false;
        }
        InvoiceReturnDetail other = (InvoiceReturnDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.isimple.intelijpos_lite.models.InvoiceReturnDetail[ id=" + id + " ]";
    }

}
