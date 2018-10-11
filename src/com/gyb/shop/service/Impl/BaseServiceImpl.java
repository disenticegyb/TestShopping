package com.gyb.shop.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.gyb.shop.service.BaseService;
import com.gyb.shop.util.Page;
/**
 * �ṩbaseservice�ӿڵ�ʵ���࣬����ʵ�ַ�����
 * @author disentice
 *
 */
@Service
public class BaseServiceImpl extends ServiceDelegateDAO implements BaseService {

	//��ȡCategory�ֽ����ļ�
	protected Class clazz;

	public BaseServiceImpl() {
		 try{
	            throw new Exception(); 
	        }
	        catch(Exception e){
	            StackTraceElement stes[]= e.getStackTrace();
	            String serviceImpleClassName=   stes[1].getClassName();
	            try {
	                Class  serviceImplClazz= Class.forName(serviceImpleClassName);
//	                System.out.println(serviceImplClazz+"-----serviceImplClazz");
	                String serviceImpleClassSimpleName = serviceImplClazz.getSimpleName();
//	                System.out.println(serviceImpleClassSimpleName+"-------serviceImpleClassSimpleName");
	                String pojoSimpleName = serviceImpleClassSimpleName.replaceAll("ServiceImpl", "");
//	                System.out.println(pojoSimpleName+"----pojoSimpleName");
	                String pojoPackageName = serviceImplClazz.getPackage().getName().replaceAll(".service.Impl", ".pojo");
//	                System.out.println(pojoPackageName+"-----pojoPackageName");
	                String pojoFullName = pojoPackageName +"."+ pojoSimpleName;
//	                System.out.println("-----------------------1"+clazz);
	                System.out.println(pojoFullName+"-------pojoFullName");
	                clazz=Class.forName(pojoFullName);
//	                System.out.println("-----------------------2"+clazz);
	            } catch (ClassNotFoundException e1) {
	                e1.printStackTrace();
	            }
	        }      
		/*try {
			throw new Exception();//�����׳��쳣
		} catch (Exception e) {//�����쳣
			StackTraceElement stes[] = e.getStackTrace();//��ȡ��ִ�з���������
			String serviceImplClassName = stes[1].getMethodName();//��ȡ�������֣����������Ĭ�ϵ��ø���Ĺ��췽������Ϊ�ڶ����������֣�
			try {
				Class serviceImplClass = Class.forName(serviceImplClassName);
				System.out.println(serviceImplClass+"-----");
				String simpleName = serviceImplClass.getSimpleName();
				System.out.println(simpleName+"-------");
				String pojoSimpleName = simpleName.replaceAll("ServiceImpl", "");
				System.out.println(pojoSimpleName+"-------");
				String pojoPackageName = serviceImplClass.getPackage().getName().replaceAll(".service.impl",".pojo" );
				System.out.println("---------"+pojoPackageName);
				String pojoFullName = pojoPackageName+"."+pojoSimpleName;
				System.out.println(pojoFullName+"---------");
				clazz = Class.forName(pojoFullName);//ƴ�Ӻ�֮���ٸ�ֵ��
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}*/
	}

	
	@Override
	public Integer save(Object object) {
		return (Integer)super.save(object);
	}


	@Override
	public Object get(Class clazz, int id) {
		return super.get(clazz, id);
	}

	@Override
	public Object get(int id) {
		return get(clazz,id);
	}

	@Override
	public List list() {
		System.out.println(clazz+"3----------------------");
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);//ʹ��hibernate���߲�ѯ����
		dc.addOrder(Order.desc("id"));//���Ӳ�ѯ����������id�ݼ���ѯ
		return findByCriteria(dc);//���ز�ѯ����
	}

	@Override
	public List listByPage(Page page) {
		System.out.println(clazz+"**********");
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);
		dc.addOrder(Order.desc("id"));
		return findByCriteria(dc, page.getStart(), page.getCount());
	}

	@Override
	public int total() {
		String hql = "select count(*) from Category";
		List<Long> l = find(hql);//��ѯ�������ؼ��ϣ�
		if (l.isEmpty()) {
			return 0;
		}
		Long result = l.get(0);//��ȡ��һ�����ݣ���Ϊ����������
		return result.intValue();
	}

	@Override
	public List listByParent(Object parent) {
		String parentName = parent.getClass().getSimpleName();//��ö����ȫ·����
		String parentNameWithFirstLetterLower = StringUtils.uncapitalize(parentName);//����һ����ĸת����Сд��
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);//�������߲�ѯ����
		dc.add(Restrictions.eq(parentNameWithFirstLetterLower, parent));//���ݸ�����в�ѯ��
		dc.addOrder(Order.desc("id"));
		return findByCriteria(dc);
	}

	@Override
	public List list(Page page, Object parent) {
		String parentName = parent.getClass().getSimpleName();//��ö����ȫ·����
		String parentNameWithFirstLetterLower = StringUtils.uncapitalize(parentName);//����һ����ĸת����Сд��
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);//�������߲�ѯ����
		dc.add(Restrictions.eq(parentNameWithFirstLetterLower, parent));//���ݸ�����в�ѯ��
		dc.addOrder(Order.desc("id"));
		return findByCriteria(dc,page.getStart(),page.getLast());//���Ӳ�ѯ���䣻
	}

	@Override
	public int total(Object parentObject) {
		String parentName = parentObject.getClass().getSimpleName();//��ö����ȫ·����
		String parentNameWithFirstLetterLower = StringUtils.uncapitalize(parentName);//����һ����ĸת����Сд��
		String sqlFormat = "select count(*) from %s bean where bean.%s = ?";//%sΪ�ַ������ͣ�ռλ����
		String hql = String.format(sqlFormat, clazz.getName(),parentNameWithFirstLetterLower);//��ʽ���ַ���������һ���ַ���
		List<Long> l = this.find(hql, parentObject);
		if (l.isEmpty()) {//�пմ���
			return 0;
		}
		Long result = l.get(0);//��ȡ��һ������
		return result.intValue();//���ع���������
	}


	@Override
	public List list(Object... pairParms) {//�����ɶԳ��֣�����map����һһ��Ӧ��
		HashMap<String,Object> m = new HashMap<>();
        for (int i = 0; i < pairParms.length; i=i+2)
            m.put(pairParms[i].toString(), pairParms[i+1]);
 
        DetachedCriteria dc = DetachedCriteria.forClass(clazz);
 
        Set<String> ks = m.keySet();//��ȡmap���ϵļ���
        for (String key : ks) {//�жϣ����������������в�ѯ��
            if(null==m.get(key))
                dc.add(Restrictions.isNull(key));
            else
                dc.add(Restrictions.eq(key, m.get(key)));
        }
        dc.addOrder(Order.desc("id"));
        return this.findByCriteria(dc);
	}

}
