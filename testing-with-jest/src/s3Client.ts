import S3 from 'aws-sdk/clients/s3';
import {PromiseResult} from "aws-sdk/lib/request";
import {AWSError} from "aws-sdk";

export class S3Client {

  s3: S3;

  constructor() {
    this.s3 = new S3();
  }

  getFileUrl = (): Promise<PromiseResult<S3.Types.GetObjectOutput, AWSError>> => {
    return this.s3
      .getObject({Bucket: 'image-folder-java-example', Key: 'thumbnails/100x100-upload6.png'})
      .promise();
  }

}
