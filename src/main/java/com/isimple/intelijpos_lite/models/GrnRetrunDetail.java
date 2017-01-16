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
@Table(name = "grnRetrunDetail")
@NamedQueries({
    @NamedQuery(name = "GrnRetrunDetail.findAll", query = "SELECT g FROM GrnRetrunDetail g")})
public class GrnRetrunDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "qty")
    private int qty;
    @Basic(optional = false)
    @Column(name = "cost")
    private double cost;
    @JoinColumn(name = "grnReturn", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GrnReturn grnReturn;
    @JoinColumn(name = "grnDetail", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GrnDetail grnDetail;

    public GrnRetrunDetail() {
    }

    public GrnRetrunDetail(Integer id) {
        this.id = id;
    }

    public GrnRetrunDetail(Integer id, int qty, double cost) {
        this.id = id;
        this.qty = qty;
        this.cost = cost;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public GrnReturn getGrnReturn() {
        return grnReturn;
    }

    public void setGrnReturn(GrnReturn grnReturn) {
        this.grnReturn = grnReturn;
    }

    public GrnDetail getGrnDetail() {
        return grnDetail;
    }

    public void setGrnDetail(GrnDetail grnDetail) {
        this.grnDetail = grnDetail;
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
        if (!(object instanceof GrnRetrunDetail)) {
            return false;
        }
        GrnRetrunDetail other = (GrnRetrunDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.isimple.intelijpos_lite.models.GrnRetrunDetail[ id=" + id + " ]";
    }

}
