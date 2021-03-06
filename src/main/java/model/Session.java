package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "session")
public class Session {
	@Id
	@GeneratedValue
	private long id;
	private Calendar date;
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "deputy_session", joinColumns = { @JoinColumn(name = "session_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "deputy_id", nullable = false, updatable = false) })
	private List<Deputy> deputies;
	private String description;
	private transient boolean isPresent;

	public String getDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String date = simpleDateFormat.format(this.date.getTime());
		return date;
	}

	public void setDate(String date) {
		String[] splitedDate = date.split("/");
		int day = Integer.parseInt(splitedDate[0]);
		int month = Integer.parseInt(splitedDate[1]);
		int year = Integer.parseInt(splitedDate[2]);

		GregorianCalendar dateCalendar = new GregorianCalendar(year, month, day);

		this.date = dateCalendar;
	}

	public void setDate(Calendar date) {

		this.date = date;
	}

	public void validate() {
		if (date == null) {
			throw new IllegalArgumentException("Date cannot be null");
		}

	}

	public List<Deputy> getDeputies() {
		return deputies;
	}

	public void setDeputies(List<Deputy> deputies) {
		this.deputies = deputies;
	}

	public void setDescription(String description) {
		this.description = description;

	}

	public String getDescription() {
		return description;
	}

	public boolean isPresent() {
		return isPresent;
	}

	public void setPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}

}