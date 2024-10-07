package com.nt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.bindings.ChildrenInputs;
import com.nt.bindings.CitizenAppRegistrationInputs;
import com.nt.bindings.EducationInputs;
import com.nt.bindings.IncomeInputs;
import com.nt.bindings.PlanInputs;
import com.nt.entity.CitizenAppRegistrationEntity;
import com.nt.entity.DC_CaseEntity;
import com.nt.entity.DC_ChildrenEntity;
import com.nt.entity.DC_EducationEntity;
import com.nt.entity.DC_IncomeEntity;
import com.nt.entity.DC_SummaryReport;
import com.nt.entity.PlanEntity;
import com.nt.repository.ICitizenAppRegistrationRepository;
import com.nt.repository.IDC_CaseRepository;
import com.nt.repository.IDC_ChildrenRepository;
import com.nt.repository.IDC_EducationRepository;
import com.nt.repository.IDC_IncomeRepository;
import com.nt.repository.IDC_PlanRepository;

@Service
public class DCMgmtServiceImpl implements ICollectionMgmtService {
	@Autowired
	private ICitizenAppRegistrationRepository citizenrepo;
	@Autowired
	private IDC_CaseRepository caserepo;
	@Autowired
	private IDC_IncomeRepository incomerepo;
	@Autowired
	private IDC_PlanRepository planrepo;
	@Autowired
	private IDC_EducationRepository edrepo;
	@Autowired
	private IDC_ChildrenRepository childrepo;

	@Override
	public Integer generateCaseNo(Integer appId) {

		Optional<CitizenAppRegistrationEntity> opt = citizenrepo.findById(appId);
		if (opt.isPresent()) {
			DC_CaseEntity caseEntity = new DC_CaseEntity();
			caseEntity.setAppId(appId);
			Integer caseNo = caserepo.save(caseEntity).getCaseNo();// save entity
			return caseNo;

		}

		return 0;
	}

	@Override
	public Integer savePlanSelection(PlanInputs planInputs) {

		Optional<DC_CaseEntity> opt = caserepo.findById(planInputs.getCaseNo());
		if (opt.isPresent()) {
			DC_CaseEntity caseEntity = opt.get();
			caseEntity.setPlanId(planInputs.getPlanId());
			return caserepo.save(caseEntity).getCaseNo(); // update entity
		}

		return null;
	}

	@Override
	public Integer saveIncomeDetails(IncomeInputs income) {

		DC_IncomeEntity incomeEntity = new DC_IncomeEntity();
		BeanUtils.copyProperties(income, incomeEntity);
		Integer caseNo = incomerepo.save(incomeEntity).getCaseNo();
		return caseNo;

	}

	@Override
	public List<String> showAllPlans() {

		List<PlanEntity> plans = planrepo.findAll();

		List<String> planList = new ArrayList<String>();
		for (PlanEntity planEntity : plans) {
			String planName = planEntity.getPlanName();
			planList.add(planName);
		}
		/*
		 * List<String>planList =
		 * plans.stream().map(plan->(plan.getPlanName().toString())).toList();
		 */
		return planList;
	}

	public Integer saveEducationDetails(EducationInputs edInputs) {
		DC_EducationEntity edentity = new DC_EducationEntity();
		BeanUtils.copyProperties(edInputs, edentity);

		return edrepo.save(edentity).getCaseNo();

	}

	@Override
	public Integer saveChildrenDetails(List<ChildrenInputs> children) {

		children.forEach(child -> {
			DC_ChildrenEntity centity = new DC_ChildrenEntity();
			BeanUtils.copyProperties(children, centity);
			childrepo.save(centity);

		});

		return children.get(0).getCaseNo();
	}

	@Override
	public DC_SummaryReport showDC_SummaryReport(Integer caseNo) {
		Integer planId = null;
		Integer appId = null;
		String planName = null;
		CitizenAppRegistrationEntity citizenEntity = null;
		DC_SummaryReport report = null;
		// get multiple entities based on case no
		DC_IncomeEntity incomeEntity = incomerepo.findByCaseNo(caseNo);
		DC_EducationEntity education = edrepo.findByCaseNo(caseNo);
		List<DC_ChildrenEntity> children = childrepo.findByCaseNo(caseNo);

		Optional<DC_CaseEntity> opt = caserepo.findById(caseNo);
		if (opt.isPresent()) {
			DC_CaseEntity caseEntity = opt.get();
			planId = caseEntity.getPlanId();
			appId = caseEntity.getAppId();
			Optional<PlanEntity> opt1 = planrepo.findById(planId);
			if (opt1.isPresent()) {
				planName = opt1.get().getPlanName();
			}
			Optional<CitizenAppRegistrationEntity> opt2 = citizenrepo.findById(appId);
			if (opt2.isPresent()) {
				citizenEntity = opt2.get();
			}

			// convert entity objects into binding objects
			IncomeInputs inputs = new IncomeInputs();
			BeanUtils.copyProperties(incomeEntity, inputs);
			EducationInputs edinputs = new EducationInputs();
			BeanUtils.copyProperties(education, edinputs);

			List<ChildrenInputs> childList = new ArrayList();
			childList.forEach(child -> {
				ChildrenInputs childInput = new ChildrenInputs();
				BeanUtils.copyProperties(child, childInput);
				childList.add(childInput);
			});

			CitizenAppRegistrationInputs citizenInput = new CitizenAppRegistrationInputs();
			BeanUtils.copyProperties(citizenEntity, citizenInput);

			report = new DC_SummaryReport();
			report.setPlanName(planName);
			report.setIncomeInputs(inputs);
			report.setEducationInputs(edinputs);
			report.setChildInputs(childList);
			report.setAppInputs(citizenInput);

		}
		return report;

	}

}
