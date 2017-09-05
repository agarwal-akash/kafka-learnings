package com.kafka.sample;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * Simple Producer that sends message to the topic.
 * @author ninad
 *
 */
public class SimpleProducer {

	private Producer<Long, Employee> producer;
	long count = 0;

	public SimpleProducer() throws IOException {
		if (producer == null) {
			createProducer();
		}
	}

	public SimpleProducer(Producer<Long, Employee> producer) {
		this.producer = producer;
	}

	private void createProducer() throws IOException {
		Properties configuration = ConfigUtils.getConfiguration("producer-config");
		producer = new KafkaProducer<>(configuration);

	}

	public void sendMessage(String topic, Employee message) throws IOException {
		producer.send(new ProducerRecord<Long, Employee>(topic, count++, message));
		producer.flush();
		producer.close();
		System.out.println("Done");
	}

	public void close() {
		producer.close();
	}

	public static void main(String[] args) throws IOException {
		SimpleProducer producer = new SimpleProducer();
		Employee emp = new Employee();
		emp.setEmpId(101);
		emp.setFirstName("Ninad");
		emp.setLastName("Ingole");
		emp.setJoiningDate(new Date());
		producer.sendMessage("test", emp);
		producer.close();
	}

}
