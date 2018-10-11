package com.gyb.shop.service;

import java.util.List;

import com.gyb.shop.util.Page;

/**
 * ��CRUD���ܵĳ���
 * @author disentice
 *
 */
public interface BaseService {
	//���Ӷ���
	public Integer save(Object object);
	//���¶���
	public void update(Object object);
	//ɾ������
	public void delete(Object object);
	//ͨ��������id��ȡ
	public Object get(Class clazz,int id);
	//ͨ��idֱ�ӻ�ȡ
	public Object get(int id);
	//��ѯ���ط��ؼ���
	public List list();
	//��ҳ��ѯ���ؼ���
	public List listByPage(Page page);
	//��ȡ��ҳ��
	public int total();
	
	//���ݷ�������ѯ���е����ԣ�
	public List listByParent(Object o);
	 //���ݸ����ҳ��ѯ����Ķ��󡪡�ҳ����ʾ
	public List list(Page page,Object parent);
	 //���ݸ����ѯ���Ե�����
	public int total(Object parentObject);
	//���ӿɱ�����Ĳ�ѯ������
	public List list(Object ...pairParms);
}
