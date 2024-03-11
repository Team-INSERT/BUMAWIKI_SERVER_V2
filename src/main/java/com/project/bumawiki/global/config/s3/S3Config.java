package com.project.bumawiki.global.config.s3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
class S3Config {

	@Value("${aws.s3.access-key}")
	private String accessKey;

	@Value("${aws.s3.secret-key}")
	private String secretKey;

	@Bean
	AmazonS3 amazonS3Client() {
		return AmazonS3ClientBuilder.standard()
			.withCredentials(
				new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey))
			)
			.withRegion(Regions.AP_NORTHEAST_2)
			.build();
	}
}
