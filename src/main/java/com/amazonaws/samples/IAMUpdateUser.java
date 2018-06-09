package com.amazonaws.samples;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.UpdateUserRequest;


public class IAMUpdateUser {

	public static void main(String[] args) {
		
		AWSCredentials credentials = null;

		credentials = new ProfileCredentialsProvider().getCredentials();
		
		System.out.println("Access Key ID is "+ credentials.getAWSAccessKeyId());
		System.out.println("Secret Access Key ID is " + credentials.getAWSSecretKey());
		
		AmazonIdentityManagement iam = AmazonIdentityManagementClientBuilder.standard()
				.withCredentials(new  AWSStaticCredentialsProvider(credentials))
				.withRegion("us-east-2")
				.build();
		
		UpdateUserRequest editUser = new UpdateUserRequest();
		editUser.withUserName("Saketh").withNewUserName("SakethReddy");
		
		iam.updateUser(editUser);

	}

}
