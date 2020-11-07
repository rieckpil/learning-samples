package com.myorg;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.RemovalPolicy;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.s3.Bucket;
import software.amazon.awscdk.services.s3.BucketAccessControl;

public class HelloCdkStack extends Stack {
  public HelloCdkStack(final Construct scope, final String id) {
    this(scope, id, null);
  }

  public HelloCdkStack(final Construct scope, final String id, final StackProps props) {
    super(scope, id, props);

    Bucket.Builder.create(this, "MyFirstBucket")
      .bucketName("my-first-cdk-java-s3-bucket")
      .removalPolicy(RemovalPolicy.DESTROY)
      .accessControl(BucketAccessControl.PRIVATE)
      .versioned(true).build();
  }
}
