package com.kafka.sample;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.I0Itec.zkclient.ZkClient;

import kafka.utils.ZkUtils;

/**
 * Deletes a Kafka Topic using Zookeeper Client.
 * @author ninad
 *
 */
public class DeleteTopic {

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		Properties prop = ConfigUtils.getConfiguration("admin-config");
		ZkClient zkClient = ZkUtils.createZkClient(prop.getProperty("bootstrap.server"), 100, 10000);
		zkClient.deleteRecursive(ZkUtils.getTopicPath("test2"));
		
	}
}
