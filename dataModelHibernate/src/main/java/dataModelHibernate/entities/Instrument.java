package dataModelHibernate.entities;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "instrument")
@NoArgsConstructor
@AllArgsConstructor
public class Instrument implements Serializable
{
	private Set<Singer> singers = new HashSet<>();
	@Serial
	private static final long serialVersionUID = 4L;
	private String instrumentId;
	
	@ManyToMany
	@JoinTable(name = "singer_instrument",
			joinColumns = @JoinColumn(name = "instrument_id"),
			inverseJoinColumns = @JoinColumn(name = "singer_id"))
	public Set<Singer> getSingers() 
	{
		return singers;
	}
	
	public void setSingers(Set<Singer> singers)
	{
		this.singers = singers;
	}
	
	@Id
	@Column(name = "instrument_id")
	public String getInstrumentId() 
	{
		return instrumentId;
	}
	
	public void setInstrumentId(String instrumentId) 
	{
		this.instrumentId = instrumentId;
	}
}
