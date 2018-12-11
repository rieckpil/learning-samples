```bash
curl -X POST --data-binary "kryo.bin" -H "Content-Type: application/x-kryo" http://localhost:8080/resources/test/kryo
curl -X POST --data-binary "fst.bin" -H "Content-Type: application/x-fst" http://localhost:8080/resources/test/fst
```