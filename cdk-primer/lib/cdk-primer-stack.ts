import { Vpc } from '@aws-cdk/aws-ec2';
import { Cluster, ContainerImage } from '@aws-cdk/aws-ecs';
import { ApplicationLoadBalancedFargateService } from '@aws-cdk/aws-ecs-patterns';
import * as cdk from '@aws-cdk/core';

export class CdkPrimerStack extends cdk.Stack {
  constructor(scope: cdk.Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    const vpc = new Vpc(this, "MyVpc", {maxAzs: 2});
    const cluster = new Cluster(this, "MyCluster", {vpc:vpc});

    new ApplicationLoadBalancedFargateService(this, "MyFargateService", {
      cluster: cluster,
      taskImageOptions: {image: ContainerImage.fromRegistry("amazon/amazon-ecs-sample")},
      publicLoadBalancer: true
    }); 
  }
}
