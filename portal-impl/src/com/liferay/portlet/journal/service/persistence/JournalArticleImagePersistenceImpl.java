/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portlet.journal.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ImagePersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.journal.NoSuchArticleImageException;
import com.liferay.portlet.journal.model.JournalArticleImage;
import com.liferay.portlet.journal.model.impl.JournalArticleImageImpl;
import com.liferay.portlet.journal.model.impl.JournalArticleImageModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the journal article image service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JournalArticleImagePersistence
 * @see JournalArticleImageUtil
 * @generated
 */
public class JournalArticleImagePersistenceImpl extends BasePersistenceImpl<JournalArticleImage>
	implements JournalArticleImagePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link JournalArticleImageUtil} to access the journal article image persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = JournalArticleImageImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_GROUPID = new FinderPath(JournalArticleImageModelImpl.ENTITY_CACHE_ENABLED,
			JournalArticleImageModelImpl.FINDER_CACHE_ENABLED,
			JournalArticleImageImpl.class, FINDER_CLASS_NAME_LIST,
			"findByGroupId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(JournalArticleImageModelImpl.ENTITY_CACHE_ENABLED,
			JournalArticleImageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByGroupId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_TEMPIMAGE = new FinderPath(JournalArticleImageModelImpl.ENTITY_CACHE_ENABLED,
			JournalArticleImageModelImpl.FINDER_CACHE_ENABLED,
			JournalArticleImageImpl.class, FINDER_CLASS_NAME_LIST,
			"findByTempImage",
			new String[] {
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_TEMPIMAGE = new FinderPath(JournalArticleImageModelImpl.ENTITY_CACHE_ENABLED,
			JournalArticleImageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByTempImage",
			new String[] { Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_G_A_V = new FinderPath(JournalArticleImageModelImpl.ENTITY_CACHE_ENABLED,
			JournalArticleImageModelImpl.FINDER_CACHE_ENABLED,
			JournalArticleImageImpl.class, FINDER_CLASS_NAME_LIST,
			"findByG_A_V",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Double.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_G_A_V = new FinderPath(JournalArticleImageModelImpl.ENTITY_CACHE_ENABLED,
			JournalArticleImageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByG_A_V",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Double.class.getName()
			});
	public static final FinderPath FINDER_PATH_FETCH_BY_G_A_V_E_E_L = new FinderPath(JournalArticleImageModelImpl.ENTITY_CACHE_ENABLED,
			JournalArticleImageModelImpl.FINDER_CACHE_ENABLED,
			JournalArticleImageImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByG_A_V_E_E_L",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Double.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName()
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_G_A_V_E_E_L = new FinderPath(JournalArticleImageModelImpl.ENTITY_CACHE_ENABLED,
			JournalArticleImageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByG_A_V_E_E_L",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Double.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(JournalArticleImageModelImpl.ENTITY_CACHE_ENABLED,
			JournalArticleImageModelImpl.FINDER_CACHE_ENABLED,
			JournalArticleImageImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(JournalArticleImageModelImpl.ENTITY_CACHE_ENABLED,
			JournalArticleImageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the journal article image in the entity cache if it is enabled.
	 *
	 * @param journalArticleImage the journal article image
	 */
	public void cacheResult(JournalArticleImage journalArticleImage) {
		EntityCacheUtil.putResult(JournalArticleImageModelImpl.ENTITY_CACHE_ENABLED,
			JournalArticleImageImpl.class, journalArticleImage.getPrimaryKey(),
			journalArticleImage);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_A_V_E_E_L,
			new Object[] {
				Long.valueOf(journalArticleImage.getGroupId()),
				
			journalArticleImage.getArticleId(),
				Double.valueOf(journalArticleImage.getVersion()),
				
			journalArticleImage.getElInstanceId(),
				
			journalArticleImage.getElName(),
				
			journalArticleImage.getLanguageId()
			}, journalArticleImage);

		journalArticleImage.resetOriginalValues();
	}

	/**
	 * Caches the journal article images in the entity cache if it is enabled.
	 *
	 * @param journalArticleImages the journal article images
	 */
	public void cacheResult(List<JournalArticleImage> journalArticleImages) {
		for (JournalArticleImage journalArticleImage : journalArticleImages) {
			if (EntityCacheUtil.getResult(
						JournalArticleImageModelImpl.ENTITY_CACHE_ENABLED,
						JournalArticleImageImpl.class,
						journalArticleImage.getPrimaryKey(), this) == null) {
				cacheResult(journalArticleImage);
			}
		}
	}

	/**
	 * Clears the cache for all journal article images.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(JournalArticleImageImpl.class.getName());
		}

		EntityCacheUtil.clearCache(JournalArticleImageImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the journal article image.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(JournalArticleImage journalArticleImage) {
		EntityCacheUtil.removeResult(JournalArticleImageModelImpl.ENTITY_CACHE_ENABLED,
			JournalArticleImageImpl.class, journalArticleImage.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_A_V_E_E_L,
			new Object[] {
				Long.valueOf(journalArticleImage.getGroupId()),
				
			journalArticleImage.getArticleId(),
				Double.valueOf(journalArticleImage.getVersion()),
				
			journalArticleImage.getElInstanceId(),
				
			journalArticleImage.getElName(),
				
			journalArticleImage.getLanguageId()
			});
	}

	/**
	 * Creates a new journal article image with the primary key. Does not add the journal article image to the database.
	 *
	 * @param articleImageId the primary key for the new journal article image
	 * @return the new journal article image
	 */
	public JournalArticleImage create(long articleImageId) {
		JournalArticleImage journalArticleImage = new JournalArticleImageImpl();

		journalArticleImage.setNew(true);
		journalArticleImage.setPrimaryKey(articleImageId);

		return journalArticleImage;
	}

	/**
	 * Removes the journal article image with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the journal article image
	 * @return the journal article image that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a journal article image with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public JournalArticleImage remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the journal article image with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param articleImageId the primary key of the journal article image
	 * @return the journal article image that was removed
	 * @throws com.liferay.portlet.journal.NoSuchArticleImageException if a journal article image with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JournalArticleImage remove(long articleImageId)
		throws NoSuchArticleImageException, SystemException {
		Session session = null;

		try {
			session = openSession();

			JournalArticleImage journalArticleImage = (JournalArticleImage)session.get(JournalArticleImageImpl.class,
					Long.valueOf(articleImageId));

			if (journalArticleImage == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						articleImageId);
				}

				throw new NoSuchArticleImageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					articleImageId);
			}

			return journalArticleImagePersistence.remove(journalArticleImage);
		}
		catch (NoSuchArticleImageException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Removes the journal article image from the database. Also notifies the appropriate model listeners.
	 *
	 * @param journalArticleImage the journal article image
	 * @return the journal article image that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public JournalArticleImage remove(JournalArticleImage journalArticleImage)
		throws SystemException {
		return super.remove(journalArticleImage);
	}

	@Override
	protected JournalArticleImage removeImpl(
		JournalArticleImage journalArticleImage) throws SystemException {
		journalArticleImage = toUnwrappedModel(journalArticleImage);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, journalArticleImage);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		JournalArticleImageModelImpl journalArticleImageModelImpl = (JournalArticleImageModelImpl)journalArticleImage;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_A_V_E_E_L,
			new Object[] {
				Long.valueOf(journalArticleImageModelImpl.getGroupId()),
				
			journalArticleImageModelImpl.getArticleId(),
				Double.valueOf(journalArticleImageModelImpl.getVersion()),
				
			journalArticleImageModelImpl.getElInstanceId(),
				
			journalArticleImageModelImpl.getElName(),
				
			journalArticleImageModelImpl.getLanguageId()
			});

		EntityCacheUtil.removeResult(JournalArticleImageModelImpl.ENTITY_CACHE_ENABLED,
			JournalArticleImageImpl.class, journalArticleImage.getPrimaryKey());

		return journalArticleImage;
	}

	@Override
	public JournalArticleImage updateImpl(
		com.liferay.portlet.journal.model.JournalArticleImage journalArticleImage,
		boolean merge) throws SystemException {
		journalArticleImage = toUnwrappedModel(journalArticleImage);

		boolean isNew = journalArticleImage.isNew();

		JournalArticleImageModelImpl journalArticleImageModelImpl = (JournalArticleImageModelImpl)journalArticleImage;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, journalArticleImage, merge);

			journalArticleImage.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(JournalArticleImageModelImpl.ENTITY_CACHE_ENABLED,
			JournalArticleImageImpl.class, journalArticleImage.getPrimaryKey(),
			journalArticleImage);

		if (!isNew &&
				((journalArticleImage.getGroupId() != journalArticleImageModelImpl.getOriginalGroupId()) ||
				!Validator.equals(journalArticleImage.getArticleId(),
					journalArticleImageModelImpl.getOriginalArticleId()) ||
				(journalArticleImage.getVersion() != journalArticleImageModelImpl.getOriginalVersion()) ||
				!Validator.equals(journalArticleImage.getElInstanceId(),
					journalArticleImageModelImpl.getOriginalElInstanceId()) ||
				!Validator.equals(journalArticleImage.getElName(),
					journalArticleImageModelImpl.getOriginalElName()) ||
				!Validator.equals(journalArticleImage.getLanguageId(),
					journalArticleImageModelImpl.getOriginalLanguageId()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_A_V_E_E_L,
				new Object[] {
					Long.valueOf(
						journalArticleImageModelImpl.getOriginalGroupId()),
					
				journalArticleImageModelImpl.getOriginalArticleId(),
					Double.valueOf(
						journalArticleImageModelImpl.getOriginalVersion()),
					
				journalArticleImageModelImpl.getOriginalElInstanceId(),
					
				journalArticleImageModelImpl.getOriginalElName(),
					
				journalArticleImageModelImpl.getOriginalLanguageId()
				});
		}

		if (isNew ||
				((journalArticleImage.getGroupId() != journalArticleImageModelImpl.getOriginalGroupId()) ||
				!Validator.equals(journalArticleImage.getArticleId(),
					journalArticleImageModelImpl.getOriginalArticleId()) ||
				(journalArticleImage.getVersion() != journalArticleImageModelImpl.getOriginalVersion()) ||
				!Validator.equals(journalArticleImage.getElInstanceId(),
					journalArticleImageModelImpl.getOriginalElInstanceId()) ||
				!Validator.equals(journalArticleImage.getElName(),
					journalArticleImageModelImpl.getOriginalElName()) ||
				!Validator.equals(journalArticleImage.getLanguageId(),
					journalArticleImageModelImpl.getOriginalLanguageId()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_A_V_E_E_L,
				new Object[] {
					Long.valueOf(journalArticleImage.getGroupId()),
					
				journalArticleImage.getArticleId(),
					Double.valueOf(journalArticleImage.getVersion()),
					
				journalArticleImage.getElInstanceId(),
					
				journalArticleImage.getElName(),
					
				journalArticleImage.getLanguageId()
				}, journalArticleImage);
		}

		return journalArticleImage;
	}

	protected JournalArticleImage toUnwrappedModel(
		JournalArticleImage journalArticleImage) {
		if (journalArticleImage instanceof JournalArticleImageImpl) {
			return journalArticleImage;
		}

		JournalArticleImageImpl journalArticleImageImpl = new JournalArticleImageImpl();

		journalArticleImageImpl.setNew(journalArticleImage.isNew());
		journalArticleImageImpl.setPrimaryKey(journalArticleImage.getPrimaryKey());

		journalArticleImageImpl.setArticleImageId(journalArticleImage.getArticleImageId());
		journalArticleImageImpl.setGroupId(journalArticleImage.getGroupId());
		journalArticleImageImpl.setArticleId(journalArticleImage.getArticleId());
		journalArticleImageImpl.setVersion(journalArticleImage.getVersion());
		journalArticleImageImpl.setElInstanceId(journalArticleImage.getElInstanceId());
		journalArticleImageImpl.setElName(journalArticleImage.getElName());
		journalArticleImageImpl.setLanguageId(journalArticleImage.getLanguageId());
		journalArticleImageImpl.setTempImage(journalArticleImage.isTempImage());

		return journalArticleImageImpl;
	}

	/**
	 * Returns the journal article image with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the journal article image
	 * @return the journal article image
	 * @throws com.liferay.portal.NoSuchModelException if a journal article image with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public JournalArticleImage findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the journal article image with the primary key or throws a {@link com.liferay.portlet.journal.NoSuchArticleImageException} if it could not be found.
	 *
	 * @param articleImageId the primary key of the journal article image
	 * @return the journal article image
	 * @throws com.liferay.portlet.journal.NoSuchArticleImageException if a journal article image with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JournalArticleImage findByPrimaryKey(long articleImageId)
		throws NoSuchArticleImageException, SystemException {
		JournalArticleImage journalArticleImage = fetchByPrimaryKey(articleImageId);

		if (journalArticleImage == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + articleImageId);
			}

			throw new NoSuchArticleImageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				articleImageId);
		}

		return journalArticleImage;
	}

	/**
	 * Returns the journal article image with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the journal article image
	 * @return the journal article image, or <code>null</code> if a journal article image with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public JournalArticleImage fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the journal article image with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param articleImageId the primary key of the journal article image
	 * @return the journal article image, or <code>null</code> if a journal article image with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JournalArticleImage fetchByPrimaryKey(long articleImageId)
		throws SystemException {
		JournalArticleImage journalArticleImage = (JournalArticleImage)EntityCacheUtil.getResult(JournalArticleImageModelImpl.ENTITY_CACHE_ENABLED,
				JournalArticleImageImpl.class, articleImageId, this);

		if (journalArticleImage == _nullJournalArticleImage) {
			return null;
		}

		if (journalArticleImage == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				journalArticleImage = (JournalArticleImage)session.get(JournalArticleImageImpl.class,
						Long.valueOf(articleImageId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (journalArticleImage != null) {
					cacheResult(journalArticleImage);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(JournalArticleImageModelImpl.ENTITY_CACHE_ENABLED,
						JournalArticleImageImpl.class, articleImageId,
						_nullJournalArticleImage);
				}

				closeSession(session);
			}
		}

		return journalArticleImage;
	}

	/**
	 * Returns all the journal article images where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching journal article images
	 * @throws SystemException if a system exception occurred
	 */
	public List<JournalArticleImage> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the journal article images where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of journal article images
	 * @param end the upper bound of the range of journal article images (not inclusive)
	 * @return the range of matching journal article images
	 * @throws SystemException if a system exception occurred
	 */
	public List<JournalArticleImage> findByGroupId(long groupId, int start,
		int end) throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the journal article images where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of journal article images
	 * @param end the upper bound of the range of journal article images (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching journal article images
	 * @throws SystemException if a system exception occurred
	 */
	public List<JournalArticleImage> findByGroupId(long groupId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<JournalArticleImage> list = (List<JournalArticleImage>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_GROUPID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_JOURNALARTICLEIMAGE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				list = (List<JournalArticleImage>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_GROUPID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_GROUPID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first journal article image in the ordered set where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching journal article image
	 * @throws com.liferay.portlet.journal.NoSuchArticleImageException if a matching journal article image could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JournalArticleImage findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchArticleImageException, SystemException {
		List<JournalArticleImage> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleImageException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last journal article image in the ordered set where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching journal article image
	 * @throws com.liferay.portlet.journal.NoSuchArticleImageException if a matching journal article image could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JournalArticleImage findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchArticleImageException, SystemException {
		int count = countByGroupId(groupId);

		List<JournalArticleImage> list = findByGroupId(groupId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleImageException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the journal article images before and after the current journal article image in the ordered set where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param articleImageId the primary key of the current journal article image
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next journal article image
	 * @throws com.liferay.portlet.journal.NoSuchArticleImageException if a journal article image with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JournalArticleImage[] findByGroupId_PrevAndNext(
		long articleImageId, long groupId, OrderByComparator orderByComparator)
		throws NoSuchArticleImageException, SystemException {
		JournalArticleImage journalArticleImage = findByPrimaryKey(articleImageId);

		Session session = null;

		try {
			session = openSession();

			JournalArticleImage[] array = new JournalArticleImageImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, journalArticleImage,
					groupId, orderByComparator, true);

			array[1] = journalArticleImage;

			array[2] = getByGroupId_PrevAndNext(session, journalArticleImage,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JournalArticleImage getByGroupId_PrevAndNext(Session session,
		JournalArticleImage journalArticleImage, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JOURNALARTICLEIMAGE_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(journalArticleImage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JournalArticleImage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the journal article images where tempImage = &#63;.
	 *
	 * @param tempImage the temp image
	 * @return the matching journal article images
	 * @throws SystemException if a system exception occurred
	 */
	public List<JournalArticleImage> findByTempImage(boolean tempImage)
		throws SystemException {
		return findByTempImage(tempImage, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the journal article images where tempImage = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tempImage the temp image
	 * @param start the lower bound of the range of journal article images
	 * @param end the upper bound of the range of journal article images (not inclusive)
	 * @return the range of matching journal article images
	 * @throws SystemException if a system exception occurred
	 */
	public List<JournalArticleImage> findByTempImage(boolean tempImage,
		int start, int end) throws SystemException {
		return findByTempImage(tempImage, start, end, null);
	}

	/**
	 * Returns an ordered range of all the journal article images where tempImage = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tempImage the temp image
	 * @param start the lower bound of the range of journal article images
	 * @param end the upper bound of the range of journal article images (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching journal article images
	 * @throws SystemException if a system exception occurred
	 */
	public List<JournalArticleImage> findByTempImage(boolean tempImage,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				tempImage,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<JournalArticleImage> list = (List<JournalArticleImage>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_TEMPIMAGE,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_JOURNALARTICLEIMAGE_WHERE);

			query.append(_FINDER_COLUMN_TEMPIMAGE_TEMPIMAGE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(tempImage);

				list = (List<JournalArticleImage>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_TEMPIMAGE,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_TEMPIMAGE,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first journal article image in the ordered set where tempImage = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tempImage the temp image
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching journal article image
	 * @throws com.liferay.portlet.journal.NoSuchArticleImageException if a matching journal article image could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JournalArticleImage findByTempImage_First(boolean tempImage,
		OrderByComparator orderByComparator)
		throws NoSuchArticleImageException, SystemException {
		List<JournalArticleImage> list = findByTempImage(tempImage, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("tempImage=");
			msg.append(tempImage);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleImageException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last journal article image in the ordered set where tempImage = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tempImage the temp image
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching journal article image
	 * @throws com.liferay.portlet.journal.NoSuchArticleImageException if a matching journal article image could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JournalArticleImage findByTempImage_Last(boolean tempImage,
		OrderByComparator orderByComparator)
		throws NoSuchArticleImageException, SystemException {
		int count = countByTempImage(tempImage);

		List<JournalArticleImage> list = findByTempImage(tempImage, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("tempImage=");
			msg.append(tempImage);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleImageException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the journal article images before and after the current journal article image in the ordered set where tempImage = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param articleImageId the primary key of the current journal article image
	 * @param tempImage the temp image
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next journal article image
	 * @throws com.liferay.portlet.journal.NoSuchArticleImageException if a journal article image with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JournalArticleImage[] findByTempImage_PrevAndNext(
		long articleImageId, boolean tempImage,
		OrderByComparator orderByComparator)
		throws NoSuchArticleImageException, SystemException {
		JournalArticleImage journalArticleImage = findByPrimaryKey(articleImageId);

		Session session = null;

		try {
			session = openSession();

			JournalArticleImage[] array = new JournalArticleImageImpl[3];

			array[0] = getByTempImage_PrevAndNext(session, journalArticleImage,
					tempImage, orderByComparator, true);

			array[1] = journalArticleImage;

			array[2] = getByTempImage_PrevAndNext(session, journalArticleImage,
					tempImage, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JournalArticleImage getByTempImage_PrevAndNext(Session session,
		JournalArticleImage journalArticleImage, boolean tempImage,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JOURNALARTICLEIMAGE_WHERE);

		query.append(_FINDER_COLUMN_TEMPIMAGE_TEMPIMAGE_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(tempImage);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(journalArticleImage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JournalArticleImage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the journal article images where groupId = &#63; and articleId = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param articleId the article ID
	 * @param version the version
	 * @return the matching journal article images
	 * @throws SystemException if a system exception occurred
	 */
	public List<JournalArticleImage> findByG_A_V(long groupId,
		String articleId, double version) throws SystemException {
		return findByG_A_V(groupId, articleId, version, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the journal article images where groupId = &#63; and articleId = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param articleId the article ID
	 * @param version the version
	 * @param start the lower bound of the range of journal article images
	 * @param end the upper bound of the range of journal article images (not inclusive)
	 * @return the range of matching journal article images
	 * @throws SystemException if a system exception occurred
	 */
	public List<JournalArticleImage> findByG_A_V(long groupId,
		String articleId, double version, int start, int end)
		throws SystemException {
		return findByG_A_V(groupId, articleId, version, start, end, null);
	}

	/**
	 * Returns an ordered range of all the journal article images where groupId = &#63; and articleId = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param articleId the article ID
	 * @param version the version
	 * @param start the lower bound of the range of journal article images
	 * @param end the upper bound of the range of journal article images (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching journal article images
	 * @throws SystemException if a system exception occurred
	 */
	public List<JournalArticleImage> findByG_A_V(long groupId,
		String articleId, double version, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, articleId, version,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<JournalArticleImage> list = (List<JournalArticleImage>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_G_A_V,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_JOURNALARTICLEIMAGE_WHERE);

			query.append(_FINDER_COLUMN_G_A_V_GROUPID_2);

			if (articleId == null) {
				query.append(_FINDER_COLUMN_G_A_V_ARTICLEID_1);
			}
			else {
				if (articleId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_A_V_ARTICLEID_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_A_V_ARTICLEID_2);
				}
			}

			query.append(_FINDER_COLUMN_G_A_V_VERSION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (articleId != null) {
					qPos.add(articleId);
				}

				qPos.add(version);

				list = (List<JournalArticleImage>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_G_A_V,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_G_A_V,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first journal article image in the ordered set where groupId = &#63; and articleId = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param articleId the article ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching journal article image
	 * @throws com.liferay.portlet.journal.NoSuchArticleImageException if a matching journal article image could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JournalArticleImage findByG_A_V_First(long groupId,
		String articleId, double version, OrderByComparator orderByComparator)
		throws NoSuchArticleImageException, SystemException {
		List<JournalArticleImage> list = findByG_A_V(groupId, articleId,
				version, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", articleId=");
			msg.append(articleId);

			msg.append(", version=");
			msg.append(version);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleImageException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last journal article image in the ordered set where groupId = &#63; and articleId = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param articleId the article ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching journal article image
	 * @throws com.liferay.portlet.journal.NoSuchArticleImageException if a matching journal article image could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JournalArticleImage findByG_A_V_Last(long groupId, String articleId,
		double version, OrderByComparator orderByComparator)
		throws NoSuchArticleImageException, SystemException {
		int count = countByG_A_V(groupId, articleId, version);

		List<JournalArticleImage> list = findByG_A_V(groupId, articleId,
				version, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", articleId=");
			msg.append(articleId);

			msg.append(", version=");
			msg.append(version);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleImageException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the journal article images before and after the current journal article image in the ordered set where groupId = &#63; and articleId = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param articleImageId the primary key of the current journal article image
	 * @param groupId the group ID
	 * @param articleId the article ID
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next journal article image
	 * @throws com.liferay.portlet.journal.NoSuchArticleImageException if a journal article image with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JournalArticleImage[] findByG_A_V_PrevAndNext(long articleImageId,
		long groupId, String articleId, double version,
		OrderByComparator orderByComparator)
		throws NoSuchArticleImageException, SystemException {
		JournalArticleImage journalArticleImage = findByPrimaryKey(articleImageId);

		Session session = null;

		try {
			session = openSession();

			JournalArticleImage[] array = new JournalArticleImageImpl[3];

			array[0] = getByG_A_V_PrevAndNext(session, journalArticleImage,
					groupId, articleId, version, orderByComparator, true);

			array[1] = journalArticleImage;

			array[2] = getByG_A_V_PrevAndNext(session, journalArticleImage,
					groupId, articleId, version, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JournalArticleImage getByG_A_V_PrevAndNext(Session session,
		JournalArticleImage journalArticleImage, long groupId,
		String articleId, double version, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JOURNALARTICLEIMAGE_WHERE);

		query.append(_FINDER_COLUMN_G_A_V_GROUPID_2);

		if (articleId == null) {
			query.append(_FINDER_COLUMN_G_A_V_ARTICLEID_1);
		}
		else {
			if (articleId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_A_V_ARTICLEID_3);
			}
			else {
				query.append(_FINDER_COLUMN_G_A_V_ARTICLEID_2);
			}
		}

		query.append(_FINDER_COLUMN_G_A_V_VERSION_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (articleId != null) {
			qPos.add(articleId);
		}

		qPos.add(version);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(journalArticleImage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JournalArticleImage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the journal article image where groupId = &#63; and articleId = &#63; and version = &#63; and elInstanceId = &#63; and elName = &#63; and languageId = &#63; or throws a {@link com.liferay.portlet.journal.NoSuchArticleImageException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param articleId the article ID
	 * @param version the version
	 * @param elInstanceId the el instance ID
	 * @param elName the el name
	 * @param languageId the language ID
	 * @return the matching journal article image
	 * @throws com.liferay.portlet.journal.NoSuchArticleImageException if a matching journal article image could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JournalArticleImage findByG_A_V_E_E_L(long groupId,
		String articleId, double version, String elInstanceId, String elName,
		String languageId) throws NoSuchArticleImageException, SystemException {
		JournalArticleImage journalArticleImage = fetchByG_A_V_E_E_L(groupId,
				articleId, version, elInstanceId, elName, languageId);

		if (journalArticleImage == null) {
			StringBundler msg = new StringBundler(14);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", articleId=");
			msg.append(articleId);

			msg.append(", version=");
			msg.append(version);

			msg.append(", elInstanceId=");
			msg.append(elInstanceId);

			msg.append(", elName=");
			msg.append(elName);

			msg.append(", languageId=");
			msg.append(languageId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchArticleImageException(msg.toString());
		}

		return journalArticleImage;
	}

	/**
	 * Returns the journal article image where groupId = &#63; and articleId = &#63; and version = &#63; and elInstanceId = &#63; and elName = &#63; and languageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param articleId the article ID
	 * @param version the version
	 * @param elInstanceId the el instance ID
	 * @param elName the el name
	 * @param languageId the language ID
	 * @return the matching journal article image, or <code>null</code> if a matching journal article image could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JournalArticleImage fetchByG_A_V_E_E_L(long groupId,
		String articleId, double version, String elInstanceId, String elName,
		String languageId) throws SystemException {
		return fetchByG_A_V_E_E_L(groupId, articleId, version, elInstanceId,
			elName, languageId, true);
	}

	/**
	 * Returns the journal article image where groupId = &#63; and articleId = &#63; and version = &#63; and elInstanceId = &#63; and elName = &#63; and languageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param articleId the article ID
	 * @param version the version
	 * @param elInstanceId the el instance ID
	 * @param elName the el name
	 * @param languageId the language ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching journal article image, or <code>null</code> if a matching journal article image could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JournalArticleImage fetchByG_A_V_E_E_L(long groupId,
		String articleId, double version, String elInstanceId, String elName,
		String languageId, boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, articleId, version, elInstanceId, elName, languageId
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_G_A_V_E_E_L,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_SELECT_JOURNALARTICLEIMAGE_WHERE);

			query.append(_FINDER_COLUMN_G_A_V_E_E_L_GROUPID_2);

			if (articleId == null) {
				query.append(_FINDER_COLUMN_G_A_V_E_E_L_ARTICLEID_1);
			}
			else {
				if (articleId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_A_V_E_E_L_ARTICLEID_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_A_V_E_E_L_ARTICLEID_2);
				}
			}

			query.append(_FINDER_COLUMN_G_A_V_E_E_L_VERSION_2);

			if (elInstanceId == null) {
				query.append(_FINDER_COLUMN_G_A_V_E_E_L_ELINSTANCEID_1);
			}
			else {
				if (elInstanceId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_A_V_E_E_L_ELINSTANCEID_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_A_V_E_E_L_ELINSTANCEID_2);
				}
			}

			if (elName == null) {
				query.append(_FINDER_COLUMN_G_A_V_E_E_L_ELNAME_1);
			}
			else {
				if (elName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_A_V_E_E_L_ELNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_A_V_E_E_L_ELNAME_2);
				}
			}

			if (languageId == null) {
				query.append(_FINDER_COLUMN_G_A_V_E_E_L_LANGUAGEID_1);
			}
			else {
				if (languageId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_A_V_E_E_L_LANGUAGEID_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_A_V_E_E_L_LANGUAGEID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (articleId != null) {
					qPos.add(articleId);
				}

				qPos.add(version);

				if (elInstanceId != null) {
					qPos.add(elInstanceId);
				}

				if (elName != null) {
					qPos.add(elName);
				}

				if (languageId != null) {
					qPos.add(languageId);
				}

				List<JournalArticleImage> list = q.list();

				result = list;

				JournalArticleImage journalArticleImage = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_A_V_E_E_L,
						finderArgs, list);
				}
				else {
					journalArticleImage = list.get(0);

					cacheResult(journalArticleImage);

					if ((journalArticleImage.getGroupId() != groupId) ||
							(journalArticleImage.getArticleId() == null) ||
							!journalArticleImage.getArticleId().equals(articleId) ||
							(journalArticleImage.getVersion() != version) ||
							(journalArticleImage.getElInstanceId() == null) ||
							!journalArticleImage.getElInstanceId()
													.equals(elInstanceId) ||
							(journalArticleImage.getElName() == null) ||
							!journalArticleImage.getElName().equals(elName) ||
							(journalArticleImage.getLanguageId() == null) ||
							!journalArticleImage.getLanguageId()
													.equals(languageId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_A_V_E_E_L,
							finderArgs, journalArticleImage);
					}
				}

				return journalArticleImage;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_A_V_E_E_L,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (JournalArticleImage)result;
			}
		}
	}

	/**
	 * Returns all the journal article images.
	 *
	 * @return the journal article images
	 * @throws SystemException if a system exception occurred
	 */
	public List<JournalArticleImage> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the journal article images.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of journal article images
	 * @param end the upper bound of the range of journal article images (not inclusive)
	 * @return the range of journal article images
	 * @throws SystemException if a system exception occurred
	 */
	public List<JournalArticleImage> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the journal article images.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of journal article images
	 * @param end the upper bound of the range of journal article images (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of journal article images
	 * @throws SystemException if a system exception occurred
	 */
	public List<JournalArticleImage> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<JournalArticleImage> list = (List<JournalArticleImage>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_JOURNALARTICLEIMAGE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_JOURNALARTICLEIMAGE;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<JournalArticleImage>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<JournalArticleImage>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_ALL,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs,
						list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the journal article images where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByGroupId(long groupId) throws SystemException {
		for (JournalArticleImage journalArticleImage : findByGroupId(groupId)) {
			journalArticleImagePersistence.remove(journalArticleImage);
		}
	}

	/**
	 * Removes all the journal article images where tempImage = &#63; from the database.
	 *
	 * @param tempImage the temp image
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTempImage(boolean tempImage) throws SystemException {
		for (JournalArticleImage journalArticleImage : findByTempImage(
				tempImage)) {
			journalArticleImagePersistence.remove(journalArticleImage);
		}
	}

	/**
	 * Removes all the journal article images where groupId = &#63; and articleId = &#63; and version = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param articleId the article ID
	 * @param version the version
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_A_V(long groupId, String articleId, double version)
		throws SystemException {
		for (JournalArticleImage journalArticleImage : findByG_A_V(groupId,
				articleId, version)) {
			journalArticleImagePersistence.remove(journalArticleImage);
		}
	}

	/**
	 * Removes the journal article image where groupId = &#63; and articleId = &#63; and version = &#63; and elInstanceId = &#63; and elName = &#63; and languageId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param articleId the article ID
	 * @param version the version
	 * @param elInstanceId the el instance ID
	 * @param elName the el name
	 * @param languageId the language ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_A_V_E_E_L(long groupId, String articleId,
		double version, String elInstanceId, String elName, String languageId)
		throws NoSuchArticleImageException, SystemException {
		JournalArticleImage journalArticleImage = findByG_A_V_E_E_L(groupId,
				articleId, version, elInstanceId, elName, languageId);

		journalArticleImagePersistence.remove(journalArticleImage);
	}

	/**
	 * Removes all the journal article images from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (JournalArticleImage journalArticleImage : findAll()) {
			journalArticleImagePersistence.remove(journalArticleImage);
		}
	}

	/**
	 * Returns the number of journal article images where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching journal article images
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGroupId(long groupId) throws SystemException {
		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GROUPID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JOURNALARTICLEIMAGE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GROUPID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of journal article images where tempImage = &#63;.
	 *
	 * @param tempImage the temp image
	 * @return the number of matching journal article images
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTempImage(boolean tempImage) throws SystemException {
		Object[] finderArgs = new Object[] { tempImage };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TEMPIMAGE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JOURNALARTICLEIMAGE_WHERE);

			query.append(_FINDER_COLUMN_TEMPIMAGE_TEMPIMAGE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(tempImage);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TEMPIMAGE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of journal article images where groupId = &#63; and articleId = &#63; and version = &#63;.
	 *
	 * @param groupId the group ID
	 * @param articleId the article ID
	 * @param version the version
	 * @return the number of matching journal article images
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_A_V(long groupId, String articleId, double version)
		throws SystemException {
		Object[] finderArgs = new Object[] { groupId, articleId, version };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_A_V,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_JOURNALARTICLEIMAGE_WHERE);

			query.append(_FINDER_COLUMN_G_A_V_GROUPID_2);

			if (articleId == null) {
				query.append(_FINDER_COLUMN_G_A_V_ARTICLEID_1);
			}
			else {
				if (articleId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_A_V_ARTICLEID_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_A_V_ARTICLEID_2);
				}
			}

			query.append(_FINDER_COLUMN_G_A_V_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (articleId != null) {
					qPos.add(articleId);
				}

				qPos.add(version);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_A_V,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of journal article images where groupId = &#63; and articleId = &#63; and version = &#63; and elInstanceId = &#63; and elName = &#63; and languageId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param articleId the article ID
	 * @param version the version
	 * @param elInstanceId the el instance ID
	 * @param elName the el name
	 * @param languageId the language ID
	 * @return the number of matching journal article images
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_A_V_E_E_L(long groupId, String articleId,
		double version, String elInstanceId, String elName, String languageId)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, articleId, version, elInstanceId, elName, languageId
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_A_V_E_E_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_COUNT_JOURNALARTICLEIMAGE_WHERE);

			query.append(_FINDER_COLUMN_G_A_V_E_E_L_GROUPID_2);

			if (articleId == null) {
				query.append(_FINDER_COLUMN_G_A_V_E_E_L_ARTICLEID_1);
			}
			else {
				if (articleId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_A_V_E_E_L_ARTICLEID_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_A_V_E_E_L_ARTICLEID_2);
				}
			}

			query.append(_FINDER_COLUMN_G_A_V_E_E_L_VERSION_2);

			if (elInstanceId == null) {
				query.append(_FINDER_COLUMN_G_A_V_E_E_L_ELINSTANCEID_1);
			}
			else {
				if (elInstanceId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_A_V_E_E_L_ELINSTANCEID_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_A_V_E_E_L_ELINSTANCEID_2);
				}
			}

			if (elName == null) {
				query.append(_FINDER_COLUMN_G_A_V_E_E_L_ELNAME_1);
			}
			else {
				if (elName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_A_V_E_E_L_ELNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_A_V_E_E_L_ELNAME_2);
				}
			}

			if (languageId == null) {
				query.append(_FINDER_COLUMN_G_A_V_E_E_L_LANGUAGEID_1);
			}
			else {
				if (languageId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_A_V_E_E_L_LANGUAGEID_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_A_V_E_E_L_LANGUAGEID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (articleId != null) {
					qPos.add(articleId);
				}

				qPos.add(version);

				if (elInstanceId != null) {
					qPos.add(elInstanceId);
				}

				if (elName != null) {
					qPos.add(elName);
				}

				if (languageId != null) {
					qPos.add(languageId);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_A_V_E_E_L,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of journal article images.
	 *
	 * @return the number of journal article images
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_JOURNALARTICLEIMAGE);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the journal article image persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.portal.util.PropsUtil.get(
						"value.object.listener.com.liferay.portlet.journal.model.JournalArticleImage")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<JournalArticleImage>> listenersList = new ArrayList<ModelListener<JournalArticleImage>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<JournalArticleImage>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(JournalArticleImageImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = JournalArticlePersistence.class)
	protected JournalArticlePersistence journalArticlePersistence;
	@BeanReference(type = JournalArticleImagePersistence.class)
	protected JournalArticleImagePersistence journalArticleImagePersistence;
	@BeanReference(type = JournalArticleResourcePersistence.class)
	protected JournalArticleResourcePersistence journalArticleResourcePersistence;
	@BeanReference(type = JournalContentSearchPersistence.class)
	protected JournalContentSearchPersistence journalContentSearchPersistence;
	@BeanReference(type = JournalFeedPersistence.class)
	protected JournalFeedPersistence journalFeedPersistence;
	@BeanReference(type = JournalStructurePersistence.class)
	protected JournalStructurePersistence journalStructurePersistence;
	@BeanReference(type = JournalTemplatePersistence.class)
	protected JournalTemplatePersistence journalTemplatePersistence;
	@BeanReference(type = ImagePersistence.class)
	protected ImagePersistence imagePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_JOURNALARTICLEIMAGE = "SELECT journalArticleImage FROM JournalArticleImage journalArticleImage";
	private static final String _SQL_SELECT_JOURNALARTICLEIMAGE_WHERE = "SELECT journalArticleImage FROM JournalArticleImage journalArticleImage WHERE ";
	private static final String _SQL_COUNT_JOURNALARTICLEIMAGE = "SELECT COUNT(journalArticleImage) FROM JournalArticleImage journalArticleImage";
	private static final String _SQL_COUNT_JOURNALARTICLEIMAGE_WHERE = "SELECT COUNT(journalArticleImage) FROM JournalArticleImage journalArticleImage WHERE ";
	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "journalArticleImage.groupId = ?";
	private static final String _FINDER_COLUMN_TEMPIMAGE_TEMPIMAGE_2 = "journalArticleImage.tempImage = ?";
	private static final String _FINDER_COLUMN_G_A_V_GROUPID_2 = "journalArticleImage.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_A_V_ARTICLEID_1 = "journalArticleImage.articleId IS NULL AND ";
	private static final String _FINDER_COLUMN_G_A_V_ARTICLEID_2 = "journalArticleImage.articleId = ? AND ";
	private static final String _FINDER_COLUMN_G_A_V_ARTICLEID_3 = "(journalArticleImage.articleId IS NULL OR journalArticleImage.articleId = ?) AND ";
	private static final String _FINDER_COLUMN_G_A_V_VERSION_2 = "journalArticleImage.version = ?";
	private static final String _FINDER_COLUMN_G_A_V_E_E_L_GROUPID_2 = "journalArticleImage.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_A_V_E_E_L_ARTICLEID_1 = "journalArticleImage.articleId IS NULL AND ";
	private static final String _FINDER_COLUMN_G_A_V_E_E_L_ARTICLEID_2 = "journalArticleImage.articleId = ? AND ";
	private static final String _FINDER_COLUMN_G_A_V_E_E_L_ARTICLEID_3 = "(journalArticleImage.articleId IS NULL OR journalArticleImage.articleId = ?) AND ";
	private static final String _FINDER_COLUMN_G_A_V_E_E_L_VERSION_2 = "journalArticleImage.version = ? AND ";
	private static final String _FINDER_COLUMN_G_A_V_E_E_L_ELINSTANCEID_1 = "journalArticleImage.elInstanceId IS NULL AND ";
	private static final String _FINDER_COLUMN_G_A_V_E_E_L_ELINSTANCEID_2 = "journalArticleImage.elInstanceId = ? AND ";
	private static final String _FINDER_COLUMN_G_A_V_E_E_L_ELINSTANCEID_3 = "(journalArticleImage.elInstanceId IS NULL OR journalArticleImage.elInstanceId = ?) AND ";
	private static final String _FINDER_COLUMN_G_A_V_E_E_L_ELNAME_1 = "journalArticleImage.elName IS NULL AND ";
	private static final String _FINDER_COLUMN_G_A_V_E_E_L_ELNAME_2 = "journalArticleImage.elName = ? AND ";
	private static final String _FINDER_COLUMN_G_A_V_E_E_L_ELNAME_3 = "(journalArticleImage.elName IS NULL OR journalArticleImage.elName = ?) AND ";
	private static final String _FINDER_COLUMN_G_A_V_E_E_L_LANGUAGEID_1 = "journalArticleImage.languageId IS NULL";
	private static final String _FINDER_COLUMN_G_A_V_E_E_L_LANGUAGEID_2 = "journalArticleImage.languageId = ?";
	private static final String _FINDER_COLUMN_G_A_V_E_E_L_LANGUAGEID_3 = "(journalArticleImage.languageId IS NULL OR journalArticleImage.languageId = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "journalArticleImage.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No JournalArticleImage exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No JournalArticleImage exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = com.liferay.portal.util.PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
	private static Log _log = LogFactoryUtil.getLog(JournalArticleImagePersistenceImpl.class);
	private static JournalArticleImage _nullJournalArticleImage = new JournalArticleImageImpl() {
			public Object clone() {
				return this;
			}

			public CacheModel<JournalArticleImage> toCacheModel() {
				return _nullJournalArticleImageCacheModel;
			}
		};

	private static CacheModel<JournalArticleImage> _nullJournalArticleImageCacheModel =
		new CacheModel<JournalArticleImage>() {
			public JournalArticleImage toEntityModel() {
				return _nullJournalArticleImage;
			}
		};
}