package com.cyou.fz.common.base.jms;

import com.cyou.fz.common.base.spring.SpringContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;
import java.io.Serializable;

/**
 * 消息发送类.
 * 
 * <pre>
 * 修改日期         修改人     修改原因
 * 2013-03-24 崔诗杰    新建
 * </pre>
 */
public class JmsSender {

	/** 默认使用点对点模式模板 . */
	private JmsTemplate queueTemplate = null;

	/** 发布订阅模式模板 . */
	private JmsTemplate topicTemplate = null;

	/** 默认使用点对点模式. */
	private boolean topic = false;

	/** 持久化模式，默认持久化. */
	private boolean deliveryMode = true;

	/** 队列名称. */
	public static final String DESTINATIONNAME = "destinationName";
	/** 点对点队列. */
	public static final String QUEUETEMPLATE = "queueTemplate";
	/** 发布订阅队列. */
	public static final String TOPICTEMPLATE = "topicTemplate";

	/** 点对点队列前缀 */
	public static final String QUEUE_POSTFIX = "Queue";

	/** 发布订阅队列前缀 */
	public static final String TOPIC_POSTFIX = "Topic";

	/**
	 * 构造函数。
	 * 
	 * <pre>
	 * 修改日期         修改人     修改原因
	 * 2011-11-29       崔诗杰         新建
	 * </pre>
	 */
	public JmsSender() {
		queueTemplate = SpringContext.getBean(QUEUETEMPLATE);

	}
	
	/**
	 * 构造函数。
	 * 
	 * @param topic
	 *            是否发布订阅模式
	 * 
	 *            <pre>
	 * 修改日期         修改人     修改原因
	 * 2011-11-29       崔诗杰         新建
	 * </pre>
	 */
	public JmsSender(boolean topic) {
		this.topic = topic;
		if (topic) {
			topicTemplate = SpringContext.getBean(TOPICTEMPLATE);
		} else {
			queueTemplate = SpringContext.getBean(QUEUETEMPLATE);
		}

	}

	/**
	 * 构造函数。
	 * 
	 * @param topic
	 *            是否发布订阅模式
	 * @param deliveryMode
	 *            持久化模式
	 * 
	 *            <pre>
	 * 修改日期         修改人     修改原因
	 * 2011-11-29       崔诗杰         新建
	 * </pre>
	 */
	public JmsSender(boolean topic, boolean deliveryMode) {
		this(topic);
		this.deliveryMode = deliveryMode;

	}

	/**
	 * 发送对象消息.
	 * 
	 * @param destinationName
	 *            队列名称
	 * @param messageType
	 *            消息类型
	 * @param message
	 *            消息体对象
	 * 
	 *            <pre>
	 * 修改日期         修改人     修改原因
	 * 2011-11-29       崔诗杰         新建
	 * </pre>
	 */
	public void sendObjectMsg(final String destinationName,
			final String messageType, final Serializable message) {
		MessageCreator messageCreator = new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage objectMessage = session
						.createObjectMessage(message);
				objectMessage.setJMSType(messageType);
				objectMessage.setStringProperty(DESTINATIONNAME,
						destinationName);
				return objectMessage;
			}
		};
		this.sendMsg(destinationName, messageType, messageCreator);

	}

	/**
	 * 发送文本消息.
	 * 
	 * @param destinationName
	 *            队列名称
	 * @param messageType
	 *            消息类型
	 * @param message
	 *            消息体对象
	 * 
	 *            <pre>
	 * 修改日期         修改人     修改原因
	 * 2011-11-29       崔诗杰         新建
	 * </pre>
	 */
	public void sendTextMsg(final String destinationName,
			final String messageType, final String message) {

		MessageCreator messageCreator = new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage(message);
				textMessage.setJMSType(messageType);
				textMessage.setStringProperty(DESTINATIONNAME, destinationName);
				return textMessage;
			}
		};
		this.sendMsg(destinationName, messageType, messageCreator);

	}

	/**
	 * 发送消息.
	 * 
	 * @param destinationName
	 *            队列名称
	 * @param messageType
	 *            消息类型
	 * @param messageCreator
	 *            消息体创建对象
	 * 
	 *            <pre>
	 * 修改日期         修改人     修改原因
	 * 2011-11-29       崔诗杰         新建
	 * </pre>
	 */
	public void sendMsg(String destinationName, String messageType,
			MessageCreator messageCreator) {

		if (topic) {
			if (!deliveryMode) {
				topicTemplate.setExplicitQosEnabled(true);
				topicTemplate.setDeliveryPersistent(deliveryMode);
			}
			topicTemplate.send(destinationName, messageCreator);
		} else {
			if (!deliveryMode) {
				queueTemplate.setExplicitQosEnabled(true);
				queueTemplate.setDeliveryPersistent(deliveryMode);
			}
			queueTemplate.send(destinationName, messageCreator);
		}

	}

	public boolean isDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(boolean deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

}
