package de.rieckpil.learning;

import cloud.localstack.LocalstackTestRunner;
import cloud.localstack.TestUtils;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(LocalstackTestRunner.class)
public class SampleS3Access {

    @Test
    public void testLocalS3API() {
        AmazonS3 s3 = TestUtils.getClientS3();
        List<Bucket> buckets = s3.listBuckets();

        for (Bucket bucket : buckets) {
            System.out.println(bucket.getName());
        }
    }
}
