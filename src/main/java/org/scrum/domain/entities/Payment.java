package org.scrum.domain.entities;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;


@Entity
public class Payment {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		
		@ManyToOne
		@NotNull(message = "Payment must be linked to an invoice!") 
		private Invoice invoice;
		
		@NotNull(message = "Payment date cannot be empty!") 
		@Future(message = "Payment date cannot be in the future!")
		private Date date;
		
		@NotNull(message = "Value cannot be empty!") 
		private Double value;
		

		
		
		public Payment(Integer id, 
				@NotNull(message = "Payment must be linked to an invoice!") 
				Invoice invoice,
				@NotNull(message = "Payment date cannot be empty!") 
				@Future(message = "Payment date cannot be in the future!") 
				Date date,
				@NotNull(message = "Value cannot be empty!") 
				Double value) {
			super();
			this.id = id;
			this.invoice = invoice;
			this.date = date;
			this.value = value;
		}





		public Integer getId() {
			return id;
		}





		public void setId(Integer id) {
			this.id = id;
		}





		public Invoice getInvoice() {
			return invoice;
		}





		public void setInvoice(Invoice invoice) {
			this.invoice = invoice;
		}





		public Date getDate() {
			return date;
		}





		public void setDate(Date date) {
			this.date = date;
		}





		public Double getValue() {
			return value;
		}





		public void setValue(Double value) {
			this.value = value;
		}





		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Payment other = (Payment) obj;
			return Objects.equals(id, other.id);
		}
		
		
		
		
		
}
