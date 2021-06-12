package tacos.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
@Entity
@Table(name = "Taco_Order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToMany(targetEntity = Taco.class)
	private List<Taco> tacos = new ArrayList<>();
	
	@NotBlank(message = "Name is required")
	@Column(name = "deliveryName")
	private String customerName;
	
	@NotBlank(message = "Street is required")
	@Column(name = "deliveryStreet")
	private String street;
	
	@NotBlank(message = "City is required")
	@Column(name = "deliveryCity")
	private String city;
	
	@NotBlank(message = "State is required")
	@Column(name = "deliveryState")
	private String state;
	
	@NotBlank(message = "Zip is required")
	@Column(name = "deliveryZip")
	private String zip;
	
	@CreditCardNumber(message = "Not a valid credit card number")
	private String ccNumber;
	
	@Pattern(regexp = "^(0[1-9]|1[0-2])([\\\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
	private String ccExpiration;
	
	@Digits(integer = 3, fraction = 0,message = "Invalid CVV")
	private String ccCVV;
	
	private Date placedAt;
	
	@PrePersist
	void placedAt() {
		this.placedAt = new Date();
	}
	
	public void addTaco(Taco taco) {
		this.tacos.add(taco);
	}

	public void addDesign(Taco taco) {
		tacos.add(taco);
	}
}
