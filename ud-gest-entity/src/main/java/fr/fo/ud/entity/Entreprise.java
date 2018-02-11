package fr.fo.ud.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe representant la table {@link Entreprise}
 * @author myPC
 *
 */

@Entity
@Table(name = "entreprise")
public class Entreprise implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_entreprise")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "libelle_entreprise", nullable = false, length = 45)
    private String libelle;
    
    @Column(name = "date_election", nullable = true)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateElection;
    
    @Column(name = "numero_rue", nullable = true, length = 10)
    private String numeroRue;
    
    @Column(name = "libelle_rue", nullable = true, length = 150)
    private String libelleRue;
    
    @Column(name = "cp_entreprise", nullable = true, length = 150)
    private String cp;
    
    @Column(name = "ville_entreprise", nullable = true, length = 150)
    private String ville;

    @Column(name = "tel_entreprise", nullable = true, length = 15)
    private String tel;
    
    @Column(name = "fax_entreprise", nullable = true, length = 15)
    private String fax;
    
    @Column(name = "mail_entreprise", nullable = true, length = 100)
    private String mail;
    
    @Column(name = "siteweb_entreprise", nullable = true, length = 150)
    private String siteWeb;
    
    @JsonIgnore
    @OneToMany(mappedBy = "entreprise")
    private List<Adherent> adherents;

    public Entreprise() {
		super();
	}

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param paramId the id to set
     */
    public void setId(Integer paramId) {
        id = paramId;
    }

    /**
     * @return the libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * @param paramLibelle the libelle to set
     */
    public void setLibelle(String paramLibelle) {
        libelle = paramLibelle;
    }

    /**
     * @return the tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param paramTel the tel to set
     */
    public void setTel(String paramTel) {
        tel = paramTel;
    }

    /**
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param paramFax the fax to set
     */
    public void setFax(String paramFax) {
        fax = paramFax;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param paramMail the mail to set
     */
    public void setMail(String paramMail) {
        mail = paramMail;
    }

    /**
     * @return the siteWeb
     */
    public String getSiteWeb() {
        return siteWeb;
    }

    /**
     * @param paramSiteWeb the siteWeb to set
     */
    public void setSiteWeb(String paramSiteWeb) {
        siteWeb = paramSiteWeb;
    }

    /**
     * @return the adherents
     */
    public List<Adherent> getAdherents() {
        return adherents;
    }

    /**
     * @param paramAdherents the adherents to set
     */
    public void setAdherents(List<Adherent> paramAdherents) {
        adherents = paramAdherents;
    }

	public String getNumeroRue() {
		return numeroRue;
	}

	public void setNumeroRue(String paramNumeroRue) {
		numeroRue = paramNumeroRue;
	}

	public String getLibelleRue() {
		return libelleRue;
	}

	public void setLibelleRue(String paramLibelleRue) {
		libelleRue = paramLibelleRue;
	}

    public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public Date getDateElection() {
        return dateElection;
    }

    public void setDateElection(Date paramDateElection) {
        dateElection = paramDateElection;
    }

//	@Override
//	public String toString() {
//		return "Entreprise [id=" + id + ", libelle=" + libelle + ", dateElection=" + dateElection + ", numeroRue="
//				+ numeroRue + ", libelleRue=" + libelleRue + ", cp=" + cp + ", ville=" + ville + ", tel=" + tel
//				+ ", fax=" + fax + ", mail=" + mail + ", siteWeb=" + siteWeb + "]";
//	}
	
}
