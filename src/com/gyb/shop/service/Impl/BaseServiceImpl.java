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
 * 提供baseservice接口的实现类，用于实现方法；
 * @author disentice
 *
 */
@Service
public class BaseServiceImpl extends ServiceDelegateDAO implements BaseService {

	//获取Category字节码文件
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
			throw new Exception();//故意抛出异常
		} catch (Exception e) {//捕获异常
			StackTraceElement stes[] = e.getStackTrace();//获取所执行方法的名字
			String serviceImplClassName = stes[1].getMethodName();//获取方法名字，由于子类会默认调用父类的构造方法，及为第二个方法名字；
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
				clazz = Class.forName(pojoFullName);//拼接好之后再赋值；
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
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);//使用hibernate离线查询对象
		dc.addOrder(Order.desc("id"));//增加查询条件，按照id递减查询
		return findByCriteria(dc);//返回查询数据
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
		List<Long> l = find(hql);//查询条数返回集合；
		if (l.isEmpty()) {
			return 0;
		}
		Long result = l.get(0);//获取第一条数据；即为数据条数；
		return result.intValue();
	}

	@Override
	public List listByParent(Object parent) {
		String parentName = parent.getClass().getSimpleName();//获得对象的全路径名
		String parentNameWithFirstLetterLower = StringUtils.uncapitalize(parentName);//将第一个字母转换成小写；
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);//创建离线查询对象
		dc.add(Restrictions.eq(parentNameWithFirstLetterLower, parent));//根据父类进行查询；
		dc.addOrder(Order.desc("id"));
		return findByCriteria(dc);
	}

	@Override
	public List list(Page page, Object parent) {
		String parentName = parent.getClass().getSimpleName();//获得对象的全路径名
		String parentNameWithFirstLetterLower = StringUtils.uncapitalize(parentName);//将第一个字母转换成小写；
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);//创建离线查询对象
		dc.add(Restrictions.eq(parentNameWithFirstLetterLower, parent));//根据父类进行查询；
		dc.addOrder(Order.desc("id"));
		return findByCriteria(dc,page.getStart(),page.getLast());//增加查询区间；
	}

	@Override
	public int total(Object parentObject) {
		String parentName = parentObject.getClass().getSimpleName();//获得对象的全路径名
		String parentNameWithFirstLetterLower = StringUtils.uncapitalize(parentName);//将第一个字母转换成小写；
		String sqlFormat = "select count(*) from %s bean where bean.%s = ?";//%s为字符串类型，占位符；
		String hql = String.format(sqlFormat, clazz.getName(),parentNameWithFirstLetterLower);//格式化字符串并返回一个字符串
		List<Long> l = this.find(hql, parentObject);
		if (l.isEmpty()) {//判空处理；
			return 0;
		}
		Long result = l.get(0);//获取第一条数据
		return result.intValue();//返回共计条数；
	}


	@Override
	public List list(Object... pairParms) {//参数成对出现；创建map集合一一对应；
		HashMap<String,Object> m = new HashMap<>();
        for (int i = 0; i < pairParms.length; i=i+2)
            m.put(pairParms[i].toString(), pairParms[i+1]);
 
        DetachedCriteria dc = DetachedCriteria.forClass(clazz);
 
        Set<String> ks = m.keySet();//获取map集合的键；
        for (String key : ks) {//判断，如果有则给条件进行查询；
            if(null==m.get(key))
                dc.add(Restrictions.isNull(key));
            else
                dc.add(Restrictions.eq(key, m.get(key)));
        }
        dc.addOrder(Order.desc("id"));
        return this.findByCriteria(dc);
	}

}
