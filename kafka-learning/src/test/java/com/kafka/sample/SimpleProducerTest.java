package com.kafka.sample;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;
import org.junit.Before;
import org.junit.Test;

public class SimpleProducerTest {

	private MockProducer<Long, Employee> producer;
	
	@Before
	public void setUp() {
		producer = new MockProducer<>(true, new LongSerializer(), new KryoSerialization());
	}
	
	@Test
	public void test() throws IOException {
		SimpleProducer testProducer = new SimpleProducer(this.producer);
		Employee emp = new Employee();
		emp.setEmpId(101);
		emp.setFirstName("ninad");
		emp.setLastName("ingole");
		emp.setJoiningDate(new Date());
		testProducer.sendMessage("test",emp);
		List<ProducerRecord<Long,Employee>> history = producer.history();
		assertThat(history, notNullValue());
		assertThat(history.size(), equalTo(1));
		System.out.println(history.toString());
		
	}

}
