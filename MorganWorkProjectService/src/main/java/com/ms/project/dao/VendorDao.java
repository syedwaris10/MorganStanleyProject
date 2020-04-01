package com.ms.project.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ms.project.dto.VendorDto;
import com.ms.project.entity.VendorDo;
import com.ms.project.entity.VendorDoPk;
import com.ms.project.util.ServiceUtil;

@Repository("VendorDao")
public class VendorDao implements VendorDaoLocal {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveVendors(VendorDto dto) {
		if (!ServiceUtil.isEmpty(dto)) {
			Session session = getSession();
			session.save(importDto(dto));
		}
	}

	private VendorDo importDto(VendorDto dto) {
		VendorDo entity = new VendorDo();
		VendorDoPk pk = new VendorDoPk();

		entity.setQuotaionValue(dto.getQuotaionValue());
		pk.setRequestId(dto.getRequestId());
		pk.setVendorId(dto.getVendorId());
		entity.setPk(pk);

		entity.setScore(dto.getScore());
		entity.setTimeFrame(dto.getTimeFrame());
		entity.setVendorName(dto.getVendorName());
		return entity;
	}

	private VendorDto exportDto(VendorDo entity) {
		VendorDto dto = new VendorDto();
		dto.setQuotaionValue(entity.getQuotaionValue());
		dto.setRequestId(entity.getPk().getRequestId());
		dto.setScore(entity.getScore());
		dto.setTimeFrame(entity.getTimeFrame());
		dto.setVendorId(entity.getPk().getVendorId());
		dto.setVendorName(entity.getVendorName());
		return dto;
	}

	public Session getSession() {
		try {
			return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {

			// System.err.println("[BaseDao]" + e.getMessage());
			return sessionFactory.openSession();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VendorDto> getTopThreeBestDealsByVendor(int requestId) {
		Session session = getSession();
		List<VendorDto> dtolist = new ArrayList<>();
		Query query = session
				.createQuery("Select d from VendorDo d Where d.requestId = :requestId ORDER By d.score desc");
		query.setParameter("requestId", requestId);
		List<VendorDo> list = (List<VendorDo>) query.setMaxResults(3).getResultList();
		if (!ServiceUtil.isEmpty(list)) {
			for (VendorDo entity : list) {
				dtolist.add(exportDto(entity));
			}
		}
		return dtolist;
	}
}
