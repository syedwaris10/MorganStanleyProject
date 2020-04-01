/**
 * 
 */
package com.ms.project.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ms.project.constants.Status;
import com.ms.project.dto.PartsRequestDto;
import com.ms.project.entity.PartsDo;
import com.ms.project.entity.PartsDoPk;
import com.ms.project.util.ServiceUtil;

/**
 * @author Syed.Waris
 *
 */
@Repository("PartsDao")
public class PartsDao implements PartsDaoLocal {

	@Autowired
	private SessionFactory sessionFactory;

	public void savePartsData(PartsRequestDto parts) {
		Session session = getSession();
		session.saveOrUpdate(importDto(parts));
	}

	public Session getSession() {
		try {
			return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {

			// System.err.println("[BaseDao]" + e.getMessage());
			return sessionFactory.openSession();
		}
	}

	private PartsDo importDto(PartsRequestDto dto) {
		PartsDo entity = new PartsDo();
		PartsDoPk pk = new PartsDoPk();
		entity.setPartName(dto.getPartName());
		pk.setPartNumber(dto.getPartNumber());
		pk.setRequestId(dto.getRequestId());
		entity.setPk(pk);
		entity.setPartsQuantity(dto.getPartsQuantity());
		entity.setPriceUnit(dto.getPriceUnit());
		entity.setPricePerUnit(dto.getPricePerUnit());
		return entity;

	}

	private PartsRequestDto exportDto(PartsDo entity) {
		PartsRequestDto dto = new PartsRequestDto();
		dto.setPartName(entity.getPartName());
		dto.setPartNumber(entity.getPk().getPartNumber());
		dto.setRequestId(entity.getPk().getRequestId());
		dto.setPartsQuantity(entity.getPartsQuantity());
		dto.setPriceUnit(entity.getPriceUnit());
		dto.setPricePerUnit(entity.getPricePerUnit());
		return dto;

	}

	@Transactional
	public void updateStaus(PartsRequestDto dto) {
		Session session = getSession();
		PartsDoPk pk = getPartsKey(dto);
		PartsDo entity = (PartsDo) session.find(PartsDo.class, pk);
		if (!ServiceUtil.isEmpty(entity)) {
			entity.setStatus(dto.getStatus());
			session.merge(entity);
		}

	}

	private PartsDoPk getPartsKey(PartsRequestDto dto) {
		PartsDoPk pk = new PartsDoPk();
		pk.setPartNumber(dto.getPartNumber());
		pk.setRequestId(dto.getRequestId());
		return pk;
	}

	@SuppressWarnings("unchecked")
	public void submitItems(int requestId) {
		Session session = getSession();
		Query query = session
				.createQuery("SELECT d FROM PartsDo WHERE d.requestId = :requestId AND d.status = :status");
		query.setParameter("requestId", requestId);
		query.setParameter("status", Status.Approved.name());
		List<PartsDo> list = (List<PartsDo>) query.getResultList();
		if (!ServiceUtil.isEmpty(list)) {

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PartsRequestDto> getRequestUploadedData(String itemState, int requestId) {
		Session session = getSession();
		List<PartsRequestDto> dtolist = new ArrayList<>();
		Query query = session
				.createQuery("SELECT d FROM PartsDo d WHERE d.itemState = :itemState AND d.requestId = :requestId");
		query.setParameter("itemState", itemState);
		query.setParameter("requestId", requestId);
		List<PartsDo> list = (List<PartsDo>) query.getResultList();
		if (!ServiceUtil.isEmpty(list)) {
			{
				for (PartsDo entity : list) {
					if (!ServiceUtil.isEmpty(entity)) {
						dtolist.add(exportDto(entity));
					}
				}
			}

		}
		return dtolist;
	}

	@Override
	public void deleteRejectedItems(int requestId) {
		Session session = getSession();
		Query query = session
				.createQuery("delete from PartsDo d Where d.requestId = : requestId AND d.status =:status");
		query.setParameter("requestId", requestId);
		query.setParameter("status", Status.Rejected.name());
		query.executeUpdate();
	}

	@Override
	public void updateItemStateAndStatus(PartsRequestDto dto) {
		Session session = getSession();
		Query query = session.createQuery(
				"Update PartsDo d set d.itemState = :itemState , d.status = :status where d.requestId =:requestId");
		query.setParameter("itemState", dto.getItemState());
		query.setParameter("status", dto.getStatus());
		query.setParameter("requestId", dto.getRequestId());
		query.executeUpdate();

	}

}
