package com.jeebase.system.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Collections;

/**
 * @ClassName: TransactionalConfig
 * @Description: 声明式事物配置
 * @author fyy
 * @date 
 */
//@Aspect
@Configuration
public class TransactionalConfig {

    private static final String CUSTOMIZE_TRANSACTION_INTERCEPTOR_NAME = "customizeTransactionInterceptor";
    
    private static final String AOP_POINTCUT_EXPRESSION = "execution (* com.jeebase.system.*.service.*.*(..))";

    /**
     * 默认只对 "*Service" , "*ServiceImpl" Bean 进行事务处理,"*"表示模糊匹配, 比如 :
     * userService,orderServiceImpl
     */
    private static final String[] DEFAULT_TRANSACTION_BEAN_NAMES = { "*Service", "*ServiceImpl"};

    /**
     * 可传播事务配置
     */
    private static final String[] DEFAULT_REQUIRED_METHOD_RULE_TRANSACTION_ATTRIBUTES = { "add*", "save*", "insert*",
            "delete*", "update*", "edit*", "batch*", "create*", "remove*", "lock*", "record*", "touch*", "apply*",
            "modify*", "execute*", "merge*", "put*", "sync*", "copy*", "change*", "adjust*", "start*", "init*",
            "upload*", "batch*", "has*", "publish*", "import*", "export*", "disable*", "cancel*", "submit*", "send*",
            "process*", "complete*", "hide*", "top*", "reset*", "bind*", "handle*", "reSubmit*", "close*", "sign*" };

    /**
     * 默认的只读事务
     */
    private static final String[] DEFAULT_READ_ONLY_METHOD_RULE_TRANSACTION_ATTRIBUTES = { "get*", "count*", "find*",
            "query*", "select*", "list*", "search*", "is*", "verify*", "check*", "read*" };

    /**
     * 自定义事务 BeanName 拦截
     */
    private String[] customizeTransactionBeanNames = {};

    /**
     * 自定义方法名的事务属性相关联,可以使用通配符(*)字符关联相同的事务属性的设置方法; 只读事务
     */
    private String[] customizeReadOnlyMethodRuleTransactionAttributes = {};

    /**
     * 自定义方法名的事务属性相关联,可以使用通配符(*)字符关联相同的事务属性的设置方法; 传播事务(默认的)
     * {@link org.springframework.transaction.annotation.Propagation#REQUIRED}
     */
    private String[] customizeRequiredMethodRuleTransactionAttributes = {};

    /**
     * 配置事务拦截器
     *
     * @param transactionManager
     *            : 事务管理器
     */
    @Bean(CUSTOMIZE_TRANSACTION_INTERCEPTOR_NAME)
    public TransactionInterceptor customizeTransactionInterceptor(PlatformTransactionManager transactionManager) {
        NameMatchTransactionAttributeSource transactionAttributeSource = new NameMatchTransactionAttributeSource();
        RuleBasedTransactionAttribute readOnly = this.readOnlyTransactionRule();
        RuleBasedTransactionAttribute required = this.requiredTransactionRule();
        // 默认的只读事务配置
        for (String methodName : DEFAULT_READ_ONLY_METHOD_RULE_TRANSACTION_ATTRIBUTES) {
            transactionAttributeSource.addTransactionalMethod(methodName, readOnly);
        }
        // 默认的传播事务配置
        for (String methodName : DEFAULT_REQUIRED_METHOD_RULE_TRANSACTION_ATTRIBUTES) {
            transactionAttributeSource.addTransactionalMethod(methodName, required);
        }
        // 定制的只读事务配置
        for (String methodName : customizeReadOnlyMethodRuleTransactionAttributes) {
            transactionAttributeSource.addTransactionalMethod(methodName, readOnly);
        }
        // 定制的传播事务配置
        for (String methodName : customizeRequiredMethodRuleTransactionAttributes) {
            transactionAttributeSource.addTransactionalMethod(methodName, required);
        }
        return new TransactionInterceptor(transactionManager, transactionAttributeSource);
    }

    /**
     * 配置事务拦截
     * <p>
     * {@link #customizeTransactionInterceptor(PlatformTransactionManager)}
     */
    @Bean
    public BeanNameAutoProxyCreator customizeTransactionBeanNameAutoProxyCreator() {
        BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
        // 设置定制的事务拦截器
        beanNameAutoProxyCreator.setInterceptorNames(CUSTOMIZE_TRANSACTION_INTERCEPTOR_NAME);
        // 默认
        for (String defaultTransactionBeanNameSuffix : DEFAULT_TRANSACTION_BEAN_NAMES) {
            beanNameAutoProxyCreator.setBeanNames(defaultTransactionBeanNameSuffix);
        }
        // 定制
        for (String customizeTransactionBeanName : customizeTransactionBeanNames) {
            beanNameAutoProxyCreator.setBeanNames(customizeTransactionBeanName);
        }
        beanNameAutoProxyCreator.setProxyTargetClass(true);
        return beanNameAutoProxyCreator;
    }

    /**
     * 支持当前事务;如果不存在创建一个新的
     * {@link org.springframework.transaction.annotation.Propagation#REQUIRED}
     */
    private RuleBasedTransactionAttribute requiredTransactionRule() {
        RuleBasedTransactionAttribute required = new RuleBasedTransactionAttribute();
        required.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(RuntimeException.class)));
        required.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        required.setTimeout(TransactionDefinition.TIMEOUT_DEFAULT);
        return required;
    }

    /**
     * 只读事务
     * {@link org.springframework.transaction.annotation.Propagation#NOT_SUPPORTED}
     */
    private RuleBasedTransactionAttribute readOnlyTransactionRule() {
        RuleBasedTransactionAttribute readOnly = new RuleBasedTransactionAttribute();
        readOnly.setReadOnly(true);
        readOnly.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
        return readOnly;
    }
    
//    @Bean
//    public Advisor txAdviceAdvisor(PlatformTransactionManager transactionManager) {
//        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
//        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
//        return new DefaultPointcutAdvisor(pointcut, customizeTransactionInterceptor(transactionManager));
//        //return new DefaultPointcutAdvisor(pointcut, txAdvice);
//    }
}
