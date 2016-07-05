package com.file.manager.quartz.job;

import com.file.manager.quartz.HeChaConstant;

public class HeChaJobFactory implements HeChaConstant {

	public static HeChaJobFactory heChaJobFactory;

	private HeChaJobFactory() {
	};

	private IHeChaJob iHeChaJob;

	public static HeChaJobFactory getInstance() {
		if (heChaJobFactory == null) {
			heChaJobFactory = new HeChaJobFactory();
			return heChaJobFactory;
		}
		return heChaJobFactory;

	}

	public IHeChaJob getHeChaJobImpl() {
		try {
			Class<?> obj = Class.forName(JOB_ClASS);
			return (IHeChaJob) obj.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return iHeChaJob;
	}
}
