package com.jeebase.system.webchat.service.imp;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.jeebase.system.webchat.service.DeptInfoService;

@Service
@Lazy(false)
public class DeptInfoServiceImp implements DeptInfoService{

	private static final Logger logger = LoggerFactory.getLogger(DeptInfoServiceImp.class);

	@Value("${hostp.dept.code}")
	private String code;

	@Value("${hostp.id}")
	private Long id;

	//0 0 1 * * ? */60 * * * * ?
	@PostConstruct
	@Scheduled(cron="0 0 1 * * ?")
	public void getDeptInfo() {
		// TODO Auto-generated method stub
		System.out.println("定时任务开始");
		System.out.println("定时任务结束");
	}

	@Override
	public Object getDeptInfoServic() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDeptInfosServic() {
		// TODO Auto-generated method stub
		return null;
	}

}
