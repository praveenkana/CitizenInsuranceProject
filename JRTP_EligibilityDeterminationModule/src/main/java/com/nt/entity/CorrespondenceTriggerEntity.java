package com.nt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "COTriggerEntity")
public class CorrespondenceTriggerEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer triggerId;
	private Integer caseNo;
	@Lob
	private byte[] coNotice;
	private String triggerStatus;

}
