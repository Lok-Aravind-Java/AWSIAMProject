package com.amazonaws.samples;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.CreateUserRequest;


public class IAMCreateUser {

	public static void main(String[] args) {
	
		AWSCredentials credentials = null;

		credentials = new ProfileCredentialsProvider().getCredentials();
		
		System.out.println("Access Key ID is "+ credentials.getAWSAccessKeyId());
		System.out.println("Secret Access Key ID is " + credentials.getAWSSecretKey());
		
		AmazonIdentityManagement iam = AmazonIdentityManagementClientBuilder.standard()
				.withCredentials(new  AWSStaticCredentialsProvider(credentials))
				.withRegion("us-east-2")
				.build();
		
		CreateUserRequest addUser = new CreateUserRequest();
         addUser.withUserName("Saketh");
		
           
           
           try{
        	   
       	   iam.createUser(addUser);
           }catch(Exception e){
        	 
        	   e.printStackTrace();
           }
           
	}

}
