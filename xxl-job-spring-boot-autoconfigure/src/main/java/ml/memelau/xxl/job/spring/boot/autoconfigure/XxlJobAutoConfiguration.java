package ml.memelau.xxl.job.spring.boot.autoconfigure;

import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * XxlJobAutoConfiguration
 *
 * @author meme
 * @since 2019-03-04 18:05
 */
@Configuration
@ConditionalOnClass(XxlJobExecutor.class)
@EnableConfigurationProperties(XxlJobProperties.class)
public class XxlJobAutoConfiguration {

    @ConditionalOnMissingBean
    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobExecutor xxlJobExecutor(XxlJobProperties properties){
        XxlJobExecutor xxlJobExecutor = new XxlJobSpringExecutor();
        xxlJobExecutor.setAppName(properties.getExecutor().getAppName());
        xxlJobExecutor.setPort(properties.getExecutor().getPort());
        xxlJobExecutor.setIp(properties.getExecutor().getIp());
        xxlJobExecutor.setAdminAddresses(String.join(",", properties.getAdmin().getAddresses()));
        xxlJobExecutor.setAccessToken(properties.getAccessToken());
        xxlJobExecutor.setLogPath(properties.getExecutor().getLogPath());
        xxlJobExecutor.setLogRetentionDays(properties.getExecutor().getLogRetentionDays());
        return xxlJobExecutor;
    }


}