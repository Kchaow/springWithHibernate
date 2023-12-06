package dataModelHibernate.entities;

import java.io.Serial;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "singer")
@NoArgsConstructor
@AllArgsConstructor
public class Singer extends AbstractEntity
{
	@Serial
	private static final long serialVersionUID = 2L;
	
	private String firstName;
	private String lastName;
	private Set<Album> albums = new HashSet<>();
	private Set<Instrument> instruments;
	private LocalDate birthDate;
	
	@ManyToMany
	@JoinTable(name = "singer_instrument",
			joinColumns = @JoinColumn(name = "singer_id"),
			inverseJoinColumns = @JoinColumn(name = "instrument_id"))
	public Set<Instrument> getInstruments() 
	{
		return instruments;
	}
	
	public void setInstruments(Set<Instrument> instruments) 
	{
		this.instruments = instruments;
	}
	
	public boolean addInstrument(Instrument instrument) 
	{
		return getInstruments().add(instrument);
	}
	
	@OneToMany(mappedBy = "singer", cascade = CascadeType.ALL, orphanRemoval=true)
	public Set<Album> getAlbums()
	{
		return albums;
	}
	
	public boolean addAlbum(Album album) 
	{
		album.setSinger(this);
		return getAlbums().add(album);
	}
	
	public void removeAlbum(Album album) 
	{
		getAlbums().remove(album);
	}
	
	public void setAlbums(Set<Album> albums) 
	{
		this.albums = albums;
	}
	
	@Column(name = "first_name")
	public String getFirstName()
	{
		return firstName;
	}
	
	@Column(name = "last_name")
	public String getLastName() 
	{
		return lastName;
	}
	
	@Column(name = "birth_date")
	public LocalDate getBirthDate()
	{
		return birthDate;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}
	
	public void setBirthDate(LocalDate birthDate) 
	{
		this.birthDate = birthDate;
	}
}
