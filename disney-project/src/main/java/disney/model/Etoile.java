package disney.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@JsonView(Views.ViewsCommon.class)
public class Etoile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private int version;

	// nombre d'étoiles a acheter ( 100, 300, 500,...)
	private int nombre;

	// prix en fonction du nombre: ( 100 etoiles = 5euro, 300 etoiles = 13euros )
	private int prix;



	public Etoile(int nombre, int prix) {
		super();
		this.nombre = nombre;
		this.prix = prix;
	}

	public Etoile(int nombre, int prix, Long id, int version) {
		super();
		this.nombre = nombre;
		this.prix = prix;
		this.id = id;
		this.version = version;
	}

	public Etoile() {
		super();
	}

	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Vie [nombre=" + nombre + ", prix=" + prix + ", id=" + id + ", version=" + version + "]";
	}



}
