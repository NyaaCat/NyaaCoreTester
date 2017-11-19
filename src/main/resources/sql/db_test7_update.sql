UPDATE test_table3 SET data1=(SELECT key FROM test_table4 WHERE test_table4.data1 = test_table3.data2)
WHERE test_table3.data2 IN (SELECT data1 FROM test_table4);