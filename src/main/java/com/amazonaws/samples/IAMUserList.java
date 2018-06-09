package com.amazonaws.samples;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.ListUsersRequest;
import com.amazonaws.services.identitymanagement.model.ListUsersResult;
import com.amazonaws.services.identitymanagement.model.User;



public class IAMUserList {

	public static void main(String[] args) {
		
		AWSCredentials credentials = null;

		credentials = new ProfileCredentialsProvider().getCredentials();
		
		System.out.println("Access Key ID is "+ credentials.getAWSAccessKeyId());
		System.out.println("Secret Access Key ID is " + credentials.getAWSSecretKey());
		
		AmazonIdentityManagement iam = AmazonIdentityManagementClientBuilder.standard()
				.withCredentials(new  AWSStaticCredentialsProvider(credentials))
				.withRegion("us-east-2")
				.build();
		
		boolean done = false;
		
		ListUsersRequest request = new ListUsersRequest();
		
		
		while(!done){
		ListUsersResult response = iam.listUsers(request);
		
		
		for(User user : response.getUsers()){
			System.out.println("Retrieved User name is " + user.getUserName());
			
		}
		
		if(!response.getIsTruncated()){
			done = true;
		}
		
		}
		
	}

}
