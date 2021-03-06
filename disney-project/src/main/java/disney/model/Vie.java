package disney.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@JsonView(Views.ViewsCommon.class)
public class Vie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private int version;

	// nombre de vie a acheter, on peut en acheter 1, 3, 5,10
	private int nombre;

	// prix en fonction du nombre (1vie =100etoiles, 3vies=250etoiles...)
	private int prix;

	public Vie(int nombre, int prix) {
		super();
		this.nombre = nombre;
		this.prix = prix;
	}

	public Vie(int nombre, int prix, Long id, int version) {
		super();
		this.nombre = nombre;
		this.prix = prix;
		this.id = id;
		this.version = version;
	}

	public Vie() {
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

