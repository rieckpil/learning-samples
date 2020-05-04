```
sls deploy -v
sls deploy function -f hello

sls invoke local -f hello -d "hello world" --context "duke"
sls invoke -f hello -l
sls invoke -f hello -d "{\"name\": \"duke\"}"
sls invoke -f hello --path data.json

sls create --template aws-python --path hello-world-python
sls logs -f hello -t
sls remove


sls logs -f hello --startTime 5h

sls metrics

sls info
sls info --verbose

sls deploy list
sls rollback -t 1588591898451

sls deploy list functions
serverless rollback function -f my-function --function-version 23

sls plugin list
sls plugin search --query sqs
sls plugin install --name serverless-offline-ssm
```