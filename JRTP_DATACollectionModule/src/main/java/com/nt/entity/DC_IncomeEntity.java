package com.nt.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DC_IncomeEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer IncomeId;
	private Integer caseNo;
	private Double empIncome;
	private Double propertyIncome;

}
