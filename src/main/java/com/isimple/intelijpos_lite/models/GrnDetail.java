package com.isimple.intelijpos_lite.models;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Hashan Chamikara
 */
@Entity
@Table(name = "grnDetail")
@NamedQueries({
    @NamedQuery(name = "GrnDetail.findAll", query = "SELECT g FROM GrnDetail g")})
public class GrnDetail implements Serializable {
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
    @Basic(optional = false)
    @Column(name = "rprice")
    private double rprice;
    @Basic(optional = false)
    @Column(name = "wprice")
    private double wprice;
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grnDetail", fetch = FetchType.LAZY)
    private List<GrnRetrunDetail> grnRetrunDetailList;
    @JoinColumn(name = "grn", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Grn grn;
    @JoinColumn(name = "item", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Item item;

    public GrnDetail() {
    }

    public GrnDetail(Integer id) {
        this.id = id;
    }

    public GrnDetail(Integer id, int qty, double cost, double rprice, double wprice) {
        this.id = id;
        this.qty = qty;
        this.cost = cost;
        this.rprice = rprice;
        this.wprice = wprice;
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

    public double getRprice() {
        return rprice;
    }

    public void setRprice(double rprice) {
        this.rprice = rprice;
    }

    public double getWprice() {
        return wprice;
    }

    public void setWprice(double wprice) {
        this.wprice = wprice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<GrnRetrunDetail> getGrnRetrunDetailList() {
        return grnRetrunDetailList;
    }

    public void setGrnRetrunDetailList(List<GrnRetrunDetail> grnRetrunDetailList) {
        this.grnRetrunDetailList = grnRetrunDetailList;
    }

    public Grn getGrn() {
        return grn;
    }

    public void setGrn(Grn grn) {
        this.grn = grn;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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
        if (!(object instanceof GrnDetail)) {
            return false;
        }
        GrnDetail other = (GrnDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.isimple.intelijpos_lite.models.GrnDetail[ id=" + id + " ]";
    }

}
