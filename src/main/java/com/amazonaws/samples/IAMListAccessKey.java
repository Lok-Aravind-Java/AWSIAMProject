package com.amazonaws.samples;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.AccessKeyMetadata;
import com.amazonaws.services.identitymanagement.model.CreateAccountAliasRequest;
import com.amazonaws.services.identitymanagement.model.CreateAccountAliasResult;
import com.amazonaws.services.identitymanagement.model.GetAccessKeyLastUsedRequest;
import com.amazonaws.services.identitymanagement.model.GetAccessKeyLastUsedResult;
import com.amazonaws.services.identitymanagement.model.ListAccessKeysRequest;
import com.amazonaws.services.identitymanagement.model.ListAccessKeysResult;
import com.amazonaws.services.identitymanagement.model.ListAccountAliasesRequest;
import com.amazonaws.services.identitymanagement.model.ListAccountAliasesResult;
import com.amazonaws.services.identitymanagement.model.UpdateAccessKeyRequest;
import com.amazonaws.services.identitymanagement.model.UpdateAccessKeyResult;

public class IAMListAccessKey {

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
		
		ListAccessKeysRequest request = new ListAccessKeysRequest();
		
		while(!done){
		ListAccessKeysResult  result =  iam.listAccessKeys(request);
		
		result.getAccessKeyMetadata();

		for(AccessKeyMetadata metadata : result.getAccessKeyMetadata()){
			
			System.out.printf("Retrieved Access Key %s", metadata.getAccessKeyId()+"\n");
			System.out.printf("Retrieved Access Key %s", metadata.getCreateDate()+"\n");
			
  	GetAccessKeyLastUsedRequest request1 = new GetAccessKeyLastUsedRequest().withAccessKeyId(metadata.getAccessKeyId());
  	GetAccessKeyLastUsedResult request2 = iam.getAccessKeyLastUsed(request1);
  	
  	System.out.println("Access key was last used at: " + request2.getAccessKeyLastUsed().getLastUsedDate());
			
		}
		
		
		request.setMarker(result.getMarker());
		
		if(!result.getIsTruncated()){
			done = true;
			
		}
		
		}
		
		
		System.out.println("*******************Update Access Key ********************************");
		
		
			UpdateAccessKeyRequest updateAccessKey = new UpdateAccessKeyRequest()
			.withAccessKeyId("AKIAI2BZVOZDCZTV46EA")
			.withUserName("IamTest1")
			.withStatus("Inactive");
	
			UpdateAccessKeyResult response = iam.updateAccessKey(updateAccessKey);
		
			
			System.out.println("*******************CreateAccount Alias Request ********************************");	
			
			
			CreateAccountAliasRequest aliasReq = new CreateAccountAliasRequest().withAccountAlias("hdfccompname");
			
			CreateAccountAliasResult responce1 = iam.createAccountAlias(aliasReq);
	
			ListAccountAliasesResult response1 = iam.listAccountAliases();
			
			
			
			for(String alias: response1.getAccountAliases()){
				
				System.out.printf("Retrieved account alias %s ", alias);
				
				
			}
		
		
	}

	
	
}
