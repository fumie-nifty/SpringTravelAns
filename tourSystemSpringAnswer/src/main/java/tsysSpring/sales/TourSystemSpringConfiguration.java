/**
 * JsysSpringConfiguration.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package tsysSpring.sales;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//import jp.co.flm.common.aspect.LoggingAspect;

/**
 * コンフィグレーションクラス
 * @author FLM
 * @version 1.0.0 2022/03/20
 */
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class TourSystemSpringConfiguration {

	/**
	 * ResourceBundleMessageSourceのBean定義
	 * @return {@code ResourceBundleMessageSource}
	 */
	@Bean
	ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource
			= new ResourceBundleMessageSource();
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setBasenames("tsysSpring-messages");
		return messageSource;
	}

	/**
	 * LoggingAspectクラスのBean定義
	 * @return {@code LoggingAspect}
	 */
	/*
	@Bean
	LoggingAspect loggingAspect() {
		return new LoggingAspect();
	}
	*/
}
